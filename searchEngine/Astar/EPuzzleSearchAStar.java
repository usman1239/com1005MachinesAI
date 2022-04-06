public class EPuzzleSearchAStar extends Search {
    // EpuzzGen generatedPuzz = new EpuzzGen();
    int[][] generatedPuzz;
    int[][] target;
    
    public EPuzzleSearchAStar(int [][] src, int[][] t) {
        generatedPuzz = src;
        target = t;
    }

    public int[][] getPuzz(){

        return generatedPuzz;
    }
    public int[][] getTarget(){
        return target;
    }
}