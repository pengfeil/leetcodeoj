package personal.pengfeil.leetcodeoj;

public class Reverse_Words_in_a_String {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.reverseWords("  the sky is blue  "));
	}

	public static class Solution {
		public String reverseWords(String s) {
			s = s.replaceAll(" +", " ");
			s = s.replaceAll("^ +", "");
			s = s.replaceAll(" +$", "");
			String[] words = s.split(" ");
			StringBuilder sb = new StringBuilder();
			for (int i = words.length - 1; i >= 0; i--) {
				sb.append(words[i]);
				if (i != 0) {
					sb.append(" ");
				}
			}
			return sb.toString();
		}
	}

}
