package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {

	public static void main(String[] args) {
		
		String input = args[0];
		String output = args[1];
		
		Path inputPath = Paths.get(input);
		
		DocumentStats stats = new DocumentStats();
		
		try (BufferedReader reader = Files.newBufferedReader(inputPath)){
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("\t");
				stats.add(data);
			}
			
		} 
		
		catch (IOException e) {
			System.err.println("Unable to read file: " + inputPath.toString());
		}
		
		// TODO number of documents
		System.out.println(stats.numDocuments());
		
		// TODO average word count of body
		System.out.println(stats.bodyWordCount(6));
		System.out.println(stats.aveBodyWordCount());
		// TODO average # of documents per category
		// TODO category with max ave body count
		// TODO category with min ave body count


	}

}
