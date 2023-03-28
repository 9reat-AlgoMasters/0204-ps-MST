import java.io.*;
import java.util.*;

public class Main {
	static int N; // 마을의 수
	static int K; // 교역로의 수
	static int p[];
	static int r[];
	static int min;
	static int max;
	static ArrayList<Edge> graph[]; //연결된 교역로들을 담는 리스트 
	static PriorityQueue<Edge> edges;
	static boolean[] visited; //dfs에 필요한 방문체크 배열
	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;

		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", w=" + w + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		p = new int[N];
		r = new int[N];
		makeSet();
		edges = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges.offer(new Edge(v1, v2, w));
		}

		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		int count = 0;
		while (count != N - 1) {
			Edge edge = edges.poll();
			if (union(edge.v1, edge.v2)) { //연결할 수 있으면
				count++;
				min += edge.w;
				graph[edge.v1].add(new Edge(edge.v1, edge.v2, edge.w)); //양방향으로 그래프 생성
				graph[edge.v2].add(new Edge(edge.v2, edge.v1, edge.w));
			}
		}
        
		max =0;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i]=true; //시작노드 방문체크
			dfs(i, 0);	 //모든 노드에 대해 각 노드를 시작점으로 한 길로 갈 수 있는 경로 탐색
		}
		System.out.println(min);
		System.out.println(max);
	}

	private static void dfs(int i, int total) {
		
		for (Edge edge : graph[i]) {
			if(!visited[edge.v2]) {
				visited[edge.v2] = true;
				dfs(edge.v2, total+edge.w);
			}
		}
		max = Math.max(total, max);
	}


	static boolean union(int x, int y) {
		x = findParent(x);
		y = findParent(y);

		if (x == y)
			return false;

		if (r[x] < r[y]) {
			r[y] += r[x];
			p[x] = y;
		} else {
			r[x] += r[y];
			p[y] = x;
		}
		return true;
	}

	static int findParent(int x) {
		if (x == p[x])
			return x;
		else
			return p[x] = findParent(p[x]);
	}

	static void makeSet() {
		for (int i = 0; i < p.length; i++) {
			p[i] = i;
			r[i] = 1;
		}

	}

}