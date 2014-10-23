package personal.pengfeil.leetcodeoj;

import java.util.HashMap;
import java.util.Map;

public class LRU_Cache {
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2048);
		Profiler.start();
		for (int i = 0; i < 20480; i++) {
			cache.set(i, i);
			cache.get(i - 1024);
		}
		Profiler.end();
	}

	public static class LRUCache {
		public class Node {
			Node next, pre;
			int key, val;

			public Node(int key, int val) {
				this.val = val;
				this.key = key;
			}
		}

		private Map<Integer, Node> cache;
		private Node head, tail;
		private int maxSize;

		public LRUCache(int capacity) {
			cache = new HashMap<Integer, Node>(capacity);
			maxSize = capacity;
			head = new Node(-1, -1);
			tail = new Node(-1, -1);
			head.next = tail;
			tail.pre = head;
		}

		public int get(int key) {
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				moveToTail(node);
				return node.val;
			} else {
				return -1;
			}
		}

		private void moveToTail(Node node) {
			// Isolate node
			isolate(node);

			// Move to tail
			append(node);
		}

		private void append(Node node) {
			tail.pre.next = node;
			node.pre = tail.pre;
			node.next = tail;
			tail.pre = node;
		}

		private void isolate(Node node) {
			Node pre = node.pre;
			Node next = node.next;
			node.next = node.pre = null;
			pre.next = next;
			next.pre = pre;
		}

		public void set(int key, int value) {
			Node newNode = new Node(key, value);
			if (cache.containsKey(key)) {
				Node node = cache.get(key);
				isolate(node);
			} else if (cache.size() < maxSize) {
			} else if (maxSize > 0) {
				Node nodeToRemove = head.next;
				isolate(nodeToRemove);
				cache.remove(nodeToRemove.key);
			} else {
				// Do nothing for capacity == 0
				return;
			}
			cache.put(key, newNode);
			append(newNode);
		}

		public void printAll() {
			Node p = head.next;
			while (p != tail) {
				System.out.print(p.key + ",");
				if (p != p.next) {
					p = p.next;
				} else {
					p = null;
				}
			}
			System.out.println();
		}
	}

}
