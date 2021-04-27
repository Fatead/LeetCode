package string;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmails {

    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] split = email.split("@");
            String localName = split[0].split("\\+")[0];
            localName = localName.replace(".","");
            String finalAddress = localName +"@" + split[1];
            set.add(finalAddress);
        }
        return set.size();
    }


    public static void main(String[] args) {
        UniqueEmails uniqueEmails = new UniqueEmails();
        String[] mails = {"test.email+alex@leetcode.com","test.email.leet+alex@code.com"};
        System.out.println(uniqueEmails.numUniqueEmails(mails));
    }

}
