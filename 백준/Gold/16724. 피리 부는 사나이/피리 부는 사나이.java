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

	static int[][] direction;
	static int[][] isVisited;
	static boolean[][] isChecked;

	static int safeZone = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		direction = new int[n][m];
		isVisited = new int[n][m];
		isChecked = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == 'R') {
					direction[i][j] = 0;
				} else if (str.charAt(j) == 'L') {
					direction[i][j] = 1;
				} else if (str.charAt(j) == 'D') {
					direction[i][j] = 2;
				} else {
					direction[i][j] = 3;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!isChecked[i][j]) {
					visit(i, j);
					check(i, j);
				}
			}
		}

		sb.append(safeZone + "\n");
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void visit(int x, int y) {
		if (isVisited[x][y] == 0) {
			int tgtX = x + dx[direction[x][y]];
			int tgtY = y + dy[direction[x][y]];
			isVisited[x][y]++;
			if (!isChecked[tgtX][tgtY]) {
				visit(tgtX, tgtY);
			}			
		} else if (isVisited[x][y] == 1) {
			safeZone++;
		}
	}
	
	public static void check(int x, int y) {
		if (!isChecked[x][y]) {
			isChecked[x][y] = true;
			int tgtX = x + dx[direction[x][y]];
			int tgtY = y + dy[direction[x][y]];
			check (tgtX, tgtY);
		}
	}
}