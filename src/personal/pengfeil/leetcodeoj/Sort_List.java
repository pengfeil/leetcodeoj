package personal.pengfeil.leetcodeoj;

public class Sort_List {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode head = new ListNode(9);
		ListNode p = head;
		for (int i = 0; i < 10; i++) {
			p.next = new ListNode(i);
			p = p.next;
		}
		head = s.sortList(head);
		p = head;
		while (p != null && p.next != null) {
			if (p.val > p.next.val) {
				throw new RuntimeException("Not correctly sorted");
			}
			p = p.next;
		}
	}

	public static void print1(ListNode p) {
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
		private ListNode mergeSort(ListNode head, int length) {
			if (length <= 1) {
				return head;
			}
			int middle = length / 2;
			ListNode h1 = head;
			ListNode t1 = getNthNode(head, middle - 1);
			ListNode h2 = getNthNode(head, middle);
			ListNode t2 = getNthNode(head, length - 1);
			t2.next = null;// break to sub list
			t1.next = null;// break to sub list
			h1 = mergeSort(h1, middle);
			h2 = mergeSort(h2, length - middle);
			return merge(h1, h2);
		}

		/**
		 * Merge h2 into h1
		 * 
		 * @param h1
		 * @param h2
		 * @return
		 */
		private ListNode merge(ListNode h1, ListNode h2) {
			ListNode p1 = h1, p2 = h2;
			ListNode preNode1 = null;
			while (p1 != null && p2 != null) {
				if (p1.val > p2.val) {
					// Always move head node of p2 into p1
					ListNode nodeToInsert = p2;
					p2 = p2.next;
					if (preNode1 != null) {
						preNode1.next = nodeToInsert;
					} else {
						h1 = nodeToInsert;
					}
					nodeToInsert.next = p1;
					p1 = nodeToInsert;
				} else {
					preNode1 = p1;
					p1 = p1.next;
				}
			}
			if (p2 != null) {
				// Which means p1==null
				preNode1.next = p2;
			}
			return h1;
		}

		private int getLength(ListNode head) {
			int length = 0;
			ListNode p = head;
			while (p != null) {
				length++;
				p = p.next;
			}
			return length;
		}

		private ListNode getNthNode(ListNode head, int index) {
			ListNode p = head;
			for (int i = 1; i <= index; i++) {
				p = p.next;
			}
			return p;
		}

		public ListNode sortList(ListNode head) {
			ListNode p = head;
			int length = getLength(p);
			return mergeSort(p, length);
		}
	}

}
