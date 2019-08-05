/*
 * FindAnagrams.java
 * Copyright (c) Raab Enterprises LLC 2019
 * @author Paul Raab
 * 
 * Description: This is an application to look for anagrams in a dictionary
 * file. This app uses a HashMap with a lowercase sorted string for each word as
 * the key. Therefore, a set of anagram matches can be found for a particular word 
 * quickly by using the hash key. Also, the hash table can be searched to get sets 
 * of anagrams of a particular count.
 * 
 */

package com.raab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.GZIPInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * FindAnagrams manages all general data structures and methods for
 * the Find Anagram program.
 */
public class FindAnagrams {

	private HashMap<String,ArrayList<String>> multiMap = new HashMap<String,ArrayList<String>>();

	public FindAnagrams() {
	}

	// Load a dictionary file to use for searching for anagrams
	public boolean setFileName(String fileName) {

		String inStr;
		String strKey;
		multiMap.clear();

		try {
			FileInputStream fis = new FileInputStream(fileName);
			GZIPInputStream gzipInputStream = new GZIPInputStream(fis);
			InputStreamReader isr = new InputStreamReader(gzipInputStream);
			BufferedReader br = new BufferedReader(isr);
			while ((inStr = br.readLine()) != null) {
				strKey = sortString(inStr);
				if (multiMap.get(strKey) == null) {
					ArrayList<String> strList = new ArrayList<String>();
					strList.add(inStr);
					multiMap.put(strKey, strList);
				}
				else {
					ArrayList<String> strList = multiMap.get(strKey);
					strList.add(inStr);
				}

			}
			br.close();
			isr.close();
			gzipInputStream.close();
			fis.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * Method to sort a string alphabetically
	 * to get the key. 
	 */
	public String sortString(String inStr) { 

		/*
		 * Convert input string to char array and
		 * if first characters upper case then lower it
		 */
		char tempArray[] = inStr.toCharArray();
		if (Character.isUpperCase(tempArray[0])) {
			tempArray[0] = Character.toLowerCase(tempArray[0]);
		}

		/*
		 * Sort tempArray 
		 */
		Arrays.sort(tempArray); 

		/*
		 * Return new sorted string 
		 */
		String strKey = new String(tempArray);
		return strKey;

	} 

	/*
	 * Find all anagrams for a given word
	 */
	public String getAnagrams(String getStr) {
		String outStr = "";
		boolean iFirst = true;
		String strKey = sortString(getStr);
		ArrayList<String> retStr = multiMap.get(strKey);
		if (retStr == null) {
			return outStr;
		}
		if (!(retStr.contains(getStr))) {
			return outStr;
		}
		int ilen = retStr.size();
		for (int j=0;j<ilen;j++) {
			if (!retStr.get(j).equals(getStr)) {
				if (iFirst) {
					outStr = "[" + retStr.get(j);
					iFirst = false;
				}
				else {
					outStr = outStr + "," + retStr.get(j);
				}
			}
		}

		if (!iFirst) {
			outStr = outStr + "]";
		}
		return outStr;
	}


	/*
	 * Endpoint that returns a count of words 
	 * in the corpus and min/max/median/average word length
	 */
	public String getStatistics() {

		int wordCount = 0;
		int wordMinLength = Integer.MAX_VALUE;
		int wordMaxLength = Integer.MIN_VALUE;
		long sum = 0;
		int mean = 0;
		int median = 0;

		/*
		 * Process input data
		 */
		ArrayList<Integer> lenArr = new ArrayList<Integer>();
		for (String key: multiMap.keySet()) {
			ArrayList<String> strList = multiMap.get(key);
			int size = strList.size();
			for (int j=0;j<size;j++) {
				wordCount++;
				int length = strList.get(j).length();
				wordMinLength = Math.min(wordMinLength, length);
				wordMaxLength = Math.max(wordMaxLength, length);
				sum += length;
				lenArr.add((Integer)length);
			}
		}

		/*
		 * Compute stats
		 */
		if (wordCount > 0) {
			mean = (int) (sum/wordCount);
			Collections.sort(lenArr);
			int imedian = wordCount / 2;
			median = lenArr.get(imedian);
		}
		String outStr = "\nDictionary statistics\n";
		outStr = outStr + "Word count = " + wordCount + "\n";
		outStr = outStr + "Minimum word length = " + wordMinLength + "\n";
		outStr = outStr + "Maximum word length = " + wordMaxLength + "\n";
		outStr = outStr + "Median = " + median + "\n";
		outStr = outStr + "Mean = " + mean + "\n";
		return outStr;

	}
	
	/*
	 * Endpoint to return all anagram groups of size == *x*
	 */
	public ArrayList<ArrayList<String>> eqAnagrams(int nVal) {

		ArrayList<ArrayList<String>> strAllList = new ArrayList<ArrayList<String>>();

		/*
		 * Process input data
		 */
		for (String key: multiMap.keySet()) {
			ArrayList<String> strList = multiMap.get(key);
			int size = strList.size();
			if (size == nVal) {
				strAllList.add(strList);
			}
		}
		return strAllList;

	}

	/*
	 * Endpoint that identifies words with the most anagrams
	 */
	public ArrayList<ArrayList<String>> mostAnagrams() {

		int cntAnagrams = 0;
		ArrayList<ArrayList<String>> strAllList = new ArrayList<ArrayList<String>>();

		/*
		 * Process hash data looking for largest number
		 * of anagrams for a key
		 */
		for (String key: multiMap.keySet()) {
			ArrayList<String> strList = multiMap.get(key);
			int size = strList.size();
			if (size > cntAnagrams) {
				cntAnagrams = size;
				strAllList.clear();
				strAllList.add(strList);
			}
			else if (size == cntAnagrams) {
				strAllList.add(strList);
			}
		}
		return strAllList;

	}

	//	Main application
	public static void main(String[] args) {
		FindAnagrams fa = new FindAnagrams();
		String fileName = "dictionary.txt.gz";
		boolean status = fa.setFileName(fileName);
		if (!status) {
			System.out.println("Error - reading dictionary file\n");
			System.exit(0);
		}
		String promptStr = "\nAnagram methods\n";
		promptStr = promptStr + "Enter 1 - to get anagram list == value\n";
		promptStr = promptStr + "Enter 2 - to get the maximum anagram list\n";
		promptStr = promptStr + "Enter 3 - to get anagrams for a word\n";
		promptStr = promptStr + "Enter 4 - to get dictionary stats\n";
		promptStr = promptStr + "Enter 5 - to exit\n";
		String valStr = "Enter value\n";
		String wordStr = "Enter word\n";
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(promptStr);
			while (scan.hasNext()) {
				String inStr = scan.nextLine();
				switch (inStr) {
				case "1":
					System.out.println(valStr);
					inStr = scan.nextLine();
					int iVal = Integer.parseInt(inStr);
					ArrayList<ArrayList<String>> geStringArray = fa.eqAnagrams(iVal);
					for(ArrayList<String> anagrams: geStringArray) {
						System.out.println(anagrams);
					};
					break;
				case "2":
					ArrayList<ArrayList<String>> mostStringArray = fa.mostAnagrams();
					for(ArrayList<String> anagrams: mostStringArray) {
						System.out.println(anagrams);
					};
					break;
				case "3":
					System.out.println(wordStr);
					inStr = scan.nextLine();
					System.out.println(fa.getAnagrams(inStr));
					break;
				case "4":
					System.out.println(fa.getStatistics());
					break;
				case "5":
					scan.close();
					System.out.println("Exit\n");
					System.exit(0);
					break;
				default:
					System.out.println("Exit\n");
					System.exit(0);
					break;
				}
				System.out.println(promptStr);
			}
		}

	}

}
