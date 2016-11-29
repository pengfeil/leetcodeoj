package personal.pengfeil.leetcodeoj;

/**
 * Created by pengfeil on 11/26/16.
 * brute force
 */
public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching wildcardMatching = new WildcardMatching();
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
        System.out.println(wildcardMatching.count1 + "-" + wildcardMatching.count2 + "-" + wildcardMatching.count3);
    }

    public boolean isMatch(String s, String p) {
        p = p.replaceAll("\\*+","*");
        return isMatch(s, 0, p, 0);
    }
    private int count1=0,count2=0,count3=0;

    private boolean isMatch(String s, int si, String p, int pi) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }

        if (si >= s.length()) {
            int i = pi;
            while (i < p.length() && p.charAt(i) == '*') {
                i++;
            }
            return i == p.length();
        }

        if ((si >= s.length() || pi >= p.length())) {
            return false;
        }
        char pChar = p.charAt(pi);
        char sChar = s.charAt(si);
        switch (pChar) {
            case '*':
                count1++;
                if (pi == p.length() - 1) {
                    return true;
                }
                for (int i = si; i < s.length(); i++) {
                    if (isMatch(s, i, p, pi + 1)) {
                        return true;
                    }
                }
                return false;
            case '?':
                count2++;
                return isMatch(s, si + 1, p, pi + 1);
            default:
                count3++;
                return pChar == sChar && isMatch(s, si + 1, p, pi + 1);
        }
    }
}
