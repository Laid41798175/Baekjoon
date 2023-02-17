import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		String Ns = scn.nextLine();
		int N = Integer.parseInt(Ns);
		String[] worda = new String[N];
		int[] digita = new int[50];

		for (int i = 0; i < N; i++) {
			String word = scn.nextLine();
			worda[i] = word;
			digita[word.length() - 1]++;
		}
		for (int i = 0; i < digita.length; i++) {
			String[] arr = new String[digita[i]];
			int k = 0;
			for (int j = 0; j < worda.length; j++) {
				if (worda[j].length() == i + 1) {
					arr[k] = worda[j];
					k++;
				}
			}
			Arrays.sort(arr);
			if(arr.length==0) {
				continue;
			} else {
				System.out.println(arr[0]);
				for (int j = 1; j < arr.length; j++) {
					if (!arr[j].equals(arr[j - 1])) {
						System.out.println(arr[j]);
					}
				}
			}
		}

	}

}