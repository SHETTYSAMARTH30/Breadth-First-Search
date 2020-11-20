class RottingOranges {
    public int orangesRotting(int[][] grid) {
        
        //We use BFS method of (-1,-1)
        int rows = grid.length;
        int cols = grid[0].length;

        int freshOranges = 0;
        
        //Stores the row,col of rotten oranges
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        
        //We need to count the number of fresh oranges, cuz at end if its not 0 then we return -1
        //If we find a rotten orange we add that to queue, cuz we do BFS from that cell and rot the other fresh oranges
        for(int i = 0; i < rows; i++){
            
            for(int j = 0; j < cols; j++){
                
                //Add the dim of rotten oranges
                if(grid[i][j] == 2)
                    queue.add(new Pair(i,j));
                
                //Count fresh oranges
                else if(grid[i][j] == 1)
                    freshOranges++;
            
            }
        }
        
        //Marking the end of each level
        queue.add(new Pair(-1, -1));
        
        //Total time taken to turn entire grid of fresh oranges rotten.
        //After each iteration of BFS we increment it.
        int timeElapsed = 0;
        
        //We add this to go -> top,right,bottom,left
        int directions[][] = {{-1,0},{0,1},{1,0},{0,-1}};
        
        while(!queue.isEmpty()){
            
            Pair<Integer, Integer> p = queue.poll();
            
            int row = p.getKey();
            int col = p.getValue();
            
            //If we reach end of a level
            if(row == -1){
            
                //If we have rotten oranges in the queue on which we have to do BFS then put (-1,-1) cuz we know all the current rotten are at same level
                if(!queue.isEmpty()){
                    
                    //Time elapsed = num of jumps from one level to next level
                    timeElapsed++;
                    queue.add(new Pair(-1, -1));
                }
                //else we have nothing rotten left hence we BFS reqd further
            }
            else{
                
                //We check whether the neighboring cells are fresh or not
                for(int d[] : directions){
                    
                    int r = row + d[0];
                    int c = col + d[1];
                    
                     //If a neighbor is fresh, then we rot that
                    if(r >= 0 && r < rows && c >= 0 && c < cols){
                        
                        if(grid[r][c] == 1){
                            
                             //Rotting the fresh orange, we mark it 2 so that we know its already rotten before and we dont count that again (mark visited)
                            grid[r][c] = 2;
                            
                            freshOranges--;
                            
                             //Since new cell is rotten, we will rot its neighboring fresh orange in future
                            queue.add(new Pair(r,c));
                        }
                    }
                }
            }
            
           
        }
        
        //If all the freshOranges are gone then return time elapsed hence -1
        return freshOranges == 0? timeElapsed : -1;
        
    }
}
