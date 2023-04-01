import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n,m;
	static int[] p, r;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		makeSet();
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int v1 = Integer.parseInt(input[1]);
			int v2 = Integer.parseInt(input[2]);
			if(Integer.parseInt(input[0]) == 0) {
				union(v1, v2);
			}else {
				if(find(v1) == find(v2)) {
					sb.append("YES").append('\n');
				}else {
					sb.append("NO").append('\n');
				}
			}
		}
		System.out.println(sb);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		if(r[x] < r[y]) {
			r[y]++;
			p[x] = y;
		}else {
			r[x]++;
			p[y] = x;
		}
		return;
	}
	
	static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static void makeSet() {
		p = new int[n+1];
		r = new int[n+1];
		for (int i = 0; i <= n; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}

}
