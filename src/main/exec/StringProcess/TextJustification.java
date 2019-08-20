package exec.StringProcess;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0 || maxWidth <= 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        int index = 0, lastIndex;
        while (index < words.length) {
            lastIndex = index;
            int tempLength = 0;
            while (tempLength <= maxWidth && index < words.length) {
                tempLength += words[index].length();
                //留一个空格
                tempLength++;
                index++;
            }
            if (tempLength > (maxWidth + 1)) {
                index--;
            }
            String part = getString(words, lastIndex, index, maxWidth);
            result.add(part);
        }
        return result;
    }
    
    private String getString(String[] words, int startIndex, int endIndex, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int charCount = 0;
        for (int index = startIndex; index < endIndex; index++) {
            charCount += words[index].length();
        }
        int wordCount = endIndex - startIndex;
        int left = maxWidth - charCount;
        
        if (wordCount == 1) {
            //one word left-justified
            sb.append(words[startIndex]);
            while (left > 0) {
                sb.append(' ');
                left--;
            }
        } else if (endIndex == words.length) {
            //last line left-justified
            for (int index = startIndex; index < endIndex; index++) {
                sb.append(words[index]);
                if (index != (endIndex - 1)){
                    sb.append(' ');
                    left--;
                }
            }
            while (left > 0) {
                sb.append(' ');
                left--;
            }
        } else {
            int avg = left / (wordCount - 1);
            int mod = left % (wordCount - 1);
            
            for (int index = startIndex; index < endIndex; index++) {
                sb.append(words[index]);
                if (index != endIndex - 1) {
                    int temp = avg;
                    while (temp > 0) {
                        sb.append(' ');
                        temp--;
                    }
                    if (mod > 0) {
                        sb.append(' ');
                        mod--;
                    }
                }
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        TextJustification justification = new TextJustification();
        List<String> r1 = justification.fullJustify(new String[] {"This", "is", "an", "example", "of", "text",
         "justification."}, 16);
        printArray(r1);

        List<String> r2 = justification.fullJustify(new String[] {"What","must","be","acknowledgment","shall",
         "be"}, 16);
        printArray(r2);
        
        List<String> r3 = justification.fullJustify(
            new String[] {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
                "computer.", "Art", "is", "everything", "else", "we", "do"}, 20);
        printArray(r3);
    
        List<String> r4 = justification.fullJustify(
            new String[] {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"}, 16);
        printArray(r4);
    }
    
    public static void printArray(List<String> array) {
        for (String s : array) {
            System.out.println(s);
        }
    }
    
}
