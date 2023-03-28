import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int m; // 집의 수
	static int n; // 길의 수
	static int p[];
	static int r[];
	static int totalCost;
	static int mincost;
	static PriorityQueue<Edge> edges;

	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		int z;

		public Edge(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Edge [x=" + x + ", y=" + y + ", z=" + z + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.z, o.z);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if(m==0 && n ==0) {
				break;
			}

			p = new int[m];
			r = new int[m];
			makeSet();
			edges = new PriorityQueue<>();
			totalCost =0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				totalCost += z;
				edges.offer(new Edge(x, y, z));
			}
			int cnt = 0;
			mincost=0;
			while (cnt != m - 1) {
				Edge edge = edges.poll();
				if (union(edge.x, edge.y)) {
					cnt++;
					mincost += edge.z;
				}
			}

			System.out.println(totalCost - mincost);
		}

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
		for (int i = 1; i < p.length; i++) {
			p[i] = i;
			r[i] = 1;
		}

	}
}