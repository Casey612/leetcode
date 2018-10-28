package contest_108;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: yuki
 * @date: 2018/10/28
 */
public class UniqueEmail {

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            email = email.toLowerCase();
            set.add(process(email));
        }
        return set.size();
    }

    private String process(String email) {
        String[] array = email.split("@");
        String name = array[0], address = array[1];
        if (name.contains("+")) {
            name = name.substring(0, name.indexOf("+"));
        }
        if (name.contains(".")) {
            name = name.replaceAll(".", "");
        }
        System.out.println(name + address);
        return name + address;
    }

    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com",
                "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};
        UniqueEmail test = new UniqueEmail();
        System.out.println(test.numUniqueEmails(emails));
    }

}
