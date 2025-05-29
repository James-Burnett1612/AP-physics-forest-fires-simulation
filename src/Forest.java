import java.util.ArrayList;



public class Forest {

    private Tree[][] forest;
    private int initialTrees = 0;

    public Forest(int rows, int cols, double burnPercent, int burnTime, double existPercent) {
        forest = new Tree[rows][cols];

        for(int i = 0; i < forest.length; i++) {
            for(int j = 0; j < forest[0].length; j++) {
                forest[i][j] = new Tree(burnPercent, burnTime, existPercent);
                if(forest[i][j].treeState == Tree.TreeState.ALIVE){
                    initialTrees += 1;
                }
            }
        }
    }

    private int time = 0;

    /**
     * gets the surrounding trees
     * @param r row
     * @param c column
     * @return array for surrounding trees
     */
    public int[][] getSurroundings(int r, int c) {
        return new int[][]{{r + 1, c}, {r - 1, c},
                            {r + 1, c + 1}, {r - 1, c + 1}, {r, c + 1},
                            {r + 1, c - 1}, {r - 1, c - 1}, {r, c - 1}};
    }


        /**
     * gets the surrounding trees
     * @param r row
     * @param c column
     * @return array for trees in the wind
     */
    public int[][] getSurroundingsWind(int r, int c) {
        return new int[][]{{r, c}, {r, c + 1}, {r, c + 2}, {r, c + 3}, {r, c + 3}, {r + 1, c},
         {r + 1, c + 1}, {r + 1, c + 2}, {r + 1, c + 3}, {r + 1, c + 3},
         {r - 1, c}, {r - 1, c + 1}, {r - 1, c + 2}, {r - 1, c + 3}, {r - 1, c + 3},  {r, c - 1} };
    }
    
           /**
     * gets the surrounding trees
     * @param r row
     * @param c column
     * @return array for trees in the wind
     */
    public int[][] getSurroundingsNight(int r, int c) {
        return new int[][]{{r + 1, c + 2}, {r + 1, c - 2}, {r - 1, c + 2}, {r - 1, c - 2}, 
        {r + 2, c + 1}, {r + 2, c - 1}, {r - 2, c + 1}, {r - 2, c - 1}
        }; 
    }

    /*
     * gets the trees in the forest currently burning
     */
    public ArrayList<int[]> getBurningTrees(){

        ArrayList<int[]> burnCoords = new ArrayList<int[]>();

        for(int i = 0; i < forest.length; i++){
            for (int j = 0; j < forest[0].length; j++) {
                if(forest[i][j].treeState == Tree.TreeState.BURNING){
                    burnCoords.add(new int[]{i, j});
                } 
            }
        }
        // I don't trust this
        return burnCoords; 
    }

    /**
     * BUUUUUUUUUUURN
     */
    public void BURN(ArrayList<int[]> burningTrees){
        ArrayList<int[]> toBurn = new ArrayList<int[]>();

        for(int i = 0; i < burningTrees.size(); i++){
            var burnTile = burningTrees.get(i);
            int[][] surroundings = getSurroundings(burnTile[0], burnTile[1]);
            
            for(int j = 0; j < surroundings.length; j++){
                if(!(surroundings[j][0] < 0 || surroundings[j][1] < 0 || surroundings[j][0] > forest.length || surroundings[j][1] > forest[0].length)){
                    
                        toBurn.add(surroundings[j]);
                }
            }
        }

        for(int i = 0; i < toBurn.size(); i++){
            try{
            burnTree(forest[toBurn.get(i)[0]][toBurn.get(i)[1]]);

            }catch(Exception e){

            }   
        }
        for(int i = 0; i < burningTrees.size(); i++)  {       
            forest[burningTrees.get(i)[0]][burningTrees.get(i)[1]].burnCounter += 1;
  

            if(forest[burningTrees.get(i)[0]][burningTrees.get(i)[1]].burnCounter >= forest[burningTrees.get(i)[0]][burningTrees.get(i)[1]].burnTime){
                forest[burningTrees.get(i)[0]][burningTrees.get(i)[1]].treeState = Tree.TreeState.DEAD;
            }
        }
    }

