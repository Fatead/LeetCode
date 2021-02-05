package string;

public class ReplaceSpace {

    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }


    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();
        System.out.println(replaceSpace.replaceSpace("We are happy."));

    }

}
