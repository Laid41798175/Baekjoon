//Do Not Modify From
import java.io.*;
import java.math.BigInteger;
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

	static int[] arr1;
	static int[] arr2;

	static int[] sum1;
	static int[] sum2;

	static BigInteger count = BigInteger.ZERO;

	public static void main(String[] args) throws IOException {
		solve();
		System.out.println(sb);
	}

	public static void solve() throws IOException {
		str = br.readLine();
		stk = new StringTokenizer(str);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		arr1 = new int[n / 2];
		arr2 = new int[n - arr1.length];
		str = br.readLine();
		stk = new StringTokenizer(str);
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = Integer.parseInt(stk.nextToken());
		}
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = Integer.parseInt(stk.nextToken());
		}

		sum1 = new int[4000001];
		sum2 = new int[4000001];

		for (int j = 1; j < (1 << arr1.length); j++) {
			int sum = 0;
			int bitmask = j;
			for (int i = 0; i < arr1.length; i++) {				
				if ((bitmask & 1) == 1) {
					sum += arr1[i];
				}
				bitmask >>= 1;
			}
			sum1[sum + 2000000]++;
		}
		
		for (int j = 1; j < (1 << arr2.length); j++) {
			int sum = 0;
			int bitmask = j;
			for (int i = 0; i < arr2.length; i++) {				
				if ((bitmask & 1) == 1) {
					sum += arr2[i];
				}
				bitmask >>= 1;
			}
			sum2[sum + 2000000]++;
		}
		
		count = count.add(BigInteger.valueOf(sum1[m + 2000000]));
		count = count.add(BigInteger.valueOf(sum2[m + 2000000]));

		for (int i = 0; i < sum1.length; i++) {
			int j = m - i + 4000000;
			if (0 <= j && j < sum2.length) {
				count = count.add(BigInteger.valueOf(sum1[i]).multiply(BigInteger.valueOf(sum2[j])));
			}
		}

		sb.append(count + "\n");
	}
}