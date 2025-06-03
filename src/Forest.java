import java.util.ArrayList;

public class Forest {

    private Tree[][] forest;
    private int initialTrees = 0;
    private ArrayList<Double> burnPercentLog = new ArrayList<Double>();

    private int time = 0;

    /**
     * Constructs the forest with a given row #, column #, burn chance, burn time,
     *  and how likely a tree is to exist
     *
     * @param rows the amount of rows in the forest
     * @param cols the amount of columns in the forest
     * @param burnPercent what percent chance a tree can catch fire
     * @param burnTime how long a tree burns for
     * @param existPercent how likely a tree is to exist
     */
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

    public Forest(int rows, int cols, double existPercent) {
        this(rows, cols, Math.random(), Math.round((float)(Math.random() * 4)), existPercent);
    }

    /**
     * gets the surrounding trees
     *
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
     * Gets the surrounding trees
     *
     * @param r row
     * @param c column
     * @return array for trees in the wind
     */
    public int[][] getSurroundingsWind(int r, int c) {
        return new int[][]{{r, c + 1}, {r, c + 2}, {r, c + 3}, {r, c + 3}, {r + 1, c},
                            {r + 1, c + 1}, {r + 1, c + 2}, {r + 1, c + 3}, {r + 1, c + 3},
                            {r - 1, c}, {r - 1, c + 1}, {r - 1, c + 2}, {r - 1, c + 3}, {r - 1, c + 3},
                            {r, c - 1} };
    }

    /**
     * Gets the trees in the forest currently burning
     *
     * @return an ArrayList of int[] with coordinates
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

    /**
     * Returns the percentage of trees burning
     * 
     * @return the percentage of trees burning
     */
    public void percentCurrentlyBurning(){
        burnPercentLog.add(getBurningTrees().size() / (double)initialTrees * 100);
        System.out.println("currently burning percent: " + getBurningTrees().size() / (double)initialTrees * 100);
        
    }

    /**
     * Returns whether or not the forest is done burning
     *
     * @return true if no trees are burning, false otherwise
     */
    public boolean doneBurning(){
        return getBurningTrees().size() == 0;
    }

    /**
     * We didn't start the fire
     */
    public void startFire(){
        try{
        forest[Math.round((float)(forest.length * Math.random()))][Math.round((float)(Math.random() * forest[0].length))].treeState = Tree.TreeState.BURNING;
        }catch(Exception e){
            forest[0][0].treeState = Tree.TreeState.BURNING;
        }
    }

    /**
     * Starts the fire before the leftmost column
     */
    public void lineFire(){
        for(int i = 0; i < forest.length; i++){
            forest[i][0].treeState = Tree.TreeState.BURNING;
        }
    }

    /**
     * leo / need to get away, it's coming!
     *
     * @param radius the radius of the meteor
     */
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

    /**
     * Simulates the fire
     */
    public void simulateFire(){
        BURN(getBurningTrees());
        time += 1;
    }

    /**
     * burns individual tree
     */
    public void burnTree(Tree tree) {
        if(tree.treeState == Tree.TreeState.ALIVE && tree.burnPercent > Math.random()){
            tree.treeState = Tree.TreeState.BURNING;
        }
    }

    /**
     * prints out all of the information after the forest finishes burning
     */
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
        // System.out.println("burn log: " + burnPercentLog.toString());
        System.out.println("Dimensionality: " + (1 + ((double)initialTrees) / ((double)(forest.length * forest[0].length))));
        System.out.println("Percent survive: " + (double)finalTrees / (double)initialTrees);
    }

    /**
     * Returns a String of the 2D array with each Tree object
     * 
     * @return [🌳  🌳  🌳  ]
     *         [🌳🌳  🌳  🌳]
     *         .
     *         .
     *         .
     */
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
