package personal.pengfeil.leetcodeoj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pengfeil on 11/27/16.
 */
public class ArithmeticSlicesII {
    public static void main(String args[]) {
        ArithmeticSlicesII arithmeticSlicesII = new ArithmeticSlicesII();
//        System.out.println(arithmeticSlicesII.numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
        System.out.println(arithmeticSlicesII.numberOfArithmeticSlices1(new int[]{1, 2, 3, 4, 5, 6, 6, 7}));
//        System.out.println(arithmeticSlicesII.numberOfArithmeticSlices(new int[]{0, 2000000000, -294967296}));
    }

    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        for(int i=0;i<A.length;i++){
            for(int j=i;j<A.length;j++){
                if(A[i]>A[j]){
                    int tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                }
            }
        }
        List<List<Integer>> previousRecorder1 = new ArrayList<>();
        List<List<Integer>> previousRecorder2 = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < A.length; i++) {
            int ele = A[i];
            int trigger = i % 2;
            List<List<Integer>> listInUse = trigger == 0 ? previousRecorder1 : previousRecorder2;
            List<List<Integer>> listInTmp = trigger == 1 ? previousRecorder1 : previousRecorder2;
            // Add one empty list to be previous of A[i]
            for (List<Integer> list : listInUse) {
                int size = list.size();
                if (list.size() < 2
                        || (long) list.get(size - 1) - (long) list.get(size - 2)
                        == (long) ele - (long) list.get(size - 1)) {
                    List<Integer> newList = new ArrayList<>();
                    newList.addAll(list);
                    newList.add(A[i]);
                    listInTmp.add(newList);
                    if (newList.size() > 2) {
                        num++;
                    }
                }
                if (list.size() <= 2 || (long) list.get(size - 1) - (long) list.get(size - 2)
                        > (long) ele - (long) list.get(size - 1)) {
                    listInTmp.add(list);
                }
            }
            listInTmp.add(new ArrayList<Integer>() {{
                add(ele);
            }});
            listInUse.clear();

            for (List<Integer> ls : listInTmp) {
//                System.out.println(Arrays.toString(ls.toArray()));
            }
//            System.out.println();
        }

        return num;
    }

    public int numberOfArithmeticSlices1(int[] A) {
        int re = 0;
        HashMap<Integer, Integer>[] maps = new HashMap[A.length];
        for (int i = 0; i < A.length; i++) {
            maps[i] = new HashMap<>();
            int num = A[i];
            for (int j = 0; j < i; j++) {
                if ((long) num - A[j] > Integer.MAX_VALUE) continue;
                if ((long) num - A[j] < Integer.MIN_VALUE) continue;
                int diff = num - A[j];
                int count = maps[j].getOrDefault(diff, 0);
                maps[i].put(diff, maps[i].getOrDefault(diff, 0) + count + 1);
                re += count;
            }
        }
        return re;
    }

    /*
    P({1,2,3,4}, 1) = P({1,2,3}, 1) + 1 = 3
    P({1,2,3,4}, 2) = P({1,2}, 2) + 1 = 1
    P({1,2,3,4}, 3) = P({1}, 3) + 1 = 1
    P({1,2,3}, 1) = P({1,2}, 1) + 1 = 2
    P({1,2,3}, 2) = P({1}, 2) + 1 = 1
    P({1,2}, 1) = P({1}, 1) + 1 = 1
    p({1}, *) = 0;
     */
}
