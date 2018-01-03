import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Anagram Finder - version 1
 * 
 */
public class AnagramFinderV1 {
	// command: java javaFile textFile.txt
	public static void main(String[] args) {
		String fileName = args[0];
		List<String> dictList = new ArrayList<String>();
		List resultList = new ArrayList<String>();
		BufferedReader br = null;
		String dictWord = null;
		try {
			System.out.println("Welcome to the Anagram Finder\n-----------------------------");
			br = new BufferedReader(new FileReader(fileName));
			long t1 = System.currentTimeMillis();
			while ((dictWord = br.readLine()) != null) {
				dictList.add(dictWord);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Dictionary loaded in " + (t2-t1) + "ms");
			while(true) {
				// message
				System.out.println("please enter a word or exit:");
				// read string from command line
				String word = System.console().readLine().trim().toLowerCase();
				if("exit".equalsIgnoreCase(word)) {
					System.out.println("exit!");
					System.exit(0);
				}

				// calculation
				resultList = new ArrayList<String>();
				long t3 = System.currentTimeMillis();
				for (int i=0; i<dictList.size(); i++) { // compare with every word from dictionary
					dictWord = dictList.get(i);
					if(isAnagram(word, dictWord.toLowerCase())) {
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
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null)
					br.close();
			} catch (IOException ioe)
			{
				System.out.println("Error in closing the BufferedReader");
			}
		}
	}

	/**
	 * method to check whether two words are angram or not
	 */
	private static Boolean isAnagram(String word, String dictWord) {
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
		int sum = 0;
		for(int c:countArr) {
			if(c!=0) { // after looping 2 words, letter char count should be 0
				return false;
			}
		}
		return true;
	}
}