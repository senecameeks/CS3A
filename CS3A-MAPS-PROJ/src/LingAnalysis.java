import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
/*Seneca Meeks
 * Frequency
 * Word Length
 * Bigram
 * 
 * 
 */

public class LingAnalysis {
	public static void main(String[] args) {
		Path source = Paths.get("corpora/tester.txt");
		Path output = Paths.get("generated.txt");
		Scanner scan = new Scanner(System.in);

		Map<String, Integer> freq = frequencyDist(source);
		System.out.println("Frequency: " + freq);

		/*Map<Integer, Integer> wordlen = wordLengths(source);
		System.out.println("Word Length: " + wordlen);

		Map<String, List<String>> bigram = bigram(output);
		System.out.println("Bigram: " + bigram);
		 */

		Map<Integer, List<String>> top = TopTen(freq);
		System.out.println("Top 10: " + top);

		//Map<String, List<String>> gram = gra

	}

	/*
	 * this should make a string of length words.
	 * 
	 * pick a first word from the keyset of gram
	 * repeat length times:
	 *    get the list associated with the word
	 *    replace word with a random pick from that list
	 *    add that word to the file
	 */
	static void generate(Map<String, List<String>> gram, int length, Path out) {
		try {
			BufferedWriter file = Files.newBufferedWriter(out, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			file.write("hello ");
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * this map should be from word length, to the number of words with that length.
	 * 
	 * for example, the output for tester.txt should be:
	 * {3=2, 5=1, 7=2} 
	 * in other words, there are two words of size three, one word of size 5, and two words of size 7
	 * 
	 */
	static Map<Integer, Integer> wordLengths(Path p) {
		Map<Integer, Integer> wordlen = new HashMap<Integer, Integer>();
		try{
			BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8); 


			for(String line = br.readLine(); line != null; line = br.readLine()){				
				for(String word : line.split(" ")){
					word = word.replaceAll("[.,?/(;$%-*:)!\"]", "");
					word = word.toLowerCase();
					word = word.replaceAll("'s?", ""); 
					word = word.replaceAll("[1234567890]", ""); 
					if(word.length()<1) continue;
					if(wordlen.containsKey(word.length())) wordlen.put(word.length(), wordlen.get(word.length()) + 1);
					else wordlen.put(word.length(), 1);

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return wordlen;
	}

	/*
	 * This map should be from every word in the text to a list of words that can follow it.
	 * There should be multiple copies of the following words if they repeat in the text
	 * 
	 * the correct output for tester should be:
	 * 
	 * {two=[three], one=[two], testing=[testing, one]}
	 * 
	 * for each word:
	 *   if the previous word is not in the map, add it to the map with an empty list
	 *   get the previous word's list from the map, add the current word.	 * 
	 */
	static Map<String, List<String>> bigram(Path p) {
		Map<String, List<String>> bigram = new HashMap<String, List<String>>();
		List<String> data = new ArrayList<String>();  
		List<String> l = new ArrayList<String>();
		l.add(null); 

		try{
			BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8); 		 

			for(String line = br.readLine(); line != null; line = br.readLine()){				
				for(String word : line.split(" ")){
					word = word.replaceAll("[.,?/(;$%-*:)!\"]", "");
					word = word.toLowerCase();
					word = word.replaceAll("'s?", ""); 
					word = word.replaceAll("[1234567890]", ""); 
					if(word.length()<1) continue;				
					bigram.put(word, new ArrayList<String>());
					data.add(word); 

				}

				//System.out.println(data);

				for (int i = 0; i < data.size(); i++) {
					if(i== data.size() - 1) break;
					bigram.get(data.get(i)).add(data.get(i+1));

				}
			}

			//System.out.println(data); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return bigram;
	}

	/*
	 * this map should be from every word to the frequency in the text
	 * 
	 * for each word:
	 *   if the word is in the map, increment the value by one
	 *   if the word is not in the map, add the word with a value of one.
	 */
	static Map<String, Integer> frequencyDist(Path p) {
		Map<String, Integer> freq = new TreeMap<String, Integer>();

		try{
			BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8); 
			for(String line = br.readLine(); line != null; line = br.readLine()){				
				for(String word : line.split(" ")){
					word = word.replaceAll("[.,?/(;$%-*:)!\"]", "");
					word = word.toLowerCase();
					word = word.replaceAll("'s?", ""); 
					word = word.replaceAll("[1234567890]", ""); 
					if(word.length()<3) continue; 
					if(freq.containsKey(word)) freq.put(word, freq.get(word)+1);
					else freq.put(word, 1); 

				}

			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return freq;
	}

	static <String, Integer> Map<Integer, List<String>> TopTen(Map<String, Integer> freq) {
		Map<Integer, List<String>> top = new TreeMap<Integer, List<String>>().descendingMap();
		List<String> ten = new ArrayList<String>();
		//System.out.println("Entry: " + freq.entrySet());
		
		//PLAN MAKE SUBLIST WITH LIST<STRING> 
		//USE COMPARATOR TO PICK OUT 10 

		try{
			
			for (Map.Entry<String, Integer> entry : freq.entrySet()){
				if(!top.containsKey(entry.getKey())) top.put(entry.getValue(), new ArrayList<String>());				
				top.get(entry.getValue()).add(entry.getKey());
				System.out.println("happens"); 
				
				//freq.ent
			}
					
		
		}

		catch (Exception e) {
			// TODO: handle exception
		}

		return top; 
	} 
}


