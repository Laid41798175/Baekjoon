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

	static boolean[][] isWall;
	static int holeX;
	static int holeY;
	static BallR R;
	static BallB B;

	static boolean goalR;
	static boolean goalB;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		isWall = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == '#') {
					isWall[i][j] = true;
				} else if (str.charAt(j) == 'B') {
					B = new BallB(i, j);
				} else if (str.charAt(j) == 'R') {
					R = new BallR(i, j);
				} else if (str.charAt(j) == 'O') {
					holeX = i;
					holeY = j;
				}
			}
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(1, B.x, B.y, R.x, R.y, ""));
		
		while (!q.isEmpty()) {
			Info i = q.poll();
			if (i.count > 10) {
				System.out.println(-1);
				System.exit(0);
			}
			
			for (int j = 0; j < 4; j++) {
				B.x = i.bx;
				B.y = i.by;
				R.x = i.rx;
				R.y = i.ry;
				goalR = false;
				goalB = false;
				if (move(j, i.count, i.route + " " + j)) {
					q.add(new Info(i.count + 1, B.x, B.y, R.x, R.y, i.route + " " + j));
				}
			}			
		}
		
		System.out.println(-1);
		System.exit(0);
	}

	public static boolean move(int direction, int count, String rt) {
		boolean isMoveR = true;
		boolean isMoveB = true;
		while (isMoveR || isMoveB) {
			switch (direction) {
			case 0: // up
				if (R.x < B.x) {
					isMoveR = R.move(0);
					isMoveB = B.move(0);
				} else {
					isMoveB = B.move(0);
					isMoveR = R.move(0);
				}
				break;
			case 2: // down
				if (R.x > B.x) {
					isMoveR = R.move(2);
					isMoveB = B.move(2);
				} else {
					isMoveB = B.move(2);
					isMoveR = R.move(2);
				}
				break;
			case 1: // right
				if (R.y > B.y) {
					isMoveR = R.move(1);
					isMoveB = B.move(1);
				} else {
					isMoveB = B.move(1);
					isMoveR = R.move(1);
				}
				break;
			case 3: // left
				if (R.y < B.y) {
					isMoveR = R.move(3);
					isMoveB = B.move(3);
				} else {
					isMoveB = B.move(3);
					isMoveR = R.move(3);
				}
				break;
			}
		}
		
		// System.out.println(count + " " + direction);
		// System.out.println(rt);
		// print();

		if (goalR && !goalB) {
			System.out.println(count);
			System.exit(0);
		}

		if (goalB) {
			return false; // 진행 불가
		}

		return true;

	}
	
	public static void print() {
		char[][] ch = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (isWall[i][j]) {
					ch[i][j] = '#';
				} else {
					ch[i][j] = '.';
				}
			}
		}
		
		ch[B.x][B.y] = 'B';		
		ch[R.x][R.y] = 'R';
		ch[holeX][holeY] = 'O';
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(ch[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static class BallR extends Ball {

		BallR(int x, int y) {
			super(x, y);
		}

		public boolean move(int direction) {
			if (goalR) {
				x = 0;
				y = 0;
				return false;
			}

			switch (direction) {
			case 0: // Up
				if (!isWall[x - 1][y] && !(B.x == x - 1 && B.y == y)) {
					x -= 1;
					goalR = check();
					return true;
				}
				break;
			case 2:
				if (!isWall[x + 1][y] && !(B.x == x + 1 && B.y == y)) {
					x += 1;
					goalR = check();
					return true;
				}
				break;
			case 1:
				if (!isWall[x][y + 1] && !(B.x == x && B.y == y + 1)) {
					y += 1;
					goalR = check();
					return true;
				}
				break;
			case 3:
				if (!isWall[x][y - 1] && !(B.x == x && B.y == y - 1)) {
					y -= 1;
					goalR = check();
					return true;
				}
				break;
			}

			goalR = check();
			return false;
		}

	}

	static class BallB extends Ball {

		BallB(int x, int y) {
			super(x, y);
		}

		public boolean move(int direction) {
			if (goalB) {
				x = 0;
				y = 0;
				return false;
			}

			switch (direction) {
			case 0: // Up
				if (!isWall[x - 1][y] && !(R.x == x - 1 && R.y == y)) {
					x -= 1;
					goalB = check();
					return true;
				}
				break;
			case 2:
				if (!isWall[x + 1][y] && !(R.x == x + 1 && R.y == y)) {
					x += 1;
					goalB = check();
					return true;
				}
				break;
			case 1:
				if (!isWall[x][y + 1] && !(R.x == x && R.y == y + 1)) {
					y += 1;
					goalB = check();
					return true;
				}
				break;
			case 3:
				if (!isWall[x][y - 1] && !(R.x == x && R.y == y - 1)) {
					y -= 1;
					goalB = check();
					return true;
				}
				break;
			}
			
			goalB = check();
			return false;
		}

	}

	static class Ball {

		int x;
		int y;

		Ball(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean check() {
			return x == holeX && y == holeY;
		}

	}
}

class Info {
	int count;
	
	int bx;
	int by;
	int rx;
	int ry;
	
	String route;
	
	Info (int c, int bx, int by, int rx, int ry, String rt) {
		count = c;
		this.bx = bx;
		this.by = by;
		this.rx = rx;
		this.ry = ry;
		
		route = rt;
	}
}