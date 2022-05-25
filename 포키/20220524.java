import java.util.Scanner;

public class BOJ2468 {

    static int[] rowDirection = {0, -1, 0, 1};
    static int[] columnDirection = {-1, 0, 1, 0};
    static int[][] graph;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        graph = new int[n][n];


        int min = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
                if (max < graph[i][j]) max = graph[i][j];
            }
        }

        int answer = 1;

        while (min <= max) {
            visited = new boolean[n][n];
            int count = 0;


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] <= min) {
                        visited[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && graph[i][j] > min) {
                        count += dfs(min, i, j);
                    }
                }
            }
            answer = Math.max(count, answer);
            min++;
        }

        System.out.println(answer);

    }

    public static int dfs(int waterHeight, int row, int column) {
        visited[column][row] = true;

        //4가지 방향 검사
        //상,좌,하,우z
        for (int i = 0; i < 4; i++) {
            int nextRow = row + rowDirection[i];
            int nextColumn = column + columnDirection[i];

            //이동할 위치가 맵을 벗어나면 무시하기
            if (nextRow < 0 || nextColumn < 0 || nextRow >= graph.length || nextColumn >= graph.length) {
                continue;
            }

            if (!visited[nextColumn][nextRow] && graph[nextColumn][nextRow] > waterHeight) {
                dfs(waterHeight, nextRow, nextColumn);
            }
        }

        return 1;
    }
}
