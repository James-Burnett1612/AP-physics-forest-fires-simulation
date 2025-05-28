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
    
    // public Tree[][] getSurroundings(int row, int col){

    // }

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
