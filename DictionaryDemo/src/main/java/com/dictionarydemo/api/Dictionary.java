package com.dictionarydemo.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.dictionarydemo.util.Constants;

public class Dictionary {
	
	private Integer dictionarySize = 0;
	
	private String filePath = null;
	
	private Map sizeMap = new HashMap<Integer, List>();
	private Map alphabetMap = new HashMap<Character, List>();
	
	public Dictionary() {
		filePath = Constants.FILEPATH;
		initializeDictionary();
	}
	
	public Dictionary(String filePath) {
		this.filePath = filePath;
		initializeDictionary();
	}
	
	/**
	 * method to initilaize dictionry
	 * load sizeMap and alphabetMap
	 */
	private void initializeDictionary() {
		sizeMap = loadDictionaryAsWordSizeMap();		
		alphabetMap = loadDictionaryAsAphabetMap();
	}
	
	/**
	 * method to reset variables in dictionary
	 */
	private void resetDictionary() {
		filePath = null;
		dictionarySize = 0;
		sizeMap = new HashMap<Integer, List>();
		alphabetMap = new HashMap<Character, List>();
	}
	
	/**
	 * Wrapper method to reset file path
	 */
	public void resetFilePathWrapper() {
		System.out.println("Please enter a new path: ");
		Scanner s = new Scanner(System.in);
		String newFilePath = s.next().trim();
		File f = new File(newFilePath);
		if(!f.exists()|| !f.getName().endsWith(".txt")) {
			System.out.println("ERROR: No .txt file found!\n");
		}
		else {
			resetFilePath(newFilePath);
		}
	}
	
	/**
	 * method to reset file path:
	 * 1) clear current dictionary
	 * 2) add new file path
	 * 3) initialize
	 * @param filePath
	 */
	private void resetFilePath(String filePath) {
		resetDictionary();
		this.filePath = filePath;
		initializeDictionary();
		System.out.println("Old dictionary file path is reset. New dictionary is loaded.");
	}
	
	/**
	 * method to get file path
	 * @return
	 */
	public String getFilePath() {
		System.out.println("File path: " + filePath);
		return filePath;
	}
	
	/**
	 * method to get dictionary size
	 * @return
	 */
	public Integer getDictionarySize() {
		if(dictionarySize==0) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(filePath));
				while ((br.readLine()) != null) {
					dictionarySize++;
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
		}
		System.out.println("Dictionary size: " + dictionarySize);
		return dictionarySize;
	}
	
	/**
	 * method to get sizeMap
	 * @return
	 */
	public Map getSizeMap() {
		return sizeMap;
	}
	
	public Map getAlphabetMap() {
		return alphabetMap;
	}
	
	/**
	 * output counts of words in alphabetMap
	 */
	public void dumpAlphabetMap() {
		System.out.println("In initial alphabet map:");
		char key = 'a';
		List dictList = new ArrayList<String>();
		for(int i=0; i<26; i++) {
			key = (char) ('a'+i);
			dictList = (alphabetMap.containsKey(key))?(List) alphabetMap.get(key):new ArrayList<String>();
			System.out.println(key + " has " + dictList.size() + " words");
		}
	}
	
	/**
	 * output counts of words in sizeMap
	 */
	public void dumpSizeMap() {
		System.out.println("In word size map:");
		List dictList = new ArrayList<String>();
		for(Object size:sizeMap.keySet()) {
			dictList = (List) sizeMap.get(size);
			System.out.println("word size " + size + " has " + dictList.size() + " words");
		}
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
	public Map loadDictionaryAsWordSizeMap() {
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
		return returnMap;
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
	public Map loadDictionaryAsAphabetMap() {
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
				dictWordInitial = Character.toLowerCase(dictWord.charAt(0));
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
		return returnMap;
	}
	
}
