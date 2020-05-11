package challenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChallengeImagine {
	public static void main(String[] args) {
    	// in our case, the sentence is only 8 words. If we didn't know, we could cap this at 50 and just delete the nulls in the result array
        String[] result = new String[8];
        String sampleString = "iwanttocommitsuicidefastandpainlessly";
        String attemptWord;
        boolean isAWord = true;
        
        // instantiating variables
        // again is to control the while loop 
        int again = 1;
        
        // to insert into the correct index of the result
        int count = 0;
        
        // length of the word found
    	int length = 0;
    	
    	while (again == 1) {
    		// let's first start at the end of the sentence and check until we find the first word
	    	for (int i = 0; i < sampleString.length(); i++) { 
	    		attemptWord = sampleString.substring(i,sampleString.length());
	    		isAWord = isWord(sampleString.substring(i,sampleString.length()));  
	    		
	    	
	    		if (isAWord == true) {
	    			// add to result
	    			result[count] = attemptWord;
	    			count++;
	    			length = attemptWord.length();
	    			
	    			// delete word from sample string
	    			sampleString = sampleString.substring(0,sampleString.length()-length);
	    			if (sampleString.length() == 0) {
	    				// if the sample string is empty, we don't check again
	    				again = 0;
	    			} else {
	    				again = 1;
	    			}
	    		}
	    	}
    	}
    	
    	// since we are checking the end of the sentence first, we want to reverse the array order
    	List<String> toReverse = Arrays.asList(result);
    	Collections.reverse(toReverse);
    	result = toReverse.toArray(new String[toReverse.size()]);
	}
	
	public static boolean isWord(String str) {
		if (str.equals("i")) {
			return true;
		} else if(str.equals("want")) {
			return true;
		} else if(str.equals("to")) {
			return true;
		} else if(str.equals("commit")) {
			return true;
		} else if(str.equals("suicide")) {
			return true;
		} else if(str.equals("fast")) {
			return true;
		} else if(str.equals("and")) {
			return true;
		} else if(str.equals("painlessly")) {
			return true;
		} else if(str.equals("pain")) {
			return true;
		} else if(str.equals("painless")) {
			return true;
		} else if(str.equals("less")) {
			return true;
		} else {
			return false;
		}
	}
}
