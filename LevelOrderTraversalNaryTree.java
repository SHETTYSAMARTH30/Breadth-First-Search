/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class LevelOrderTraversalNaryTree {
    public List<List<Integer>> levelOrder(Node root) {
        
        //Stores the nodes in each level
        List<List<Integer>> result = new ArrayList<>();
        
        if(root == null)
            return result;
        
        //to keep track of child nodes that we want to visit
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        
        //Performing BFS
        while(!queue.isEmpty()){
            
            //It will store all the node values which are in same level
            List<Integer> level = new ArrayList<>();
            
            int len = queue.size();
            
            //All the values which are present in the queue will be at the same level
            for(int i = 0; i < len; i++){
                
                //Fetch the current node
                Node curr = queue.poll();
                
                //Add the value in level list
                level.add(curr.val);
                
                //Add all its childrens to queue cuz we want to visit them in next level
                queue.addAll(curr.children);
            }
            
            //Add all the nodes which are in same level into final result list
            result.add(level);
            
        }
        
        return result;
        
    }
}
