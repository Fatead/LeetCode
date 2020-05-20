import java.util.ArrayList;
import java.util.List;

public class GraphFindCycle {
    public List servList;

    public void setServList(List servList){
        this.servList = servList;
    }

    class ServRel{
        public String useServId;
        public String providerServId;
        public double amount;
    }

    class Serv{
        public String servId;
        public List refServ = new ArrayList();
    }

    public List findRefServ(String servId,List servRelList){
        List refServList = new ArrayList();
        for(int i=0;i<servRelList.size();i++){
            ServRel rel = (ServRel)servRelList.get(i);
            if(servId.equals(rel.useServId)){
                refServList.add(rel.providerServId);
            }
        }
        return refServList;
    }

    class CycleServ{
        public List cycleServList;
    }

    public void findCycleServ(String servId,String cyclePath,List servRelList){
        String id = servId;
        List refServList = findRefServ(servId,servRelList);
        if(!(refServList.size()>0)){
            return;
        }
        else if(cyclePath.indexOf(servId)>0){
            System.out.println(cyclePath.substring(cyclePath.indexOf(servId))+"->"+id);
            return;
        }
        else{
            cyclePath = cyclePath+"->"+id;
            for(int j=0;j<refServList.size();j++){
                String childServId = (String)refServList.get(j);
                findCycleServ(childServId,cyclePath,servRelList);
            }
        }
    }


    public static void main(String[] args) {
        GraphFindCycle gfc = new GraphFindCycle();
        List servList = new ArrayList();
        servList.add("A");
        servList.add("B");
        servList.add("C");
        servList.add("D");
        servList.add("E");
        servList.add("F");

        List servRelList = new ArrayList();
        ServRel sr = gfc.new ServRel();
        sr.useServId = "A";
        sr.providerServId = "B";
        servRelList.add(sr);
        ServRel sr1 = gfc.new ServRel();
        sr1.useServId = "B";
        sr1.providerServId = "D";
        servRelList.add(sr1);
        ServRel sr2 = gfc.new ServRel();
        sr2.useServId = "D";
        sr2.providerServId = "C";
        servRelList.add(sr2);
        ServRel sr3 = gfc.new ServRel();
        sr3.useServId = "C";
        sr3.providerServId = "E";
        servRelList.add(sr3);
        ServRel sr4 = gfc.new ServRel();
        sr4.useServId = "E";
        sr4.providerServId = "F";
        servRelList.add(sr4);
        ServRel sr5 = gfc.new ServRel();
        sr5.useServId = "A";
        sr5.providerServId = "C";
        servRelList.add(sr5);

        //System.out.println(servRelList.size());
        for(int i=0;i<servList.size();i++){
            String servId = (String)servList.get(i);
            gfc.findCycleServ(servId,"",servRelList);
        }
    }

}