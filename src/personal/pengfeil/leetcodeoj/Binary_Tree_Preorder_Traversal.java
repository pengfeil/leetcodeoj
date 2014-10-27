package personal.pengfeil.leetcodeoj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Binary_Tree_Preorder_Traversal {

	public static void main(String[] args) {
		Solution s = new Solution();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		List<Integer> list = s.preorderTraversal(root);
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
		public List<Integer> preorderTraversal(TreeNode root) {
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
					list.add(n.val);
					recordStack.push(2);
					stack.push(n);
					if (n.left != null) {
						stack.push(n.left);
						recordStack.push(1);
					}
				} else if (record == 2) {
					// Second pop, try push its right children into stack, and drop it
					if (n.right != null) {
						stack.push(n.right);
						recordStack.push(1);
					}
				}
			}
			return list;
		}
	}

}
