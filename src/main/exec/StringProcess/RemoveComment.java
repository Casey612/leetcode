package exec.StringProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveComment {

    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean mulFlag = false, inline = false;
        for (String line : source) {
            char[] array = line.toCharArray();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < array.length) {
                char c = array[i];
                if (!mulFlag) {
                    if (i + 1 < array.length) {
                        if (c == '/' && array[i + 1] == '/') {
                            break;
                        } else if (c == '/' && array[i + 1] == '*') {
                            mulFlag = true;
                            inline = true;
                            i += 2;
                        } else {
                            sb.append(c);
                            i++;
                        }
                    } else {
                        sb.append(c);
                        i++;
                    }
                } else {
                    //mulFlag = true
                    if (c == '*' && i + 1 < array.length && array[i + 1] == '/') {
                        mulFlag = false;
                        i += 2;
                        if (!inline) {
                            if (result.size() > 0) {
                                result.size();
                                String last = result.get(result.size() - 1);
                                result.remove(result.size() - 1);
                                sb.append(last);
                            }
                        }
                    } else {
                        i++;
                    }
                }
            }
            inline = false;
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RemoveComment comment = new RemoveComment();
        List<String> result = comment.removeComments(new String[]{
                "class test{", "public: ", "   int x = 1;", "   /*double y = 1;*/", "   char c;", "};"
        });
        System.out.println(Arrays.toString(result.toArray()));

    }
}
