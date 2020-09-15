package task1;

public class LineCleaner {
	
	/** Regular expression for removing special characters. */
	public static final String CLEAN_REGEX = "(?U)[^\\p{Alnum}\\p{Space}]+";
	
	public static String getData(String line) {
		
		return line.substring(line.indexOf(":") + 1).trim();
		
	}
	
	public static String cleanLine(String line) {
		
		String text = line;
		text = stripTags(text);
		text = text.replaceAll(CLEAN_REGEX, "").toLowerCase().trim();
		text = text.replaceAll("\\s+", " ");
		return text;
	}
	
	/**
	 * Removes all HTML tags, which is essentially anything between the "<" and
	 * ">" symbols. The tag will be replaced by the empty string.
	 *
	 * @param html
	 *            html code to parse
	 * @return text without any html tags
	 */
	public static String stripTags(String line) {
		return line.replaceAll("<[^>]*>", "");
	}
	

}
