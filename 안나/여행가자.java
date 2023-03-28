import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; //도시의 수
	static int M; //계획에 속한 도시의 수
	static int[] p;
	static int[][] map;
	static int[] plan;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		p = new int[N+1];
		map = new int[N+1][N+1];
		plan = new int[M];
		makeSet();
		for (int i = 1; i <= N ; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j] ==1) {
					union(i, j);
				}
			}
		}
		
		st= new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}

		int px = find(plan[0]);
		for (int i = 1; i < M; i++) {
			if(px == find(plan[i])) {
				continue;
			}else {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");

		
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		
		if(a < b) { //값이 작은 쪽으로 합침
			p[b] = a;
		}else {
			p[a] = b;
		}
		
	}
	static int find(int x) {
		if(x == p[x]) return x;
		else  return p[x] = find(p[x]);
	}
	static void makeSet() {
		for (int i = 0; i < N+1; i++) {
			p[i] = i;
		}
		
	}

}