/**
 *
 *  @author Grzechnik Mariusz S14456
 *
 */

package zad3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Anagrams {

	ArrayList<String> readList = new ArrayList<String>();
	
	public Anagrams(String allWords) {
		try {
			Scanner scan = new Scanner(new File(allWords));
			while(scan.hasNext()) {
					readList.add(scan.next());
			}
			scan.close();
		} catch (IOException e) {
			System.out.println("Cos poszlo nie tak...");
			e.printStackTrace();
		}
	}

	public String getAnagramsFor(String next) {
		ArrayList<String> anagList = new ArrayList<>();
		char[] word1 = next.toCharArray();
		Arrays.sort(word1);
	
		for(int i = 0; i < readList.size(); i++) {
			char[] word2 = readList.get(i).toString().toCharArray();
			Arrays.sort(word2);
			if((Arrays.equals(word1, word2)) && !(next.equals(readList.get(i)))) {
				anagList.add(readList.get(i));
			}
		}
		return next + ": " + anagList.toString();
	}

	public List<ArrayList<String>> getSortedByAnQty() {
		
		List<ArrayList<String>> returnList = new ArrayList<>();
		
		for(int i = 0; i < readList.size(); i++) {
			
			ArrayList<String> tempList = new ArrayList<>();
			char[] word1 = readList.get(i).toCharArray();
			Arrays.sort(word1);
			
			for(int j = 0; j <readList.size(); j++) {
				
				char[] word2 = readList.get(j).toCharArray();
				Arrays.sort(word2);
				
			if(Arrays.equals(word1, word2)) {
					tempList.add(readList.get(j));
				}
			}
			if(!returnList.contains(tempList)) {
				returnList.add(tempList);
			}
		}
		Collections.sort(returnList, new Comparator<List<String>>() {

			@Override
			public int compare(List<String> o1, List<String> o2) {
				Integer sizeO1 = o1.size();
				Integer sizeO2 = o2.size();
				return sizeO2.compareTo(sizeO1);
			}
        });
		
		
		return returnList;
	}
}  
