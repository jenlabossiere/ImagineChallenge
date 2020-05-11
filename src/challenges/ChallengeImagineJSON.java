package challenges;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ChallengeImagineJSON {
	
	public static void main(String[] args) {
		// initialize ArrayLists so that we can compare 
		ArrayList<String> IDList = new ArrayList<String>();
		ArrayList<String> datesCreated = new ArrayList<String>();
		ArrayList<String> risks = new ArrayList<String>();
		ArrayList<String> riskLevels = new ArrayList<String>();
		ArrayList<String> metaData = new ArrayList<String>();
		ArrayList<String> allUsers = new ArrayList<String>();
 		
		// File to load the JSON object
		File file = new File("resources/audit.json");
		Object obj = initialize(file);
		JSONObject fullRecords = (JSONObject) obj;
		JSONArray listOfRecords = (JSONArray)fullRecords.get("RECORDS");
		
		// The JSON array listOfRecords has individual objects. We will extract each one of them to get the individual data elements
		for (int i = 0; i < listOfRecords.size(); i++) {
			JSONObject oneRecord = (JSONObject)listOfRecords.get(i);
			
			// getting the info from one record
			String recordID = (String)oneRecord.get("id");
			String created = (String)oneRecord.get("created");
			String risk = (String)oneRecord.get("risk");
			String riskLevel = (String)oneRecord.get("risk_level");
			String meta = (String)oneRecord.get("meta");
			String users = (String)oneRecord.get("active");
			
			// appending this info to our arrayLists
			IDList.add(recordID);
			datesCreated.add(created);
			risks.add(risk);
			riskLevels.add(riskLevel);
			metaData.add(meta);
			allUsers.add(users);
		}
		
		
		// Initializing variables
		String recordWanted;
		
		/**
		 * Analyzing portion
		 */
		
		// IDs are unique, so there's nothing to compare the values with. Thus, we probably want to know the amount of IDs we have
		int amountOfRecords = IDStats(IDList);
		
		
		// Our datesCreated list shows the time and date when the record was created. For this, any information might be useful
		// but in this case, it might be useful to know when the first and last were made. 
		recordWanted = "first";
		String firstDate = createdStats(datesCreated,recordWanted);
		recordWanted = "last";
		String lastDate = createdStats(datesCreated,recordWanted);
		
		
		// Most of the records have no risks at all, but for the ones that do, we want to see the unique values of what might 
		// be considered a risk. 
		ArrayList<String> allRisks = riskStats(risks);
		
		// We might also want to see at any given time how many of our users are active 
		int activeUsers = activeUserStats(allUsers);
		
		
		
		// Finally, we can print out some information
		System.out.println("Altogether, we have " + amountOfRecords + " records. The first ID was created on " + firstDate + " and the last one was made " + 
		lastDate);
		System.out.println("Some of the risks involved with these records are " + allRisks.toString().replace("[", "").replace("]", ""));
		System.out.println("The amount of active users in our records is " + activeUsers);
	}	
	
	/**
	 * 
	 * @param allUsers is an ArrayList carrying a "t" or "f" indicating whether the user is currently active
	 * @return the amount of users that have "t" (thus, they are active)
	 */
	
	private static int activeUserStats(ArrayList<String> allUsers) {
		int count = 0;
		String bool; 
		for (int i = 0; i < allUsers.size(); i++) {
			bool = allUsers.get(i);
			if (bool.equals("t")) 
				count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @param risks is an ArrayList with each associated risk (including empty strings) that each record has
	 * @return the unique ArrayList of all risks 
	 */

	private static ArrayList<String> riskStats(ArrayList<String> risks) {
		String risk;
		ArrayList<String> allRisks = new ArrayList<String>();
		for (int i = 0; i < risks.size(); i++) {
			risk = risks.get(i);
			if (!risk.trim().isEmpty())
				allRisks.add(risk);
			
		}
		
		// We can use a hashset to get the unique values in our ArrayList. 
		HashSet<String> uniqueRisks = new HashSet(allRisks);
        
		allRisks.clear();
        for(String risky: uniqueRisks)
            allRisks.add(risky);
		return allRisks;
	}

	/**
	 * 
	 * @param datesCreated is an ArrayList with all associated dates when the record was created
	 * @param recordWanted (can be either first or last)
	 * @return a String of when that record was created
	 */
	
	private static String createdStats(ArrayList<String> datesCreated, String recordWanted) {
		if (recordWanted == "first") 
			return datesCreated.get(0);
	    else if (recordWanted == "last") 
			return datesCreated.get(datesCreated.size()-1);
	    else
		    return "Invalid value";
	}

	/**
	 * 
	 * @param IDList that contain every ID
	 * @return the amount of IDs initialized
	 */
	
	private static int IDStats(ArrayList<String> IDList) {
		return IDList.size();
	}

	/**
	 * 
     * @param file of JSON info
     * @return JSON object
     */
	public static Object initialize(File file) {
		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		try (FileReader reader = new FileReader("resources/audit.json")) {
			obj = jsonParser.parse(reader);
			return obj;
		} catch (Exception e) {
			System.out.println(e.toString());
			return obj;
		}
	}

	



}
