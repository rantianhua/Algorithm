import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner Scanner = new Scanner(System.in);
		int[][] teris = new int[15][10];
		int[][] cell = new int[4][4];
		while(Scanner.hasNext()) {
			for (int i = 0; i < teris.length; i++) {
				for (int j = 0; j < teris[0].length; j++) {
					teris[i][j] = Scanner.nextInt();
				}
			}
			for (int i = 0; i < cell.length; i++) {
				for (int j = 0; j < cell[0].length; j++) {
					cell[i][j] = Scanner.nextInt();
				}
			}

			int start = Scanner.nextInt();
			start--;

			int step = 15;
			for (int i = start; i < start + 4; i++) {
				int cellJ = 3;
				for (; cellJ >= 0; cellJ--) {
					if (cell[cellJ][i-start] == 1) {
						break;
					}
				}
				int terisJ = 0;
				for (; terisJ < 15; terisJ++) {
					if(teris[terisJ][i] == 1) {
						break;
					}
				}
				int columStep = terisJ - cellJ - 1;
				if (columStep < step) {
					step = columStep;
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (i+step < 15 && cell[i][j] == 1) {
						teris[i+step][start+j] = 1;
					}
				}
			}

			for (int i = 0; i < teris.length; i++) {
				for (int j = 0; j < teris[0].length; j++) {
					if (j == 9) {
						System.out.print(teris[i][j]);
					}else {
						System.out.print(teris[i][j] + " ");
					}
				}
				System.out.println();
			}
		}
	}
}