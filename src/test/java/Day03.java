import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Day03 {

    @Test
    void valueOfTest() {
        assertThat(valueOf('a')).isEqualTo(1);
        assertThat(valueOf('z')).isEqualTo(26);
        assertThat(valueOf('A')).isEqualTo(27);
        assertThat(valueOf('Z')).isEqualTo(52);
    }

    @Test
    void part1() throws IOException {
        List<String> lines = readToList();
        int sum = 0;
        for (String line : lines) {
            Set<Character> characters = new HashSet<>();
            for (int i = 0; i < line.length() / 2; i++) {
                characters.add(line.charAt(i));
            }
            for (int i = line.length() / 2; i < line.length(); i++) {
                if (characters.contains(line.charAt(i))) {
                    sum += valueOf(line.charAt(i));
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    @Test
    void part2() throws IOException {
        List<String> lines = readToList();
        int sum = 0;
        for (int i = 0; i < lines.size() / 3; i++) {
            Set<Integer> firstCharacters = lines.get(i * 3).chars().boxed().collect(Collectors.toSet());
            Set<Integer> secondCharactersMatchingFirst = lines.get(i * 3 + 1).chars().boxed().filter(firstCharacters::contains).collect(Collectors.toSet());
            int value = valueOf((char) lines.get(i * 3 + 2).chars().boxed().filter(secondCharactersMatchingFirst::contains).findFirst().get().intValue());
            sum += value;
        }
        System.out.println(sum);
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

    private int valueOf(char a) {
        if (a >= 97) {
            return a - 96;
        } else {
            return a - 38;
        }
    }

    private BufferedReader readFile() {
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("03")));
    }
}
