package personal.pengfeil.leetcodeoj;

import java.util.LinkedList;

public class Evaluate_Reverse_Polish_Notation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.evalRPN(new String[]{
				"2", "1", "+", "3", "*"
		}));
	}

	public static class Solution {
		public int evalRPN(String[] tokens) {
			LinkedList<Integer> stack = new LinkedList<Integer>();
			for(String token: tokens){
				if(token.matches("\\+|\\-|\\*|/")){
					int v2 = stack.pop();
					int v1 = stack.pop();
					if(token.equals("+")){
						stack.push(v1 + v2);
					} else if(token.equals("-")){
						stack.push(v1 - v2);
					} else if(token.equals("*")){
						stack.push(v1 * v2);
					} else if(token.equals("/")){
						stack.push(v1 / v2);
					}
				} else {
					stack.push(Integer.parseInt(token));
				}
			}
			return stack.pop();
		}
	}

}
