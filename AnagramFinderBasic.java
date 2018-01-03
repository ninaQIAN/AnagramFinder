
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Anagram Finder Basic - version 1
 * 
 */
public class AnagramFinderBasic {
	// command: java AnagramFinderBasic dictionary.txt
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("ERROR: No dictionary found!\n");
			System.exit(0);
		}
		String fileName = args[0];
		List<String> dictList = new ArrayList<String>();
		List resultList = new ArrayList<String>();
		String dictWord = null;
		dictList = loadDictionaryAsList(fileName);
		while(true) {
			// message
			System.out.println("Please enter a word or exit:");
			// read string from command line
			String word = System.console().readLine().trim().toLowerCase(); // clean up
			if(!word.matches("[a-z]+")) { // word validation
				System.out.println("ERROR: Input word should contain alphabets only!\n");
				continue;
			}
			// exit command
			if("exit".equalsIgnoreCase(word)) {
				System.out.println("exit!");
				System.exit(0);
			}
			
			// calculation
			resultList = new ArrayList<String>();
			long t3 = System.currentTimeMillis();
			for (int i=0; i<dictList.size(); i++) { // compare with every word from dictionary
				dictWord = dictList.get(i);
				if(isAnagram(word.toLowerCase(), dictWord.toLowerCase())) {
					resultList.add(dictWord);
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
	public static List loadDictionaryAsList(String fileName) {
		List returnList = new ArrayList<String>();
		BufferedReader br = null;
		String dictWord = null;
		try {
			System.out.println("Welcome to the Anagram Finder\n-----------------------------");
			File f = new File(fileName);
			if(!f.exists() || f.isDirectory()) { 
				System.out.println("ERROR: No dictionary found!\n");
				System.exit(0);
			}
			br = new BufferedReader(new FileReader(fileName));
			long t1 = System.currentTimeMillis();
			while ((dictWord = br.readLine()) != null) {
				returnList.add(dictWord);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Dictionary loaded in " + (t2-t1) + "ms");
		} catch(Exception e) {
			System.out.println("ERROR: Something happened!");
		} finally {
			try {
				if(br!=null)
					br.close();
			} catch (IOException ioe) {
				System.out.println("Error: Error in closing the BufferedReader!");
			}
		}
		return returnList;
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