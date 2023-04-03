import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        pq = new PriorityQueue<>();

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.add(new Edge(A, B, C));
        }

        makeSet();

        int cnt = 0;
        int ans = 0;
        while (!pq.isEmpty()) {
            // 마지막 mst를 만드는 edge를 빼면 두개의 도시로 나누어지면서
            // mst를 구성할 가장 높은 가중치의 엣지가 빠지는 것이기 때문
            if (cnt == n - 2) {
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

        System.out.println(ans);

    }

    static void makeSet() {
        for (int i = 1; i < n+1; i++) {
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
