import java.io.*;
import java.util.StringTokenizer;

public class Q1976 {
    static int N, M;
    static int[] parent;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q1976.txt";
        setInputFile(remainPath, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N];
        initParent();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int pathParent = find(Integer.parseInt(st.nextToken())-1);
        boolean isPossible = true;
        while(st.hasMoreTokens()) {
            if (find(Integer.parseInt(st.nextToken())-1) != pathParent) {
                isPossible = false;
                break;
            }
        }

        sb.append(isPossible ? "YES" : "NO");


            bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void initParent() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        parent[b] = a;
        return true;
    }
}

















