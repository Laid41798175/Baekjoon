import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String str;
	static String[] spl;

	static int[] arr;
	static int n;

	public static void main(String[] args) throws IOException {
		str = br.readLine();
		spl = str.split(" ");
		int k = Integer.parseInt(spl[0]);
		n = Integer.parseInt(spl[1]);

		arr = new int[k];
		int max = 0;
		for (int i = 0; i < k; i++) {
			str = br.readLine();
			arr[i] = Integer.parseInt(str);
			if (max < arr[i])
				max = arr[i];
		}

		System.out.println(maxLength(1, max + 1l));
	}

	public static long maxLength(long start, long end) { // both start and end are inclusive
		if (start >= end - 1) {
			return start;
		}
		
		long half = (start + end) / 2;
		if (!pass(half)) {
			return maxLength(start, half);
		} else {
			return maxLength(half, end);
		}
	}

	public static boolean pass(long length) {
		long count = 0;
		for (int i = 0; i < arr.length; i++) {
			count += (arr[i] / length);
		}
		return count >= n;
	}

}