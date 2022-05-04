public class EPuzzleSearchAStar extends Search {
    int[][] generatedPuzz;
    int[][] target;
    
    public EPuzzleSearchAStar(int [][] src, int[][] t) {
        generatedPuzz = src;
        target = t;
    }

    /**
     * @return the initial puzzle
     */
    public int[][] getPuzz(){
        return generatedPuzz;
    }
    
    /**
     * @return the target puzzle
     */
    public int[][] getTarget(){
        return target;
    }
}