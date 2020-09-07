package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class IndexBuilder {
	
	public static void readArray(ArrayList<String> documents, Indexer indexer) {
		
		for (String doc : documents) {
			Path inputFile = Paths.get(doc);
			openFile(inputFile, indexer);
		}
		
	}
	
	public static void openFile(Path inputFile, Indexer indexer) {
		
		String information[] = new String[4];
		Arrays.fill(information, null);
		
		String category = inputFile.getParent().getFileName().toString();
		
		// TODO parse through file for information
		// information[0]: from
		// information[1]: organization
		// information[2]: subject
		// information[3]: body
		
		try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
			
			String line;
			String body = "";
			
			while ((line = reader.readLine()) != null) {
				
				line.toLowerCase();
				
				//for the needed information
				if (line.contains(":")) {
					// TODO check if startswith info needed
					
					if (line.startsWith("From")) {
						information[0] = LineCleaner.getData(line);
					}
					
				}
				
				// lines for body
				else {
					// using + since /n can't be used when writing tsv
					body+=line + '+'; 
				}
				
			}
			
			System.out.println(information[0]);
			
		} 
		
		catch (IOException e) {
			System.err.println("Unable to read file: " + inputFile.toString());
		}
		
		
		indexer.add(category, information);
		
	}


}
