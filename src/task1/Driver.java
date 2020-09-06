package task1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		ArrayList<String> documents = new ArrayList<>();
		
		String path = args[0];
		String output = args[1];

		// TODO use invertedindexbuilder as a guide to opening each file
		
		Path dir = Paths.get(path);
		documents.addAll(DirectoryTraverser.traverse(dir));
		System.out.println(documents.size());

	}

}
