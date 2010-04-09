import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.danga.MemCached.MemCachedClient;

public class WordMarkovModel {

	public MemCachedClient mcc;

    private String[] myString;
    private ArrayList<String> temp;
    private Random myRandom;
    private Map<WordNgram, ArrayList<String>> myMap;
    private static int myK = 3;
    private static int numWords = 80;
        
    public WordMarkovModel() {
       
        myRandom = new Random();
    }

	@SuppressWarnings("unchecked")
	public String initialize(String s) {
    	if(!mcc.keyExists("myMap"))
    	{
    		mcc.add("myMap",new HashMap<WordNgram, ArrayList<String>>());
    		
    	}    	
    	if(!mcc.keyExists("myString")){
    		temp = new ArrayList<String>();
    		int count = readChars(s);
    		myString = new String[count];
    		for(int i=0;i<temp.size();i++)
    		{
    			myString[i] = temp.get(i);
    		}
    		mcc.set("myString",myString);
    	} else {
    		myString = (String[]) mcc.get("myString");
    	}
    	
        myMap = (HashMap<WordNgram, ArrayList<String>>) mcc.get("myMap");
        
        if(myMap.size() == 0)
        {
        	buildMap(myK, numWords);
        }
        
        return elegant(myK,numWords);
    }
    
    protected int readChars(String s) {
    	String[] words = s.split("\\s+");
    	for(int i=0;i<words.length;i++)
    	{
    		temp.add(words[i]);
    	}
        return temp.size();
    }
    
    public String elegant(int k, int numLetters){

    	StringBuilder builder = new StringBuilder();
    	
    	int start = myRandom.nextInt(myString.length - k);
        WordNgram seed = new WordNgram(myString,start,k);
        
        for(int i=0;i<numLetters;i++)
        {
        	ArrayList<String> foo = myMap.get(seed);
            String nextgram = foo.get(myRandom.nextInt(foo.size()));
            builder.append(" "+nextgram);
            String[] temp = seed.getSubset(1, k-1);
            temp[k-1] = nextgram;
            seed = new WordNgram(temp,0,k);
        }
        return builder.toString();
    }

    public void buildMap(int k, int numLetters) {
        for(int j=0;j<myString.length-k;j++)
        {
    		WordNgram words = new WordNgram(myString,j,k);

    		if(!myMap.containsKey(words))
    		{
    			myMap.put(words, new ArrayList<String>());
    		}
    		ArrayList<String> list = myMap.get(words);
            list.add(myString[j+k]);
        }
        mcc.set("myMap", myMap);
    }
}