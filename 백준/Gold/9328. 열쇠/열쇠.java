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

	static Player player;
	static Space[][] spaces;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		T = Integer.parseInt(str);
		for (int t = 0; t < T; t++) {
			solve();
		}
		System.out.print(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		player = new Player(n, m);
		spaces = new Space[n][m];

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if (str.charAt(j) == '*') {
					spaces[i][j] = new Wall();
					continue;
				}

				if ('A' <= str.charAt(j) && str.charAt(j) <= 'Z') {
					spaces[i][j] = new Door(str.charAt(j) - 'A');
				} else if ('a' <= str.charAt(j) && str.charAt(j) <= 'z') {
					spaces[i][j] = new Key(str.charAt(j) - 'a');
				} else if (str.charAt(j) == '$') {
					spaces[i][j] = new Document();
					player.documentCount++;
				} else if (str.charAt(j) == '.') {
					spaces[i][j] = new Empty();
				}
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					player.startList.add(new Info(i, j));
				}
			}
		}

		str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '0') {
				break;
			}
			player.keys[str.charAt(i) - 'a'] = true;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
					if (spaces[i][j].isMoveable()) {
						player.startList.add(new Info(i, j));
					}
				}
			}
		}

		player.reset();
		player.move();
	}

	static class Player {
		public int count;
		public int documentCount;

		public boolean[] keys;

		Stack<Info> s;
		ArrayList<Info> startList;
		boolean[][] isVisited;

		Player(int n, int m) {
			count = 0;
			s = new Stack<>();
			startList = new ArrayList<>();
			keys = new boolean[26];
			isVisited = new boolean[n][m];
		}

		public void reset() {
			for (int i = 0; i < startList.size(); i++) {
				s.add(startList.get(i));
			}
			isVisited = new boolean[n][m];
		}

		static int[] dx = { 0, 0, 1, -1 };
		static int[] dy = { 1, -1, 0, 0 };

		public void move() {
			while (!s.isEmpty()) {
				Info c = s.pop();
				if (isVisited[c.x][c.y]) {
					continue;
				}
				if (!Main.spaces[c.x][c.y].isMoveable()) {
					continue;
				}
				isVisited[c.x][c.y] = true;

				Main.spaces[c.x][c.y].move();
				// System.out.println(c.x + "," + c.y + " reached");

				int tgtX = -1;
				int tgtY = -1;
				for (int i = 0; i < 4; i++) {
					tgtX = c.x + dx[i];
					tgtY = c.y + dy[i];

					if (tgtX < 0 || Main.n <= tgtX || tgtY < 0 || Main.m <= tgtY || isVisited[tgtX][tgtY]) {
						continue;
					}

					Space next = Main.spaces[tgtX][tgtY];
					if (next.isMoveable()) {
						if (count == documentCount) {
							s.clear();
							break;
						}
						s.push(new Info(tgtX, tgtY));
					}
				}
			}

			// System.out.println(Arrays.toString(keys));
			sb.append(count + "\n");
		}
	}

	static class Info {
		int x;
		int y;

		Info(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static abstract class Space {
		abstract boolean isMoveable();

		abstract void move();
	}

	static class Wall extends Space {
		@Override
		boolean isMoveable() {
			return false;
		}

		@Override
		void move() {
		}
	}

	static class Empty extends Space {
		@Override
		boolean isMoveable() {
			return true;
		}

		@Override
		void move() {
		}
	}

	static class Door extends Space {
		int type;

		Door(int t) {
			type = t;
		}

		@Override
		boolean isMoveable() {
			return Main.player.keys[type];
		}

		@Override
		void move() {
		}
	}

	static class Key extends Space {
		boolean isObtained = false;
		int type;

		Key(int t) {
			type = t;
		}

		@Override
		boolean isMoveable() {
			return true;
		}

		@Override
		void move() {
			if (!isObtained) {
				Main.player.reset();
				Main.player.keys[type] = true;
				isObtained = true;
			}
		}
	}

	static class Document extends Space {
		boolean isObtained = false;

		@Override
		boolean isMoveable() {
			return true;
		}

		@Override
		void move() {
			if (!isObtained) {
				Main.player.count++;
				isObtained = true;
			}
		}
	}
}