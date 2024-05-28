package idh.java;

import java.util.Iterator;

public class SkipIterator <T> implements Iterator<T> {
	int skippedTokens; 
	Iterator <T> basicIterator; 
	T token; 
	
	public SkipIterator(int tokensToSkip, Iterator <T> basic) {
		this.skippedTokens=tokensToSkip; 
		this.basicIterator=basic; 
	}
	

	@Override
	public boolean hasNext() {
		
		for(int i=this.skippedTokens; i>0; i-=1) {
			if(!basicIterator.hasNext()) {
				return false; 
			}
			else this.token=this.basicIterator.next();
		}
		return true; 
	}


	@Override
	public T next() {
		return this.token; 
	}
}
