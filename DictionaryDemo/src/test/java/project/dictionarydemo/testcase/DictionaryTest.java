package project.dictionarydemo.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.dictionarydemo.api.Dictionary;
import project.dictionarydemo.util.Constants;

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
		assertTrue(sizeMap!=null&&!sizeMap.isEmpty());
		assertFalse(sizeMap.containsKey(0));
		for(int i=1; i<=sizeMap.size(); i++) { // use default dictionary
			assertTrue(sizeMap.containsKey(i));
		}
	}

	@Test
	public void testGetAlphabetMap() {
		Dictionary dictionary = new Dictionary();
		Map alphabetMap = dictionary.getAlphabetMap();
		assertTrue(alphabetMap!=null&&!alphabetMap.isEmpty());
		char key = 'a';
		for(int i=0; i<26; i++) {
			key = (char) ('a'+i);
			assertTrue(alphabetMap.containsKey(key));			
		}
	}
	
	@Test
	public void testGetDictionarySize() {
		Dictionary dictionary = new Dictionary();
		dictionary.getDictionarySize();
		assertTrue(outContent.toString().contains("Dictionary size: "));
		
	}
	
	@Test
	public void testGetFilePath() {
		Dictionary dictionary = new Dictionary();
		dictionary.getFilePath();
		assertTrue(outContent.toString().contains(Constants.FILEPATH));
		
	}
	
	@Test
	public void testResetFilePathWrapper() {
		Dictionary dictionary = new Dictionary();
		String[] files = {"error", Constants.FILEPATH_TEST};
		ByteArrayInputStream in = new ByteArrayInputStream(files[0].getBytes());
		System.setIn(in);
		dictionary.resetFilePathWrapper();
		assertTrue(outContent.toString().contains("ERROR: No .txt file found!\n"));
		// change file
		in = new ByteArrayInputStream(files[1].getBytes());
		System.setIn(in);
		dictionary.resetFilePathWrapper();
		assertTrue(outContent.toString().contains("Old dictionary file path is reset. New dictionary is loaded."));
		dictionary.getFilePath();
		assertTrue(outContent.toString().contains(files[1]));
		// newDict.txt only contains words begin with "i"
		Map alphabetMap = dictionary.getAlphabetMap();
		assertTrue(alphabetMap!=null&&!alphabetMap.isEmpty());
		assertTrue(alphabetMap.containsKey(new Character('i')));
	}
	
}
