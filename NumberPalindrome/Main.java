import java.util.*;
import java.lang.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i<n; i++) {
                arr[i] = scan.nextInt();
            }
            int times = calculateMinPalindromeTimes(arr);
            System.out.println(times); 
        }
    }

    public static int calculateMinPalindromeTimes(int[] arr) {
        int res = 0;
        int head = 0;
        int tail = arr.length - 1;
        while(head < tail) {
            if(arr[head] < arr[tail]) {
                head++;
                arr[head] = arr[head] + arr[head - 1];
                res++;
            } else if(arr[head] > arr[tail]) {
                tail--;
                arr[tail] = arr[tail] + arr[tail + 1];
                res++;
            } else {
                head++;
                tail--;
            }
        }
        return res;
    }
}