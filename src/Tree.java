public class Tree {
    // is the tree alive burning dead or not exist
    public enum TreeState{
        ALIVE,
        BURNING,
        DEAD,
        DNE
    }

    public TreeState treeState = TreeState.ALIVE;
    private double burnPercent;
    private int burnTime;

    /**
     * constructor for tree
     * @param burnPercent what is the chance of the tree burning (decimal)
     * @param burnTime how many loops does it take to stop burning and die
     * @param existPercent does the tree exist
     */
    public Tree(double burnPercent, int burnTime, double existPercent) {
        this.burnPercent = burnPercent;
        this.burnTime = burnTime;

        if(Math.random() < existPercent){
            this.treeState = TreeState.DNE;
        }

    }

    // /**
    //  * buuuurn baby burn!! DISCO INFERNOOOOOOO
    //  */
    // public void burnTree(){
    //     if(Math.random() < burnPercent && treeState != TreeState.DNE){
    //         treeState = TreeState.BURNING;
    //         for(int i = 0; i < burnTime; i++);
    //         treeState = TreeState.DEAD;
    //     }
    // }

    /**
     * Returns the value of the the tree as a Char
     * 
     * @return alive -> 'O', burning '\', dead -> 'X' 
     */
    public String toString() {
        switch(treeState)
        {
            case ALIVE:
                return "O";
            case BURNING:
                return "\\";
            case DEAD:
                return "X";
            default:
                return " ";
        }
    }
}
