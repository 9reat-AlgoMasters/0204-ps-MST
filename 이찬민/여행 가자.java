import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        parent = new int[N + 1];
        rank = new int[N + 1];

        makeSet();
        
        StringTokenizer st = null;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    union(i, j);
                }
            }
        }



        String[] a = br.readLine().split(" ");

        boolean flag = true;
        int tempParent = parent[Integer.parseInt(a[0])];

        for (int i = 0; i < M; i++) {
            int newCity = Integer.parseInt(a[i]);

            if (tempParent != parent[newCity]) {
                flag = false;
                break;
            }
            tempParent = parent[newCity];
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }

    static void makeSet() {
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    static boolean union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) {
            return false;
        }
        if (rank[x] < rank[y]) {
            parent[x] = y;
        } else if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            rank[x] += rank[y];
            parent[y] = x;
        }
        return true;
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
