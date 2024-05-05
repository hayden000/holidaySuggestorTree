import java.util.*;
import java.io.*;

class TreeNode {
    public String value;
    private TreeNode left;
    private TreeNode right;
    private TreeNode remove;
    private TreeNode payload;

    TreeNode (TreeNode payload) {
        this.payload = payload;
    }

    TreeNode getPayload () {
        return payload;
    }

    public TreeNode (String value) {
        this.value = value;
    }

    public TreeNode (String value , TreeNode left , TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;

    }

    public TreeNode getLeft () {
        return left;
    }

    public TreeNode getRight () {
        return right;
    }

    public void setRight (TreeNode right) {
        this.right = right;
    }

    public void setLeft (TreeNode left) {
        this.left = left;
    }

    public void removeNode (TreeNode remove) {
        this.remove = null;
    }

    TreeNode addLeftChild (String payload) {
        return this.left = new TreeNode(payload);
    }

    TreeNode addRightChild (String payload) {
        return this.right = new TreeNode(payload);
    }


    public Boolean isLeaf() {
        return this.right == null && this.left == null ? true : false;
    }


}