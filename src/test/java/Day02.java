import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Day02 {

    //A X Rock
    //B Y Paper
    //C Z Scissors


    Map<String, Integer> results = Map.of(
            "A X", 1 + 3,
            "A Y", 2 + 6,
            "A Z", 3 + 0,
            "B X", 1 + 0,
            "B Y", 2 + 3,
            "B Z", 3 + 6,
            "C X", 1 + 6,
            "C Y", 2 + 0,
            "C Z", 3 + 3
    );

    @Test
    void part1() throws IOException {
        try (BufferedReader bufferedReader = readFile()) {
            String line;
            int sum = 0;

            while ((line = bufferedReader.readLine()) != null) {
                sum += results.get(line);
            }
            System.out.println(sum);
        }
    }

    //A Rock     1
    //B Paper    2
    //C Scissors 3
    //X lose
    //Y draw
    //Z win

    Map<String, Integer> resultsOutcome = Map.of(
            "A X", 3 + 0,
            "A Y", 1 + 3,
            "A Z", 2 + 6,
            "B X", 1 + 0,
            "B Y", 2 + 3,
            "B Z", 3 + 6,
            "C X", 2 + 0,
            "C Y", 3 + 3,
            "C Z", 1 + 6
    );

    @Test
    void part2() throws IOException {
        try (BufferedReader bufferedReader = readFile()) {
            String line;
            int sum = 0;

            while ((line = bufferedReader.readLine()) != null) {
                sum += resultsOutcome.get(line);
            }
            System.out.println(sum);
        }
    }

    private BufferedReader readFile() {
        return new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("02")));
    }
}
