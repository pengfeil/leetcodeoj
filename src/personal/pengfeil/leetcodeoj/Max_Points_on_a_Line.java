package personal.pengfeil.leetcodeoj;

import java.util.HashMap;

public class Max_Points_on_a_Line {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.maxPoints(new Point[] { new Point(0, 10),
				new Point(0, 10), new Point(0, 2), new Point(1, 2) }));
	}

	static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static class Solution {
		public int maxPoints(Point[] points) {
			int max = 0;
			if (points.length == 1) {
				return 1;
			}
			for (int i = 0; i < points.length; i++) {
				HashMap<Double, Integer> map = new HashMap<Double, Integer>();
				int hNum = 0, vNum = 0, sNum = 0;
				for (int j = 0; j < points.length; j++) {
					if (i == j) {
						continue;
					}
					if (points[i].x == points[j].x
							&& points[i].y == points[j].y) {
						sNum++;
						vNum++;
						hNum++;
					} else if (points[i].x == points[j].x) {
						vNum++;
					} else if (points[i].y == points[j].y) {
						hNum++;
					} else {
						double ratio = (double) (points[i].y - points[j].y)
								/ (double) (points[i].x - points[j].x);
						if (map.containsKey(ratio)) {
							map.put(ratio, map.get(ratio).intValue() + 1);
						} else {
							map.put(ratio, 1);
						}
					}
				}
				int maxThroughThisPoint = 0;
				for(double num : map.keySet()){
					if(map.get(num) > maxThroughThisPoint){
						maxThroughThisPoint = map.get(num);
					}
				}
				maxThroughThisPoint += sNum;
				maxThroughThisPoint = hNum > maxThroughThisPoint ? hNum : maxThroughThisPoint;
				maxThroughThisPoint = vNum > maxThroughThisPoint ? vNum : maxThroughThisPoint;
				maxThroughThisPoint++;//Add self
				max = maxThroughThisPoint > max ? maxThroughThisPoint : max;
			}
			return max;
		}
	}

}
