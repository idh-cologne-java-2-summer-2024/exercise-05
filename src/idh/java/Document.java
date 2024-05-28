import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String content;

    private Document(String content) {
        this.content = content;
    }

    public static Document loadFromFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return new Document(contentBuilder.toString());
    }

    @Override
    public Iterator<String> iterator() {
        return new DocumentIterator(content);
    }

    private static class DocumentIterator implements Iterator<String> {
        private StringTokenizer tokenizer;

        public DocumentIterator(String content) {
            this.tokenizer = new StringTokenizer(content);
        }

        @Override
        public boolean hasNext() {
            return tokenizer.hasMoreTokens();
        }

        @Override
        public String next() {
            return tokenizer.nextToken();
        }
    }
}
