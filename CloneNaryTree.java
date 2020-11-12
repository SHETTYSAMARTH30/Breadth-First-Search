/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class CloneNaryTree {
    public Node cloneTree(Node root) {
        
        //Breadth first search
        
        if(root == null)
            return null;
        
        //Stores the original node, clone node
        Map<Node, Node> map = new HashMap<>();
        
        map.put(root, new Node(root.val));
        
        //Stores the childrens of the node we want to visit next
        Deque<Node> queue = new ArrayDeque<>();
        
        queue.add(root);
        
        while(!queue.isEmpty()){
            
            //Fetch the node whose neighbors we want to visit
            Node curr = queue.poll();
            
            //Visit all the neighbors of that node (BFS)
            for(Node next : curr.children){
                
                //If a child node is not visited before
                if(!map.containsKey(next)){
                    
                    //Create a clone of the child and put in map
                    map.put(next, new Node(next.val));
                    
                    //We want to visit the child nodes children in future
                    queue.add(next);
                }
                
                //Add the cloned child node in the list of cloned current node
                map.get(curr).children.add(map.get(next));
            }
            
        }
        
        //Return clone of root node, as all other nodes are connected to that clone
        return map.get(root);  
    }
}
