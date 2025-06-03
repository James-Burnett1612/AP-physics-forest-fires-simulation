public class Tree {
    /**
     * Defines the 4 tree states: tree is alive, burning, dead, or does not exist.
     */
    public enum TreeState{
        ALIVE,
        BURNING,
        DEAD,
        DNE
    }

    public TreeState treeState = TreeState.ALIVE;
    public double burnPercent;
    public int burnTime;
    public int burnCounter = 0;

    /**
     * Constructor for tree
     * @param burnPercent what is the chance of the tree burning (decimal)
     * @param burnTime how many loops does it take to stop burning and die
     * @param existPercent does the tree exist
     */
    public Tree(double burnPercent, int burnTime, double existPercent) {
        this.burnPercent = burnPercent;
        this.burnTime = burnTime;

        if(Math.random() > existPercent){
            this.treeState = TreeState.DNE;
        }
    }

    /**
     * Returns the value of the the tree as an emoji
     * 
     * @return alive -> '🌳', burning '🔥', dead -> '💀', or "  " for DNE
     */
    public String toString() {
        switch(treeState)
        {
            case ALIVE:
                return "🌳";
            case BURNING:
                return "🔥";
            case DEAD:
                return "💀";
            default:
                return "  ";
        }
    }
}
