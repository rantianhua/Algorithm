    /**
    * 将要解析的路径按/拆分，去掉.
    * 如果路径开头是/，则去掉..以及它前面的一个目录后直接输出结果
    * 如果路径开头不是/，则将当前路径入栈，然后逐个判断，如果不是 ..，
    * 直接入栈，如果是，则进行一次出栈
    **/

    import java.util.*;
    import java.lang.*;

    public class Main {
        public static void main(String[] args) {
            Scanner scan  = new Scanner(System.in);
            List<String> subPathes = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            Stack<String> stack = new Stack<>();
            int num = scan.nextInt();
            scan.nextLine();
            String curPath = scan.nextLine();
            for (int i = 0; i < num; i++) {
                String path = scan.nextLine();
                    //空字符串直接输出当前路径
                if(path.length() == 0) {
                    System.out.println(curPath);
                    continue;
                }
                    //去除路径中多余的/，并将路径按照/拆分
                subPathes.clear();
                sb.delete(0,sb.length());
                char[] arr = path.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char c = arr[j];
                    if(c == '/' && j != arr.length-1) {
                        String name = sb.toString();
                        if (name.length() > 0 && !name.equals(".")) {
                           subPathes.add(name);
                       }
                       sb.delete(0,sb.length());
                   }else if(c != '/'){
                    sb.append(c);
                }
            }
            if(sb.toString().length() > 0) {
                subPathes.add(sb.toString());
                sb.delete(0,sb.length());
            }
            if(path.startsWith("/")) {
                         //去除..后直接输出结果
                for (int k = 0; k < subPathes.size(); k++) {
                    if(subPathes.get(k).equals("..")) {
                        subPathes.remove(k);
                        k--;
                        if(k >= 0) {
                            subPathes.remove(k);
                            k--;
                        }
                    }
                }
                printResult(subPathes);
            }else {
                        //先将当前目录入栈
                String[] curPathes = curPath.split("/");
                for (String s : curPathes) {
                    if(s.length() > 0) stack.push(s);
                }
                        //构建要解析的路径
                for (String s : subPathes) {
                    if(s.equals("..")) {
                                //出栈
                        if(!stack.isEmpty()) {
                            stack.pop();
                        }
                    }else {
                                //直接入栈
                        stack.push(s);
                    }
                }
                        //输出结果
                subPathes.clear();
                while(!stack.isEmpty()) {
                    subPathes.add(0,stack.pop());
                }
                printResult(subPathes);
            }
        }
        scan.close();
    }

    public static void printResult(List<String> result) {
        if(result.size() == 0) {
            System.out.println("/");
        }else {
            for (String s : result) {
                if(s.equals(".")) continue;
                System.out.print("/" + s);
            }
            System.out.println();
        }
    }
}