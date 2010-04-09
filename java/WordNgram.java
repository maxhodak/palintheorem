import java.io.Serializable;

/*
 * This class encapsulates N words/strings so that the
 * group of N words can be treated as a key in a map or an
 * element in a set, or an item to be searched for in an array.
 * <P>
 * @author Max Hodak
 */

public class WordNgram implements Comparable<WordNgram>, Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7587636173503744356L;
	private String[] myWords;
    private Integer myHashCode;
    
    /*
     * Store the n words that begin at index start of array list as
     * the N words of this N-gram.
     * @param list contains at least n words beginning at index start
     * @start is the first of the N worsd to be stored in this N-gram
     * @n is the number of words to be stored (the n in this N-gram)
     */
    public WordNgram(String[] list, int start, int n) {
    	myWords = new String[n];
        System.arraycopy(list, start, myWords, 0, n);
        setHashCode();
    }
    
    /**
     * Return value that meets criteria of compareTo conventions.
     * @param wg is the WordNgram to which this is compared
     * @return appropriate value less than zero, zero, or greater than zero
     */
    public int compareTo(WordNgram wg) {
    	String[] theirWords = wg.getList();
    	for(int i=0;i<myWords.length;i++)
    	{
    		int foo = myWords[i].compareTo(theirWords[i]);
    		if(foo > 0 || foo < 0)
    			return foo;
    	}
        return 0;
    }
    
    /**
     * Return true if this N-gram is the same as the parameter: all words the same.
     * @param o is the WordNgram to which this one is compared
     * @return true if o is equal to this N-gram
     */
    public boolean equals(Object o){
        WordNgram wg = (WordNgram) o;
    	String[] theirWords = wg.getList();
    	for(int i=0;i<myWords.length;i++)
    	{
    		if(!myWords[i].equals(theirWords[i]))
    			return false;
    	}
        return true;
    }

    /**
     * Returns a good value for this N-gram to be used in hashing.
     * @return value constructed from all N words in this N-gram
     */
    public int hashCode(){
    	if(myHashCode.equals(null))
    		setHashCode();
    	
    	return myHashCode;
    }
    
    private void setHashCode()
    {
    	myHashCode = 0;
    	for(int i=0;i<myWords.length;i++)
    	{
    		myHashCode += myWords[i].hashCode()*i; 
    	}
    }
    
    public String[] getList()
    {
    	return myWords;
    }
    
    public String[] getSubset(int start, int length)
    {
    	String[] ret = new String[myWords.length];
    	if(start+length > myWords.length)
    	{
    		length = myWords.length;
    		start = 0;
    	}
    	
    	for(int i=start;i<start+length;i++)
    	{
    		ret[i-start] = myWords[i];
    	}
    	return ret;
    }
}