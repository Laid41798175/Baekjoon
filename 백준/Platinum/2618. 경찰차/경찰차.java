//Do Not Modify From
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int T;
	static int n;
	static int m;

	// Do Nod Modify To

	static Loc[] loc;
	
	static int[] prevDp;
	static int[] dp;
	
	static int[][] prev;
	
	final static int X = 987654321;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		m = Integer.parseInt(str);
		loc = new Loc[m + 1];		
		prev = new int[m + 1][];

		str = br.readLine();
		stk = new StringTokenizer(str);
		loc[1] = new Loc(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		prev[1] = new int[] {0, 1};
		
		// process(1)은 전처리로 따로 뺐음
		prevDp = new int[] { loc[1].dst(1, 1), loc[1].dst(n, n) };
		dp = prevDp;
		
		for (int i = 2; i <= m; i++) {
			process(i);
		}

		int min = X;
		int minIndex = -1;
		for (int i = 0; i < 2 * m; i++) {
			if (dp[i] < min) {
				min = dp[i];
				minIndex = i;
			}
		}
		
		for (int i = m; i >= 1; i--) {
			sb.insert(0, (minIndex < i ? "1\n" : "2\n"));
			minIndex = prev[i][minIndex];
		}
		sb.insert(0, min + "\n");
	}
	
	public static void process(int i) throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		loc[i] = new Loc(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		dp = new int[2 * i];
		prev[i] = new int[2 * i];
		Arrays.fill(dp, X);

		for (int j = 0; j < i - 1; j++) { // A가 움직임
			// dp[i][j]: A는 loc[i], B는 loc[j]에 있는 상황
			dp[j] = prevDp[j] + loc[i].dst(loc[i - 1]);
			prev[i][j] = j;
		}

		for (int j = 0; j < i - 1; j++) {
			if (prevDp[i - 1 + j] + (j != 0 ? loc[i].dst(loc[j]) : loc[i].dst(1, 1)) < dp[i - 1]) {
				dp[i - 1] = prevDp[i - 1 + j] + (j != 0 ? loc[i].dst(loc[j]) : loc[i].dst(1, 1));
				prev[i][i - 1] = i - 1 + j;
			}
		}

		for (int j = i; j < 2 * i - 1; j++) { //B가 움직임
			// dp[i][j]: A는 loc[j - i], B는 loc[i]에 있는 상황
			dp[j] = prevDp[j - 1] + loc[i].dst(loc[i - 1]);
			prev[i][j] = j - 1;
		}

		for (int j = 0; j < i - 1; j++) {
			if (prevDp[j] + (j != 0 ? loc[i].dst(loc[j]) : loc[i].dst(1, 1)) < dp[2 * i - 1]) {
				dp[2 * i - 1] = prevDp[j] + (j != 0 ? loc[i].dst(loc[j]) : loc[i].dst(n, n));
				prev[i][2 * i - 1] = j;
			}			
		}
		
		prevDp = dp;
	}
}

class Loc {
	int x;
	int y;

	Loc(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int dst(Loc o) {
		return dst(o.x, o.y);
	}
	public int dst(int ox, int oy) {
		return Math.abs(x - ox) + Math.abs(y - oy);
	}
}