package fp_tree;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        data.add("a,b,f");
        data.add("b,c,d,f");
        data.add("d,e,g");
        data.add("d,e,m");
        data.add("a,b,c,m");
        data.add("a,b,c,d,n");
        data.add("a,p");
        data.add("a,b,c,d");
        data.add("a,b,d");
        data.add("b,c,e");

        FpGrowth algo = new FpGrowth(data.iterator(), data.iterator(), 3);
        algo.getFrequentItemSet();
    }
}
