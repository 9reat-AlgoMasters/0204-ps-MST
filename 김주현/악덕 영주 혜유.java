import java.io.*;
import java.util.*;

public class Q20010 {
    static int N, K;
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int v1, v2, w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
    
    static class Graph {
        List<Node>[] adjList;
        long[] dp;
        boolean[] visited;
        int size;

        public Graph(int size) {
            adjList = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                adjList[i] = new ArrayList<>();
            }
            this.size = size;
        }

        public void addEdge(Edge edge) {
            adjList[edge.v1].add(new Node(edge.v2, edge.w));
            adjList[edge.v2].add(new Node(edge.v1, edge.w));
        }
        
        public void initDP() {
            dp = new long[size];
            visited = new boolean[size];
        }
        
        public long maxPathByBFS(int start) {
            initDP();
            solveDPByBfs(start);
            long max = 0;
            for (int i = 0; i < size; i++) {
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        // start에서 해당 노드까지 가는 최대 거리
        private void solveDPByBfs(int start) {
            Deque<Node> q = new ArrayDeque<>();
            q.add(new Node(start, 0));
            visited[start] = true;

            while (!q.isEmpty()) {
                Node now = q.poll();

                for (Node next : adjList[now.to]) {
                    if (!visited[next.to]) {
                        dp[next.to] = Math.max(dp[next.to], dp[now.to] + next.w);
                        visited[next.to] = true;
                        q.add(new Node(next.to, dp[next.to]));
                    }
                }
            }
        }
    }
    
    static class Node {
        int to;
        long w;

        public Node(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Graph g = new Graph(N);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // KRUSKAL
        initParent();
        int connection = 0;
        int minCost = 0;
        while (connection < N - 1) {
            Edge edge = pq.poll();
            if (union(edge.v1, edge.v2)) {
                g.addEdge(edge);
                minCost += edge.w;
                connection++;
            }
        }

        // 최대 경로 찾기
        long maxPath = 0;
        for (int start = 0; start < N; start++) {
            maxPath = Math.max(maxPath, g.maxPathByBFS(start));
        }

        sb.append(minCost).append("\n").append(maxPath);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);

        if (v1 == v2) {
            return false;
        }

        parent[v2] = v1;
        return true;
    }

    private static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    private static void initParent() {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
}
