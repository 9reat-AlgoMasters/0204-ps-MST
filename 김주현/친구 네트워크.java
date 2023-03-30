import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q4195 {
    static int N;
    static Map<String, Integer> parent;
    static ArrayList<String> nameSpace;
    static Map<String, Integer> rank;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int caseNo = 1;
        while (T-- > 0) {
//            System.out.printf("########### caseNo : %d ###########\n", caseNo);
            nameSpace = new ArrayList<>();
            parent = new HashMap<>();
            rank = new HashMap<>();
            cnt = 0;
            N = Integer.parseInt(br.readLine());

            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();
                initParent(f1);
                initParent(f2);
                union(f1, f2);
                sb.append(rank.get(find(f1))).append("\n");
            }
            caseNo++;
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void initParent(String name) {
        if (!parent.containsKey(name)) {
            nameSpace.add(name);
            parent.put(name, cnt++);
            rank.put(name, 1);
        }
    }

    // find : name의 부모 name을 출력한다.
    public static String find(String name) {
        if (nameSpace.get(parent.get(name)).equals(name)) {
            return name;
        }
        // 부모 name을 받아와 부모 name에 해당하는 index를 저장한다.
        // find(nameSpace.get(parent.get(name))) --> 부모의 부모 name
        String rootParentName = find(nameSpace.get(parent.get(name)));
        parent.put(name, parent.get(rootParentName));
        return rootParentName;
    }

    public static boolean union(String name1, String name2) {
        name1 = find(name1);
        name2 = find(name2);

        if (name1.equals(name2)) {
            return false;
        }

        if (rank.get(name1) >= rank.get(name2)) {
            parent.put(name2, parent.get(name1));
            rank.put(name1, rank.get(name1) + rank.get(name2));
        } else {
            parent.put(name1, parent.get(name2));
            rank.put(name2, rank.get(name1) + rank.get(name2));
        }
        return true;
    }

}
