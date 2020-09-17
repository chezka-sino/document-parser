package task1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTraverser {
	
	/**
	 * Calls the recursive traverse method to get the files
	 * 
	 * @param path
	 *            the directory to be traversed
	 * @return the list of files
	 */	
	public static List<String> traverse(Path path) {
		
		ArrayList<String> documents = new ArrayList<>();
		traverse(path, documents);
		return documents;
	
	}
	
	/**
	 * This method recursively traverses through the directory and it adds all
	 * the .txt files into an list
	 * 
	 * @param path
	 *            the directory to be traversed
	 * @param paths
	 *            list of files found in the directory
	 * 
	 */
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
