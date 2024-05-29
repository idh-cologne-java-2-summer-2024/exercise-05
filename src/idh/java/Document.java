package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Made class iterable by implement the interface Iterable<String>
 */
public class Document implements Iterable<String> {
	String documentText;

	/**
	 * New class StringTokenizerIterator that wraps the StringTokenizer
	 */
	class StringTokenizerIterator implements Iterator<String> {
	    StringTokenizer tokenizer;

	    public StringTokenizerIterator(String text) {
		this.tokenizer = new StringTokenizer(text);
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

	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
			b.append((char) ch);
		}
		fileReader.close();
		Document doc = new Document();
		doc.documentText = b.toString();
		
		return doc;
	}
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		int i = 0;
		for (String token : d) {
			System.out.println(i++ + ": " + token + " ");
			if (i > 100)
				break;
		}
		
		i = 0;
		SkipIterator<String> skiter = new SkipIterator<String>(d.iterator(), 1);
		while(skiter.hasNext()) {
			System.out.println(i++ + ": "+ skiter.next() + " ");
			if (i > 100)
				break;
		}
	}

	/**
	 * Added new method iterator() to satisfy the interface.
	 */
	@Override
	public Iterator<String> iterator() {
	    // return an object of class StringTokenizerIterator
	    return new StringTokenizerIterator(documentText);
	}
	
	
}
