package personal.pengfeil.leetcodeoj;

import java.util.LinkedList;

public class Maximum_Product_Subarray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.maxProduct(new int[] { 0,2,0,2 }));
	}

	public static class Solution {
		public int maxProduct(int[] A) {
			LinkedList<Integer> results = new LinkedList<Integer>();
			LinkedList<Integer> zeroIndexs = new LinkedList<Integer>();
			boolean allZero = true;
			for (int i = 0; i < A.length; i++) {
				if (A[i] == 0) {
					zeroIndexs.add(i);
				}
				if(A[i] != 0){
					allZero = false;
				}
			}
			if(allZero){
				return 0;
			}
			if (zeroIndexs.size() > 0) {
				int lastStart = 0;
				for (int i = 0; i < zeroIndexs.size(); i++) {
					results.push(maxProductInNonZero(A, lastStart,
							zeroIndexs.get(i)));
					lastStart = zeroIndexs.get(i) + 1;
				}
				results.push(maxProductInNonZero(A, lastStart, A.length));
				int max = Integer.MIN_VALUE;
				for (int r : results) {
					if (r > max) {
						max = r;
					}
				}
				return max > 0 ? max : 0;
			} else {
				return maxProductInNonZero(A, 0, A.length);
			}
		}

		private int maxProductInNonZero(int[] A, int start, int end) {
			System.out.println(start + " ~ " + end);
			int length = end - start;
			if (length <= 0) {
				return Integer.MIN_VALUE;
			} else if (length == 1) {
				return A[start];
			}
			int negativeCount = 0;
			for (int i = start; i < end; i++) {
				if (A[i] < 0) {
					negativeCount++;
				}
			}
			int totalProduct = 1;
			for (int i = start; i < end; i++) {
				totalProduct *= A[i];
			}
			if (negativeCount % 2 == 0) {
				System.out.println(start + " ~ " + end + "=" + totalProduct);
				return totalProduct;
			} else {
				int lastNegativeIndex = -1;
				int firstNegativeIndex = -1;
				for (int i = end-1; i >= start; i--) {
					if (A[i] < 0) {
						lastNegativeIndex = i;
						break;
					}
				}
				for (int i = start; i < end; i++) {
					if (A[i] < 0) {
						firstNegativeIndex = i;
						break;
					}
				}
				int max1 = 1;
				int max2 = 1;
				for (int i = start; i < lastNegativeIndex; i++) {
					max1 *= A[i];
				}
				for (int i = firstNegativeIndex + 1; i < end; i++) {
					max2 *= A[i];
				}
				int max = max1 > max2 ? max1 : max2;
				System.out.println(start + " ~ " + end + "=" + max);
				return max;
			}
		}
	}

}
