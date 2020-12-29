package array;

import java.util.*;

public class EmployImportance {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };


    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Integer> id2importanceMap = new HashMap<>();
        Map<Integer,Employee> id2employMap = new HashMap<>();
        Employee employ = null;
        for(Employee employee:employees){
            id2importanceMap.put(employee.id,employee.importance);
            id2employMap.put(employee.id,employee);
            if(employee.id == id){
                employ = employee;
            }
        }
        int sum = 0;
        Queue<Employee> queue = new LinkedList<>();
        queue.add(employ);
        while(!queue.isEmpty()){
            Employee employee1 = queue.poll();
            sum += employee1.importance;
            for(int i:employee1.subordinates){
                queue.add(id2employMap.get(i));
            }
        }
        return sum;
    }


    public static void main(String[] args) {

    }
}
