public class RunEPuzzleBFS {
    public static void main(String[] args) {
        int[][] initP1 = new int[][]{{1,0,3}, {4,2,6}, {7,5,8}};
        // int[][] initP2 = new int[][] { { 4, 1, 3 }, { 7,2, 5 }, { 0, 8, 6 } };
        // int[][] initP3 = new int[][] { { 2, 3, 6 }, { 1, 5, 8 }, { 4, 7, 0 } };
        int [][] target = new int[][] { {1,2,3}, {4,5,6}, {7,8,0} };
        
        EPuzzleSearch searcherP1 = new EPuzzleSearch(initP1, target);
        // EPuzzleSearch searcherP2 = new EPuzzleSearch(initP2, target);
        // EPuzzleSearch searcherP3 = new EPuzzleSearch(initP3, target);
        
        SearchState initStateP1 = (SearchState) new EPuzzleState(initP1);
        // SearchState initStateP2 = (SearchState) new EPuzzleState(initP2);
        // SearchState initStateP3 = (SearchState) new EPuzzleState(initP3);
        
        String resBFSP1 = searcherP1.runSearch(initStateP1, "breadthFirst");
        // String resBFSP2 = searcherP2.runSearch(initStateP2, "breadthFirst");
        // String resBFSP3 = searcherP3.runSearch(initStateP3, "breadthFirst");

        System.out.print(resBFSP1);
        // System.out.print(resBFSP2);
        // System.out.print(resBFSP3);
    }
}
