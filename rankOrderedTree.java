/* 
 * Rank Ordered tree provides k min k max 
 * along with binary search tree operations 
 * Worst Case : O(n)
 * How to better : Build a height balanced tree 
 * there for k min / kmax we will have O(logn)
 * 
 */

public class rankOrderedTree{
    private Node root;
    
    private class Node{
        private int data;
        private Node left;
        private Node right;
        private int lNodes;
        private int rNodes;
        
        public Node(int data){
            this.data = data;
            lNodes = 0;
            rNodes = 0;
        }
        
        
    }
    private void printInorder(Node root){
        if (root != null){
            printInorder(root.left);
            System.out.print(root.data+" ");
            printInorder(root.right);
        }
    }
    public void print(){
        print (root);
    }
    private void print(Node root){
        if (root != null){
            print(root.left);
            System.out.println(root .data +" "+ root.lNodes + " "+root.rNodes + " ");
            print (root.right);
        }
    }
    public rankOrderedTree(){};
    
    public void insert(int data){
        root = insert(root,data);
        setCnt(root);
    }
    public void delete(int data){
        delete(root,data);
        setCnt(root);
    }
    private void delete(Node root, int data){
        if (root == null){
            return;
        }
        Node parent = null;
        while (root != null){
            if (root.data == data){
                break;
            }
            else if (root.data > data){
                parent = root;
                root = root.left;
            }
            else{
                parent = root;
                root = root.right;
            }
        }
        if(root == null){
            return ; // the key to delete is not in the binary search tree;
        }
        if (root != null){
            if (parent == null){
                // root node itself nedds to be deleted
                Node tmp = root;
                Node tmpParent = null;
                while(tmp.left != null){
                    tmpParent = tmp;
                    tmp = tmp.left;
                }
                if(tmp != root){
                    root.data = tmp.data;
                    tmpParent.left = null;
                }
                else{
                    tmp = root;
                    tmpParent = null;
                    while(tmp.right != null){
                        tmpParent = tmp;
                        tmp = tmp.right;
                    }
                    if(tmp == root){
                        root = null;
                    }
                    else{
                        root.data = tmp.data;
                        tmpParent.right = null;
                    }
                    
                }
            }
            else if(isLeaf(root)){
                if(parent.left == root){
                    parent.left = null;
                }
                else{
                    parent.right = null;
                }
                // the easy case node to be deleted is leaf
            }
            else if (root.left == null){
                parent.right = root.right;
                // has only right child
            }
            else if (root.right == null){
                // has only leeft child
                parent.left = root.left;
            }
            else {
                // the general case
                Node newTmp = root;
                Node newParent = null;
                while(newTmp .left != null){
                    newParent = newTmp;
                    newTmp = newTmp.left;
                }
                
                root.data = newTmp.data;
                newParent.left = null;
            }
        }
    }
   
    private boolean search(Node root, int data){
        if (root == null){
            return false;
        }
        else if (root.data == data){
            return true;
        }
        else if (root.data < data){
            return search(root.right, data);
            
        }
        else {
            return search (root.left, data);
        }
    }
    public void setCnt(){
        setCnt(root);
    }
    private void setCnt(Node root){
        if(root!= null){
            if (!isLeaf(root)){
                setCnt (root.left);
                setCnt(root.right);
                if(root.left != null)
                    root.lNodes = root.left.lNodes + root.left.rNodes+1;
                if(root.right != null)
                    root.rNodes = root.right.rNodes + root.right.lNodes+1;
            }
                
        }
    }
    public void inorder(){
        printInorder(root);
    }
    private boolean isLeaf(Node root){
        return (root.left == null && root.right == null);
    }
    private Node insert(Node root, int data){
        if (root == null){
            return new Node(data);
        }
        else if(root.data > data){
            root.left = insert(root.left, data);
            return root;
        }
        else {
            root.right = insert(root.right, data);
            return root;
        }
    }
    private int findKmin(Node root, int k){
        if (root == null){
            return Integer.MAX_VALUE;
            
        }
        else if (root.lNodes + 1 == k){
            return root.data;
        }
        else if ((root.lNodes + 1) > k){
            return findKmin(root.left, k);
        }
        else {
            return findKmin(root.right, (k-(root.lNodes + 1)));
        }
    }
    public int findkmin(int k){
        return (findKmin(root, k));
    }
    public int findKmax(int k){
        return findKmax(root,k);
    }
    private int findKmax(Node root, int k){
        if (root == null){
            return Integer.MIN_VALUE;
        }
        else if ( root.rNodes + 1 == k){
            return root.data;
        }
        else if (root.rNodes + 1 > k){
            return findKmax(root.right, k);
        }
        else {
            return findKmax(root.left, (k -(root.rNodes + 1)));
        }
    }
}
