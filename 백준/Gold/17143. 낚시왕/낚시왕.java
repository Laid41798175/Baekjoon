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

	static Shark[][] shark;
	static ArrayList<Shark> sharkList;
	static int[] parent;
	static int[] arr;

	static int sum = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		int num = Integer.parseInt(stk.nextToken());
		sharkList = new ArrayList<>();

		for (int i = 0; i < num; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int s = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			int z = Integer.parseInt(stk.nextToken());
			Shark sh = new Shark(r, c, s, d, z);
			sharkList.add(sh);
		}

		for (int t = 1; t <= m; t++) {
			Shark tgt = null;
			for (Shark sh : sharkList) {
				if (sh.y == t) {
					if (tgt == null) {
						tgt = sh;
					} else {
						if (tgt.x > sh.x) {
							tgt = sh;
						}
					}
				}
			}
			if (tgt != null) {
				sum += tgt.size;
				sharkList.remove(tgt);
			}

			shark = new Shark[n + 1][m + 1];
			ArrayList<Shark> deadList = new ArrayList<>();
			for (Shark sh : sharkList) {
				sh.move();
				if (shark[sh.x][sh.y] == null) {
					shark[sh.x][sh.y] = sh;
				} else {
					if (shark[sh.x][sh.y].size > sh.size) {
						deadList.add(sh);
					} else {
						deadList.add(shark[sh.x][sh.y]);
						shark[sh.x][sh.y] = sh;
					}
				}
			}
			sharkList.removeAll(deadList);
		}

		sb.append(sum);
	}

}

class SharkList {

	Queue<Shark> q;
	Queue<Shark> temp;

	SharkList() {
		q = new PriorityQueue<>();
		temp = new PriorityQueue<>();
	}

	public boolean caught() {
		if (q.isEmpty()) {
			return false;
		}
		Main.sum += q.poll().size;
		return true;
	}

	public void move() {
		while (!q.isEmpty()) {
			q.poll().move();
		}
	}

	public void finishMove() {
		assert q.isEmpty();
		q = temp;
		temp.clear();

		cannibal();
	}

	void cannibal() {
		if (q.isEmpty()) {
			return;
		}
		while (q.size() > 1) {
			q.poll();
		}
	}
}

class Shark {

	int R = Main.n;
	int C = Main.m;

	int x;
	int y;

	int speed;
	int direction; // 1: up, 2: down, 3: right, 4: left
	int size;

	Shark(int r, int c, int s, int d, int z) {
		x = r;
		y = c;
		speed = s;
		direction = d;
		size = z;
	}

	public String toString() {
		return x + "," + y + ", speed: " + speed + ", direction: " + direction + ", size:" + size;
	}

	public void move() {
		for (int i = 0; i < speed; i++) {
			moveOnce();
		}
	}

	void moveOnce() {
		if (direction == 1) {
			x -= 1;
			if (x == 0) {
				x = 2;
				direction = 2;
			}
		} else if (direction == 2) {
			x += 1;
			if (x > R) {
				x = R - 1;
				direction = 1;
			}
		} else if (direction == 3) {
			y += 1;
			if (y > C) {
				y = C - 1;
				direction = 4;
			}
		} else {
			y -= 1;
			if (y == 0) {
				y = 2;
				direction = 3;
			}
		}
	}
}