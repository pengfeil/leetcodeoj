package personal.pengfeil.leetcodeoj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pengfeil on 11/25/16.
 */
public class TextJustification {
    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        List<String> result = textJustification.fullJustify(
                //new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
                new String[]{"What", "must", "be", "shall", "be."}, 12);
        for (String line : result) {
            System.out.println("'" + line + "'");
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int lineLength = -1; //Remove the space ahead of first word
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // Each word is prefixed with a space
            if (lineLength + word.length() + 1 <= maxWidth) {
                // append the word into this line
                line.add(word);
                lineLength += word.length() + 1;
            } else {
                // Pop current word and construct a result line.
                i--;
                lines.add(constructTextLine(maxWidth, lines, line));
                //Reset
                line.clear();
                lineLength = -1;
            }
        }
        if (line.size() > 0) {
            lines.add(constructTextLine(maxWidth, lines, line));
        }
        // Handle the last line
        String lastLine = lines.get(lines.size() - 1);
        lastLine = lastLine.replaceAll("\\s+", " ");
        int lastLineLength = lastLine.length();
        for (int i = 0; i < maxWidth - lastLineLength; i++) {
            lastLine = lastLine + " ";
        }
        lines.remove(lines.size() - 1);
        lines.add(lastLine);
        return lines;
    }

    private String constructTextLine(int maxWidth, List<String> lines, List<String> line) {
        StringBuilder stringBuilder = new StringBuilder();
        if (line.size() == 1) {
            stringBuilder.append(line.get(0));
            for (int j = 0; j < maxWidth - line.get(0).length(); j++) {
                stringBuilder.append(' ');
            }
        } else {
            int spaceSlots = line.size() - 1;
            long pureTextLength = line.stream().collect(Collectors.summarizingInt(w -> w.length())).getSum();
            long avgSpaceBetweenWords = (maxWidth - pureTextLength) / spaceSlots;
            long additionalSpaces = maxWidth - pureTextLength - spaceSlots * avgSpaceBetweenWords;
            for (int j = 0; j < line.size(); j++) {
                stringBuilder.append(line.get(j));
                if (j != line.size() - 1) {
                    for (int m = 0; m < avgSpaceBetweenWords; m++) {
                        stringBuilder.append(' ');
                    }
                }
                if (additionalSpaces > 0) {
                    stringBuilder.append(' ');
                    additionalSpaces--;
                }
            }
            for (int m = 0; m < maxWidth - stringBuilder.length(); m++) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }
}
