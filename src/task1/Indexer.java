package task1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Indexer {
	
	private final ArrayList<String []> docInfo;
	
	public Indexer() {
		docInfo = new ArrayList<String[]>();
	}
	
	public void add(String information[]) {
		docInfo.add(information);
	}

	public void toTSV(String output) throws IOException {
		Path outputFile = Paths.get("results/" + output);
		TSVWriter.indexWriter(outputFile, docInfo);			
	}
}
