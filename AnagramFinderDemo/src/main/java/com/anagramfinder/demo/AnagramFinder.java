package com.anagramfinder.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Anagram Finder Advanced - version 2
 * 
 */
public class AnagramFinder {
	// command: java AnagramFinderAdvanced dictionary.txt
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("ERROR: No dictionary found!\n");
			System.exit(1);
		}
		String fileName = args[0];
		Map dictMap = new HashMap<Integer, List>();
		List resultList = new ArrayList<String>();
		long t1 = System.currentTimeMillis();
		dictMap = loadDictionaryAsMap(fileName);
		long t2 = System.currentTimeMillis();
		System.out.println("Dictionary loaded in " + (t2-t1) + "ms");
		if(dictMap==null || dictMap.isEmpty()) {
			System.exit(1);
		}
		while(true) {
			// message
			System.out.println("Please enter a word or exit:");
			// read string from command line
			Scanner s = new Scanner(System.in);
			String word = s.next().trim().toLowerCase();
			if(!word.matches("[a-z]+")) { // word validation
				System.out.println("ERROR: Input word should contain alphabets only!\n");
				continue;
			}
			// exit command
			if("exit".equalsIgnoreCase(word)) {
				System.out.println("exit!");
				s.close();
				System.exit(0);
			}
			
			// calculation
			resultList = new ArrayList<String>();
			long t3 = System.currentTimeMillis();
			resultList = fetchAnagram(word, dictMap);
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
	 * wrapper method to find a word's anagram form dictionary hashMap
	 * @param word
	 * @param dictMap
	 * @return
	 */
	public static List fetchAnagram(String word, Map<Integer, List> dictMap) {
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
			File f = new File(fileName);
			if(!f.exists()) {// || f.isDirectory()) { 
				System.out.println("ERROR: No dictionary found!\n");
				return returnMap;
			}
			br = new BufferedReader(new FileReader(fileName));
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
		return returnMap;
	}
	
	/**
	 * method to check whether two words are anagram or not
	 * 2 strings have already been cleaned up:
	 * 1) not empty or null; 
	 * 2) only lowercase alphabets
	 * 3) same length
	 */
	public static boolean isAnagram(String word, String dictWord) {
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