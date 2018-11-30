package fp_tree;

import java.util.Iterator;
import java.util.LinkedList;

public class TableEntry {
    /**
     * This is the class implement the table in FP-growth algorithm,
     * The table has many rows, each row contains an item, a number
     * which represent the frequency of the item, and a linked list
     * which links all the same items in the FP-tree. All the rows
     * are sorted according to num.
     */
    private String item;
    private int num;
    private LinkedList<TreeNode> list;

    public TableEntry(String item, int num){
        this.item = item;
        this.num = num;
        this.list = new LinkedList<>();
    }

    public int getNum() {
        return num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Add a node to the linked list
     * @param node
     */
    public void addNode(TreeNode node){
        this.list.add(node);
    }

    /**
     * Allow to traversal the linked list
     * @return iterator
     */
    public Iterator<TreeNode> getIterator(){
        return this.list.iterator();
    }
}
