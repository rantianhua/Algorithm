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
				char[] arrY =  new char[arrX.length];
				for (int i = 0; i < arrX.length; i++) {
					arrY[arrX.length-1-i] = arrX[i];
				}
				int lcs = getLcsLength(arrX,arrY);
				System.out.println(s.length() - lcs);
			}
		}
	}

	public static int getLcsLength(char[] arrX,char[] arrY) {
		int len = arrY.length;
		int[][] tags = new int[len+1][len+1];
		for (int i = 0; i<len; i++) {
			for (int j = 0; j < len; j++) {
				if(arrX[i] == arrY[j]) {
					tags[i+1][j+1] = tags[i][j] + 1;
				}else{
					tags[i+1][j+1] = max(tags[i+1][j],tags[i][j+1]);
				}
			}
		}
		return tags[len][len];
	}

	public static int max(int a,int b) {
		return a > b ? a : b;
	}
}