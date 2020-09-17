package task1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Indexer {
	
	/** ArrayList of document information*/
	private final ArrayList<String []> docInfo;
	
	public Indexer() {
		docInfo = new ArrayList<String[]>();
	}
	
	/**
	 * Adds the array of information to the docInfo ArrayList
	 *
	 * @param information[]
	 * 			Array to add
	 *
	 */
	public void add(String information[]) {
		docInfo.add(information);
	}

	/**
	 * Writes the contents of docInfo to a .tsv file
	 * 
	 * @param output
	 * 			File name of .tsv file
	 * 
	 */
	public void toTSV(String output) throws IOException {
		Path outputFile = Paths.get("results/" + output);
		TSVWriter.indexWriter(outputFile, docInfo);			
	}
}
