package com.jimp2;
//https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
public class TreeNode {
    Integer data;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(Integer data) {this.data = data;}

    public void print() {
        print("|", left, false);
        print("", right, false);
    }

    public void print(String prefix, TreeNode n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.data);
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }
}