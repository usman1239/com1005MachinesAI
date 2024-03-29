import java.util.Arrays;

public class RunEPuzzleBFS {
    public static void main(String[] args) {
        int[][] target = new int[][] {{1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }};
        int[][] puzz = new int[][]{{3,8,2} ,{7,5,6}, {4,1,0}};
        int[][] initP1 = new int[][]{{1,0,3},{4,2,6},{7,5,8}};
        int[][] initP2 = new int[][]{{4,1,3},{7,2,5},{0,8,6}};
        int[][] initP3 = new int[][]{{2,3,6},{1,5,8},{4,7,0}};

        EPuzzleSearch searcherP1 = new EPuzzleSearch(initP1, target);
        EPuzzleSearch searcherP2 = new EPuzzleSearch(initP2, target);
        EPuzzleSearch searcherP3 = new EPuzzleSearch(initP3, target);
        
        SearchState initStateP1 = (SearchState) new EPuzzleState(initP1);
        SearchState initStateP2 = (SearchState) new EPuzzleState(initP2);
        SearchState initStateP3 = (SearchState) new EPuzzleState(initP3);
        
        String resBFSP1 = searcherP1.runSearch(initStateP1, "breadthFirst");
        String resBFSP2 = searcherP2.runSearch(initStateP2, "breadthFirst");
        String resBFSP3 = searcherP3.runSearch(initStateP3, "breadthFirst");
        
        System.out.println("Initial Puzzle 1 : " + resBFSP1);
        System.out.println("Initial Puzzle 2: " + resBFSP2);
        System.out.println("Initial Puzzle 3: " + resBFSP3);
        
        //Below is the code I used for testing in my experiment
        // int[][] target = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
        // int[][] puzz = new int[][]{};
        // int seed = 9908;
        // int d = 10;
        // EpuzzGen gen = new EpuzzGen(seed);
    
        // for (int i = 0; i < 10; i++){
        //     System.out.println("Number " + i);
        //     puzz = gen.puzzGen(d);
        //     System.out.println(" fdsf: " + Arrays.deepToString(puzz));
        //     SearchState initStateP1 = (SearchState) new EPuzzleState(puzz);
        //     EPuzzleSearch searcherP1 = new EPuzzleSearch(puzz, target);
        //     String resBFSP1 = searcherP1.runSearch(initStateP1, "breadthFirst");
        //     System.out.println("Initial Puzzle 1 : " + resBFSP1);
        // }
    }
}