import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicIntegerFieldUpdater;

class Day05 {

    public static final IntegerPattern INTEGER_PATTERN = new IntegerPattern(Pattern.compile("move ([0-9]*) from ([0-9]*) to ([0-9]*)"));
    private final List<Deque<Character>> stacks = List.of(
            deque("BQC".toCharArray()),
            deque("RQWZ".toCharArray()),
            deque("BMRLV".toCharArray()),
            deque("CZHVTW".toCharArray()),
            deque("DZHBNVG".toCharArray()),
            deque("HNPCJFVQ".toCharArray()),
            deque("DGTRWZS".toCharArray()),
            deque("CGMNBWZP".toCharArray()),
            deque("NJBMWQFP".toCharArray())
    );

    private static ArrayDeque<Character> deque(char[] containers) {
        ArrayDeque<Character> characters = new ArrayDeque<>();
        for (Character character : containers) {
            characters.push(character);
        }
        return characters;
    }

    @Test
    void part1() throws IOException {
        List<String> lines = readToList();
        for (String line : lines) {
            IntegerPattern.PatternResult matcherResult = INTEGER_PATTERN.match(line);
            for (int i = 0; i < matcherResult.getGroup(1); i++) {
                Character pop = stacks.get(matcherResult.getGroup(2) - 1).pop();
                stacks.get(matcherResult.getGroup(3) - 1).push(pop);
            }
        }
        for (Deque<Character> stack : stacks) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    @Test
    void part2() throws IOException {
        List<String> lines = readToList();
        for (String line : lines) {
            IntegerPattern.PatternResult matcherResult = INTEGER_PATTERN.match(line);
            List<Integer> integers = IntStream.range(0, matcherResult.getGroup(1))
                    .map(i -> stacks.get(matcherResult.getGroup(2) - 1).pop())
                    .boxed()
                    .toList();
            for (int i = integers.size(); i > 0; i--) {
                stacks.get(matcherResult.getGroup(3) - 1).push((char) (int) integers.get(i - 1));
            }
        }
        for (Deque<Character> stack : stacks) {
            System.out.print(stack.pop());
        }
        System.out.println();
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
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("05")));
    }
}
