package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubDomainVisits {

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String,Integer> countMap = new HashMap<>();
        for(String domain:cpdomains){
            String[] parts = domain.split(" ");
            int counts = Integer.parseInt(parts[0]);
            String[] sites = parts[1].split("\\.");
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = sites.length-1;i>=0;i--){
                if(stringBuilder.length() == 0){
                    stringBuilder.append(sites[i]);
                    countMap.put(stringBuilder.toString(), countMap.getOrDefault(stringBuilder.toString(),0)+counts);
                }else {
                    StringBuilder stringBuilder1 = new StringBuilder(sites[i]);
                    stringBuilder1.append(".").append(stringBuilder);
                    stringBuilder = stringBuilder1;
                    //stringBuilder.append(".").append(sites[i]);
                    countMap.put(stringBuilder.toString(), countMap.getOrDefault(stringBuilder.toString(),0)+counts);
                }
            }
        }
        List<String> result = new ArrayList<>();
        for(String site:countMap.keySet()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(countMap.get(site)).append(" ").append(site);
            result.add(stringBuilder.toString());
        }
        return result;
    }

    public static void main(String[] args) {
        SubDomainVisits subDomainVisits = new SubDomainVisits();
        String[] websites = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subDomainVisits.subdomainVisits(websites));
    }

}
