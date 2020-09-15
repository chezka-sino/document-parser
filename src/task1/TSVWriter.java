package task1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class TSVWriter {
	
	private static final String TAB = "\t";
	
	public static void indexWriter(Path outputFile, ArrayList<String[]> fileMap) 
			throws IOException {
		
		// TODO indexwriter
		
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, Charset.forName("UTF-8"))) {
			
			
//			Iterator<String> itr = categories.iterator();
//			
//			while (itr.hasNext()) {
//				String current = itr.next();
//				System.out.println(current);
//			}
			for (String [] documentInfo:fileMap) {
				writer.write(String.join(TAB, documentInfo));
				writer.write("\n");
			}
			
		}
		
	}

}
