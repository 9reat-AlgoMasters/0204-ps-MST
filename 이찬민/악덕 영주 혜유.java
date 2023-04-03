import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static PriorityQueue<Edge> pq;
    static List<Edge>[] list;

    static int[] parent;
    static int[] rank;

    static boolean[] visited;
    static int start;
    static int max;

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
            return Integer  .compare(this.w, o.w);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        list = new List[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }


        parent = new int[N];
        rank = new int[N];

        start = 0;

        for (int i = 0; i < K; i++) {
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
            if (cnt == N - 1) {
                break;
            }

            Edge e = pq.poll();
            int start = e.s;
            int end = e.e;
            int weight = e.w;

            if(union(start, end)) {
                ans += weight;
                list[start].add(new Edge(start, end, weight));
                list[end].add(new Edge(end, start, weight));
                cnt +=1;
            }
        }

        System.out.println(ans);

        max = Integer.MIN_VALUE;
        visited = new boolean[N];
        dfs(0, 0);

        start  = tempVal;

        visited = new boolean[N];
        max = Integer.MIN_VALUE;
        dfs(start, 0);
        System.out.println(max);

    }

    static int tempVal = 0;
    static void dfs(int a, int val) {
        if(max < val) {
            max = val;
            tempVal = a;
        }

        visited[a] = true;

        for (Edge edge : list[a]) {
            if (!visited[edge.e]) {
                dfs(edge.e, val + edge.w);
            }
        }
    }

    static void makeSet() {
        for (int i = 0; i < N; i++) {
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
