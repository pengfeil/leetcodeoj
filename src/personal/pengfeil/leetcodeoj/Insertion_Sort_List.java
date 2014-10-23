package personal.pengfeil.leetcodeoj;

public class Insertion_Sort_List {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode head = new ListNode(1);
		ListNode p = head;
		for (int i = 2; i < 5; i++) {
			p.next = new ListNode(i);
			p = p.next;
		}
		head = s.insertionSortList(head);
		p = head;
		while (p != null && p.next != null) {
			if (p.val > p.next.val) {
				throw new RuntimeException("Not correctly sorted");
			}
			p = p.next;
		}
	}

	public static void print(ListNode p) {
		while (p != null) {
			System.out.print(p.val + ",");
			p = p.next;
		}
		System.out.println();
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static class Solution {
		public ListNode insertionSortList(ListNode head) {
			if (head == null) {
				return head;
			}
			ListNode p = head;
			ListNode nodeToInsert = p.next;
			ListNode preNodeToInsert = p;
			while (nodeToInsert != null) {
				print(head);
				// Insert nodeToInsert into proper position
				// Find position to insert
				ListNode tmpP = p;
				ListNode preNode = null;
				while (tmpP != nodeToInsert && tmpP.val <= nodeToInsert.val) {
					preNode = tmpP;
					tmpP = tmpP.next;
				}
				preNodeToInsert.next = nodeToInsert.next;// Make the node
				// isolated
				// Insert
				if (tmpP != null && tmpP != nodeToInsert) {
					if (preNode != null) {
						preNode.next = nodeToInsert;
					} else {
						head = p = nodeToInsert;// New head
					}
					nodeToInsert.next = tmpP;
					nodeToInsert = preNodeToInsert.next;
				} else {
					// No insertion, the node is in right position already.
					// Reconnect it.
					nodeToInsert.next = preNodeToInsert.next;
					preNodeToInsert.next = nodeToInsert;
					preNodeToInsert = nodeToInsert;
					nodeToInsert = nodeToInsert.next;
				}
			}
			return head;
		}
	}

}
