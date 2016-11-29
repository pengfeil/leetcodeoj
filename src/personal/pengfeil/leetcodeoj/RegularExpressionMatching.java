package personal.pengfeil.leetcodeoj;

/**
 * Created by pengfeil on 11/26/16.
 * DP
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        RegularExpressionMatching wildcardMatching = new RegularExpressionMatching();
        System.out.println(!wildcardMatching.isMatch("aa", "a"));
        System.out.println(wildcardMatching.isMatch("aa", "aa"));
        System.out.println(!wildcardMatching.isMatch("aaa", "aa"));
        System.out.println(wildcardMatching.isMatch("aa", "a*"));
        System.out.println(wildcardMatching.isMatch("aa", ".*"));
        System.out.println(wildcardMatching.isMatch("ab", ".*"));
        System.out.println(wildcardMatching.isMatch("", ".*"));
        System.out.println(wildcardMatching.isMatch("aab", "c*a*b"));
        System.out.println(!wildcardMatching.isMatch("aaa", "ab*a"));
        System.out.println(wildcardMatching.isMatch("a", ".*."));
        System.out.println(wildcardMatching.isMatch("", "c*c*"));
        /*
        aa
        c*a*
        [c][aa] = 0
        [c][a] = 0
        [c*][aa] = 0
        [c*][a] = 0
        [c*][0] = 1
        [c*a][a] =
         */
    }

    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+", "*");
        if (p.length() > 0 && p.charAt(0) == '*') {
            return false;
        }

        if (p.length() == 0) {
            return s.length() == 0;
        }
        boolean[][] mark = new boolean[p.length() + 1][s.length() + 1];
        mark[0][0] = true;
        for (int i = 2; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                mark[i][0] = mark[i - 2][0];
            }
        }
        if(s.length() == 0){
            return mark[p.length()][0];
        }
        mark[1][0] = false;
        mark[0][1] = false;

        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                switch (p.charAt(i - 1)) {
                    case '.':
                        mark[i][j] = mark[i - 1][j - 1];
                        break;
                    case '*':
                        int curIndex = j + 1;
                        // Other case
                        do {
                            curIndex--;
                            if (mark[i - 2][curIndex]) {
                                mark[i][j] = true;
                                break;
                            }
                        } while (curIndex > 0
                                && (p.charAt(i - 2) == s.charAt(curIndex - 1) || p.charAt(i - 2) == '.'));
                        break;
                    default:
                        mark[i][j] = mark[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return mark[p.length()][s.length()];
    }
}
