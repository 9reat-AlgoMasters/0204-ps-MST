import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		p = new int[n+1];
		makeSet();
		for (int i = 0; i < m; i++) {
			st= new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(cal == 0) {
				union(a, b);
			}else {
				if(find(a) == find(b)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		} 
		
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
		for (int i = 0; i < n+1; i++) {
			p[i] = i;
		}
		
	}

}