package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		String input = args[0];
		String output = args[1];
		
		Path inputPath = Paths.get(input);
		Path outputFile = Paths.get("results/" + output);
		
		DocumentStats stats = new DocumentStats();
		ArrayList<String> docStats = new ArrayList<>();
		
//		try (BufferedReader reader = Files.newBufferedReader(inputPath)){
//			
//			String line;
//			
//			while ((line = reader.readLine()) != null) {
//				String[] data = line.split("\t");
//				stats.add(data);
//			}
//			
//		} 
//		
//		catch (IOException e) {
//			System.err.println("Unable to read file: " + inputPath.toString());
//		}
		
		stats.readFile(inputPath);
		

		docStats.add(String.valueOf(stats.numDocuments()));
		

		docStats.add(String.valueOf(stats.aveBodyWordCount()));
		
		// TODO average # of documents per category
		stats.groupByCategory();
		docStats.add(String.valueOf(stats.aveNumDocsCategory()));

		
		stats.aveEachCategory();
		docStats.add(stats.getMaxCategory());

		docStats.add(stats.getMinCategory());

		TSVWriter.statsWriter(outputFile, docStats);

	}

}
