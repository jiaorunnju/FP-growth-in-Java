package fp_tree;

import java.util.*;


public class TreeNode {
    /**
     * This class represents the node in the FP-tree. It contains
     * item, num and maintains a pointer to its father, also a set
     * of its child.
     */

    private String item;
    private int num;

    public String getItem() {
        return item;
    }

    private TreeNode father;
    private HashMap<String, TreeNode> child;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }

    /**
     * check whether contains a child
     * @param key
     * @return true if contain, else false
     */
    public boolean containChild(String key){
        return this.child.containsKey(key);
    }

    /**
     * get the child according to key
     * @param key
     * @return child
     */
    public TreeNode getChild(String key){
        return this.child.get(key);
    }

    /**
     * add a child to the current node
     * @param key
     * @return the node added
     */
    public TreeNode addChild(String key){
        TreeNode node = new TreeNode(key);
        this.child.put(key, node);
        return node;
    }

    /**
     * print the tree structure from top to down, recursively.
     */
    public void print(){
        System.out.print(this.item + " " + this.num +": ");
        for(String s: child.keySet()){
            System.out.print(s+",");
        }
        System.out.println();
        for(String s: child.keySet()){
            child.get(s).print();
        }
    }

    public TreeNode(String item){
        this.item = item;
        this.child = new HashMap<>();
        this.num = 1;
    }


}
