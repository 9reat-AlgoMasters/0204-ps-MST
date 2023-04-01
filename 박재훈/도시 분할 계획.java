import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge>{
		int u, v, w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int N, M, ans;
	static PriorityQueue<Edge> pq;
	static int[] p, r;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			pq.add(new Edge(a, b, c));
		}
		
		makeSet();
		int cnt = 0;
		while(cnt != N-2) {
			Edge edge = pq.poll();
			if(union(edge.u, edge.v)) {
				cnt++;
				ans += edge.w;
			}
		}
		System.out.println(ans);
		
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return false;
		if(r[x] < r[y]) {
			r[y]++;
			p[x] = y;
		}else {
			r[x]++;
			p[y] = x;
		}
		return true;
	}

	static int find(int x) {
		if(x == p[x]) return x;
		return p[x] = find(p[x]);
	}

	static void makeSet() {
		p = new int[N+1];
		r = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

}
