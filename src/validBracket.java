import java.util.Stack;

public class validBracket {

    public boolean isValid(String s) {
        Stack<Character> bracketStack = new Stack<>();
        char[] charArray = s.toCharArray();
        if(s.length()%2==1)return false;
        else {
            for(int i = 0;i<charArray.length;i++){
                char c = charArray[i];
                if(c=='('||c=='['||c=='{'||bracketStack.size()==0){
                    bracketStack.push(c);
                }else if(c==')'&&bracketStack.peek()=='('){
                    bracketStack.pop();
                }else if(c==']'&&bracketStack.peek()=='['){
                    bracketStack.pop();
                }else if(c=='}'&&bracketStack.peek()=='{'){
                    bracketStack.pop();
                }
            }
            if(bracketStack.size() == 0)return true;
            else return false;
        }
    }


    public static void main(String[] args) {
        validBracket valid = new validBracket();
        String str = "{}[]()";
        String str2 = ")[";
        System.out.println(valid.isValid(str));
        System.out.println(valid.isValid(str2));
    }

}
