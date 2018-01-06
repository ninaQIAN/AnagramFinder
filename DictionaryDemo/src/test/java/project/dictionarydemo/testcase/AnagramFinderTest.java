package project.dictionarydemo.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.dictionarydemo.api.AnagramFinder;
import project.dictionarydemo.api.Dictionary;

public class AnagramFinderTest {
	
	private static Logger log = Logger.getLogger(AnagramFinderTest.class.getName());
	
	private AnagramFinder anagramFinder = new AnagramFinder();
	
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
		log.info("testIsAnagram...");
		long t1 = System.currentTimeMillis();
		// 2 words have already been cleaned up
		boolean isAnagram = anagramFinder.isAnagram("stop", "spot");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("master", "stream");
		assertTrue(isAnagram);
		isAnagram = anagramFinder.isAnagram("saop", "spot");
		assertFalse(isAnagram);
		long t2 = System.currentTimeMillis();
		log.info("done in " + (t2-t1) + "ms");
	}
	
	@Test
	public void testFetchAnagramFromDictionary() {
		log.info("testFetchAnagramFromDictionary...");
		long t1 = System.currentTimeMillis();
		Dictionary dictionary = new Dictionary();
		Map sizeMap = dictionary.getSizeMap();
		assertTrue(sizeMap!=null);
		assertTrue(!sizeMap.isEmpty());

		String[] testWords = {"spot", "early", "master", "a"};
		String[] expectWords = {"stop", "relay", "stream", "A"};
		List resultList = new ArrayList<String>();
		for(int i=0; i<testWords.length; i++) { // test result list
			resultList = anagramFinder.fetchAnagramFromDictionary(testWords[i], sizeMap);
			assertTrue(resultList.contains(expectWords[i]));
			assertFalse(resultList.contains(expectWords[testWords.length-i-1]));
		}
		long t2 = System.currentTimeMillis();
		log.info("done in " + (t2-t1) + "ms");
	}
	
	@Test
	public void testFindAnagram() {
		log.info("testFindAnagram...");
		long t1 = System.currentTimeMillis();
		Dictionary dictionary = new Dictionary();
		Map sizeMap = dictionary.getSizeMap();
		assertTrue(sizeMap!=null);
		assertTrue(!sizeMap.isEmpty());
		
		// input edge cases
		String[] inputEdgeCase = {"a.", "a a", "a.a", ".a"};
		for(int i=0; i<inputEdgeCase.length; i++) {
			anagramFinder.findAnagram(inputEdgeCase[i], sizeMap);
			assertTrue(outContent.toString().contains("ERROR: Input word should contain alphabets only!"));
		}

		// found Anagrams
		String[] testWords = {"spot", "early", "master", "a", ""};
		for(int i=0; i<testWords.length; i++) {
			anagramFinder.findAnagram(testWords[i], sizeMap);
			assertTrue(outContent.toString().contains("Anagrams found for " + testWords[i]));
		}
		// no Anagram found
		String noWord = "pl";
		anagramFinder.findAnagram(noWord, sizeMap);
		assertTrue(outContent.toString().contains("No anagrams found for " + noWord));
		long t2 = System.currentTimeMillis();
		log.info("done in " + (t2-t1) + "ms");
	}
	

}
