import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner (System.in);
		while(scn.hasNextLine()) {
			String ABs=scn.nextLine();
			int A=Integer.valueOf(ABs.charAt(0)-'0');
			int B=Integer.valueOf(ABs.charAt(2)-'0');
			System.out.println(A+B);
		}
	}

}
