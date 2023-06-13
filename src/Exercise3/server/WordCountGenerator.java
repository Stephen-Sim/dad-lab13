package Exercise3.server;

public class WordCountGenerator {

	/**
	 * return the text length
	 * 
	 * @param text
	 * @return
	 */
    public int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
