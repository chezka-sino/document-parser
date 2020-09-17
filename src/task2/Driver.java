package task2;

import java.io.IOException;
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
		
		System.out.println("Reading input file...");
		stats.readFile(inputPath);
		System.out.println("DONE\n");
		
		System.out.println("Calculating stats...");
		// Total number of documents
		docStats.add(String.valueOf(stats.numDocuments()));
		
		// Average word count of the document "body" in the data set
		docStats.add(String.valueOf(stats.aveBodyWordCount()));
		
		// Average number of documents per category
		stats.groupByCategory();
		docStats.add(String.valueOf(stats.aveNumDocsCategory()));
		
		stats.aveEachCategory();
		
		// Category with maximum average "body" word count
		docStats.add(stats.getMaxCategory());
		
		// Category with minimum average "body" word count
		docStats.add(stats.getMinCategory());
		System.out.println("DONE\n");

		System.out.println("Writing to tsv...");
		TSVWriter.statsWriter(outputFile, docStats);
		System.out.println("See " + output + " for results\n");

	}

}
