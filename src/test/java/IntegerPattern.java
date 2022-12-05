import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerPattern {

    private final Pattern pattern;

    public IntegerPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    PatternResult match(String s) {
        Matcher matcher = pattern.matcher(s);
        matcher.matches();
        return new PatternResult(matcher);
    }

    public static class PatternResult {
        private final Matcher matcher;

        public PatternResult(Matcher matcher) {
            this.matcher = matcher;
        }

        public int getGroup(int group) {
            return Integer.parseInt(matcher.group(group));
        }
    }
}
