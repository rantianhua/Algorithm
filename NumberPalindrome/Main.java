/**
* 1. 任选两个数求和构造新的序列，一共有n-1种
* 2. 判断新构造的序列是否可以符合回文序列的要求，只有一个数字可以出现奇数次，其他数字必须出现偶数，或者没有奇数次的数字
* 3. 计算变成回文序列需要的操作数。
* 4. 在所有之前计算的结果中输出最小的一个
**/
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
            if(n < 2) System.out.println(0); 
            int times = calculateMinPalindromeTimes(arr);
            System.out.println(times); 
        }
    }

    public static int calculateMinPalindromeTimes(int[] arr) {
        int res = Integer.MAX_VALUE;
        if(canBePalindrome(arr)) {
            res = timesToBePalindrome(arr);
        }
        int n = arr.length;
        //构造新的序列
        int[] newArr = new int[n-1];
        int sumIndex = -1;
        int newNum = -1;
        for (int i  = 0; i < n-1; i++) {
            makeNewArr(arr,newArr,i);
            if(canBePalindrome(newArr)) {
                //计算变成回文序列需要的步骤
                int times = timesToBePalindrome(newArr);
                times++;
                if (times < res) {
                    res = times;
                }
            }
        }
        return res;
    }

    public static void makeNewArr(int[] oldArr,int[] newArr,int changeIndex) {
        int index = 0;
        for (int i  = 0; i < oldArr.length; i++) {
            if (i == changeIndex) {
                newArr[index++] = oldArr[i] + oldArr[i+1];
                i++;
            }else {
                newArr[index++] = oldArr[i];
            }
        }
    }

    public static boolean canBePalindrome(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int appearTimes;
            if(map.containsKey(arr[i])) {
                appearTimes = map.get(arr[i]);
                appearTimes++;
            }else {
                appearTimes = 1;
            }
            map.put(arr[i],appearTimes);
        }
        //找出出现奇数次的数的个数
        int oddTimes = 0;
        for (int value : map.values()) {
            if(value % 2 != 0) {
                oddTimes++;
            }
        }
        return oddTimes == 0 || oddTimes % 2 != 0;
    }

    //变成回文的操作
    public static int timesToBePalindrome(int[] arr) {
        int times = 0;
        int targetIndex = arr.length / 2;
        for (int i = 0;i < targetIndex;i++) {
            int oppsite = arr.length-1-i;
            if(arr[oppsite] != arr[i]) {
                times++;
                int exChangeIndex = findExChangeIndex(i+1,oppsite-1,arr);
                int temp = arr[oppsite];
                arr[oppsite] = arr[exChangeIndex];
                arr[exChangeIndex] = temp;
            }
        }
        return times;
    }

    public static int findExChangeIndex(int start,int end,int[] arr) {
        for (int i = start;i<=end;i++) {
            if (arr[start-1] == arr[i]) {
                return i;
            }
        }
        return 0;
    }
}