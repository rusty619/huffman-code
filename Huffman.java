import java.util.HashMap;
import java.util.Map;
import java.nio.file.*;
import java.util.PriorityQueue;
import java.util.Collection;
public class Huffman {

	
	public static String readFile(String filename)throws Exception
	{
		String poem = "";
		poem = new String(Files.readAllBytes(Paths.get(filename)));
		return poem;
	}
	
	
	
	
	public static void freqCounter(String s)  // only used for testing to see if the hash map worked to store characters and their frequencies
	{
		HashMap<Character,Integer> freqCount = new HashMap<Character,Integer>();
		for(char c : s.toCharArray())
		{
			if(freqCount.containsKey(c))
				freqCount.put(c, (Integer) freqCount.get(c)+1);
				else
					freqCount.put(c, 1);
		}
		System.out.println(freqCount);
	}
	
	
	
	public static void printCode(huffmanNode root,String str) //only used to test the prefix code for each character
	{
		if(root.isLeaf() )
		{
			System.out.println(root.c + ": "+ str);
			return;
		}
		printCode(root.left, str + "0");
		printCode(root.right, str + "1");
	}
	
	public static HashMap<Character,String> binaryCode(HashMap<Character,String> map, huffmanNode root,String s)
	{
		if(root.isLeaf())
		{
			map.put(root.c, s);
		}
		else
		{
			binaryCode(map,root.left,s+"0");
			binaryCode(map,root.right,s+"1");
		}
		return map;
	}
	
	public static void textToBinary(HashMap<Character,String>map, String s)
	{   
		String str = "";
		for(char c : s.toCharArray())
		{   
			if(map.containsKey(c))
			{    
			      
			  str +=  map.get(c);
			  
		    }
			
		}
		System.out.println(str);
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        final int SIZE = 47; // size = 47
         HashMap<Character,String> map = new HashMap<Character,String>() ;
		String poem = readFile("C:\\Users\\rusty\\Desktop\\Algorithms\\Projects\\Project 1\\poem.txt");
		
         HashMap<Character,Integer> freqCount = new HashMap<Character,Integer>();
		for(char c : poem.toCharArray())
		{
			if(freqCount.containsKey(c))
				freqCount.put(c, (Integer) freqCount.get(c)+1);
				else
					freqCount.put(c, 1);
			
		}
		
	
		 
	    Collection<Integer> values = freqCount.values();
	    Integer[] valueArray  = values.toArray(new Integer[values.size()]);
	    
	    Collection<Character> ch = freqCount.keySet();
	    Character[] charArray = ch.toArray(new Character[values.size()]);

	    PriorityQueue<huffmanNode> pq = new PriorityQueue<huffmanNode> (SIZE,new comparator());
	    
	    
	    for(int i = 0; i < SIZE;i++)
	    {
	    	huffmanNode node = new huffmanNode();
		    node.c = charArray[i]; 
		    node.frequency = valueArray[i];
		    node.left = null;
		    node.right = null;
		    pq.add(node);
	    }
	 huffmanNode root = null;
     
	  
	 while (pq.size() != 1) 
		 {
         
            huffmanNode x = pq.peek();
            pq.poll();

        
            huffmanNode y = pq.peek();
            pq.poll();

     
            huffmanNode z = new huffmanNode();       
            z.frequency = x.frequency + y.frequency; 
            z.left = x;
            z.right = y;
            root = z;
            pq.add(z);
        }
	freqCounter(poem); // testing to see if the hash map contains the characters and their frequency
	printCode(root,"");// test to see the prefix code of each character
    binaryCode(map,root,"");// helping to change the characters to their prefix code
    textToBinary(map,poem);   //the size of the trie is 11. the size of the text file that contains the poem
                              //is about 1.47 kb while the huffman endcoded is about 6.86 kb
                              // I thought that the huffman code that compressed the file should have a similar
                              // amount of memory to the file that it is trying to compress.
                              // time complexity O(nlogn)
    
	}
}
