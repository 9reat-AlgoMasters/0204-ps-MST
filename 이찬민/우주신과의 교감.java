import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] god;
    static PriorityQueue<Edge> pq;

    static int[] parent;
    static int[] rank;

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        double w;

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        parent = new int[n + 1];
        rank = new int[n + 1];

        god = new int[n+1][2];


        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            god[i][0] = A;
            god[i][1] = B;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if(i==j) {
                    continue;
                }
                pq.add(new Edge(i, j, Math.sqrt(Math.pow(god[i][0] - god[j][0], 2) + Math.pow(god[i][1] - god[j][1], 2))));

            }

        }



        makeSet();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            union(A, B);
        }

        int cnt = 0;
        double ans = 0;
        while (!pq.isEmpty()) {
            if (cnt == n - 1) {
                break;
            }
            Edge e = pq.poll();
            int start = e.s;
            int end = e.e;
            double weight = e.w;

            if(union(start, end)) {
                ans += weight;
                cnt +=1;
            }
        }

        System.out.printf("%.2f", ans);
        
    }

    static void makeSet() {
        for (int i = 1; i < n + 1; i++) {
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
