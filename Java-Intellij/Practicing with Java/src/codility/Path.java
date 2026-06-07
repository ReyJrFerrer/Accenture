package codility;


import java.util.*;

/**
 *
 * N
 * S
 * W
 * E
 */
public class Path {
    public static void main(String[] args) {
        String path = "NEENWN";

        System.out.println(solution(path));

    }

public static String solution(String forth) {
    int x = 0;
    int y = 0;

    Set<String> visited = new HashSet<>();
    visited.add(x + "," + y);

    for (char c : forth.toCharArray()){
        switch (c) {
            case 'N' : y++; break;
            case 'S' : y--; break;
            case 'E' : x++; break;
            case 'W' : x--; break;
        }
        visited.add(x + "," + y);
    }

    int startX = x;
    int startY = y;

    StringBuilder sb = new StringBuilder();
    int[][] moves = {{-1, 0, 'W'}, {1,0, 'E'}, {0,-1, 'S'}, {0, 1, 'N'}};
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{startX, startY});

    Map<String, String> parent = new HashMap<>();
    parent.put(startX + "," + startY,null);
    String target = "0,0";

    boolean found = false;
    while(!queue.isEmpty() && !found){
        int[] current = queue.poll();
        int cx = current[0];
        int cy = current[1];
        if ((cx + "," + cy).equals(target)){
            found = true;
            break;
        }
        for (int[] move : moves){
            int nx = cx + move[0];
            int ny = cy + move[1];
            String key = nx + "," + ny;
            if (!parent.containsKey(key) && (!visited.contains(key)) || key.equals(target)){
                parent.put(key, cx + "," + cy + "," + (char)move[2]);
                queue.offer(new int[]{nx,ny});
            }
        }
    }

    if (!found) {
        return "";
    }

    String curr = target;
    List<Character> pathChars = new ArrayList<>();
    while(parent.get(curr) != null){
        String[] parts = parent.get(curr).split(",", 3);
        pathChars.add(parts[2].charAt(0));
        curr = parts[0] + "," + parts[1];
    }
    Collections.reverse(pathChars);
    for (char c : pathChars){
        sb.append(c);
    }

    return sb.toString();




}

}
