package tree;

public class ValidTreeSerialization {

    public boolean isValidSerialization(String preorder) {
        String[] strings = preorder.split(",");
        int slotNum = 1;
        for(String str:strings){
            slotNum--;
            if(slotNum<0)return false;
            if(!str.equals("#")){
                slotNum+=2;
            }
        }
        return slotNum == 0;
    }

    public static void main(String[] args) {
        ValidTreeSerialization validTreeSerialization = new ValidTreeSerialization();
        String preOrder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean result = validTreeSerialization.isValidSerialization(preOrder);
        System.out.println(result);
    }

}
