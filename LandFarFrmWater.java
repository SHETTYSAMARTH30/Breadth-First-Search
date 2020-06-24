class LandFarFrmWater {
    public int maxDistance(int[][] grid) {
        
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                
                if(grid[i][j] == 1){
                 
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
                
            }
        }

        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int levels = -1;
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i=0; i < size; i++){
                
                int[] start = queue.poll();
                int x= start[0];
                int y= start[1];
                
                for(int dir[] : directions){
                    
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                      && !visited[newX][newY] && grid[newX][newY] == 0){
                        
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX,newY});
                    }
                        
                } 
            }
            
            levels++;
        }
        
        return levels <= 0 ? -1 : levels;
    }
}
