package com.revature.eval.java.core;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		String acronym = "";
		String[] strArr = phrase.split("[- ]");
		
		for (String s : strArr) 
			acronym = acronym.concat(s.substring(0, 1));
			
		return acronym.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (sideOne == sideTwo && sideTwo == sideThree && sideOne == sideThree);
		}

		public boolean isIsosceles() {
			return (sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree);
				
		}

		public boolean isScalene() {
			return(!this.isIsosceles());
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		
		int score = 0;
		string = string.toUpperCase();		
		
		for(int i = 0, s = string.length(); i < s; i++) {
						
			char c = string.charAt(i);					
			
			if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'L' || c == 'N' || c == 'R' || c == 'S' || c == 'T')
				score += 1;
			else if (c == 'D' || c == 'G')
				score +=2;
			else if (c == 'B' || c == 'C' || c == 'M' || c == 'P')
				score += 3;
			else if (c == 'F' || c == 'H' || c == 'V' || c == 'W' || c == 'Y')
				score += 4;
			else if (c == 'K')
				score += 5;
			else if (c == 'J' || c == 'X')
				score += 8;
			else if (c == 'Q' || c == 'Z')
				score += 10;
		}		
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		

		string = string.replaceAll("\\s+", "");
		
		string = string.replaceAll("\\.", "");
		
		string = string.replaceAll("[()-]","");
			
			if(string.length() > 10) {
				throw new IllegalArgumentException();
				
			}
			
			String[] strArr = string.split("[-.!@():a-zA-Z ]");		
			string = "";
			
			for (String s : strArr)	
				string = string.concat(s);		
	
			if(string.length() != 10)
				throw new IllegalArgumentException();
						
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		Map<String, Integer> wcMap = new java.util.HashMap<>();
		string = string.replace("\n","");
		String[] strArr = string.split("[,.;: ]");
		
		for(String s : strArr) {
			if(wcMap.get(s) == null) { wcMap.put(s, 1);} 
			else if (wcMap.containsKey(s)) { wcMap.put(s, wcMap.get(s) + 1);}			
		}		
		return wcMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {


		List<T> sortedList;

		@SuppressWarnings("unchecked")
		public int indexOf(T t) {
			int right = sortedList.size() - 1;
			int left = 0;
			
			while(left <= right) {
				int pivot = (right + left) / 2;
				
				if(((Comparable<T>) sortedList.get(pivot)).compareTo(t) == 0) {
					return pivot;
				} else 
					if(((Comparable<T>) sortedList.get(pivot)).compareTo(t)== 1) {
					right = pivot - 1;
				} else {
					left = pivot + 1;
				}
			}
			
			return -1;
		}
		
		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
		
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		String[] strArr = string.split(" ");
		
		String answer = "";
		String buffer = "test";
		
		abelay:
		for(String s : strArr){		
			
			if(s.charAt(0) == ' ')
				break abelay;
			
			
			if (!(s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u')) {
				
				for(int i = 0; i < s.length() - 1; i++) {
					if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u') {
					buffer = s.substring(0, i);
					s = s.substring(i, s.length());
					s = s + buffer + "ay";
					answer += s;
					break abelay;
				}
			}
	
			} else {
				s +="ay ";
				answer += s;
			}
				answer = answer.substring(0, answer.length()-1);				
			}
			return answer;
	}
						

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {

		Integer in = input;
		String inputString = in.toString();
		String[] strArr = inputString.split("");
		int power = strArr.length;
		int val = 0;
		int answer = 0;
		
		for (String s : strArr) {
			val = Integer.parseInt(s);
			val = (int) java.lang.Math.pow(val, power);
			answer += val;
			val = 0;
		}
		
		if(in == answer)
			return true;
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) throws IllegalArgumentException {

		long div = 2;
		List<Long> list = new ArrayList<>();
			
		while(l != 1) {
			if(l % div == 0 && (div % 2 != 0 || div == 2)){
				
				boolean prime = true;
				
				for(int n = 2; n <= (div / 2); n++) {
					
					if(div % n == 0)
						prime = false;
				}
				
				if(prime)
					list.add(div);
				l = l/div;
				div = 1;
			}
			
			div++;
		} 
		
		return list;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		
	
		@SuppressWarnings("unused")
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			String lower = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
			String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
			char[] input = string.toCharArray();
			String buffer = "";
			char c;
			int index;
			
			for (int i = 0; i < input.length; i++) {
				index = 0;
				if(Character.isLowerCase(input[i])) {
					c = input[i];
					index = lower.indexOf(c);
					buffer = buffer + lower.charAt(index + key);
		
				} else if(Character.isUpperCase(input[i])) {
										
					c = input[i];
					index = upper.indexOf(c);
					buffer = buffer + upper.charAt(index + key);
				
				} else {			
					
					c = input[i];
					buffer = buffer + c;
				}
			} 
			return buffer;
		}
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		/*
		int counter = 0;
		int p = 2;
		
		while (counter <!= i) {
			p++
			for(int j = 2; j <= p; j++) {
				if(p % j == 0) {
					break;
				}
			}
			
			if (i == p)
				counter++;
		}
		
		*/
		return 0;
	}
	

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
		
			String answer = "";
			
			string = string.replaceAll(" ", "");			
			char[] charArr = string.toCharArray();
			
			for(char c : charArr) {
				
				switch (c) {
					case 'a':
					case 'A': answer = answer.concat("z");
						continue;	
					case 'b':
					case 'B': answer = answer.concat("y");
						continue;
					case 'c':
					case 'C': answer = answer.concat("x");
						continue;
					case 'd': 
					case 'D': answer = answer.concat("w");
						continue;
					case 'e': 
					case 'E': answer = answer.concat("v");
						continue;						
					case 'f': 
					case 'F': answer = answer.concat("u");
						continue;
					case 'g': 
					case 'G': answer = answer.concat("t");												
						continue;
					case 'h': 
					case 'H': answer = answer.concat("s");
						continue;	
					case 'i': 
					case 'I': answer = answer.concat("r");
						continue;
					case 'j': 
					case 'J': answer = answer.concat("q");
						continue;					
					case 'k': 	
					case 'K': answer = answer.concat("p");
						continue;						
					case 'l': 
					case 'L': answer = answer.concat("o");
						continue;					
					case 'm': 
					case 'M': answer = answer.concat("n");
						continue;					
					case 'n': 
					case 'N': answer = answer.concat("m");
						continue;								
					case 'o': 
					case 'O': answer = answer.concat("l");
						continue;		
					case 'p': 
					case 'P': answer = answer.concat("k");
						continue;	
					case 'q': 
					case 'Q': answer = answer.concat("j");
						continue;
					case 'r':
					case 'R': answer = answer.concat("i");
						continue;	
					case 's': 
					case 'S': answer = answer.concat("h");
						continue;
					case 't': 
					case 'T': answer = answer.concat("g");
						continue;
					case 'u': 
					case 'U': answer = answer.concat("f");
						continue;
					case 'v': 
					case 'V': answer = answer.concat("e");
						continue;			
					case 'w': 
					case 'W': answer = answer.concat("d");
						continue;
					case 'x': 
					case 'X': answer = answer.concat("c");
						continue;
					case 'y': 
					case 'Y': answer = answer.concat("b");
						continue;
					case 'z': 
					case 'Z': answer = answer.concat("a");
						continue;
					case '0': answer = answer.concat("0");
						continue;
					case '1': answer = answer.concat("1");
						continue;
					case '2': answer = answer.concat("2");
						continue;
					case '3': answer = answer.concat("3");
						continue;
					case '4': answer = answer.concat("4");
						continue;
					case '5': answer = answer.concat("5");
						continue;
					case '6': answer = answer.concat("6");
						continue;
					case '7': answer = answer.concat("7");
						continue;
					case '8': answer = answer.concat("8");
						continue;
					case '9': answer = answer.concat("9");
				}
				
			}
			
			answer = answer.replaceAll(".....(?!$)", "$0 ");			
			return answer;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			String answer = "";
			string = string.replaceAll("[^A-Za-z0-9]+", "");			
			char[] charArr = string.toCharArray();
			
			for(char c : charArr) {
				
				switch (c) {
					case 'a':
					case 'A': answer = answer.concat("z");
						continue;	
					case 'b':
					case 'B': answer = answer.concat("y");
						continue;
					case 'c':
					case 'C': answer = answer.concat("x");
						continue;
					case 'd': 
					case 'D': answer = answer.concat("w");
						continue;
					case 'e': 
					case 'E': answer = answer.concat("v");
						continue;						
					case 'f': 
					case 'F': answer = answer.concat("u");
						continue;
					case 'g': 
					case 'G': answer = answer.concat("t");												
						continue;
					case 'h': 
					case 'H': answer = answer.concat("s");
						continue;	
					case 'i': 
					case 'I': answer = answer.concat("r");
						continue;
					case 'j': 
					case 'J': answer = answer.concat("q");
						continue;					
					case 'k': 	
					case 'K': answer = answer.concat("p");
						continue;						
					case 'l': 
					case 'L': answer = answer.concat("o");
						continue;					
					case 'm': 
					case 'M': answer = answer.concat("n");
						continue;					
					case 'n': 
					case 'N': answer = answer.concat("m");
						continue;								
					case 'o': 
					case 'O': answer = answer.concat("l");
						continue;		
					case 'p': 
					case 'P': answer = answer.concat("k");
						continue;	
					case 'q': 
					case 'Q': answer = answer.concat("j");
						continue;
					case 'r':
					case 'R': answer = answer.concat("i");
						continue;	
					case 's': 
					case 'S': answer = answer.concat("h");
						continue;
					case 't': 
					case 'T': answer = answer.concat("g");
						continue;
					case 'u': 
					case 'U': answer = answer.concat("f");
						continue;
					case 'v': 
					case 'V': answer = answer.concat("e");
						continue;			
					case 'w': 
					case 'W': answer = answer.concat("d");
						continue;
					case 'x': 
					case 'X': answer = answer.concat("c");
						continue;
					case 'y': 
					case 'Y': answer = answer.concat("b");
						continue;
					case 'z': 
					case 'Z': answer = answer.concat("a");
						continue;
					case '0': answer = answer.concat("0");
						continue;
					case '1': answer = answer.concat("1");
						continue;
					case '2': answer = answer.concat("2");
						continue;
					case '3': answer = answer.concat("3");
						continue;
					case '4': answer = answer.concat("4");
						continue;
					case '5': answer = answer.concat("5");
						continue;
					case '6': answer = answer.concat("6");
						continue;
					case '7': answer = answer.concat("7");
						continue;
					case '8': answer = answer.concat("8");
						continue;
					case '9': answer = answer.concat("9");
				}
			}		
			return answer;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		
		string = string.replaceAll("-","");
		String[] strArr = string.split(""); 
		String intString = "";
		boolean hasX = false;
		
		for (String s : strArr) {
			intString += s;
		}

		if(intString.length() != 10) 
			return false;
		
		if(intString.substring(0, 8).matches("^[0-9]+$")) {
			if(intString.substring(9).matches("^[0-9X]+$")) {
				if(intString.substring(9).matches("X"))
					hasX = true;
				
				if(hasX) {
					strArr = intString.substring(0, 9).split("");
				} else {
				strArr = intString.split("");
				}
								
				int multiplier = 10;
				int sum = 0;
				
				for (String r : strArr) {
					sum += (multiplier * Integer.parseInt(r));
					multiplier--;
				}	

				if(hasX) 
					sum += 10;
				if(sum % 11 == 0) {
					return true;
				}
			}
		}
	
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {

		if(string.length() < 1) {
			return false;
		}
		
		string = string.replaceAll("[ ]", "");	
		Map<Character, Integer> panagram = new java.util.HashMap<>();
		String[] strArr = string.split("");
		
		for (String s : strArr) {
			panagram.put(s.charAt(0), 1);
		}
		
		if(panagram.size() == 26)
			return true;
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		LocalDateTime answer = null;
		
		if(given instanceof LocalDate) {
			Instant pivot = ((LocalDate) given).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			answer = LocalDateTime.ofInstant(pivot, ZoneId.systemDefault());
		} else {
			answer = (LocalDateTime) given;
		}
				
		return answer.plusSeconds(1000000000);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		
		if (!string.replaceAll("\\s+", "").matches("[0-9]+")) 
			return false;
		
		string = string.replaceAll(" ","");		

		if(string.length() < 1) 
			return false;
		
		if(string.matches((".*[a-z].*")))
			return false;
		
		boolean flipper = false;
		int sum = 0;
				
		String[] strArr = string.split("");
		
		
		for(int i = string.length() - 1; i >= 0; i--) {
			int x = Integer.parseInt(string.substring(i, i + 1));
			if(flipper) {
				x *= 2;
				if(x > 9) {
					x -= 9;
				}
			}
			flipper ^= true;
			sum += x;
		}		
		return (sum % 10 == 0);
		
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {

		String add = "plus";
		String multiply = "multiplied";
		String divide = "divided";
		String sub = "minus";
		Integer x = null;
		Integer y = null;
		
		Pattern p = Pattern.compile("(-?[0-9]+)");
		
		Matcher m = p.matcher(string);
		
		m.find();
		x = Integer.parseInt(m.group(0));
		m.find();
		y = Integer.parseInt(m.group(1));	

		if (string.toLowerCase().indexOf(add.toLowerCase()) != -1 )
			return x + y;
		
		if (string.toLowerCase().indexOf(sub.toLowerCase()) != -1 )
			return x - y;
			
		if (string.toLowerCase().indexOf(multiply.toLowerCase()) != -1 ) 
			return x * y;
			
		if (string.toLowerCase().indexOf(divide.toLowerCase()) != -1 ) 
			return x / y;
		
		return 0;
		}
	
}