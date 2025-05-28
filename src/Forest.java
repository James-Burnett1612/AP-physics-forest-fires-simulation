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

    /**
     * BUUUUURRRRRRNNNNNNNN
     */
    public void BURN(Tree tree) {
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
