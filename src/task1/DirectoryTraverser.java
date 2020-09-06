package task1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTraverser {
	
	public static List<String> traverse(Path path) {
		
		ArrayList<String> documents = new ArrayList<>();
		traverse(path, documents);
		return documents;
	
	}
	
	public static void traverse(Path path, List<String> paths) {
		
		try (DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {

			for (Path file:listing) {
				if (Files.isDirectory(file)) {
					traverse(file, paths);
				}
				else {
					paths.add(file.normalize().toString());
				}
			}
			
		}
		
		catch (IOException e) {
			System.err.println("Unable to travers the directory : " + path);
		}
		
	}

}
