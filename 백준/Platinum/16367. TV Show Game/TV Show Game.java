//Do Not Modify From
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str = "";
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int T;
	static int n;
	static int m;
	// Do Nod Modify To

	static Vertex[] X;
	static Vertex[] revX;
	static boolean[] isVisited;

	static Stack<Integer> s;
	static ArrayList<ArrayList<Integer>> SCClist;
	
	static int[][][] sbm = new int[m][3][2];
    static int[] ret;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		X = new Vertex[2 * n + 1];
		revX = new Vertex[2 * n + 1];

		s = new Stack<>();
		SCClist = new ArrayList<>();

		for (int i = 1; i <= 2 * n; i++) {
			X[i] = new Vertex();
			revX[i] = new Vertex();
		}

		sbm = new int[m][3][2];
		for (int i = 0; i < m; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			
			for (int j = 0; j < 3; j++) {
				int tmp = Integer.parseInt(stk.nextToken());
				if (stk.nextToken().charAt(0) == 'R') {
					sbm[i][j][0] = tmp;
					sbm[i][j][1] = tmp + n;
				} else {
					sbm[i][j][0] = tmp + n;
					sbm[i][j][1] = tmp;
				}
			}
			X[sbm[i][0][1]].add(sbm[i][1][0]);
			X[sbm[i][1][1]].add(sbm[i][2][0]);
			X[sbm[i][2][1]].add(sbm[i][0][0]);
			
			revX[sbm[i][1][0]].add(sbm[i][0][1]);
			revX[sbm[i][2][0]].add(sbm[i][1][1]);
			revX[sbm[i][0][0]].add(sbm[i][2][1]);
			
			X[sbm[i][0][1]].add(sbm[i][2][0]);
			X[sbm[i][1][1]].add(sbm[i][0][0]);
			X[sbm[i][2][1]].add(sbm[i][1][0]);
			
			revX[sbm[i][2][0]].add(sbm[i][0][1]);
			revX[sbm[i][0][0]].add(sbm[i][1][1]);
			revX[sbm[i][1][0]].add(sbm[i][2][1]);
		}

		for (int i = 1; i <= 2 * n; i++) {
			X[i].sort();
			revX[i].sort();
		}

		kosaraju();

		int[] sccNumber = new int[2 * n + 1];
		for (int i = 0; i < SCClist.size(); i++) {
			for (int n : SCClist.get(i)) {
				sccNumber[n] = i;
			}
		}

		for (int i = 1; i <= n; i++) {
			if (sccNumber[i] == sccNumber[n + i]) {
				sb.append("-1\n");
				return;
			}
		}
		
		// 여기까지는 완벽해요잉
		
		ret = new int[n + 1];
		Arrays.fill(ret, -1);
		Pair[] p = new Pair[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			p[i] = new Pair(i + 1, sccNumber[i + 1]);
		}
		Arrays.sort(p, (Pair a, Pair b) -> a.y - b.y);

        isVisited = new boolean[2 * n + 1];
		for (int i = 0; i < 2 * n; i++) {
            if (p[i].x > n) {
                if (ret[p[i].x - n] == -1) {
                    // NOT이 먼저 False를 차지, 변수는 True
                    ret[p[i].x - n] = 1;

                    // 변수에서 출발하는 imply들은 전부 True
                    setTrues(p[i].x - n);
                } 
            } else {
                if (ret[p[i].x] == -1) {
                    // 변수가 먼저 False를 차지, NOT은 True
                    ret[p[i].x] = 0;

                    // NOT에서 출발하는 imply들은 전부 True
                    setTrues(p[i].x + n);
                }
            }
		}

        for (int i = 1; i <= n; i++) {
            sb.append(ret[i] == 0 ? "B" : "R");
        }
        sb.append('\n');
	}

    public static void setTrues(int curr) {
        if (isVisited[curr]) {
            return;
        }
        isVisited[curr] = true;

        ArrayList<Integer> list = X[curr].edges;
        for (int j = 0; j < list.size(); j++) {
            int to = list.get(j);
            if (to > n) {
                if (ret[to - n] == -1) {
                    ret[to - n] = 0;
                }
                assert ret[to - n] == 0;
                setTrues(to);
            } else {
                if (ret[to] == -1) {
                    ret[to] = 1;
                }
                assert ret[to] == 1;
                setTrues(to);
            }
        }
    }

	public static void kosaraju() {
		isVisited = new boolean[2 * n + 1];
		for (int i = 1; i <= 2 * n; i++) {
			if (!isVisited[i]) {
				dfs(i);
			}
		}

		isVisited = new boolean[2 * n + 1];
		while (!s.isEmpty()) {
			int i = s.pop();
			if (isVisited[i]) {
				continue;
			}
			scc = new ArrayList<>();
			dfsRev(i);
			SCClist.add(scc);
		}
	}

	public static ArrayList<Integer> scc;

	public static void dfs(int curr) {
		isVisited[curr] = true;
		for (int to : X[curr].edges) {
			if (!isVisited[to]) {
				dfs(to);
			}
		}
		s.add(curr);
	}

	public static void dfsRev(int curr) {
		isVisited[curr] = true;
		scc.add(curr);
		for (int to : revX[curr].edges) {
			if (!isVisited[to]) {
				dfsRev(to);
			}
		}
	}
}

class Pair implements Comparable<Pair> {
	int x;
	int y;

	Pair(int a, int b) {
		x = a;
		y = b;
	}

	public int compareTo(Pair o) {
		return y - o.y;
	}

	public String toString() {
		return x > Main.n ? ("-" + (x - Main.n) + ": " + y) : (x + ": " + y);
	}
}

class Vertex {
	ArrayList<Integer> edges;

	Vertex() {
		edges = new ArrayList<>();
	}

	void add(int i) {
		edges.add(i);
	}

	void sort() {
		edges.sort(null);
	}
}