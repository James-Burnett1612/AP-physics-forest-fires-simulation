import java.util.ArrayList;

public class Forest {
    private Tree[][] forest;

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
    public int[][] getBurningTrees(){

        ArrayList<int[]> burnCoords = new ArrayList<int[]>();

        for(int i = 0; i < forest.length; i++){
            for (int j = 0; j < forest[0].length; j++) {
                if(forest[i][j].treeState == Tree.TreeState.BURNING){
                    burnCoords.add(new int[]{i, j});
                } 
            }
        }
        return (int[][]) burnCoords.toArray(); 
    }
    /**
     * BUUUUUUUUUUURN
     */
    public void BURN(int[][] burningTrees){
        ArrayList<int[]> toBurn = new ArrayList<int[]>();

        for(int i = 0; i < burningTrees.length; i++){
            for (int j = 0; j < burningTrees[0].length; j++) {
                int[][] surrounding = getSurroundings(i, j);
                for(int k = 0; k < surrounding.length; i++){
                    for (int l = 0; l < surrounding[0].length; j++) {
                        toBurn.add(new int[] {k, l});
                    }
                }
            }
        }
        for(int i = 0; i < toBurn.size(); i++){
 
            burnTree(forest[toBurn.get(i)[0]][toBurn.get(i)[1]]);
        }

    }

    /**
     * burns individual tree
     */
    public void burnTree(Tree tree) {
        try{
            if(tree.treeState == Tree.TreeState.ALIVE && tree.burnPercent > Math.random()){
                tree.treeState = Tree.TreeState.BURNING;
            }
        }catch(Exception e){}
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
