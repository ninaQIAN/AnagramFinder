package com.dictionarydemo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Anagram Finder Advanced - version 2
 * 
 */
public class AnagramFinder {
	
	/**
	 * Wrapper method to find a word's anagram
	 * @param dictMap
	 */
	public void findAnagram(Map dictMap) {
		List resultList = new ArrayList<String>();
		String word = null;
		Scanner s = new Scanner(System.in);
		while(true) {
			// message
			System.out.println("Please enter a word (enter 0: return to menu):");
			// read string from command line
			word = s.next().trim().toLowerCase();
			// exit command
			if("0".equalsIgnoreCase(word)) {
				System.out.println("return!");
				break;
			}
			else {
				if(!word.matches("[a-z]+")) { // word validation
					System.out.println("ERROR: Input word should contain alphabets only!\n");
					continue;
				}
				
				// calculation
				resultList = fetchAnagram(word, dictMap);
				// output result
				if(resultList!=null && resultList.size()>0) { // has anagram
					System.out.println(resultList.size() + " Anagrams found for " + word + ":\n"
							+ String.join(",", resultList) + "\n");
				}
				else {
					System.out.println("No anagrams found for " + word +"\n");
				}
			}
		}	
	}
	
	/**
	 * wrapper method to find a word's anagram form dictionary hashMap
	 * @param word
	 * @param dictMap
	 * @return
	 */
	public List fetchAnagram(String word, Map<Integer, List> dictMap) {
		List returnList = new ArrayList<String>();
		String dictWord = null;
		List<String> dictList = (List) dictMap.get(word.length());
		if(dictList!=null && !dictList.isEmpty()) {
			for (int i=0; i<dictList.size(); i++) { // compare with every word from dictionary
				dictWord = dictList.get(i);
				if(isAnagram(word.toLowerCase(), dictWord.toLowerCase())) {
					returnList.add(dictWord);
				}
			}				
		}
		return returnList;
	}
	
	/**
	 * method to check whether two words are anagram or not
	 * 2 strings have already been cleaned up:
	 * 1) not empty or null; 
	 * 2) only lowercase alphabets
	 * 3) same length
	 */
	public boolean isAnagram(String word, String dictWord) {
		int wordLen = word.length();
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