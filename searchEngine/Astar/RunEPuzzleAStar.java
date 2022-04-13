// import java.lang.ref.Cleaner;

public class RunEPuzzleAStar {
    static int gLocalCost;
    static double estRemCost;
    public static void main(String[] args) {
        int seed = 23465;
        EpuzzGen gen = new EpuzzGen(seed);
        int d = 6;
        int [][] puzz = gen.puzzGen(d);
        // int[][] puzz = new int[][]{{1,0,3},{4,2,6},{7,5,8}};

        int[][] target = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
        
        EPuzzleSearchAStar searcherPuzz = new EPuzzleSearchAStar(puzz, target);
        
        SearchState state1 = (SearchState) new EPuzzleStateAStar(puzz, 0, 0);
        
        String res_aStar = searcherPuzz.runSearch(state1, "A*");
        
        System.out.println(res_aStar);
    }
}
