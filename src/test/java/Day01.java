import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Day01 {

    @Test
    void part1() throws IOException {
        try (BufferedReader bufferedReader = readFile()) {
            String line;
            int sum = 0;
            int max = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank()) {
                    if (sum > max) {
                        max = sum;
                    }
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }
            System.out.println(max);
        }
    }

    @Test
    void part2() throws IOException {
        try (BufferedReader bufferedReader = readFile()) {
            String line;
            int sum = 0;
            List<Integer> sums = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank()) {
                    sums.add(sum);
                    sum = 0;
                } else {
                    sum += Integer.parseInt(line);
                }
            }
            sums.sort((o1, o2) -> Integer.compare(o2, o1));
            int top3 = sums.stream().limit(3).mapToInt(Integer::intValue).sum();
            System.out.println(top3);
        }
    }

    private BufferedReader readFile() {
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("01")));
    }
}
