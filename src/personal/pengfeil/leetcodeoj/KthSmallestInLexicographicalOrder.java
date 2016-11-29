package personal.pengfeil.leetcodeoj;

/**
 * Created by pengfeil on 11/27/16.
 */
public class KthSmallestInLexicographicalOrder {
    public static void main(String args[]) {
        KthSmallestInLexicographicalOrder kthSmallestInLexicographicalOrder = new KthSmallestInLexicographicalOrder();
        System.out.println(kthSmallestInLexicographicalOrder.findKthNumber(131, 131));
    }

    /***
     *
     Main function:
     Firstly, calculate how many steps curr need to move to curr + 1.

     if the steps <= k, we know we can move to curr + 1, and narrow down k to k - steps.

     else if the steps > k, that means the curr + 1 is actually behind the target node x in the preorder path, we can't jump to curr + 1. What we have to do is to move forward only 1 step (curr * 10 is always next preorder node) and repeat the iteration.

     CalSteps function:
     how to calculate the steps between curr and curr + 1?
     Here we come up a idea to calculate by level.
     Let n1 = curr, n2 = curr + 1.
     n2 is always the next right node beside n1's right most node (who shares the same ancestor "curr")
     (refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is right next to 199).

     so, if n2 <= n, what means n1's right most node exists, we can simply add the number of nodes from n1 to n2 to steps.

     else if n2 > n, what means n (the biggest node) is on the path between n1 to n2, add (n + 1 - n1) to steps.

     organize this flow to "steps += Math.min(n + 1, n2) - n1; n1 *= 10; n2 *= 10;"
     * @param n
     * @param k
     * @return
     */

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            int steps = calSteps(n, curr, curr + 1);
            if (steps <= k) {
                curr += 1;
                k -= steps;
            } else {
                curr *= 10;
                k -= 1;
            }
        }
        return curr;
    }

    //use long in case of overflow
    public int calSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }
}
