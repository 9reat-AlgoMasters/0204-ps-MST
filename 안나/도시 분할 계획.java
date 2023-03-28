import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N; //집의 개수
	static int M; //길의 개수
	static int p[];
	static int r[];
	static PriorityQueue<Edge> edges;
	static int sum;
	static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int w;
		public Edge(int v1, int v2, int w) {
			super();
			this.v1 = v1;
			this.v2 = v2;
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
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new int[N+1];
		r = new int[N+1];
		makeSet();
		edges = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges.offer(new Edge(v1, v2, w));
		}
		
		
		int count = 0;
		while (count != N-2) { //모든 정점이 연결되는 마지막 간선을 빼면 두개의 마을로 나눌 수 있다.
			Edge edge = edges.poll();
			if(union(edge.v1, edge.v2)) {
				count++;
				sum += edge.w;
			}
		}
		System.out.println(sum);
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