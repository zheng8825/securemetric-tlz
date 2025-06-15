import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // To validate file path
        // before run application please add 'problem2/src/input.txt' as args
        // in this case tmp hardcode a file path

        String filePath;

        if (args.length < 1) {
            System.out.println("Please provide the file path.");
            filePath = "problem2/src/input.txt";
        } else {
            filePath = args[0];
        }

        // To read file and procees replace line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String processed = processLine(line);
                System.out.println(processed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processLine(String line) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        // Process each character in the line
        while (i < line.length()) {
            char c = line.charAt(i);
            if (c != 'a' && c != 'A') {
                // If Non 'a'/'A' characters then keep it as it is
                result.append(c);
                i++;
            } else {
                // If found an 'a'/'A', determine the sequence length
                int j = i;
                while (j < line.length() && (line.charAt(j) == 'a' || line.charAt(j) == 'A')) {
                    j++;
                }
                // to count how many subsequance 'a'/'A' after first 'a'/'A' found

                int count = j - i;

                // Apply replacement rules
                if (count == 1) {
                    result.append('#'); // Single 'a'/'A' becomes '#'
                } else {
                    result.append(line.charAt(i)); // Keep first 'a'/'A' character
                    for (int k = 1; k < count; k++) {
                        result.append('$'); // Replace subsequent 'a'/'A' with '$'
                    }
                }
                // since subsequent 'a'/'A' already replace then assign back then J to I and continue the replacement process
                i = j;
            }
        }
        return result.toString();
    }
}