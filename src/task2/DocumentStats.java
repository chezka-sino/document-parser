package task2;

import java.util.ArrayList;

public class DocumentStats {
	
	private final ArrayList<String[]> data;
	
	public DocumentStats() {
		data = new ArrayList<String[]>();
	}
	
	public void add(String information[]) {
		data.add(information);
	}
	
	public int numDocuments() {
		return data.size();
	}
	
	public int bodyWordCount(int i) {
		return data.get(i)[4].split(" ").length;
	}
	
	public float aveBodyWordCount() {
		int total = 0;
		
		for (int i = 0; i < data.size(); i++) {
			total+=bodyWordCount(i);
		}
		
//		for (String[] test:data) {
//			System.out.println(test.length);
//		}
		System.out.println();
		
		System.out.println(total);
		System.out.println(numDocuments());
		
		return (float) total/numDocuments();
	}

}
