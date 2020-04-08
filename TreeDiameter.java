class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int d){
        data = d;
        left = null;
        right = null;
    }
}
public class TreeDiameter {
    TreeNode bToDLL(TreeNode root)
    {
        //  Your code here
        if(root == null)
            return null;
        TreeNode leftHead = bToDLL(root.left);
        TreeNode rightHead = bToDLL(root.right);
        TreeNode curr = leftHead;
        while(curr!=null && curr.right!=null)
            curr = curr.right;
        if(curr!=null)
            curr.right = root;
        root.left = curr;
        root.right = rightHead;
        if(rightHead!=null)
            rightHead.left = root;
        if(leftHead!=null)
            return  leftHead;
        else return root;
    }
    int diameter(TreeNode node)
    {
        if(node==null)
            return 0;
        int lheight = height(node.left);
        int rheight = height(node.right);
        int ldiamter = diameter(node.left);
        int rdiameter = diameter(node.right);
        return Math.max(lheight + rheight+ 1, Math.max(ldiamter, rdiameter));
    }
    int height(TreeNode root){
        if(root==null)
            return 0;
        return  1+ Math.max(height(root.left), height(root.right));
    }

    public static int maxPathSum(TreeNode root)
    {
        // your code here
        if(root==null)
            return 0;
        //for a pointer simulation
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, res);
        return res[0];
    }
    static int maxPathSumUtil(TreeNode root, int[] res){
        if(root==null)
            return 0;
        int l = maxPathSumUtil(root.left, res);
        int r = maxPathSumUtil(root.right, res);
        //when parent is root of max path sum
        int maxForParent = Math.max(Math.max(l,r)+root.data, root.data);
        //when self is root of max path sum, alone or with left and right
        int maxForSelf = Math.max(maxForParent, l+r+root.data);
        res[0] = Math.max(res[0], maxForSelf);
        //NOTE: sending sum from one side to parent,
        //'cause maxforself wont fall in parent's path
        return maxForParent;
    }
}
