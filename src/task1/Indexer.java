package task1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Indexer {
	
	private final HashMap<String, String[]> map;
	
	public Indexer() {
		map = new HashMap<>();
	}
	
	public void add(String category, String information[]) {
		map.put(category, information);
	}

	public void toTSV(String output) {
		Path outputFile = Paths.get(output);
		TSVWriter.indexWriter(outputFile, map);
	}
}
