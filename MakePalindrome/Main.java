import java.util.*;
public class Main {
	public static void main(String[] argrs) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String s = scan.nextLine();
			if(s.length() < 3) {
				System.out.println(0);
			}else {
				char[] arrX = s.toCharArray();
				// char[] arrY =  new char[arrX.length];
				// for (int i = 0; i < arrX.length; i++) {
				// 	arrY[arrX.length-1-i] = arrX[i];
				// }
				int lcs = getLcsLength(arrX,arrX.length-1,arrX.length-1);
				System.out.println(s.length() - lcs);
			}
		}
	}

	public static int getLcsLength(char[] arrX,int i,int j) {
		if(i < 0 || j < 0) return 0;
		if(arrX[i] == arrX[arrX.length-1-i]) {
			return getLcsLength(arrX,i-1,j-1) + 1;
		}else {
			return max(getLcsLength(arrX,i,j-1),getLcsLength(arrX,i-1,j));
		}
	}

	public static int max(int a,int b) {
		return a > b ? a : b;
	}
}