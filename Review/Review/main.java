package Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<String> orders = new ArrayList<>();
        orders.add("iPhone");
        orders.add("Macbook");
        orders.add("");
        orders.add("iPad");
        orders.add("Macbook");
        orders.add(" ");
        orders.add(null);
        orders.add("iPhone");
        orders.add("AirPods");
        orders.add("iPhone");
        System.out.println(orders);
        Iterator<String> it = orders.iterator();
        while (it.hasNext()){
            String str = it.next();
            if (str==null || str.trim().isEmpty()){it.remove();}
        }
        System.out.println(orders);
        List <String> counted = new ArrayList<>();

        for (String name:orders){
            if (!counted.contains(name)){
                int count =0;
                for (String item:orders){
                    if (name.equals(item)){
                        count++;
                    }
                    if (name.equals("Macbook") && count<2){
                        System.out.println("Macbook sale chậm!");
                    }
                }
                System.out.println(name+" : "+count+" đơn hàng");
                counted.add(name);
            }


        }
    }
}
