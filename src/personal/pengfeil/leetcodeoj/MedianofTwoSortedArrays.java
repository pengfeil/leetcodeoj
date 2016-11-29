package personal.pengfeil.leetcodeoj;

/**
 * Created by pengfeil on 11/29/16.
 */
public class MedianofTwoSortedArrays {
    public static void main(String[] args) {
        MedianofTwoSortedArrays medianofTwoSortedArrays = new MedianofTwoSortedArrays();
        System.out.println(2 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 3},
                new int[]{2}));
        System.out.println(2.5 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4}));
        System.out.println(2 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 1, 3, 3},
                new int[]{1, 1, 3, 3}));
        System.out.println(2 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2, 3},
                new int[]{1, 2}));
        System.out.println(2 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2, 3},
                new int[]{}));
        System.out.println(2.5 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2, 3, 4},
                new int[]{}));
        System.out.println(1.5 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{1, 2}));
        System.out.println(1 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{1, 1}));
        System.out.println(2.5 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{2},
                new int[]{1, 3, 4}));
        System.out.println(1.5 - medianofTwoSortedArrays.findMedianSortedArrays(
                new int[]{2},
                new int[]{1}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 0) {
            return (findKthItem(nums1, nums2, 0, 0, totalLength / 2)
                    + findKthItem(nums1, nums2, 0, 0, totalLength / 2 + 1)) / 2;
        } else {
            return findKthItem(nums1, nums2, 0, 0, totalLength / 2 + 1);
        }
    }

    private double findKthItem(int[] nums1, int[] nums2, int s1, int s2, int k) {
        if (s1 == nums1.length && s2 == nums2.length) {
            return -1;
        }
        if (s1 == nums1.length && s2 < nums2.length) {
            return k - 1 < nums2.length ? nums2[s2 + k - 1] : -1;
        }
        if (s2 == nums2.length && s1 < nums1.length) {
            return k - 1 < nums1.length ? nums1[s1 + k - 1] : -1;
        }
        if (k == 1) {
            int min1 = s1 < nums1.length ? nums1[s1] : Integer.MAX_VALUE;
            int min2 = s2 < nums2.length ? nums2[s2] : Integer.MAX_VALUE;
            return Math.min(min1, min2);
        }
        int inc1 = s1 + k / 2 <= nums1.length ? k / 2 : nums1.length - s1;
        int inc2 = s2 + k / 2 <= nums2.length ? k / 2 : nums2.length - s2;
        int divider1 = nums1[s1 + inc1 - 1];
        int divider2 = nums2[s2 + inc2 - 1];
        if (divider1 < divider2) {
            return findKthItem(nums1, nums2, s1 + inc1, s2, k - inc1);
        } else if (divider2 < divider1) {
            return findKthItem(nums1, nums2, s1, s2 + inc2, k - inc2);
        } else {
            if (inc1 + inc2 == k) {
                if (k % 2 == 0) {
                    return Math.max(nums1[s1 + inc1 - 1], nums2[s2 + inc2 - 1]);
                } else {
                    return Math.min(nums1[s1 + inc1], nums2[s2 + inc2]);
                }
            } else {
                return findKthItem(nums1, nums2, s1 + inc1, s2 + inc2, k - inc1 - inc2);
            }
        }
    }
}
