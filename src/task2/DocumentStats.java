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
	
	private final ArrayList<String[]> data;
	private final HashMap<String, ArrayList<String>> byCategory;
	private final HashMap<String, Float> categoryAverage;
	
	public DocumentStats() {
		data = new ArrayList<String[]>();
		byCategory = new HashMap<>();
		categoryAverage = new HashMap<>();
	}
	
	public void readFile(Path inputFile) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(inputFile)){
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] information = line.split("\t");
				data.add(information);
			}
			
		} 
	}
	
//	public void add(String information[]) {
//		data.add(information);
//	}
	
	public int numDocuments() {
		return data.size();
	}
	
	public int bodyWordCount(String body) {
		return body.split(" ").length;
	}
	
	public float aveBodyWordCount() {
		int total = 0;
		
		for (int i = 0; i < data.size(); i++) {
			total+=bodyWordCount(data.get(i)[4]);
		}

		return (float) total/numDocuments();
	}
	
	
	public void groupByCategory() {
		
		for (String[] document:data) {
			if (!byCategory.containsKey(document[0])) {
				byCategory.put(document[0], new ArrayList<String>());
			}

			byCategory.get(document[0]).add(document[4]);
			
		}
		
	}
	
	public float aveNumDocsCategory() {
		int count = 0;
		
		for (ArrayList<String> categoryGroup:byCategory.values()) {
			count+=categoryGroup.size();
		}
		
		return (float) count/byCategory.size();
	}
	
	public Set<String> getCategories() {
		return byCategory.keySet();
	}
	
	public float aveCountCategory(String category) {
		
		int total = 0;
		
		for (String email:byCategory.get(category)) {
			total+=bodyWordCount(email);
		}
		
		return (float) total/byCategory.get(category).size();
	}
	
	public void aveEachCategory() {
		
		for (String cat:getCategories()) {
			categoryAverage.put(cat, aveCountCategory(cat));
		}
		
	}
	
	public String getMaxCategory() {
		
		Map.Entry<String, Float> max = null;
		
		for (Map.Entry<String, Float> entry:categoryAverage.entrySet()) {
			if (max == null || entry.getValue() > max.getValue()) {
				max = entry;
			}
		}
		
		return max.getKey();
	}
	
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
