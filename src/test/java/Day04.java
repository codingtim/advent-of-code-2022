import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class Day04 {

    private static final Pattern PATTERN = Pattern.compile("([0-9]*)-([0-9]*),([0-9]*)-([0-9]*)");

    @Test
    void containsTest() {
        assertThat(overlapsCompletely(new Range(2, 4), new Range(6, 8))).isFalse();
        assertThat(overlapsCompletely(new Range(2, 3), new Range(4, 5))).isFalse();
        assertThat(overlapsCompletely(new Range(5, 7), new Range(7, 9))).isFalse();
        assertThat(overlapsCompletely(new Range(2, 8), new Range(3, 7))).isTrue();
        assertThat(overlapsCompletely(new Range(3, 7), new Range(2, 8))).isTrue();
        assertThat(overlapsCompletely(new Range(6, 6), new Range(4, 6))).isTrue();
        assertThat(overlapsCompletely(new Range(4, 6), new Range(6, 6))).isTrue();
        assertThat(overlapsCompletely(new Range(2, 6), new Range(4, 8))).isFalse();
    }

    public record Range(int from, int to) {
    }

    public record Pair(Range a, Range b) {
    }

    private boolean overlapsCompletely(Range range, Range range1) {
        if (range.from() <= range1.from() && range.to() >= range1.to()) {
            return true;
        } else if (range1.from() <= range.from() && range1.to() >= range.to()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean overlaps(Range range, Range range1) {
        return range.from <= range1.to && range.to >= range1.from;
    }

    @Test
    void part1() throws IOException {
        List<String> lines = readToList();
        long count = lines.stream()
                .map(this::toPair)
                .filter(pair -> overlapsCompletely(pair.a, pair.b))
                .count();
        System.out.println(count);
    }

    @Test
    void part2() throws IOException {
        List<String> lines = readToList();
        long count = lines.stream()
                .map(this::toPair)
                .filter(pair -> overlaps(pair.a, pair.b))
                .count();
        System.out.println(count);
    }

    private Pair toPair(String line) {
        Matcher matcher = PATTERN.matcher(line);
        matcher.matches();
        Range range1 = new Range(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        Range range2 = new Range(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        return new Pair(range1, range2);
    }

    private List<String> readToList() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = readFile()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private BufferedReader readFile() {
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("04")));
    }
}
