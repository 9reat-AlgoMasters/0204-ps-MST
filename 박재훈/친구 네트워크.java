import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	static int T, F;
	static ArrayList<Integer> p, r;
	static HashMap<String, Integer> map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
      //이름과 번호를 등록할 map 자료구조
			map = new HashMap<>();
      //몇번 네트워크에 속하는지 저장
			p = new ArrayList<>();
      //네트워크 내 인원수 저장
			r = new ArrayList<>();
			F = Integer.parseInt(br.readLine());
			int num = 0;
			for (int i = 0; i < F; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 0; j < 2; j++) {
          // 이전에 나오지 않은 이름일 경우
					if(!map.containsKey(input[j])) {
						map.put(input[j], num);
            //map에 넣을 때 부여한 번호를 p 리스트에 저장
						p.add(num++);
            //최초 네트워크 인원 = 본인 1명
						r.add(1);
					}
				}
				
        //union 메서드 반환값을 출력하도록
				sb.append(union(map.get(input[0]), map.get(input[1]))).append('\n');
			}
		}
		System.out.println(sb);
		
	}
	
	static int union(int x, int y) {
		x = find(x);
		y = find(y);
    //이미 같은 네트워크면 현재 인원수 리턴
		if(x==y) return r.get(x);
    //다른 네트워크라면 두 네트워크 인원수를 합친 후 리턴
		if(r.get(x) < r.get(y)) {
			r.set(y, r.get(y) + r.get(x));
			p.set(x, y);
			return r.get(y);
		}else {
			r.set(x, r.get(x) + r.get(y));
			p.set(y, x);
			return r.get(x);
		}
		
	}

	static int find(int x) {
		if(x == p.get(x)) return x;
		int parent = find(p.get(x));
		p.set(x, parent);
		return parent;
		//return p.set(x, find(p.get(x)));
	}

	
}
