package com.dictionarydemo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dictionarydemo.api.AnagramFinder;

public class AnagramFinderTest {
	
	AnagramFinder anagramFinder = new AnagramFinder();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testIsAnagram() {
		// 2 strings have already been cleaned up
		boolean isAnagram = anagramFinder.isAnagram("stop", "spot");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("master", "stream");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("saop", "spot");
		assertFalse(isAnagram);
		
	}
	
	@Test
	public void testLoadDictionaryAsMap() {
		String fileName = "./src/test/resources/dictionary.txt";
		Map dictMap = anagramFinder.loadDictionaryAsMap(fileName);
		assertTrue(outContent.toString().contains("Welcome to the Anagram Finder"));
		assertTrue(dictMap!=null);
		assertTrue(!dictMap.isEmpty());
		
		fileName = "dictionary.txt";
		dictMap = anagramFinder.loadDictionaryAsMap(fileName);
		assertTrue(dictMap==null||dictMap.isEmpty());
	}
	
	@Test
	public void testMain() {
		String[] args = {"./src/test/resources/dictionary.txt"};
		String[] testWords = {"spot", "early", "master", "a"};
		String[] expectWords = {"stop", "relay", "stream", "A"};
		Map dictMap = anagramFinder.loadDictionaryAsMap(args[0]);
		assertTrue(outContent.toString().contains("Welcome to the Anagram Finder"));
		assertTrue(dictMap!=null);
		assertTrue(!dictMap.isEmpty());
		List resultList = new ArrayList<String>();
		for(int i=0; i<testWords.length; i++) {
			resultList = anagramFinder.fetchAnagram(testWords[i], dictMap);
			assertTrue(resultList.contains(expectWords[i]));
			assertFalse(resultList.contains(expectWords[testWords.length-i-1]));
		}
	}


}
