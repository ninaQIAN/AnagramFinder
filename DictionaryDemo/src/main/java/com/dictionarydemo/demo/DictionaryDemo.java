package com.dictionarydemo.demo;

import java.util.Map;
import java.util.Scanner;

import com.dictionarydemo.api.AnagramFinder;
import com.dictionarydemo.api.Dictionary;
import com.dictionarydemo.api.Search;
import com.dictionarydemo.util.Constants;


public class DictionaryDemo {
	
	/**
	 * Main function
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		System.out.println("Welcome. This is a dictionary.");
		Dictionary dictionary = new Dictionary();
//		Map alphaMap = dictionary.getAlphabetMap();
//		Map sizeMap = dictionary.getSizeMap();
		System.out.println("Please take a look at the menu:");
		boolean exit = false;
		Scanner s = new Scanner(System.in);
		while(!exit) {
			System.out.println(Constants.MENU);
			String option = s.next().trim();
			switch(option) {
			case "1":
				AnagramFinder anagranFinder = new AnagramFinder();
				anagranFinder.findAnagramWrapper(dictionary.getSizeMap());
				break;
			case "2":
				Search search = new Search();
				search.searchWordWrapper(dictionary.getSizeMap());
				break;
			case "3":
				dictionary.dumpAlphabetMap();
				break;
			case "4":
				dictionary.dumpSizeMap();
				break;
			case "5":
				dictionary.getFilePath();
				break;
			case "6":
				dictionary.getDictionarySize();
				break;
			case "7":
				dictionary.resetFilePathWrapper();
				break;
			case "0":
				s.close();
				System.out.println("Bye!");
				exit = true;
				break;
			}
		}
		System.exit(0);
	}
	


}
