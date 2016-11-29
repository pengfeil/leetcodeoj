package personal.pengfeil.leetcodeoj;

import java.util.*;

/**
 * Created by pengfeil on 11/25/16.
 */
public class WordLadderII2 {
    public static void main(String[] args) {
        WordLadderII2 wordLadderII = new WordLadderII2();
//        List<List<String>> result = wordLadderII.findLadders("hit", "cog", new HashSet<String>() {{
//            add("hot");
//            add("dot");
//            add("dog");
//            add("lot");
//            add("log");
//        }});
        List<List<String>> result = wordLadderII.findLadders("a", "c", new HashSet<String>() {{
            add("a");
            add("b");
            add("c");
        }});


        for (List<String> path : result) {
            System.out.println(Arrays.toString(path.toArray()));
        }
    }

    /**
     * Using BSF to seatch the shortest path
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        Map<String, List<String>> previousNodeRecord = new HashMap<>();

        wordList.add(endWord);
        wordList.remove(beginWord);

        for (String word : wordList) {
            previousNodeRecord.put(word, new ArrayList<>());
        }

        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        int queueSwitcher = 0;
        queue1.add(beginWord);

        int minPathLength2End = Integer.MAX_VALUE;
        int currentPathLength = 0;

        while ((!queue1.isEmpty() || !queue2.isEmpty()) && (currentPathLength <= minPathLength2End)) {
            Queue<String> fromQueue = queueSwitcher % 2 == 0 ? queue1 : queue2;
            Queue<String> toQueue = queueSwitcher % 2 == 0 ? queue2 : queue1;
            String root = fromQueue.poll();
            System.out.println(root);
            for (int i = 0; i < root.length(); i++) {
                StringBuilder stringBuilder = new StringBuilder(root);
                char beforeC = root.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    stringBuilder.setCharAt(i, c);
                    String newWord = stringBuilder.toString();
                    // If it's in dictionary
                    if (wordList.contains(newWord) && !visited.contains(newWord)) {
                        System.out.println("-" + newWord);
                        if (newWord.equals(endWord)) {
                            if (minPathLength2End >= currentPathLength) {
                                previousNodeRecord.get(newWord).add(root);
                                minPathLength2End = currentPathLength;
                            }
                        } else {
                            previousNodeRecord.get(newWord).add(root);
                        }
                        if (!toQueue.contains(newWord)) {
                            toQueue.add(newWord);
                        }
                    }
                    stringBuilder.setCharAt(i, beforeC);
                }
            }

            if (fromQueue.isEmpty()) {
                visited.addAll(toQueue);
                queueSwitcher++;
                currentPathLength++;
            }
            System.out.println(Arrays.toString(fromQueue.toArray()));
            System.out.println(Arrays.toString(toQueue.toArray()));
            System.out.println(Arrays.toString(visited.toArray()));
            System.out.println();
        }

        return backtrackPath(endWord, previousNodeRecord);
    }

    private List<List<String>> backtrackPath(String endWord, Map<String, List<String>> previousNodeRecord) {
        List<String> previousNodes = previousNodeRecord.get(endWord);
        List<List<String>> resultList = new ArrayList<>();
        if (previousNodes == null) {
            resultList.add(new ArrayList<String>() {{
                add(endWord);
            }});
        } else {
            for (String previousNode : previousNodes) {
                List<List<String>> previousResult = backtrackPath(previousNode, previousNodeRecord);
                for (List<String> path : previousResult) {
                    path.add(endWord);
                }
                resultList.addAll(previousResult);
            }
        }
        return resultList;
    }
}
