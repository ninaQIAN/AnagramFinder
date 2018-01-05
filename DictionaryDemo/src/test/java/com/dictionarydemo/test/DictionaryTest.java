package com.dictionarydemo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dictionarydemo.api.Dictionary;

public class DictionaryTest {

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
	public void testGetSizeMap() {
		Dictionary dictionary = new Dictionary();
		Map sizeMap = dictionary.getSizeMap();
		assertTrue(outContent.toString().contains("Loading dictionary by word size"));
		assertTrue(sizeMap!=null&&!sizeMap.isEmpty());
		assertFalse(sizeMap.containsKey(0));
		for(int i=1; i<=sizeMap.size(); i++) {
			assertTrue(sizeMap.containsKey(i));
		}
	}

	@Test
	public void testGetAlphabetMap() {
		Dictionary dictionary = new Dictionary();
		Map alphabetMap = dictionary.getAlphabetMap();
		assertTrue(outContent.toString().contains("Loading dictionary by initial alphabet"));
		assertTrue(alphabetMap!=null&&!alphabetMap.isEmpty());
		char key = 'a';
		for(int i=0; i<26; i++) {
			key = (char) ('a'+i);
			assertTrue(alphabetMap.containsKey(key));			
		}
	}

}
