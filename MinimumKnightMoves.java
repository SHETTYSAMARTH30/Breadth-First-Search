class Solution {
    public int minKnightMoves(int x, int y) {
        
        if (x == 1 && y == 1)
			return 2;

        //As we can see from fig, a horse can move in 8 directions, so we will add those lengths each time and perform BFS
        int[][] directions = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        
        //Div -INF to +INF matrix into 4 quadrants with 0,0 as center. 1st - +ve,+ve, 2nd -ve, +ve, 3rd - -ve,-ve, 4th +ve,-ve
        //The num of moves to reach a particular pt in 1st quad == same pt at some other quadrant. Eg- x=2, y=1 and x=-2, y=-1 will be same
        //So we will only check in 1st quadrant and find ans and the values of x and y will be between 0 to 300
        
        //Get the positive values (1st quadrant)
        x = Math.abs(x);
        y = Math.abs(y);
        
        //Add the next positions that we want to perform BFS on in queue
        Queue<int[]> queue = new ArrayDeque<>();
        
        //We start from 0,0 and try to reach x,y
        queue.add(new int[]{0, 0});
        
        //To keep a track of whether a co-ordinate is visited or not. (Dont think of this as 2d matrix ie row,col , this is just for keeping track of coordinate)
        boolean visited[][] = new boolean[301][301];
        visited[0][0] = true;
        
        int minMoves = 0;
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            //Perform BFS
            for(int i = 0; i < size; i++){
                
                int[] cell = queue.poll();
                
                //If we have reached destination then stop BFS and return moves. And it will be minimum moves cuz this is first time we faced this co-ordinate
                if(x == cell[0] && y == cell[1])
                    return minMoves;
                
                //We will move on all the 8 directions and add the cells to queue
                for(int d[] : directions){
                    
                    int newX = cell[0] + d[0];
                    int newY = cell[1] + d[1];
                    
                    //Check whether they are inside the grid
                    if(newX < 0 || newY < 0 || newX > 300 || newY > 300)
                        continue;
                    
                    //Check whether they are visited or not
                    if(!visited[newX][newY]){
                        
                        visited[newX][newY] = true;
                        
                        //Add co-ordinate to queue
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
            
                //After a level is completed we increment the move
                minMoves++;
        }
        
        return minMoves;
    }
}
