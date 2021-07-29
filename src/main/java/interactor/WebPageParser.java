package interactor;

import org.jsoup.nodes.Document;
import repository.WebPageRepository;

import java.util.HashMap;

public class WebPageParser {

    private final WebPageRepository repository;

    public WebPageParser(WebPageRepository repository) {
        this.repository = repository;
    }

    public HashMap<String, Integer> pars(String url) throws Exception {
        Document doc = repository.getHtmlPage(url);
        String allWords = doc.body().text();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allWords.length(); i++) {
            if (Character.isLetter(allWords.charAt(i)) || allWords.charAt(i) == ' ')
                sb.append(allWords.charAt(i));
        }
        allWords = sb.toString();
        String[] wordsWithSpaces = allWords.toUpperCase().split(" ");
        HashMap<String, Integer> wordCount = new HashMap<>();
        int count;
        for (String anyWord : wordsWithSpaces) {
            count = 0;
            for (String arrayFromWordsWithSpace : wordsWithSpaces) {
                if (anyWord.equals(arrayFromWordsWithSpace)) {
                    count++;
                }
            }
            if (!anyWord.isBlank()) {
                wordCount.put(anyWord, count);
            }
        }
        return wordCount;
    }
}

