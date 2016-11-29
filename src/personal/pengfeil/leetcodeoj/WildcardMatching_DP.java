package personal.pengfeil.leetcodeoj;

/**
 * Created by pengfeil on 11/26/16.
 * DP
 */
public class WildcardMatching_DP {
    public static void main(String[] args) {
        WildcardMatching_DP wildcardMatching = new WildcardMatching_DP();
        System.out.println(!wildcardMatching.isMatch("aa", "a"));
        System.out.println(wildcardMatching.isMatch("aa", "aa"));
        System.out.println(!wildcardMatching.isMatch("aaa", "aa"));
        System.out.println(wildcardMatching.isMatch("aa", "*"));
        System.out.println(wildcardMatching.isMatch("aa", "a*"));
        System.out.println(wildcardMatching.isMatch("ab", "?*"));
        System.out.println(!wildcardMatching.isMatch("aab", "c*a*b"));
        System.out.println(wildcardMatching.isMatch("abefcdgiescdfimde", "ab*cd?i*de"));
        System.out.println(wildcardMatching.isMatch("", "*"));
        System.out.println(wildcardMatching.isMatch("ho", "ho**"));
        System.out.println(wildcardMatching.isMatch("hoasdasdasdas", "ho**"));
        System.out.println(wildcardMatching.isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
    }

    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+", "*");
        if (s.length() == 0 && p.length() > 0) {
            return p.equals("*");
        }
        if (p.length() == 0) {
            return s.length() == 0;
        }
        boolean[][] mark = new boolean[p.length() + 1][s.length() + 1];
        mark[0][0] = true;
        mark[1][0] = p.charAt(0) == '*';
        mark[0][1] = false;
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                switch (p.charAt(i - 1)) {
                    case '?':
                        mark[i][j] = mark[i - 1][j - 1];
                        break;
                    case '*':
                        int curIndex = j;
                        while (curIndex >= 0) {
                            if (mark[i - 1][curIndex]) {
                                mark[i][j] = true;
                                break;
                            }
                            curIndex--;
                        }
                        break;
                    default:
                        mark[i][j] = mark[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        return mark[p.length()][s.length()];
    }
}
