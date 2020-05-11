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
		ArrayList<String> activeUsers = new ArrayList<String>();
 		
		File file = new File("resources/audit.json");
		Object obj = initialize(file);
		JSONObject fullRecords = (JSONObject) obj;
		JSONArray listOfRecords = (JSONArray)fullRecords.get("RECORDS");
		
		
		for (int i = 0; i < listOfRecords.size(); i++) {
			JSONObject oneRecord = (JSONObject)listOfRecords.get(i);
			
			// getting the info from one record
			String recordID = (String)oneRecord.get("id");
			String created = (String)oneRecord.get("created");
			String risk = (String)oneRecord.get("risk");
			String riskLevel = (String)oneRecord.get("risk_level");
			String meta = (String)oneRecord.get("meta");
			String activeUser = (String)oneRecord.get("active");
			
			// appending this info to our arrayLists
			IDList.add(recordID);
			datesCreated.add(created);
			risks.add(risk);
			riskLevels.add(riskLevel);
			metaData.add(meta);
			activeUsers.add(activeUser);
		}
		
		
		// initializing variables for looking at stats
		String when;
		
		// Let's look at some info from our JSON file
		int amountOfRecords = createdIDs(IDList);
		
		when = "first";
		String firstDate = createdStats(datesCreated,when);
		when = "last";
		String lastDate = createdStats(datesCreated,when);
		
		ArrayList<String> allRisks = riskInfo(risks);
		
		int totalActiveUsers = countActiveUsers(activeUsers);
		
		
		
		// Finally, we can print out some information
		System.out.println("Altogether, we have " + amountOfRecords + " records. The first ID was created on " + firstDate + " and the last one was made " + 
		lastDate);
		System.out.println("Some of the risks involved with these records are " + allRisks.toString().replace("[", "").replace("]", ""));
		System.out.println("The amount of active users in our records is " + totalActiveUsers);
	}	
	
	/**
	 * 
	 * @param activeUsers
	 * @return
	 */
	
	private static int countActiveUsers(ArrayList<String> activeUsers) {
		int count = 0;
		String bool; 
		for (int i = 0; i < activeUsers.size(); i++) {
			bool = activeUsers.get(i);
			if (bool.equals("t")) 
				count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @param risks
	 * @return
	 */

	private static ArrayList<String> riskInfo(ArrayList<String> risks) {
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
	 * @param datesCreated is an array of all the dates of when the ID was created
	 * @param when (can be either first or last)
	 * @return
	 */
	
	private static String createdStats(ArrayList<String> datesCreated, String when) {
		if (when == "first") 
			return datesCreated.get(0);
	    else if (when == "last") 
			return datesCreated.get(datesCreated.size()-1);
	    else
		    return "Invalid value";
	}

	/**
	 * 
	 * @param IDList that contain every ID
	 * @return the amount of IDs initialized
	 */
	
	private static int createdIDs(ArrayList<String> IDList) {
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
