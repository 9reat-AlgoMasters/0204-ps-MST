import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge>{
    //연결된 우주신 번호, 거리
		int p1, p2;
		double w;

		public Edge(int p1, int p2) {
			super();
			this.p1 = p1;
			this.p2 = p2;
      //location배열에서 좌표 값 가져오기. 아래 w계산 시 int범위를 넘길 수 있으므로 long타입으로 해주었음
			long[] l1 = location[p1];
			long[] l2 = location[p2];
			this.w = Math.sqrt(Math.abs(l1[0]-l2[0])*Math.abs(l1[0]-l2[0])
					+Math.abs(l1[1]-l2[1])*Math.abs(l1[1]-l2[1]));
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}

	}
	static int N, M, cnt;
	static double ans;
	static long[][] location;
	static int[] p, r;
	static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		pq = new PriorityQueue<>();
    //좌표 저장할 배열
		location = new long[N+1][2];
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			location[i][0] = Integer.parseInt(input[0]);
			location[i][1] = Integer.parseInt(input[1]);
		}
		makeSet();
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int u1 = Integer.parseInt(input[0]);
			int u2 = Integer.parseInt(input[1]);
      //이미 연결된 선들에 대한 union 메서드 실행
			if(union(u1,u2)) {
				cnt++;
			}
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
        //아직 연결 가능한 점들을 pq에 넣기
				if(find(i) == find(j)) continue;
				pq.add(new Edge(i, j));
			}
		}
    
	  //N개의 정점이 N-1개 간선으로 모두 연결될 때까지 최소 스패닝 트리 형성
		while(cnt != N-1) {
			Edge edge = pq.poll();
			if(union(edge.p1,edge.p2)) {
				cnt++;
				ans += edge.w;
			}
		}
		System.out.printf("%.2f",ans);
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
