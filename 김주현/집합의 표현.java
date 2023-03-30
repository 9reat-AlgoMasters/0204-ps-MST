import java.io.*;
import java.util.StringTokenizer;

public class Q1717 {
    static int N, M;
    static int[] parent;

    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curWorkingDir = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curWorkingDir + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String remainPath = "\\solve\\tc\\";
        String fileName = "Q1717.txt";
        setInputFile(remainPath, fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initParent();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (option == 0) {
                union(v1, v2);
            } else {
                sb.append(find(v1) == find(v2) ? "YES" : "NO").append("\n");
            }
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void union(int v1, int v2) {
        parent[find(v2)] = find(v1);
    }

    private static int find(int v) {
        return v == parent[v] ? v : (parent[v]=find(parent[v]));
    }

    private static void initParent() {
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }
}
