import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1774 {
    static int N, M;
    static God[] gods;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class God {
        int x, y;

        public God(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double dist(God o) {
            return Math.sqrt(Math.pow(this.x - o.x, 2) + Math.pow(this.y - o.y, 2));
        }
    }

    static class Edge implements Comparable<Edge> {
        int v1, v2;
        double dist;

        public Edge(int v1, int v2, double dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gods = new God[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            gods[i] = new God(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        makeEdges();
        initParent();
        int connectedEdge = 0;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                connectedEdge++;
            }
        }

        double cost = 0;
        while (connectedEdge < N - 1) {
            Edge edge = pq.poll();
            if (union(edge.v1, edge.v2)) {
                cost += edge.dist;
                connectedEdge++;
            }
        }

        sb.append(String.format("%.2f", cost));


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void initParent() {
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        parent[b] = a;
        return true;
    }

    private static void makeEdges() {
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                pq.add(new Edge(i, j, gods[i].dist(gods[j])));
            }
        }
    }
}
