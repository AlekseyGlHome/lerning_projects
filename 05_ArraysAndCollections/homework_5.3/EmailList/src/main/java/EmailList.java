import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

    private final Set<String> emailDB = new TreeSet<>();

    public void add(String email) {
        if (isValidEmail(email)) {
            emailDB.add(email.toLowerCase());
        }
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public List<String> getSortedEmails() {
        return new ArrayList<>(emailDB);
    }

}
