package project.dictionarydemo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Search {
	
	/**
	 * Wrapper method to search words that contains input values
	 * @param sizeMap
	 */
	public void searchWordWrapper(Map sizeMap) {
		String word = null;
		Scanner s = new Scanner(System.in);
		while(true) {
			// message
			System.out.println("Please enter search term (at least 3 letters) (enter 0: return to menu):");
			// read string from command line
			word = s.next().trim().toLowerCase();
			if("0".equalsIgnoreCase(word)) { // exit command
				System.out.println("return!");
				break;
			}
			else {
				searchWord(word, sizeMap);
			}
		}	
	}
	
	/**
	 * method to search and output result
	 * @param searchTerm
	 * @param sizeMap
	 */
	public void searchWord(String searchTerm, Map sizeMap) {
		if(searchTerm!=null) {
			searchTerm = searchTerm.trim().toLowerCase();
			if(searchTerm.isEmpty() || !searchTerm.matches("[a-z]+")) {
				System.out.println("ERROR: Input word should contain alphabets only!\n");
			}
			else if(searchTerm.length()<3) {
				System.out.println("ERROR: Search term should at least contain 3 characters!");
			}
			else {
				List resultList = findWordByString(searchTerm, sizeMap);
				if(resultList.size()>0) {
				System.out.println(resultList.size() + " word found for searchTerm: " + searchTerm + "\n"
						+ String.join(",", resultList) + "\n");
				}
				else {
					System.out.println("No result found for searchTerm: " + searchTerm + "\n");
				}
			}
		}
	}

	/**
	 * searchTerm is already cleaned:
	 * 1) lowercase
	 * 2) not trailing space
	 * 3) not null
	 * @param searchTerm
	 * @param sizeMap
	 * @return
	 */
	public List findWordByString(String searchTerm, Map<Integer, List> sizeMap) {
		int len = searchTerm.length();
		List returnList = new ArrayList<String>();
		Integer size = 0;
		List wordList = new ArrayList<String>();
		String dictWord = null;
		for(Entry<Integer, List> entry:sizeMap.entrySet()) {
			size = entry.getKey();
			wordList = entry.getValue();
			if(size>=len) {
				for(int i=0; i<wordList.size(); i++) {
					dictWord = (String) wordList.get(i);
					if(dictWord.toLowerCase().contains(searchTerm)) {
						returnList.add(dictWord);
					}
				}
			}
		}
		return returnList;
	}
}
