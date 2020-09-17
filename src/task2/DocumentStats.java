package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DocumentStats {
	
	/* ArrayList of data from file*/
	private final ArrayList<String[]> data;
	
	/* Stores the data grouped by category*/
	private final HashMap<String, ArrayList<String>> byCategory;
	
	/* Stores the average word count per category*/
	private final HashMap<String, Float> categoryAverage;
	
	public DocumentStats() {
		data = new ArrayList<String[]>();
		byCategory = new HashMap<>();
		categoryAverage = new HashMap<>();
	}
	
	/**
	 * Reads the file and stores in an ArrayList of String arrays
	 * 
	 * @param inputFile
	 * 			Path where the file is written
	 */
	public void readFile(Path inputFile) throws IOException {
		
		try (BufferedReader reader = Files.newBufferedReader(inputFile)){
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] information = line.split("\t");
				data.add(information);
			}
			
		} 
	}

	
	/**
	 * Returns the number of documents in the data set
	 * 
	 * @return number of documents 
	 */
	public int numDocuments() {
		return data.size();
	}
	
	/**
	 * Splits the string to words and returns the count of the array
	 * 
	 * @param body
	 * 				String to count
	 * @return number of words in body
	 */
	public int bodyWordCount(String body) {
		return body.split(" ").length;
	}
	
	/**
	 * Calculates the average body word count of the data set
	 * 
	 * @return average body word count
	 */
	public float aveBodyWordCount() {
		int total = 0;
		
		for (int i = 0; i < data.size(); i++) {
			total+=bodyWordCount(data.get(i)[4]);
		}

		return (float) total/numDocuments();
	}
	
	/**
	 * Groups the data set based on the categories (i.e., by the first element
	 * on each document array)
	 */
	public void groupByCategory() {
		
		for (String[] document:data) {
			if (!byCategory.containsKey(document[0])) {
				byCategory.put(document[0], new ArrayList<String>());
			}
			byCategory.get(document[0]).add(document[4]);
		}
		
	}
	
	/**
	 * Calculates the average number of documents per category.
	 * 
	 * @return average number of documents per category
	 **/
	public float aveNumDocsCategory() {
		int count = 0;
		
		for (ArrayList<String> categoryGroup:byCategory.values()) {
			count+=categoryGroup.size();
		}
		
		return (float) count/byCategory.size();
	}
	
	/**
	 * Returns the unique categories in the data set
	 * 
	 * @return set of the categories
	 */
	public Set<String> getCategories() {
		return byCategory.keySet();
	}
	
	/**
	 * Calculates the average word count per document for a given category. 
	 * Returns 0 if category is not on data set.
	 * 
	 * @param category
	 * 			Category to be calculated
	 * @return average word count per document in category
	 */
	public float aveCountCategory(String category) {
		
		int total = 0;
		
		if (!byCategory.containsKey(category)) {
			System.out.println("No documents for category: " + category);
			return 0;
		}
		
		for (String email:byCategory.get(category)) {
			total+=bodyWordCount(email);
		}
		
		return (float) total/byCategory.get(category).size();
		
	}
	
	/**
	 * Calculates the average for each category in the data set and stores in
	 * categoryAverage HashMap
	 */
	public void aveEachCategory() {
		
		for (String cat:getCategories()) {
			categoryAverage.put(cat, aveCountCategory(cat));
		}
		
	}
	
	/**
	 * Returns the category with the highest average number of words per document
	 * 
	 * @return String of category name
	 **/
	public String getMaxCategory() {
		
		Map.Entry<String, Float> max = null;
		
		for (Map.Entry<String, Float> entry:categoryAverage.entrySet()) {
			if (max == null || entry.getValue() > max.getValue()) {
				max = entry;
			}
		}
		
		return max.getKey();
	}
	
	/**
	 * Returns the category with the highest average number of words per document
	 * 
	 * @return String of category name
	 **/
	public String getMinCategory() {
		
		Map.Entry<String, Float> max = null;
		
		for (Map.Entry<String, Float> entry:categoryAverage.entrySet()) {
			if (max == null || entry.getValue() < max.getValue()) {
				max = entry;
			}
		}
		
		return max.getKey();
	}

}
