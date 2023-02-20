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

	static int x;
	static int y;

	static int[][] object;
	static boolean[][] isVisited;
	
	static int count = 0;

	public static void main(String[] args) throws IOException {
		solve();

		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		object = new int[n + 1][m + 1];
		isVisited = new boolean[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == '*') {
					object[i][j + 1] = 0;
				} else if ('A' <= str.charAt(j) && str.charAt(j) <= 'Z') { // 10 ~ 35
					object[i][j + 1] = str.charAt(j);
				} else if ('a' <= str.charAt(j) && str.charAt(j) <= 'z') { // 40 ~ 65
					object[i][j + 1] = str.charAt(j);
				} else if (str.charAt(j) == '#') {
					object[i][j + 1] = 1;
				} else if (str.charAt(j) == '!') {
					object[i][j + 1] = -1;
				} else if (str.charAt(j) == '@') {
					object[i][j + 1] = 0;
					x = i;
					y = j + 1;
				}
			}
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(x, y));
		
		while (!q.isEmpty()) {
			Info i = q.poll();
			if (object[i.x][i.y] == -1) {
				sb.insert(0, ++count + "\n");
				sb.append(i.toString());
				return;
			}
			if (object[i.x][i.y] == 1) {
				continue;
			}
			
			if (isVisited[i.x][i.y]) {
				continue;
			}
			
			if ('A' <= object[i.x][i.y] && object[i.x][i.y] <= 'Z') {
				q.add(i);
				continue;
			}
			isVisited[i.x][i.y] = true;
			if ('a' <= object[i.x][i.y] && object[i.x][i.y] <= 'z') {
				update(object[i.x][i.y]);
			}
			count++;
			sb.append(i.toString());
			
			for (int j = 0; j < 4; j++) {
				q.add(new Info(i.x + dx[j], i.y + dy[j]));
			}
		}
	}
	
	public static void update(int type) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (object[i][j] - 'A' + 'a' == type) {
					object[i][j] = 0;
				}
			}
		}
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
}

class Info {
	int x;
	int y;
	
	Info (int a, int b) {
		x = a;
		y = b;
	}
	
	public String toString() {
		return x + " " + y + "\n";
	}
}