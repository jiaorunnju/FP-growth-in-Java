package fp_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class FpTree {
    /**
     * This class is the class to implement the construction
     * of FP-tree
     */

    //table used in FP-Tree
    public ArrayList<TableEntry> table;
    //maps a key to num it appears, easy to look up
    private HashMap<String, Integer> keyToNum;
    //maps a key to idx in table, easy to look up
    private HashMap<String, Integer> keyToIdx;
    //the root node
    private TreeNode root;
    //the iterators needed. Because FP-Growth need to go through the data set twice.
    private Iterator<String> first;
    private Iterator<String> second;
    //the support number to discard unusual items
    private int support;

    public int getSupport() {
        return support;
    }

    //the symbol to split records to get items
    private final String SPLIT = ",";
    //the symbol represent the root node
    private final String ROOT = "ROOT";

    class Tuple{
        /**
         * a simple helper class
         */
        public String item;
        public int num;

        public Tuple(String key, int num){
            this.item = key;
            this.num = num;
        }
    }

    public FpTree(Iterator<String> it1, Iterator<String> it2, int support){
        this.first = it1;
        this.second = it2;
        this.table = new ArrayList<>();
        this.support = support;
        this.keyToNum = new HashMap<>();
        this.keyToIdx = new HashMap<>();
        this.root = new TreeNode(this.ROOT);
    }

    void buildTable(){
        /**
         * This builds the table needed in FP-Growth.
         * Use hashmap to avoid spending too much time
         * on looking up.
         */
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        while (first.hasNext()){
            String s = first.next();
            for(String key: s.split(SPLIT)) {
                int val = map.getOrDefault(key, 0);
                map.put(key, val + 1);
            }
        }
        ArrayList<Tuple> arr = new ArrayList<>();
        for (String key: map.keySet()){
            int num = map.get(key);
            //filter the unusual items
            if (num >= this.support){
                arr.add(new Tuple(key, num));
            }
        }
        //descending sort
        arr.sort((Tuple t1, Tuple t2)->{
            if (t1.num <= t2.num)
                return 1;
            else
                return -1;
        });

        int idx = 0;
        for(Tuple t: arr){
            this.table.add(new TableEntry(t.item, t.num));
            this.keyToNum.put(t.item, t.num);
            this.keyToIdx.put(t.item, idx);
            idx += 1;
        }
        /*
        for(TableEntry e: table){
            System.out.println(e.getItem()+ " "+ e.getNum());
        }*/
    }

    void buildTree(){
        /**
         * This builds the tree.
         */
        while(second.hasNext()){
            String s = second.next();
            String[] arr = s.split(SPLIT);
            //descending sort according to num
            Arrays.sort(arr, (String s1, String s2)->{
                int a = this.keyToNum.getOrDefault(s1, 0);
                int b = this.keyToNum.getOrDefault(s2, 0);
                if (a <= b)
                    return 1;
                else
                    return -1;
            });

            //current node
            TreeNode curr = root;
            for (String item: arr){
                if (!keyToNum.containsKey(item))
                    continue;
                if(!curr.containChild(item)){
                    TreeNode node = curr.addChild(item);
                    //change the current node
                    curr = node;
                    //add new node in table
                    TableEntry e = table.get(keyToIdx.get(item));
                    e.addNode(node);
                }else{
                    curr = curr.getChild(item);
                    curr.setNum(curr.getNum()+1);
                }
            }
        }
        /*
        this.root.print();
        for(TableEntry e: table){
            Iterator<TreeNode> it = e.getIterator();
            while(it.hasNext()){
                System.out.print(it.next().getItem()+" ");
            }
            System.out.println();
        }
        */
    }
}