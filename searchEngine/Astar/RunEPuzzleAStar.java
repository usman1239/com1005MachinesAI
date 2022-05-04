import java.util.Arrays;

// import java.lang.ref.Cleaner;

public class RunEPuzzleAStar {
    static int gLocalCost;
    static double estRemCost;
    
    public static void main(String[] args) {
        int[][] target = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
        int [][] puzz = new int[][]{};
        int seed = 9908;
        int d = 6;
        EpuzzGen gen = new EpuzzGen(seed);

        System.out.println("Seed: " + seed + " && diff: " + d);

        for (int i = 0; i < 10; i++) {
            puzz = gen.puzzGen(d);
            System.out.println(" fdsf: " + Arrays.deepToString(puzz));            
            SearchState state1 = (SearchState) new EPuzzleStateAStar(puzz, 0, 0);
            EPuzzleSearchAStar searcherPuzz = new EPuzzleSearchAStar(puzz, target);
            String res_aStarString = searcherPuzz.runSearch(state1, "A*");
            Float res_aStarEff = searcherPuzz.runSearchE(state1, "A*");
            System.out.println("Solution Path: " + res_aStarString);
            System.out.println("Eff: " + res_aStarEff);
        }
    }
}
