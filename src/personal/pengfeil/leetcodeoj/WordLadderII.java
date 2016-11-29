package personal.pengfeil.leetcodeoj;

import java.util.*;

/**
 * Created by pengfeil on 11/25/16.
 */
public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        List<List<String>> result = wordLadderII.findLadders("hit", "cog", new HashSet<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
        }});


        for (List<String> path : result) {
            System.out.println(Arrays.toString(path.toArray()));
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord) || isOneCharDiff(beginWord, endWord)) {
            return new ArrayList<List<String>>() {{
                add(new ArrayList<String>() {{
                    add(beginWord);
                    add(endWord);
                }});
            }};
        }
        // Transfor the data structure
        WordNode start = new WordNode(beginWord);
        WordNode end = new WordNode(endWord);
        Map<String, WordNode> nodeMap = new HashMap<>();
        for (String word : wordList) {
            nodeMap.put(word, new WordNode(word));
        }
        nodeMap.put(beginWord, start);
        nodeMap.put(endWord, end);
        // Build the transition map
        buildMapConnections(nodeMap);

        // Calculate the shortest path

        List<String> allWords = new ArrayList<>();
        allWords.addAll(nodeMap.keySet());
        allWords.remove(beginWord);
        List<Integer>[] pathRecorder = new List[allWords.size()];
        int[] lengthRecorder = new int[allWords.size()];
        int endWordIndex = allWords.indexOf(endWord);
        for (int i = 0; i < lengthRecorder.length; i++) {
            if (nodeMap.get(allWords.get(i)).contains(start)) {
                lengthRecorder[i] = 1;
            } else {
                lengthRecorder[i] = Integer.MAX_VALUE;
            }
            pathRecorder[i] = new ArrayList<Integer>();
        }
        Set<Integer> markedSet = new HashSet<>();
        while (markedSet.size() < allWords.size()) {
            int min = Integer.MAX_VALUE;
            int i = -1;
            for (int n = 0; n < lengthRecorder.length; n++) {
                if (min > lengthRecorder[n] && !markedSet.contains(n)) {
                    i = n;
                    min = lengthRecorder[n];
                }
            }
            markedSet.add(i);
            if (i == -1) {
                break;
            }
            for (int j = 0; j < lengthRecorder.length; j++) {
                // If j->start and i->j are connected
                if (nodeMap.get(allWords.get(i)).contains(
                        nodeMap.get(allWords.get(j)))) {
                    if (lengthRecorder[i] + 1 < lengthRecorder[j]) {
                        lengthRecorder[j] = lengthRecorder[i] + 1;
                        pathRecorder[j].clear();
                        pathRecorder[j].add(i);
                    } else if (lengthRecorder[i] + 1 == lengthRecorder[j]) {
                        pathRecorder[j].add(i);
                    }
                }
            }
        }

        return constructPaths(endWordIndex, beginWord, lengthRecorder, pathRecorder, allWords);
    }

    private List<List<String>> constructPaths(int endIndex, String beginWord, int[] lengthRecorder, List<Integer>[] pathRecorder, List<String> allWords) {
        List<Integer> previousIndexs = pathRecorder[endIndex];
        if (previousIndexs.isEmpty() && lengthRecorder[endIndex] < Integer.MAX_VALUE) {
            List<List<String>> paths = new ArrayList<>();
            paths.add(new LinkedList<String>() {{
                add(beginWord);
                add(allWords.get(endIndex));
            }});
            return paths;
        }
        List<List<String>> results = new ArrayList<>();
        for (int previousIndex : previousIndexs) {
            results.addAll(constructPaths(previousIndex, beginWord, lengthRecorder, pathRecorder, allWords));
        }
        if(lengthRecorder[endIndex] < Integer.MAX_VALUE) {
            for (List<String> path : results) {
                path.add(allWords.get(endIndex));
            }
        }
        return results;
    }

    private void buildMapConnections(Map<String, WordNode> nodeMap) {
        for (Map.Entry<String, WordNode> entryA : nodeMap.entrySet()) {
            for(int i=0;i<entryA.getKey().length();i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    StringBuilder stringBuilder = new StringBuilder(entryA.getKey());
                    stringBuilder.setCharAt(i, c);
                    String potentialKey = stringBuilder.toString();
                    if (nodeMap.containsKey(stringBuilder.toString())) {
                        entryA.getValue().addConnection(nodeMap.get(potentialKey));
                        nodeMap.get(potentialKey).addConnection(entryA.getValue());
                    }
                }
            }
        }
    }

    private boolean isOneCharDiff(String strA, String strB) {
        if (strA.length() != strB.length()) {
            return false;
        }
        if (strA.equals(strB)) {
            throw new RuntimeException("Dictionary words can't be the duplicated");
        }
        int count = 0;
        for (int i = 0; i < strA.length(); i++) {
            if (strA.charAt(i) != strB.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    private class WordNode {
        private Set<WordNode> connections;
        private String word;

        public WordNode(String word) {
            this.word = word;
            this.connections = new HashSet<>();
        }

        public void addConnection(WordNode nodeToAdd) {
            this.connections.add(nodeToAdd);
        }

        public boolean contains(WordNode node) {
            return connections.contains(node);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WordNode wordNode = (WordNode) o;

            return word.equals(wordNode.word);

        }

        @Override
        public int hashCode() {
            return word.hashCode();
        }
    }
}
