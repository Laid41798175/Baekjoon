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

	static long[][][] nums;

	static long count;
	final static int X = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		n = Integer.parseInt(str);
		if (n < 10) {
			sb.append(0 + "\n");
			return;
		}
		nums = new long[n][1024][10]; // depth, bitmask, last

		for (int i = 1; i < 10; i++) {
			nums[0][add(0, i)][i] = 1;
		}

		for (int i = 1; i < n; i++) {

			for (int j = 0; j < 1024; j++) {
				nums[i][add(j, 0)][0] += nums[i - 1][j][1];
				nums[i][add(j, 0)][0] %= X;

				for (int k = 1; k < 9; k++) {
					nums[i][add(j, k)][k] += (nums[i - 1][j][k - 1] + nums[i - 1][j][k + 1]);
					nums[i][add(j, k)][k] %= X;
				}

				nums[i][add(j, 9)][9] += nums[i - 1][j][8];
				nums[i][add(j, 9)][9] %= X;
			}
		}		

		for (int i = 0; i < 10; i++) {
			count += nums[n - 1][1023][i];
		}
		sb.append(count % X);
	}

	public static boolean contain(int bitmask, int n) {
		return ((bitmask >> n) & 1) == 1;
	}

	public static int add(int bitmask, int n) {
		if (contain(bitmask, n)) {
			return bitmask;
		}
		return bitmask + (1 << n);
	}
}