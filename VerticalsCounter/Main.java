import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			int n = scan.nextInt();
			int[] verticals =  new int[n];
			for (int i = 0; i < n; i++) {
				verticals[i] = scan.nextInt();
			}
			int res = 0;
			for (int i = 0; i < n - 2; i++) {
				int first = verticals[i];
				int second = verticals[i+1];
				int third = verticals[i+2];
				if((second < first && second < third)
					|| (second > first && second > third)) {
					res++;
				}
			}
			System.out.println(res);
		}
		scan.close();
	}
}