package com.dictionarydemo.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dictionarydemo.util.Constants;

public class Dictionary {
	
	private static final String filePath = Constants.FILEPATH;
	
	private Map sizeMap = new HashMap<Integer, List>();
	private Map alphabetMap = new HashMap<Character, List>();
	
	public Dictionary() {
		loadDictionaryAsWordSizeMap();
		loadDictionaryAsAphabetMap();
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public Map getSizeMap() {
		return this.sizeMap;
	}
	
	public Map getAlphabetMap() {
		return this.alphabetMap;
	}
	

	/**
	 * load dictionary as a HashMap
	 * key is word length, value is a list of words with the same length
	 * {
	 * 	1:["a", "A", "b", "c"....],
	 * 	2:[...],
	 * 	...
	 * }
	 * @param fileName
	 * @return
	 */
	private void loadDictionaryAsWordSizeMap() {
		Map returnMap = new HashMap<Integer, List>();
		BufferedReader br = null;
		String dictWord = null;
		Integer dictWordLen = null;
		List wordList = new ArrayList<String>(); // word list by word length
		try {
			System.out.println("Loading dictionary by word size...");
			long t1 = System.currentTimeMillis();
			br = new BufferedReader(new FileReader(filePath));
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
			System.out.println("ERROR: Something happened!");
		} finally {
			try {
				if(br!=null)
					br.close();
			} catch (IOException ioe) {
				System.out.println("Error: Error in closing the BufferedReader!");
			}
		}
		this.sizeMap = returnMap;
	}
	
	/**
	 * load dictionary as a HashMap with size = 26
	 * key is alphabet from 'a' to 'z';
	 * value is a list of words that begins with the key
	 * {
	 * 	"a": ["a", "A", "b" ...],
	 * 	"b": [...],
	 * 	 ....
	 * }
	 * @param fileName
	 * @return
	 */
	private void loadDictionaryAsAphabetMap() {
		Map returnMap = new HashMap<Character, List>();
		BufferedReader br = null;
		String dictWord = null;
		Character dictWordInitial = 0;
		List wordList = new ArrayList<String>(); // word list by word length
		try {
			System.out.println("Loading dictionary by initial alphabet...");
			long t1 = System.currentTimeMillis();
			br = new BufferedReader(new FileReader(filePath));
			while ((dictWord = br.readLine()) != null) {
				dictWordInitial = dictWord.charAt(0);
				if(returnMap.containsKey(dictWordInitial)) {
					wordList = (List) returnMap.get(dictWordInitial);
				}
				else {
					wordList = new ArrayList<String>();
				}
				wordList.add(dictWord);
				returnMap.put(dictWordInitial, wordList);
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
		this.alphabetMap = returnMap;
	}
	
}