    public  boolean doneBurning(){
        return getBurningTrees().size() == 0;
    }

    public void startFire(){
        try{
        forest[Math.round((float)(forest.length * Math.random()))][Math.round((float)(Math.random() * forest[0].length))].treeState = Tree.TreeState.BURNING;
        }catch(Exception e){
            forest[0][0].treeState = Tree.TreeState.BURNING;
        }
    }

    public void lineFire(){
        for(int i = 0; i < forest.length; i++){
            forest[i][0].treeState = Tree.TreeState.BURNING;
        }
    }

    public void pulseOfAMeteor(int radius){
        try{
            int[] center = {Math.round((float)(forest.length * Math.random())), Math.round((float)(Math.random() * forest[0].length))};

            for(int i = 0; i < forest.length; i++) {
                for(int j = 0; j < forest[0].length; j++) {
                        if(Math.round(Math.pow(i - center[0], 2) + Math.pow(j - center[1], 2)) == radius){
                            try{
                                forest[i][j].treeState = Tree.TreeState.BURNING;
                            }catch(Exception e){

                            } 
                        }
                        if(Math.round(Math.pow(i - center[0], 2) + Math.pow(j - center[1], 2)) < radius){
                            try{
                                forest[i][j].treeState = Tree.TreeState.DEAD;
                            }catch(Exception e){

                            }       
                        }
                    }
                }
            } catch(Exception e){
            }
    }

    public void simulateFire(){
        BURN(getBurningTrees());
        time += 1;
    }

    public void information(){
        System.out.println("time: " + time);
        System.out.println("initial trees: " + initialTrees);
        int finalTrees = 0;

        for(int i = 0; i < forest.length; i++) {
            for(int j = 0; j < forest[0].length; j++) {
                if(forest[i][j].treeState == Tree.TreeState.ALIVE){
                    finalTrees += 1;
                }
            }
        }

        System.out.println("final trees: " + finalTrees);

        System.out.printf("%.2f percent remains\nDimensionality = %.2f", ((double)finalTrees)/initialTrees * 100, getDimensionalityStandard(initialTrees));

    }

    /**
     * burns individual tree
     */
    public void burnTree(Tree tree) {
            if(tree.treeState == Tree.TreeState.ALIVE && tree.burnPercent > Math.random()){
                tree.treeState = Tree.TreeState.BURNING;
            }
    }

    
    public double getDimensionalityStandard(int treeCount)
    {
        int ROW_SHIFT = 0;
        int COL_SHIFT = 0;
        boolean isThere = false;
        double count = 0.0;
        
        while( ROW_SHIFT < 25 )
        {
            COL_SHIFT = 0;
            while( COL_SHIFT < 25)      
            {
                for( int i = 0 + ROW_SHIFT; i < ROW_SHIFT + 5; i++ )
                {
                    for( int j = 0 + COL_SHIFT; j < COL_SHIFT + 5; j++ )
                    {
                        if( forest[i][j].treeState == Tree.TreeState.ALIVE )
                        {
                            isThere = true;
                        }
                    }
                }
                COL_SHIFT += 5;
                if( isThere )
                {
                    count++;
                    isThere = false;
                }
            }
            ROW_SHIFT += 5;
        }
        count = (Math.log(treeCount) - Math.log(count)) / (Math.log(5) - Math.log(1)); 

        return( count );
    }

    public String toString() {
        String str = "";

        for (int i = 0; i < forest.length; i++) {
            str += "[ ";
            for (int j = 0; j < forest[0].length; j++) {
                str += forest[i][j].toString() + " ";
            }
            str += "]\n";
        }

        return str;
    }
}
