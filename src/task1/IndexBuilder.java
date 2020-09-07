package task1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IndexBuilder {
	
	public static void readArray(ArrayList<String> documents, Indexer indexer) {
		
		for (String doc : documents) {
			Path inputFile = Paths.get(doc);
			openFile(inputFile, indexer);
		}
		
	}
	
	public static void openFile(Path inputFile, Indexer indexer) {
		
		// TODO this is where I should process the file
		
	}


}
