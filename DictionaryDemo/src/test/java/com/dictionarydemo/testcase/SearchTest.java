package com.dictionarydemo.testcase;

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

import com.dictionarydemo.api.Dictionary;
import com.dictionarydemo.api.Search;

public class SearchTest {
	
	private Search search = new Search();
	
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
	public void testFindWordByString() {
		Dictionary dictionary = new Dictionary();
		Map sizeMap = dictionary.getSizeMap();
		assertTrue(sizeMap!=null);
		assertTrue(!sizeMap.isEmpty());
		
		String[] searchArr = {"abc", "kings", "asu", "xyc"};
		String[] expectWords = {"dabchick", "fackings", "Basuto", "oxycamphor"};
		List resultList = new ArrayList<String>(); 
		for(int i=0; i<searchArr.length; i++) {
			resultList = search.findWordByString(searchArr[i], sizeMap);
			assertTrue(resultList.contains(expectWords[i]));
			assertFalse(resultList.contains(expectWords[searchArr.length-i-1]));
		}
	}
	
	@Test
	public void testSearchWord() {
		Dictionary dictionary = new Dictionary();
		Map sizeMap = dictionary.getSizeMap();
		assertTrue(sizeMap!=null);
		assertTrue(!sizeMap.isEmpty());
		
		String[] inputEdgeCase = {"a.", "a a", "a.a", ".a"};
		for(int i=0; i<inputEdgeCase.length; i++) {
			search.searchWord(inputEdgeCase[i], sizeMap);
			assertTrue(outContent.toString().contains("ERROR: Input word should contain alphabets only!"));
		}
		String[] lessLen = {"a", "aa"};
		for(int i=0;i<lessLen.length;i++) {
			search.searchWord(lessLen[i], sizeMap);
			assertTrue(outContent.toString().contains("ERROR: Search term should at least contain 3 characters!"));			
		}
		
		String[] searchArr = {"abc", "asu", "xyc"};
		String[] expectWords = {"dabchick", "Basuto", "oxycamphor"};
		for(int i=0; i<searchArr.length; i++) {
			search.searchWord(searchArr[i], sizeMap);
			assertTrue(outContent.toString().contains("word found for searchTerm: " + searchArr[i]));
			assertTrue(outContent.toString().contains(expectWords[i]));
		}
		
		String noWord = "adfb";
		search.searchWord(noWord, sizeMap);
		assertTrue(outContent.toString().contains("No result found for searchTerm: " + noWord));
	}

}
