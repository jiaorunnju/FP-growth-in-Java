package fp_tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FpGrowth {
    /**
     * This is a class implement the function of a Fp-growth algorithm with
     * the help of Fp-tree
     */

    private FpTree tree;

    public FpGrowth(Iterator<String> it1, Iterator<String> it2, int support){
        this.tree = new FpTree(it1, it2, support);
        tree.buildTable();
        tree.buildTree();
    }

    /**
     * This function returns the frequent item set. Each row of the result
     * contains a set of items. The frequent item set of that row is the power set
     * of items in that row.
     * Example: a row is a,b, then the frequent item set is {a},{b},{a,b}
     * @return frequent item set
     */
    public ArrayList<ArrayList<String>> getFrequentItemSet(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(int i=0;i<this.tree.table.size();++i){
            Iterator<TreeNode> iter = this.tree.table.get(i).getIterator();
            HashMap<String,Integer> map = new HashMap<>();
            while(iter.hasNext()){
                TreeNode curr = iter.next();
                int base = curr.getNum();

                while (!curr.isRoot()){
                    map.put(curr.getItem(),map.getOrDefault(curr.getItem(),0)+base);
                    curr = curr.getFather();
                }
            }

            ArrayList<String> temp = new ArrayList<>();
            for(String key: map.keySet()){
                if (map.get(key) >= tree.getSupport()){
                    temp.add(key);
                }
            }
            result.add(temp);
        }
        /*
        for(ArrayList<String> arr:result){
            for(String s:arr){
                System.out.print(s+" ");
            }
            System.out.println();
        }
        */
        return result;
    }
}
