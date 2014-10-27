package personal.pengfeil.leetcodeoj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Word_Break_II {

	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("aa");
		set.add("b");
		Profiler.start();
		List<String> result = wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", set);
		Profiler.end();
		for (String s : result) {
			System.out.println(s);
		}
	}

	public static List<String> wordBreak(String s, Set<String> dict) {
		if (s.length() <= 0) {
			return new LinkedList<String>();
		}
		// For every position, use a list to remember all last cut point.
		List<LinkedList<Integer>> cache = new LinkedList<LinkedList<Integer>>();
		for (int i = 0; i < s.length(); i++) {
			cache.add(new LinkedList<Integer>());
		}
		// Find every 'last cut points' for every position.
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				List<Integer> cachedResult = cache.get(j);
				if (cachedResult.size() > 0) {
					String word = s.substring(j + 1, i + 1);
					if (dict.contains(word)) {
						cache.get(i).add(j);
					}
				}
			}
			String word = s.substring(0, i + 1);
			if (dict.contains(word)) {
				cache.get(i).add(-1);
			}
		}
		// Assemble all sentences
		List<String> result = new LinkedList<String>();
		LinkedList<Integer> indexs = new LinkedList<Integer>();
		LinkedList<LinkedList<Integer>> stack = new LinkedList<LinkedList<Integer>>();
		stack.push(cache.get(s.length() - 1));
		indexs.push(s.length() - 1);
		while (stack.size() > 0) {
			LinkedList<Integer> cutPoints = stack.peek();
			if (cutPoints.size() > 0) {
				int lastCp = cutPoints.pop();// Always get the first one.
				if (lastCp >= 0) {
					LinkedList<Integer> tmp = new LinkedList<Integer>(
							cache.get(lastCp));
					stack.push(tmp);
					indexs.push(lastCp);
				} else {
					// Reach the leaf
					StringBuilder sb = new StringBuilder();
					int start = -1;
					for (int i = 0; i < indexs.size(); i++) {
						int end = indexs.get(i);
						if (start != -1) {
							sb.append(" ");
						}
						sb.append(s.substring(start + 1, end + 1));
						start = end;
					}
					result.add(sb.toString());
				}
			} else {
				stack.pop();
				indexs.pop();
			}
		}
		return result;
	}
	public List<String> wordBreak1(String s, Set<String> dict) {
	    if(s.length() <= 0){
			return new ArrayList<String>();
		}
		ArrayList<List<String>> cache = new ArrayList<List<String>>(s.length());
		for (int i = 0; i < s.length(); i++) {
			cache.add(new ArrayList<String>());
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				List<String> cachedResult = cache.get(j);
				if (cachedResult.size() > 0) {
					String word = s.substring(j + 1, i + 1);
					if (dict.contains(word)) {
						ArrayList<String> newSet = new ArrayList<String>(
								cachedResult);
						for (String sentence : newSet) {
							cache.get(i).add(sentence + " " + word);
						}
					}
				}
			}
			String word = s.substring(0, i + 1);
			if (dict.contains(word)) {
				cache.get(i).add(word);
			}
		}
		return cache.get(s.length() - 1);
	}
}

