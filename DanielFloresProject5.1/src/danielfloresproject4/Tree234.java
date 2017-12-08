/*
******************************************************
*** Project 5 - Item
*** Daniel Flores
******************************************************
*** Created as part of project 5
******************************************************
*** October 17, 2017
******************************************************
*** Original, no changes
******************************************************
 */
package danielfloresproject4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree234
{
    private TreeNode root = new TreeNode();
    
    public void insert(String dValue)
    {
        TreeNode curNode = root;
        Item tempItem = new Item(dValue);
        
        while(true)
        {
            if (curNode.isFull())
            {
                split(curNode);
                curNode = curNode.getParent();
                curNode = getNextChild(curNode, dValue);
            }
            else if (curNode.isLeaf())
                break;
            else
                curNode = getNextChild(curNode, dValue);
        }
        
        curNode.insertItem(tempItem);
    }
    
    public void split(TreeNode thisNode)
    {
        Item itemB, itemC;
        TreeNode parent, child2, child3;
        int itemIndex;
        
        itemC = thisNode.removeItem();
        itemB = thisNode.removeItem();
        child2 = thisNode.disconnectChild(2);
        child3 = thisNode.disconnectChild(3);
        
        TreeNode newRight = new TreeNode();
        
        if (thisNode == root)
        {
            root = new TreeNode();
            parent = root;
            root.connectChild(0, thisNode);
        }
        else
        {
            parent = thisNode.getParent();
        }
        
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();
        
        for (int i = n-1; i > itemIndex; i--)
        {
            TreeNode temp = parent.disconnectChild(i);
            parent.connectChild(i + 1, temp);
        }
        
        parent.connectChild(itemIndex+1, newRight);
        
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);    
    }
    
    public TreeNode getNextChild(TreeNode theNode, String theValue)
    {
        int i;
        int numItems = theNode.getNumItems();
        
        for (i=0; i<numItems; i++)
        {
            if (theValue.compareTo(theNode.getItem(i).dData) < 0)
            {
                return theNode.getChild(i);
            }
        }
        return theNode.getChild(i);
    }
    
    public String displayTree()
    {
        String toDisplay;
        toDisplay = reDisplayTree(root, 0, 0);
        return toDisplay;
    }
    
    private String reDisplayTree(TreeNode thisNode, int level, int childNum)
    {
        String tempString;
        tempString = ("Level= " + level + "  Child= " + childNum);
        thisNode.displayNode();
        int numItems = thisNode.getNumItems();
        
        for (int j=0; j<numItems+1; j++)
        {
            TreeNode nextNode = thisNode.getChild(j);
            
            if(nextNode != null)
            {
                reDisplayTree(nextNode, level+1, j);
            }
            else
            {
                return tempString;
            }
        }
        
        return tempString;
    }
    
    public String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    
    public char getChar()throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }
    
    public int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}
