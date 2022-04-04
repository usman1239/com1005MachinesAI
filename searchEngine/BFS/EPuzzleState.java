import java.util.ArrayList;

public class EPuzzleState extends SearchState{
    int[][] currentState;
    int emptyXCoord;
    int emptyYCoord;
    public EPuzzleState(int [][] c){
        currentState = c;
    }

    public int[][] getCurrentState(){
        return currentState;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        EPuzzleSearch eSearch = (EPuzzleSearch) searcher;
        int[][] target = eSearch.getTarget();
        if (currentState == target){
            return true;
        }
        return false;
    }
    
    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        //Go through each node and see where the next possibilities are.
        EPuzzleSearch eSearcher = (EPuzzleSearch) searcher;
        
        SearchNode curr = eSearcher.currentNode;
        System.out.println(curr);
        ArrayList<EPuzzleState> eslis = new ArrayList<>();
        ArrayList<SearchState> slis = new ArrayList<>();


        int[] emptyCoordinates = empty(currentState);
        System.out.println(emptyCoordinates[0] + " " + emptyCoordinates[1]);
        
        

        for (EPuzzleState p : eslis){
            slis.add((SearchState) p);
        }
        return slis;
    }

    @Override
    boolean sameState(SearchState n2) {
        EPuzzleState es2 = (EPuzzleState) n2;
        if (currentState == es2.getCurrentState()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String out = "";
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                out += currentState[x][y];
            }
        };
        return "Puzzle : " + out;
    }

    public int[] empty(int[][] c){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // find the empty space
                if (c[i][j] == 0) {
                    emptyXCoord = j;
                    emptyYCoord = i;
                }
            }
        }
        return new int[] {emptyXCoord,emptyYCoord};
    }

}
