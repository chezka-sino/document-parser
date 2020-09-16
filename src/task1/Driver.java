package task1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		String path = args[0];
		String output = args[1];
		
		ArrayList<String> documents = new ArrayList<>();
		Indexer index = new Indexer();
		
		Path dir = Paths.get(path);
		
		System.out.println("Getting document file paths...");
		documents.addAll(DirectoryTraverser.traverse(dir));
		System.out.println("DONE\n");
		
		System.out.println("Processing data...");
		IndexBuilder.readArray(documents, index);
		System.out.println("DONE\n");
		
		System.out.println("Writing to tsv...");
		index.toTSV(output);
		System.out.println("See " + output + " for results\n");
		
	}

}
