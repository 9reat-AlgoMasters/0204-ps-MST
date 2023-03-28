import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int F; // 친구 관계의 수
	static HashMap<String, Integer> friends;
	static ArrayList<int[]> relation;
	static int p[];
	static int r[];
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			friends = new LinkedHashMap<>();// 순서보장
			relation = new ArrayList<>();

			F = Integer.parseInt(br.readLine());
			
			cnt = 1;
			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String names[] = new String[2];
				names[0] = st.nextToken();
				names[1] = st.nextToken();

				for (int j = 0; j < 2; j++) {
					if (friends.containsKey(names[j])) {
						continue;
					} else {
						friends.put(names[j], cnt);
						cnt++;
					}
				}
				
				relation.add(new int[] { friends.get(names[0]), friends.get(names[1]) });
			}
			p = new int[cnt+1];
			r = new int[cnt+1];
			
			makeSet();
			for (int i = 0; i < F; i++) {
				int rel[] = relation.get(i);
				union(rel[0], rel[1]);
				System.out.println(r[p[find(rel[0])]]);
			}

		}

	}

	static void union(int a, int b) {
		 a = find(a);// n1의 부모
		 b = find(b);// n2의 부모

		if (a == b)
			return;

		if (r[a] < r[b]) {
			r[b] += r[a];
			p[a] = b;
		} else {
			r[a] += r[b];
			p[b] = a;
		}
	}

	static int find(int x) {
		if( x == p[x]) return x;
		else return p[x] = find(p[x]);
	}
	static void makeSet() {
		for (int i = 0; i < cnt+1; i++) {
			p[i] =i;
			r[i] =1;
		}
	}
}