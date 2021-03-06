package adventofcode;

import java.util.*;

/**
 * Why Did you create this class? what does it do?
 */
@SuppressWarnings("Duplicates") public class SeriesOfTubes_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.trim().length() == 0)
                break;
            builder.append(s).append("\n");
        }
        System.out.println(solve(builder.toString()));

    }

    private static String solve(String s) {
        if (s == null || s.length() == 0)
            return s;
        StringBuilder builder = new StringBuilder();
        String[] a = s.split("\n");
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int direction = 1;
        for (int k = 0; k < a[0].length(); k++) {
            if (a[0].charAt(k) != ' ') {
                //   dfs(a, 0, k, result, 1);
                j = k;
                break;
            }
        }

        while (true) {
            if (i < 0 || j < 0 || i >= a.length || j >= a[i].length())
                break;
            char current = a[i].charAt(j);
            if (current == ' ') {
                System.out.println("over this is destination ");
                break;
            }

            if (Character.isAlphabetic(current))
                builder.append(current);

            if (current == '+') { // change direction
                if (Math.abs(direction) == 1) {
                    direction = (j > 0 && a[i].charAt(j - 1) == ' ') ? 2 : -2;
                    j += (j > 0 && a[i].charAt(j - 1) == ' ') ? 1 : -1;
                } else {
                    direction = (i > 0 && a[i - 1].charAt(j) == ' ') ? 1 : -1;
                    i += (i > 0 && a[i - 1].charAt(j) == ' ') ? 1 : -1;
                }
            } else if (direction == 1) { // down
                i++;
            } else if (direction == -1) { // up
                i--;
            } else if (direction == 2) { // right
                j++;
            } else { // left
                j--;
            }
        }

        return builder.toString();
    }

    private static void dfs(String[] a, int i, int j, StringBuilder builder, int direction) {
        if (i < 0 || j < 0 || i >= a.length || j >= a[i].length())
            return;
        char current = a[i].charAt(j);
        if (current == ' ')
            return;

        if (Character.isAlphabetic(current))
            builder.append(current);

        if (current == '+') { // change direction
            if (Math.abs(direction) == 1) {
                direction = (j > 0 && a[i].charAt(j - 1) == ' ') ? 2 : -2;
                j += (j > 0 && a[i].charAt(j - 1) == ' ') ? 1 : -1;
            } else {
                direction = (i > 0 && a[i - 1].charAt(j) == ' ') ? 1 : -1;
                i += (i > 0 && a[i - 1].charAt(j) == ' ') ? 1 : -1;
            }
        } else if (direction == 1) { // down
            i++;
        } else if (direction == -1) { // up
            i--;
        } else if (direction == 2) { // right
            j++;
        } else { // left
            j--;
        }
        dfs(a, i, j, builder, direction);

    }

    private static void makeGraph(String[] a, Map<Integer, Set<Integer>> map) {
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length(); j++) {
                if (a[i].charAt(j) == ' ') {
                    counter++;
                    continue;
                }
                if (j < a[i].length() - 1 && a[i].charAt(j + 1) != ' ') {
                    if (!map.containsKey(counter))
                        map.put(counter, new HashSet<>());
                    map.get(counter).add(counter + 1);
                }
                if (j > 0 && a[i].charAt(j - 1) != ' ') {
                    if (!map.containsKey(counter))
                        map.put(counter, new HashSet<>());
                    map.get(counter).add(counter - 1);
                }
                if (i > 0 && a[i - 1].charAt(j) != ' ') {
                    if (!map.containsKey(counter))
                        map.put(counter, new HashSet<>());
                    map.get(counter).add(counter - 1);
                }

                counter++;
            }
        }
    }
}
