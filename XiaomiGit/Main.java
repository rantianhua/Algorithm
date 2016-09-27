import java.util.*;
public class Main{
    public static void main(String[] args) {
        String[] matrix = {"0000000100","0000001100","0000000100","0000010100","0000000001","0001000010","0100000000","1111000000","0000010001","0000100010"};
        Solution solution = new Solution();
        System.out.println(solution.getSplitNode(matrix,9,7));
    }

    static public class Solution {
    private Stack<Integer> stack = new Stack<>();
    private List<Integer> queue = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    /**
     * 返回git树上两点的最近分割点
     * 
     * @param matrix 接邻矩阵，表示git树，matrix[i][j] == '1' 当且仅当git树中第i个和第j个节点有连接，节点0为git树的跟节点
     * @param indexA 节点A的index
     * @param indexB 节点B的index
     * @return 整型
     */
    class Result {
        int res;
    }
    public int getSplitNode(String[] matrix, int indexA, int indexB) {
        //用标记法，从A开始找它的双亲，找到一个标记一个，直到根结点
        //再从B开始找，也是标记双亲，如果发现这个双亲已经被标记了，则输出结果
        if(indexA == indexB) return indexB;
        boolean[] flags = new boolean[matrix.length];
        for (int i = 0; i < flags.length; i++) {
            flags[i] = i == indexA;
        }
        //先将matrix改为单向图
        makeSingleDirection(matrix,0);
        //从A开始找
        Result res = new Result();
        flagParent(indexA,matrix,false,flags,res);
        if(flags[indexB]) return indexB;
        flagParent(indexB,matrix,true,flags,res);
        return res.res;
    }
    
    public void makeSingleDirection(String[] matrix,int index) {
        String s = matrix[index];
        for(int i = 0;i < s.length();i++) {
            if(s.charAt(i) == '1') {
                stack.push(i);
                String other = matrix[i];
                for(int j = 0;j < other.length();j++) {
                    if(j == index) {
                        sb.append('0');
                    }else {
                        sb.append(other.charAt(j));
                    }
                }
                matrix[i] = sb.toString();
                sb.delete(0,sb.length());
            }
        }
        while(!stack.isEmpty()) {
            makeSingleDirection(matrix,stack.pop());
        }
    }
    
    public void flagParent(int rowIndex,String[] matrix,boolean hasReturn,boolean[] flags,Result res) {
        for(int i = 0;i < matrix.length;i++) {
            String row = matrix[i];
            if(row.charAt(rowIndex) == '1') {
                if(flags[i] && hasReturn) {
                    res.res = i;
                    return;
                }
                flags[i] = true;
                queue.add(i);
            }
        }
        while(queue.size() > 0) {
            int index = queue.remove(queue.size()-1);
            flagParent(index,matrix,hasReturn,flags,res);
        }
   }
    
}
}