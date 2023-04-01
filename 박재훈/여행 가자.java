import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] p, r;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		p = new int[N+1];
		r = new int[N+1];
		
		makeSet();
		
		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(input[j-1]) == 1) {
					union(i, j);
				}
			}
		}
		
		String[] input = br.readLine().split(" ");
		int num = input.length;
		int parent = find(Integer.parseInt(input[0]));
		for (int i = 1; i < num; i++) {
			if(find(Integer.parseInt(input[i]))!=parent) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return;
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
		if(x == p[x]) return x;
		return p[x] = find(p[x]);
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			p[i] = i;
			r[i] = 1;
		}
	}
}
