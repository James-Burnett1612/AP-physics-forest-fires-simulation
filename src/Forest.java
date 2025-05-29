import java.util.ArrayList;



public class Forest {
    private Tree[][] forest;
    //we need some way to randomly burn a tree
    public Forest(int rows, int cols, double burnPercent, int burnTime, double existPercent) {
        forest = new Tree[rows][cols];

        for(int i = 0; i < forest.length; i++) {
            for(int j = 0; j < forest[0].length; j++) {
                forest[i][j] = new Tree(burnPercent, burnTime, existPercent);
            }
        }
    }

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

    public void startFire(){
        forest[3][3].treeState = Tree.TreeState.BURNING;

    }

    public void simulateFire(){
        BURN(getBurningTrees());
    }

    /**
     * burns individual tree
     */
    public void burnTree(Tree tree) {
            if(tree.treeState == Tree.TreeState.ALIVE && tree.burnPercent > Math.random()){
                tree.treeState = Tree.TreeState.BURNING;
            }
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
