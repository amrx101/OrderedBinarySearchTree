/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class testRank {
    public static void main(String[] args) {
        rankOrderedTree my = new rankOrderedTree();
        int [] arr = {22,12,4,34,23,5};
        for(int i = 0 ; i< arr.length; i++){
            my.insert(arr[i]);
        }
        my.inorder();
        System.out.println("");
        my.print();
        System.out.println("");
        System.out.println(my.findkmin(3));
        System.out.println();
        System.out.println(my.findKmax(3));
    }
    
    
}
