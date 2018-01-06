package project.dictionarydemo.demo;

import java.util.Scanner;

import project.dictionarydemo.api.AnagramFinder;
import project.dictionarydemo.api.Dictionary;
import project.dictionarydemo.api.Search;
import project.dictionarydemo.util.Constants;


public class DictionaryDemo {
	
	/**
	 * Main function
	 */
	public static void main(String[] args) {
		System.out.println("Welcome. This is a dictionary.");
		Dictionary dictionary = new Dictionary();
		System.out.println("Please take a look at the menu:");
		boolean exit = false;
		Scanner s = new Scanner(System.in);
		while(!exit) {
			System.out.println(Constants.MENU);
			String option = s.next().trim();
			switch(option) {
			case "1": // find anagram
				AnagramFinder anagranFinder = new AnagramFinder();
				anagranFinder.findAnagramWrapper(dictionary.getSizeMap());
				break;
			case "2": // search word
				Search search = new Search();
				search.searchWordWrapper(dictionary.getSizeMap());
				break;
			case "3": // output dictionary summary in alphabetic order
				dictionary.dumpAlphabetMap();
				break;
			case "4": // output dictionary summary in word size order
				dictionary.dumpSizeMap();
				break;
			case "5": // output dictionary path
				dictionary.getFilePath();
				break;
			case "6": // output dictionary word size
				dictionary.getDictionarySize();
				break;
			case "7": // load another dictionary
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
