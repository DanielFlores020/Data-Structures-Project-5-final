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



public class TreeNode
{
    private static final int Order = 3;
    private int numItems;
    private TreeNode parent;
    private TreeNode childArray[] = new TreeNode[Order];
    private Item itemArray[] = new Item[Order - 1];
    
    public void connectChild(int childNum, TreeNode child)
    {
        childArray[childNum] = child;
        
        if (child != null)
        {
            child.parent = this;
        }
    }
    
    public TreeNode disconnectChild(int childNum)
    {
        return childArray[childNum];
    }
    
    public TreeNode getChild(int childNum)
    {
        TreeNode tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }
    
    public TreeNode getParent()
    {
        return parent;
    }
    
    public boolean isLeaf()
    {
        return (childArray[0] == null) ? true:false;
    }
    
    public int getNumItems()
    {
        return numItems;
    }
    
    public Item getItem(int index)
    {
        return itemArray[index];
    }
    
    public boolean isFull()
    {
        return (numItems == Order - 1) ? true:false;
    }
    
    public int insertItem(Item newItem)
    {
        numItems++;
        String newKey = newItem.dData;
        
        for (int i = Order - 2; i >= 0; i++)
        {
            if (itemArray[i] == null)
            {
                continue;
            }
            else
            {
                String curKey = itemArray[i].dData;
                
                if (newKey.compareTo(curKey) < 0)
                {
                    itemArray[i + 1] = itemArray[i];
                }
                else
                {
                    itemArray[i + 1] = newItem;
                    return i + 1;
                }
            }
        }
        
        itemArray[0] = newItem;
        return 0;
    }
    
    public Item removeItem()
    {
        Item temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
        return temp;
    }
    
    public String displayNode()
    {
        String tempDisplay = "";
        for (int i = 0; i < numItems; i++)
        {
            tempDisplay += itemArray[i].displayItem();
        }
        
        return tempDisplay;
    }
}
