package project.dictionarydemo.test;

import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import project.dictionarydemo.testcase.AnagramFinderTest;
import project.dictionarydemo.testcase.DictionaryTest;
import project.dictionarydemo.testcase.SearchTest;


public class TestRunner {
	private static Logger log = Logger.getLogger(TestRunner.class.getName());
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AnagramFinderTest.class, DictionaryTest.class, SearchTest.class);
		
		log.info("\nTEST RESULT:\n" + result.getRunCount() + " test cases. " + result.getRunTime() + " ms\n");
		for (Failure failure : result.getFailures()) {
			log.info("ERROR: " + failure.toString());
		}
		if(result.wasSuccessful()) {
			log.info("Successful!");
			System.exit(0);
		}
		else {
			log.info("Not successful!");
			System.exit(1);
		}
	}
} 
