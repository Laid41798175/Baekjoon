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

	static ArrayList<Line> line;
	static int[] parent;

	static int count = 0;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		line = new ArrayList<>();
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < n; i++) {
			str = br.readLine();
			stk = new StringTokenizer(str);
			Line l = new Line(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
			for (int j = 0; j < line.size(); j++) {
				if (l.isCrossing(line.get(j))) {
					union(j, i);
				}
			}
			line.add(l);
		}
				
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[find(parent[i])]++;
		}
		
		for (int i = 0; i < n; i++) {
			if (num[i] != 0) {
				count++;
				max = Math.max(max, num[i]);
			}
		}
		
		sb.append(count + "\n" + max + "\n");
	}

	public static int find(int x) {
		int c = x;
		while (c != parent[c]) {
			c = parent[c];
		}
		parent[x] = c;
		return c;
	}

	public static void union(int x, int y) {
		if (find(x) == find(y)) {
			return;
		}

		parent[find(x)] = find(y);
	}

}

class Line {	
	long x1;
	long y1;
	long x2;
	long y2;

	Line(long a, long b, long c, long d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}

	public boolean isCrossing(Line o) {		
		// a * d - b * c
		long i0 = (o.x1 - x1) * (o.y2 - y1) - (o.y1 - y1) * (o.x2 - x1);
		long i1 = (o.x1 - x2) * (o.y2 - y2) - (o.y1 - y2) * (o.x2 - x2);
		long i2 = (o.x1 - x1) * (o.y1 - y2) - (o.y1 - y1) * (o.x1 - x2);
		long i3 = (o.x2 - x1) * (o.y2 - y2) - (o.y2 - y1) * (o.x2 - x2);

		if (i0 == 0 && i1 == 0 && i2 == 0 && i3 == 0) {
			if (isOverlapped(x1, x2, o.x1, o.x2) && isOverlapped(y1, y2, o.y1, o.y2)) {
				return true;
			} else {
				return false;
			}
		}
		if (i0 * i1 <= 0 && i2 * i3 <= 0) {
			return true;
		} else {
			return false;
		}
	}

	static boolean isOverlapped(long x0, long x1, long x2, long x3) {
		if (x0 > x1) {
			long tmp = x0;
			x0 = x1;
			x1 = tmp;
		}
		if (x2 > x3) {
			long tmp = x2;
			x2 = x3;
			x3 = tmp;
		}
		return (x2 <= x0 && x0 <= x3) || (x2 <= x1 && x1 <= x3) || (x0 <= x2 && x2 <= x1) || (x0 <= x3 && x3 <= x1);
	}
}

class Point {
	int x;
	int y;

	Point(int a, int b) {
		x = a;
		y = b;
	}
}

class Vector {
	long x;
	long y;

	Vector(Point l1, Point l2) {
		x = l2.x - l1.x;
		y = l2.y - l1.y;
	}

	int OuterProduct(Vector v) {
		long val = x * v.y - y * v.x;
		if (val > 0) {
			return 1;
		} else if (val < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}