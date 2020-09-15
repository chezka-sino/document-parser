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
		documents.addAll(DirectoryTraverser.traverse(dir));
		
		IndexBuilder.readArray(documents, index);
		
		System.out.println("Writing to tsv...");
		index.toTSV(output);
		
	}

}
