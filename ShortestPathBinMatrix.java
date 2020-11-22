class ShortestPathBinMatrix {
    
     //We need to add this to row and col to travel on a cells 8-directions
    int[][] directions = {{-1,-1},{-1, 0},{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        //If the top left and bottom right cells are not open then return -1
        if(grid[0][0] != 0 || grid[rows - 1][cols - 1] != 0)
            return -1;
        
        //We will perform BFS and add each of the cells that we want to visit in the queue
        Queue<int[]> neighborsQueue = new ArrayDeque<>();
        
        //Since we start from r=0, c=0, we add that cell and Distance = 1. We have to calculate min distance to reach bottom right cell from here.
        //Hence we increment the distance after each BFS traversal level and stop when we reach bottom right
        neighborsQueue.add(new int[]{0, 0, 1});
        
        //Keeps track of whether a cell has been visited or not. Cuz we dnt want to visit same cell twice. Cuz its guaranteed the cells distance will be greater than the first time we saw
        boolean visited[][] = new boolean[rows][cols];
        
        visited[0][0] = true;
        
        while(!neighborsQueue.isEmpty()){
            
            int[] cell = neighborsQueue.poll();
            
            int row = cell[0];
            int col = cell[1];
            int currentDistance = cell[2];
            
            //If we reach the destination then  return the distance, cuz the first time we reach the destination will always be shortest distance
            if(row == rows - 1 && col == cols - 1)
                return currentDistance;
                
                //We check various constraints like we are inside the grid and whether we visited a cell or not. Then add all the neighbouring cells to queue
                //Now we will visit each of its 8 neighbors
                for(int d[] : directions){
                
                    int r = row + d[0];
                    int c = col + d[1];
                    
                    //Check whether the cell is inside the grid and the cell is open (ie not 1)
                    if(r < 0 || c < 0 || r > grid.length - 1 || c > grid[0].length - 1 || grid[r][c] == 1){
                    continue;
                }
        
                    //If a cell is already visited we wont add that to queue
                    if(visited[r][c])
                        continue;
                    
                    //Add neighboring cell along with the distance
                    neighborsQueue.add(new int[]{r, c, currentDistance + 1});
                    
                    //Mark cell as visited
                    visited[r][c] = true;
                    
                }
            
        }
        
        return -1;   
    }
}
