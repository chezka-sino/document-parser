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
		
//		int i = 0;
//		while (i< 600) {
//			Path inputFile = Paths.get(documents.get(i));
//			openFile(inputFile, indexer);
//			i++;
//			
//		}
		
	}
	
	public static void openFile(Path inputFile, Indexer indexer) {
		
		String information[] = new String[5];
		Arrays.fill(information, "N/A");
		
		information[0] = inputFile.getParent().getFileName().toString();
//		information[0] = inputFile.toString();
		
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
					if (!line.startsWith("In article") & !line.startsWith(">") & !line.isEmpty()) {
						body += LineCleaner.cleanLine(line) + " ";
					}

				}
				
			}
			
			if (!body.trim().isEmpty()) {
				information[4] = body.trim().replaceAll("\\s+", " ");
			}

		} 
		
		catch (IOException e) {
			System.err.println("Unable to read file: " + inputFile.toString());
		}
		
		indexer.add(information);
		
	}


}
