
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Anagram Finder Advanced - version 2
 * 
 */
public class AnagramFinderAdvanced {
	// command: java AnagramFinderAdvanced dictionary.txt
	public static void main(String[] args) {
		String fileName = args[0];
		Map returnMap = new HashMap<Integer, List>();
		List resultList = new ArrayList<String>();
		String dictWord = null;
		returnMap = loadDictionaryAsMap(fileName);
		while(true) {
			// message
			System.out.println("please enter a word or exit:");
			// read string from command line
			String word = System.console().readLine().trim();
			if("exit".equalsIgnoreCase(word)) {
				System.out.println("exit!");
				System.exit(0);
			}
			
			// calculation
			resultList = new ArrayList<String>();
			long t3 = System.currentTimeMillis();
			List<String> dictList = (List) returnMap.get(word.length());
			if(dictList!=null && !dictList.isEmpty()) {
				for (int i=0; i<dictList.size(); i++) { // compare with every word from dictionary
					dictWord = dictList.get(i);
					if(isAnagram(word.toLowerCase(), dictWord.toLowerCase())) {
						resultList.add(dictWord);
					}
				}				
			}
			long t4 = System.currentTimeMillis();
			// output result
			if(resultList.size()>0) { // has anagram
				System.out.println(resultList.size() + " Anagrams found for " + word + " in " + (t4-t3) + "ms:\n"
						+ String.join(",", resultList) + "\n");
			}
			else {
				System.out.println("No anagrams found for " + word +" in " + (t4-t3) + "ms\n");
			}
		}
	}
	
	/**
	 * load dictionary as a ArrayList
	 */
	public static Map loadDictionaryAsMap(String fileName) {
		Map returnMap = new HashMap<Integer, List>();
		BufferedReader br = null;
		String dictWord = null;
		Integer dictWordLen = null;
		List wordList = new ArrayList<String>(); // word list by word length
		try {
			System.out.println("Welcome to the Anagram Finder\n-----------------------------");
			br = new BufferedReader(new FileReader(fileName));
			long t1 = System.currentTimeMillis();
			while ((dictWord = br.readLine()) != null) {
				dictWordLen = dictWord.length();
				if(returnMap.containsKey(dictWordLen)) {
					wordList = (List) returnMap.get(dictWordLen);
				}
				else {
					wordList = new ArrayList<String>();
				}
				wordList.add(dictWord);
				returnMap.put(dictWordLen, wordList);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Dictionary loaded in " + (t2-t1) + "ms");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null)
					br.close();
			} catch (IOException ioe) {
				System.out.println("Error in closing the BufferedReader");
			}
		}
		return returnMap;
	}
	
	/**
	 * method to check whether two words are angram or not
	 */
	public static Boolean isAnagram(String word, String dictWord) {
		int wordLen = word.length();
		int dictWordLen = dictWord.length();
		if(wordLen!=dictWordLen) { // word length should be the same
			return false;
		}
		int[] countArr = new int[26];
		char wordChar = 0;
		char dictWordChar = 0;
		for(int i=0;i<wordLen;i++) {
			wordChar = word.charAt(i);
			dictWordChar = dictWord.charAt(i);
			countArr[wordChar-'a']++;
			countArr[dictWordChar-'a']--;
		}
		for(int c:countArr) {
			if(c!=0) { // after looping 2 words, letter char count should be 0
				return false;
			}
		}
		return true;
	}


}