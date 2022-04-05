public class EPuzzleSearch extends Search {
    int[][] toSolve;
    int[][] target;
    
    public EPuzzleSearch(int [][] src,int [][] t){
        toSolve = src;
        target = t;
    }

    public int[][] getToSolve(){
        return toSolve;
    }

    public int[][] getTarget(){
        return target;
    }
}
