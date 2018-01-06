package com.dictionarydemo.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.dictionarydemo.testcase.AnagramFinderTest;


public class TestRunner {
	   public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(AnagramFinderTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
	} 
