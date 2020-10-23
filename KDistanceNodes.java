/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class KDistanceNodes {
    
    Map<TreeNode, TreeNode> parent;
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        
        parent = new HashMap();
        
        //We will consider this tree as a acyclic graph and perform BFS traversal to get all the nodes at level K
        
        List<Integer> result = new ArrayList<>();
        
        //We will perform preorder traversal and map (node,parent)
        dfs(root,null);
        
        //So that we dont add same node twice in queue
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        
        //Store the neighboring nodes in graph
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        
        while(!queue.isEmpty()){
            
            //Will store no of nodes at current level
            int size = queue.size();
            
            if(K == 0){
                
                for(TreeNode t : queue){
                    
                    result.add(t.val);
                }
                
                return result;
                
            }
            else{
                
                //We will traverse all the neighbors of a given node -> left child, right child, parent node
                for(int i = 0; i < size; i++){
                    
                    TreeNode curr = queue.poll();
                    
                    TreeNode leftChild = curr.left;
                    
                    //If node is not visited add it to queue and visited
                    if(leftChild != null && !visited.contains(leftChild)){
                        
                        visited.add(leftChild);
                        queue.add(leftChild);
                    }
                    
                    TreeNode rightChild = curr.right;
                    
                    if(rightChild != null && !visited.contains(rightChild)){
                        
                        visited.add(rightChild);
                        queue.add(rightChild);
                    }
                    
                    TreeNode par = parent.get(curr);
                    
                    if(par != null && !visited.contains(par)){
                        
                        visited.add(par);
                        queue.add(par);
                    }
                }
                
                //We decrement the level
                K--;
            }
            
        }
        
        return result;
        
    }
    
    public void dfs(TreeNode node,TreeNode par){
        
        if(node != null){
            
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
    
}
