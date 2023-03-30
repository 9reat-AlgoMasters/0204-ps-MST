import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1647 {
    static int N, M;
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
    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q1647.txt";
        setInputFile(remainPath, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initParent();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(v1, v2, w));
        }

        int connectionCnt = 0;
        int minCost = 0;
        int maxEdge = 0;
        while (connectionCnt < N - 1) {
            Edge edge = pq.poll();
            if (union(edge.v1, edge.v2)) {
                connectionCnt++;
                minCost += edge.w;
                maxEdge = Math.max(maxEdge, edge.w);
            }
        }

        sb.append(minCost- maxEdge).append("\n");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean union(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return false;
        }
        parent[find(v2)] = find(v1);
        return true;
    }

    private static int find(int v) {
        return v == parent[v] ? v : (parent[v]=find(parent[v]));
    }

    private static void initParent() {
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }
}