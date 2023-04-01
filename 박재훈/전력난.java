import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static class Edge implements Comparable<Edge>{
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int m, n;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] input = br.readLine().split(" ");
			m = Integer.parseInt(input[0]);
			n = Integer.parseInt(input[1]);
			if(m==0) break;
			
			int[] distance = new int[m];
			boolean[] visited = new boolean[m];
			ArrayList<Edge>[] lists = new ArrayList[m];
			for (int i = 0; i < m; i++) {
				lists[i] = new ArrayList<>();
			}
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			int before = 0;
			for (int i = 0; i < n; i++) {
				input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				int z = Integer.parseInt(input[2]);
				lists[x].add(new Edge(y, z));
				lists[y].add(new Edge(x, z));
        //가로등 일부를 소등하기 전 원래 나가던 총 비용 저장 
				before += z;
			}
			
      //최소 스패닝 트리를 이루도록 하면 어느 집으록 가든 가로등 있는 길로 다닐 수 있음
			pq.add(new Edge(0,0));
			int cnt = 0, after = 0;
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				if(visited[edge.v]) continue;
				visited[edge.v] = true;
				after += edge.w;
        //Prim 알고리즘으로 선택한 정점 수가 m개 되면 끝
				if(++cnt == m) break;
				int size = lists[edge.v].size();
				for (int i = 0; i < size; i++) {
					Edge next = lists[edge.v].get(i);
					if(visited[next.v]) continue;
					pq.add(next);
				}
			}
			
      //소등전 - 소등후 = 절약한 돈
			sb.append(before-after).append('\n');
		}
		System.out.println(sb);
	}

}
