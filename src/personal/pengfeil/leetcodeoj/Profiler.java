package personal.pengfeil.leetcodeoj;

public class Profiler {
    private static long startTime = -1;
    private static long endTime = -1;

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void end() {
        if (startTime == -1) {
            return;
        }
        endTime = System.currentTimeMillis();
        System.out.println("cost:" + (endTime - startTime) + "ms");
        startTime = endTime = -1;
    }
}
