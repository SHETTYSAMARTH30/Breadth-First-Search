class OpenLock {
    public int openLock(String[] deadends, String target) {
        
        //We will store all the deadends in the set
        Set<String> dead = new HashSet<>();
        
        for(String s : deadends){
            dead.add(s);
        }
        
        //We consider it lock codes as the nodes in the graph. Where each digit in the 4 locks that differ by 1 will be adjacent to each other.
        //Eg:-   9000, 0900, 0090, 0009 <- 0000 -> 1000, 0100, 0010, 0001. Similarly for other nodes. So when we go from 1 level to another thats 1 move. So we perform BFS and count moves until we reach target
        
        //List of nodes we are performing BFS on
        Queue<String> queue = new ArrayDeque<>();
        
        //Start BFS from 0000 to target
        queue.add("0000");
        
        //It contains all the nodes that we have visited before
        Set<String> visited = new HashSet<>();
        
        visited.add("0000");
        
        int minMoves = 0;
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            //We will travel the graph level wise 
            for(int i = 0; i < size; i++){
                
                String lock = queue.poll();
                
                //If we reach target return moves until then
                if(target.equals(lock))
                    return minMoves;
                
                //If we hit deadend then ignore
                else if(dead.contains(lock))
                        continue;
                
                //Perform BFS
                else{
                    
                    //We increment(+1) and decrement(-1) each digit of lock and add them queue if not visited.
                    //Each lock code will have 8 neighbors (4-inc , 4-dec)
                    for(int j = 0; j < 4; j++){
                        
                        //Get current digit 
                        char ch = lock.charAt(j);
                        
                        //We do -1 and +1 each time to each digit
                        for(int d = -1; d <= 1; d += 2){
                            
                            //Cal next digit. We do +10 if we go to -1 frm 0 we get 9. We do %10 cuz if we go frm 9 to 10 we get 0
                            int nextDigit = ((ch - '0') + d + 10) % 10;
                            
                            String s = lock.substring(0, j) + nextDigit + lock.substring(j + 1);
                            
                            //If we havent seen the node yet add to queue
                            if(!visited.contains(s)){
                                
                                queue.add(s);
                                visited.add(s);
                            }
                        }
                        
                    }
                    
                }
            }
            
            minMoves++;
        }
        
        return -1;   
    }
}
