package stack;

import java.util.Stack;

public class SimplifyPath {

    public String simplifyPath(String path) {
        Stack<String> pathStack = new Stack<>();
        String[] stringArray = path.split("/");
        for(String str:stringArray){
            if(str.equals("..")){
                if(!pathStack.isEmpty()){
                    pathStack.pop();
                }
            }else if(!str.equals("")&&!str.equals(".")){
                pathStack.push(str);
            }
        }
        if ( pathStack.isEmpty() ) {
            return "/";
        }
        // 这里用到 StringBuilder 操作字符串，效率高
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < pathStack.size(); i++) {
            ans.append( "/" + pathStack.get(i) );
        }
        return ans.toString();

    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        String str1 = "///home/";
        String str2 = "/../";
        String str3 = "/.../";
        System.out.println(simplifyPath.simplifyPath(str3));
    }
}
