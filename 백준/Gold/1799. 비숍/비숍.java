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

	static ArrayList<ArrayList<Bishop>> black; // shape of '\'
	static ArrayList<ArrayList<Bishop>> white; // shape of '\'
	
	static int blackNum;
	static int whiteNum;	
	
	static int bMax = 0;
	static int wMax = 0;
	
	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);

		if (n / 2 == 0) {
			blackNum = n - 1;
			whiteNum = n;
		} else {
			blackNum = n;
			whiteNum = n - 1;
		}		
		
		black = new ArrayList<>();
		white = new ArrayList<>();
		for (int i = 0; i < blackNum; i++) {
			black.add(new ArrayList<Bishop>());
		}
		for (int i = 0; i < whiteNum; i++) {
			white.add(new ArrayList<Bishop>());
		}

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			for (int j = 0; j < n; j++) {
				int tmp = Integer.parseInt(stk.nextToken());
				int diag = i - j + n - 1;
				if (tmp == 1 && diag % 2 == 0) {
					black.get(diag / 2).add(new Bishop(i, j));
				} else if (tmp == 1 && diag % 2 == 1) {
					white.get(diag / 2).add(new Bishop(i, j));
				}
			}
		}
		
		dfsBlack(0, 0, new boolean[2 * n]);
		dfsWhite(0, 0, new boolean[2 * n]);
		sb.append((bMax + wMax) + "\n");
	}

	public static void dfsBlack(int depth, int count, boolean[] place) {
		if (depth == blackNum) {
			if (bMax < count) {
				bMax = count;
			}
			return;
		}

		ArrayList<Bishop> cand = black.get(depth);
		for (int i = 0; i < cand.size(); i++) {
			Bishop b = cand.get(i);
			if (!place[b.diagNum]) {
				place[b.diagNum] = true;
				dfsBlack(depth + 1, count + 1, place);
				place[b.diagNum] = false;
			}
		}
		dfsBlack(depth + 1, count, place);
	}

	public static void dfsWhite(int depth, int count, boolean[] place) {
		if (depth == whiteNum) {
			if (wMax < count) {
				wMax = count;
			}
			return;
		}

		ArrayList<Bishop> cand = white.get(depth);
		for (int i = 0; i < cand.size(); i++) {
			Bishop b = cand.get(i);
			if (!place[b.diagNum]) {
				place[b.diagNum] = true;
				dfsWhite(depth + 1, count + 1, place);
				place[b.diagNum] = false;
			}
		}
		dfsWhite(depth + 1, count, place);
	}
}

class Bishop {
	int diagNum; // shape of '/'

	Bishop(int x, int y) {
		diagNum = x + y;
	}
}