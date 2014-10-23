package personal.pengfeil.leetcodeoj;

public class Find_Minimum_in_Rotated_Sorted_Array {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] test1 = { 2, 3, 4, 5, 1 };
		int[] test2 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] test3 = { 3, 1, 2 };
		Solution s = new Solution();
		System.out.println(s.findMin(test1));
		System.out.println(s.findMin(test2));
		System.out.println(s.findMin(test3));
	}

	public static class Solution {
		public int findMin(int[] num) {
			if (num.length <= 0) {
				return Integer.MIN_VALUE;
			} else if (num.length <= 1) {
				return num[0];
			}
			if (num[0] < num[num.length - 1]) {
				return num[0];
			}
			int start = 0;
			int end = num.length - 1;
			int mid = (end - start) / 2;
			while (end > start) {
				if (num[mid] < num[mid + 1] && num[mid] > num[0]) {
					start = mid;
				} else if (num[mid] < num[mid + 1] && num[mid] < num[0]) {
					end = mid;
				} else {
					return num[mid + 1];
				}
				mid = start + (end - start) / 2;
			}
			return num[mid];
		}
	}
}
