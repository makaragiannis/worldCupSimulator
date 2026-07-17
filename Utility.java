package worldCupSimulator;

import java.util.ArrayList;

public class Utility {
	
	int getGroupIntegerFromGroupChar(char GroupIndexChar) {
		
		return (int) (GroupIndexChar - 'A');
	}
	
	boolean checkValidThirdPlaceTeamSequence(ArrayList<Character> bestThirdPlaceGroupIndices) {
		
		if (bestThirdPlaceGroupIndices.get(0)  != 'E' &&
			bestThirdPlaceGroupIndices.get(0)  != 'I' &&
			bestThirdPlaceGroupIndices.get(1)  != 'E' &&
			bestThirdPlaceGroupIndices.get(1)  != 'I' &&
			bestThirdPlaceGroupIndices.get(2)  != 'D' &&
			bestThirdPlaceGroupIndices.get(2)  != 'G' &&
			bestThirdPlaceGroupIndices.get(3)  != 'D' &&
			bestThirdPlaceGroupIndices.get(3)  != 'G' &&
			bestThirdPlaceGroupIndices.get(4)  != 'A' &&
			bestThirdPlaceGroupIndices.get(4)  != 'L' &&
			bestThirdPlaceGroupIndices.get(5)  != 'A' &&
			bestThirdPlaceGroupIndices.get(5)  != 'L' &&
			bestThirdPlaceGroupIndices.get(6)  != 'B' &&
			bestThirdPlaceGroupIndices.get(6)  != 'K' &&
			bestThirdPlaceGroupIndices.get(7)  != 'B' &&
			bestThirdPlaceGroupIndices.get(7)  != 'K' ) {
			
			return true;
		}
		
		return false;
			
	}


}
