		//Check if regular dims are the same
		if(!(Math.abs(regularDimLineOne-regularDimLineTwo) < UNIVERSAL_EPSILON_VAL)){
			return null;
		}
		
		//Check if either are within the other's bounds
		if(!((leastDimOfLineOne < leastDimOfLineTwo && leastDimOfLineTwo < greatestDimOfLineOne) ||
		   (leastDimOfLineOne < greatestDimOfLineTwo && greatestDimOfLineTwo < greatestDimOfLineOne) ||
		   (leastDimOfLineTwo < leastDimOfLineOne && leastDimOfLineOne < greatestDimOfLineTwo) ||
		   (leastDimOfLineTwo < greatestDimOfLineOne && greatestDimOfLineOne < greatestDimOfLineTwo))
		){
			return null;
		}
		
		//Only 2 possibilites here
		//Check if line one's points are both inside lineTwo
		if((leastDimOfLineTwo < leastDimOfLineOne && greatestDimOfLineOne < greatestDimOfLineTwo) &&
		   (leastDimOfLineTwo < greatestDimOfLineOne && greatestDimOfLineOne < greatestDimOfLineTwo)
		){
			float distOne = Box.getDistanceBetweenPoints(perfLineStartOne, perfLineStartTwo);
			float distTwo = Box.getDistanceBetweenPoints(perfLineEndOne, perfLineStartTwo);
			
			//Find the point of perfLineOne that is closest to perfLineStartOne
			//If the start point is farther away from perfLineTwo's start
			if(distOne > distTwo){
				return perfLineEndOne;
			}
			//If the end point is farther away from perfLineTwo's start
			else if(distTwo > distOne){
				return perfLineStartOne;
			}
			//If the distance is equal, then perfLine's points are right on top of each other
			else{
				return perfLineStartOne;
			}
		}
		//At least one point, if not two, are inside lineOne
		else{
			//Find which points of lineTwo are within line One's bounds
			Coordinates[] lineTwoPoints = {perfLineStartTwo, perfLineEndTwo};
			ArrayList<Coordinates> pointsBetweenLineOne = new ArrayList<Coordinates>(2);
			int pointsCount = 0;
			for(int i = 0; i < 2; i++){
				if(twoXWise){
					if(leastDimOfLineOne < lineTwoPoints[i].getY() && lineTwoPoints[i].getY() < greatestDimOfLineOne){
						pointsBetweenLineOne.add(lineTwoPoints[i]);
						pointsCount++;
					}
				else if(!twoXWise){
					if(leastDimOfLineOne < lineTwoPoints[i].getX() && lineTwoPoints[i].getX() < greatestDimOfLineOne){
						pointsBetweenLineOne.add(lineTwoPoints[i]);
						pointsCount++;
					}
				}
				else{
					//IDK
				}
			}
			
			if(pointsBetweenLineOne.size() == 1){
				return pointsBetweenLineOne.get(0);
			}
			//Both points of lineTwo are between lineOne
			else if(pointsBetweenLineOne.size() == 2){
				float distOne = Box.getDistanceBetweenPoints(perfLineStartOne, perfLineStartOne);
				float distTwo = Box.getDistanceBetweenPoints(perfLineTwoEnd, perfLineStartOne);
				
				//Find the point of perfLineTwo that is closest to perfLineStartOne
				//If startPoit of line two is farther away than endPoint
				if(distOne > distTwo){
					return perfLineEndTwo;
				}
				//If the end point is farther away from perfLineOne's start
				//If end point of lineTwo is farthere away than start point
				else if(distTwo > distOne){
					return perfLineStartTwo;
				}
				//If the distance is equal, then perfLine's points are right on top of each other
				else{
					return perfLineStartTwo;
				}
			}
			else{
				//IDK
			}
		}
	}
    }