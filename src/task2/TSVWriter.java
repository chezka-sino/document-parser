package task2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TSVWriter {
	
	private static final String TAB = "\t";
	
	/**
	 * Writes the ArrayList to a tab-delimited file.
	 * 
	 * @param outputFile
	 * 			Path of the output
	 * @param stats
	 * 			ArrayList of strings to be written to file
	 */
	public static void statsWriter(Path outputFile, ArrayList<String> stats) throws IOException {
		
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, 
				Charset.forName("UTF-8"))) {
			
			for (String s:stats) {
				writer.write(s + TAB);
			}
			
		}
	}

}
