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

	static int[] input;
	static int[] board;
	
	static int min = Integer.MAX_VALUE;
	static int count;
	
	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {		
		input = new int[10];
		for (int i = 0; i < 10; i++) {
			str = br.readLine();
			for (int j = 9; j >= 0; j--) {
				if (str.charAt(j) == 'O') {
					input[i] += (1 << j);
				}
			}
		}
		
		for (int i = 0; i < 1024; i++) {
			board = Arrays.copyOf(input, 10);
			count = 0;
			process(i);
		}
		
		sb.append(min == Integer.MAX_VALUE ? -1 : min).append("\n");
	}
	
	public static void process(int boardBitmask) {
		for (int i = 0; i < 10; i++) {
			if (isOn(boardBitmask, i)) {
				switchAll(i, 0);
			}
		}
		
		for (int i = 1; i < 10; i++) {
			greedy(i);
		}
		if (board[9] == 0) {
			min = Math.min(min, count);
		}
	}
	
	public static void greedy(int y) { // y >= 1
		for (int i = 0; i < 10; i++) {
			if (isOn(board[y - 1], i)) {
				switchAll(i, y);
			}
		}
	}
	
	static int[] dx = {0, 0, 1, -1, 0};
	static int[] dy = {1, -1, 0, 0, 0};
	
	static void switchAll(int x, int y) {
		for (int i = 0; i < 5; i++) {
			int tgtX = x + dx[i];
			int tgtY = y + dy[i];
			switchOne(tgtX, tgtY);
		}
		count++;
	}
	
	static void switchOne(int x, int y) {
		if (x < 0 || x >= 10 || y < 0 || y >= 10) {
			return;
		}
		
		int bit = 1 << x;
		if ((board[y] & bit) == 0) {
			board[y] += bit;
		} else {
			board[y] -= bit;
		}
	}

	static boolean isOn(int bitmask, int index) {
		return ((bitmask >> index) & 1) == 1;
	}
}