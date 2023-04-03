import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int m;
    static int n;
    static PriorityQueue<Edge> pq;

    static int[] parent;
    static int[] rank;

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                return;
            }

            pq = new PriorityQueue<>();

            parent = new int[m];
            rank = new int[m];

            int fullWeight = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                pq.add(new Edge(A, B, C));
                fullWeight += C;
            }

            makeSet();

            int cnt = 0;
            int ans = 0;
            while (!pq.isEmpty()) {
                if (cnt == n - 1) {
                    break;
                }
                Edge e = pq.poll();
                int start = e.s;
                int end = e.e;
                int weight = e.w;

                if(union(start, end)) {
                    ans += weight;
                    cnt +=1;
                }
            }

            System.out.println(fullWeight - ans);
        }

    }

    static void makeSet() {
        for (int i = 0; i < m; i++) {
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
        if (x != y) {
            if (rank[x] < rank[y]) {
                parent[x] = y;
            } else if (rank[x] > rank[y]) {
                parent[y] = x;
            } else {
                rank[x] += rank[y];
                parent[y] = x;
            }
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
