import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int T;
    static int F;

    static int[] parent;
    static int[] rank;
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            map = new HashMap<>();
            F = Integer.parseInt(br.readLine());
            parent = new int[2*F + 1];
            rank = new int[2*F + 1];


            makeSet();

            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int i = 0; i < F; i++) {
                String[] temp = br.readLine().split(" ");
                String first = temp[0];
                String second = temp[1];


                int a = 0;
                int b = 0;


                if(map.get(first) == null) {
                    map.put(first, count++);
                }

                if(map.get(second) == null) {
                    map.put(second, count++);
                }


                int ans = union(map.get(first), map.get(second));

                System.out.println(ans);
            }



        }

    }

    static void makeSet() {
        for (int i = 1; i <2*F + 1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    static int union(int a, int b) {
        int x = find(a);
        int y = find(b);



        if (x != y) {
            parent[y] = x;
            rank[x] += rank[y];
        }

        return rank[x];
    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
