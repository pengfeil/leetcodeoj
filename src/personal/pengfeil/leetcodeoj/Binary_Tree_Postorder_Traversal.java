package personal.pengfeil.leetcodeoj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Binary_Tree_Postorder_Traversal {

	public static void main(String[] args) {
		Solution s = new Solution();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		List<Integer> list = s.postorderTraversal(root);
		for (Integer i : list) {
			System.out.println(i);
		}
	}

	// Definition for binary tree
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return val + "";
		}

	}

	public static class Solution {
		public List<Integer> postorderTraversal(TreeNode root) {
			List<Integer> list = new ArrayList<Integer>();
			if (root == null) {
				return list;
			}
			LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
			LinkedList<Integer> recordStack = new LinkedList<Integer>();
			stack.push(root);
			recordStack.push(1);
			while (stack.size() > 0) {
				TreeNode n = stack.pop();
				int record = recordStack.pop();
				if (record == 1) {
					// First pop, push left children into stack
					recordStack.push(2);
					stack.push(n);
					if (n.left != null) {
						stack.push(n.left);
						recordStack.push(1);
					}
				} else if (record == 2) {
					// Second pop, try push its right children into stack
					recordStack.push(3);
					stack.push(n);
					if (n.right != null) {
						stack.push(n.right);
						recordStack.push(1);
					}
				} else if (record == 3) {
					// third pop, print it and drop it.
					list.add(n.val);
				}
			}
			return list;
		}
	}

}
