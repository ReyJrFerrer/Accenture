package data_annotation_assessment;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * In this exercise, you will write code to solve a problem. Your code must be in Python (preferred), JavaScript, TypeScript, Java, Kotlin, C#, C++, Go, Rust, Swift or Ruby—solutions in other languages will not be accepted! You can write your code using any IDE you want.
 *
 * Problem
 * You are given a published Google Doc like this one https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub that contains a list of Unicode characters and their positions in a 2D grid. Your task is to write a function that takes in the URL for such a Google Doc as an argument,
 * retrieves and parses the data in the document, and prints the grid of characters. When printed in a fixed-width font, the characters in the grid will form a graphic showing a sequence of uppercase letters, which is the secret message.
 *
 * The document specifies the Unicode characters in the grid, along with the x- and y-coordinates of each character.
 *
 * The minimum possible value of these coordinates is 0. There is no maximum possible value, so the grid can be arbitrarily large.
 *
 * Any positions in the grid that do not have a specified character should be filled with a space character.
 *
 * You can assume the document will always have the same format as the example document linked above.
 *
 * For example, the simplified example document linked above draws out the letter 'F':
 * █▀▀▀
 * █▀▀
 * █
 *
 * You may write helper functions, but there should be one function that:
 *
 * 1. Takes in one argument, which is a string containing the URL for the Google Doc with the input data, AND
 *
 * 2. When called, prints the grid of characters specified by the input data, displaying a graphic of correctly oriented uppercase letters.
 */

public class MessageDecoder {
    public static void main(String[] args) {
        decode("https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub");
    }

    public static void decode(String urlString) {
        try {
            // 1. Fetch the Google Doc HTML using standard Java 11+ HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

            // 2. Parse HTML Table: Extract all cells, stripping nested tags (<p>, <span>) and HTML entities
            Pattern cellPattern = Pattern.compile("<td[^>]*>(.*?)</td>", Pattern.DOTALL);
            Matcher cellMatcher = cellPattern.matcher(html);

            List<String> cells = new ArrayList<>();
            while (cellMatcher.find()) {
                // Remove nested HTML tags, decode entities, and clean whitespace
                String text = cellMatcher.group(1).replaceAll("<[^>]+>", "").trim();
                text = text.replace("&nbsp;", " ")
                        .replace("&amp;", "&")
                        .replace("&lt;", "<")
                        .replace("&gt;", ">");
                cells.add(text);
            }

            // 3. Find Data Start: Locate "y-coordinate" header to skip x-coordinate/character headers
            int startIndex = -1;
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).contains("y-coordinate")) {
                    startIndex = i + 1;
                    break;
                }
            }

            if (startIndex == -1 || startIndex >= cells.size()) {
                System.out.println("Could not find table headers.");
                return;
            }

            // 4. Parse Data: Extract coordinates and characters dynamically tracking grid dimensions
            int maxX = 0;
            int maxY = 0;

            List<int[]> coords = new ArrayList<>();
            List<Character> chars = new ArrayList<>();

            // Iterate through data in chunks of 3: x, character, y
            for (int i = startIndex; i < cells.size(); i += 3) {
                if (i + 2 < cells.size()) {
                    try {
                        int x = Integer.parseInt(cells.get(i).trim());
                        String charStr = cells.get(i + 1);
                        char c = charStr.isEmpty() ? ' ' : charStr.charAt(0);
                        int y = Integer.parseInt(cells.get(i + 2).trim());

                        coords.add(new int[]{x, y});
                        chars.add(c);

                        if (x > maxX) maxX = x;
                        if (y > maxY) maxY = y;
                    } catch (NumberFormatException e) {
                        break;
                    }
                }
            }

            // 5. Grid Creation: Initialize grid with spaces to represent unassigned positions
            char[][] grid = new char[maxY + 1][maxX + 1];
            for (int i = 0; i <= maxY; i++) {
                for (int j = 0; j <= maxX; j++) {
                    grid[i][j] = ' ';
                }
            }

            // 6. Grid Population: Place extracted characters at their specified coordinates
            for (int i = 0; i < coords.size(); i++) {
                int x = coords.get(i)[0];
                int y = coords.get(i)[1];
                char c = chars.get(i);
                grid[y][x] = c;
            }

            // 7. Print Loop: Iterate Y backwards (maxY to 0) to print standard Cartesian coordinates
            for (int y = maxY; y >= 0; y--) {
                for (int x = 0; x <= maxX; x++) {
                    System.out.print(grid[y][x]);
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

