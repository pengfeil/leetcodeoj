package personal.pengfeil.leetcodeoj;

/**
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn, reorder it to:
 * L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
 * 
 */
public class Reorder_List {

	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		s.reorderList(head);
		ListNode p = head;
		while (p != null) {
			System.out.println(p.val);
			p = p.next;
		}
	}

	// Definition for singly-linked list.
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
		@Override
		public String toString() {
			return val + "";
		}
	}

	public static class Solution {
		public void reorderList(ListNode head) {
			int length = 0;
			ListNode p = head;
			while (p != null) {
				length++;
				p = p.next;
			}
			int middle = length / 2 + (length % 2 == 0 ? 0 : 1);
			ListNode midHead = head;
			while (middle > 0) {
				middle--;
				// Break the list to two list
				ListNode tmp = midHead.next;
				if (middle == 0) {
					midHead.next = null;
				}
				midHead = tmp;
			}
			//Reverse the midHead list
			ListNode preP = null;
			p = midHead;
			while(p != null){
				ListNode tmp = p.next;
				p.next = preP;
				preP = p;
				p= tmp;
			}
			midHead = preP;
			// Insert midHead list into head list;
			ListNode p1 = head;
			ListNode p2 = midHead;
			while (p1 != null) {
				// Insert p2 after p1
				ListNode p1NTmp = p1.next;
				if (p2 != null) {
					ListNode p2NTmp = p2.next;
					p1.next = p2;
					p2.next = p1NTmp;
					p2 = p2NTmp;
				}
				p1 = p1NTmp;
			}
		}
	}

}
