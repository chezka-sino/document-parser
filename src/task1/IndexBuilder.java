package task1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
		String information[] = new String[5];
		Arrays.fill(information, "N/A");
		
		information[0] = inputFile.getParent().getFileName().toString();
		
		// TODO parse through file for information
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputFile.toString()),"utf-8"))) {
			
			String line;
			String body = "";
			
			while ((line = reader.readLine()) != null) {
				
				line.toLowerCase();
				
				//for the needed information
				if (line.contains(":")) {
					
					if (line.startsWith("From")) {
						information[1] = LineCleaner.getData(line);
					}
					else if (line.startsWith("Organization")) {
						information[2] = LineCleaner.getData(line);
					}
					else if (line.startsWith("Subject")) {
						information[3] = LineCleaner.getData(line);
					}
					
				}
				
				// lines for body
				else {
					// NOTE: using + since /n can't be used when writing tsv
					if (!line.startsWith("In article") & !line.startsWith(">")) {
						body += LineCleaner.cleanLine(line) + " ";
					}
					 
				}
				
			}
			
			body = body.replaceAll("\\s+", " ");
			information[4] = body.trim();
			
		} 
		
		catch (IOException e) {
			System.err.println("Unable to read file: " + inputFile.toString());
		}
		
//		System.out.println(information[0]);
//		System.out.println(information[1]);
//		System.out.println(information[2]);
//		System.out.println(information[3]);
//		System.out.println(information[4]);
//		System.out.println();
//		
		indexer.add(information);
		
	}


}
