package com.dictionarydemo.test;

import static org.junit.Assert.assertEquals;
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
import com.dictionarydemo.api.Dictionary;

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
		// 2 words have already been cleaned up
		boolean isAnagram = anagramFinder.isAnagram("stop", "spot");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("master", "stream");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("saop", "spot");
		assertFalse(isAnagram);
	}
	
	@Test
	public void testFetchAnagramFromDictionary() {
		String[] testWords = {"spot", "early", "master", "a"};
		String[] expectWords = {"stop", "relay", "stream", "A"};
		Dictionary dictionary = new Dictionary();
		Map dictMap = dictionary.getSizeMap();
		assertTrue(dictMap!=null);
		assertTrue(!dictMap.isEmpty());
		List resultList = new ArrayList<String>();
		for(int i=0; i<testWords.length; i++) { // test result list
			resultList = anagramFinder.fetchAnagramFromDictionary(testWords[i], dictMap);
			assertTrue(resultList.contains(expectWords[i]));
			assertFalse(resultList.contains(expectWords[testWords.length-i-1]));
		}
	}
	
	@Test
	public void testFindAnagram() {
		Dictionary dictionary = new Dictionary();
		Map dictMap = dictionary.getSizeMap();
		assertTrue(dictMap!=null);
		assertTrue(!dictMap.isEmpty());
		
		// input edge cases
		String[] inputEdgeCase = {"a.", "a a", "a.a", ".a"};
		for(int i=0; i<inputEdgeCase.length; i++) {
			anagramFinder.findAnagram(inputEdgeCase[i], dictMap);
			assertTrue(outContent.toString().contains("ERROR: Input word should contain alphabets only!"));
		}

		// found Anagrams
		String[] testWords = {"spot", "early", "master", "a", ""};
		String[] expectWords = {"stop", "relay", "stream", "A"};
		List resultList = new ArrayList<String>();
		for(int i=0; i<testWords.length; i++) {
			anagramFinder.findAnagram(testWords[i], dictMap);
			assertTrue(outContent.toString().contains("Anagrams found for " + testWords[i]));
		}
		// no Anagram found
		String noWord = "pl";
		anagramFinder.findAnagram(noWord, dictMap);
		assertTrue(outContent.toString().contains("No anagrams found for " + noWord));
	}
	

}
