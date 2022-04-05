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
        
        for (int i = 0; i < target.length; i++){
            for (int j = 0; j < target[i].length; j++){
                if (target[i][j] != currentState[i][j]){
                    return false;
                }
            }
        }
        return true;
        // return currentState.equals(target);
    }
    
    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        ArrayList<EPuzzleState> eslis = new ArrayList<>();
        ArrayList<SearchState> slis = new ArrayList<>();
        int[] emptyCoordinates = empty(currentState);

        EPuzzleState afterLeftMove = leftMove(currentState, emptyCoordinates);
        if (afterLeftMove != null){
            eslis.add(afterLeftMove);
        }

        EPuzzleState afterRightMove = rightMove(currentState, emptyCoordinates);
        if (afterRightMove != null){
            eslis.add(afterRightMove);
        }

        EPuzzleState afterUpMove = upMove(currentState, emptyCoordinates);
        if (afterUpMove != null) {
            eslis.add(afterUpMove);
        }

        EPuzzleState afterDownMove = downMove(currentState, emptyCoordinates);
        if (afterDownMove != null) {
            eslis.add(afterDownMove);
        }

        for (EPuzzleState p : eslis){
            if (p != null){
                slis.add((SearchState) p);
            }
        }
        return slis;
    }

    @Override
    boolean sameState(SearchState n2) {
        EPuzzleState es2 = (EPuzzleState) n2;
        return currentState == es2.getCurrentState();
    }

    @Override
    public String toString() {
        String out = "";
        for (int x = 0; x < currentState.length; x++){
            for (int y = 0; y < currentState[x].length; y++){
                out += currentState[x][y];
            }
        };
        return "Puzzle : " + out;
    }

    public int[] empty(int[][] c){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (c[i][j] == 0) {
                    emptyXCoord = j;
                    emptyYCoord = i;
                    // System.out.println("(" + emptyXCoord + " , " + emptyYCoord + ")");
                    return new int[] {emptyXCoord, emptyYCoord};
                }
            }
        }
        return null;
    }

    public EPuzzleState leftMove(int [][] s, int[] emptyOnes){
        int [][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[0] == 2){
            // System.out.println("rejected LEFT");
            return null;
        }
        else {
            for(int i = 0; i < s.length; i++){
                System.arraycopy(s[i], 0, sCopy[i] , 0,s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1]][emptyOnes[0] + 1];
            sCopy[emptyOnes[1]][emptyOnes[0]+1] = zero;
        }
        return new EPuzzleState(sCopy);
    }

    public EPuzzleState rightMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[0] == 0) {
            // System.out.println("rejected right move");
            return null;
        }
        else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]] [emptyOnes[0]];
            sCopy[emptyOnes[1]] [emptyOnes[0]] = sCopy[emptyOnes[1]] [emptyOnes[0] - 1];
            sCopy[emptyOnes[1]] [emptyOnes[0] - 1] = zero;
        }
        return new EPuzzleState(sCopy);
    }
    public EPuzzleState upMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[1] == 2) {
            // System.out.println("rejected up move");
            return null;
        }
        else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1]+1][emptyOnes[0]];
            sCopy[emptyOnes[1]+1][emptyOnes[0]] = zero;
        }
        return new EPuzzleState(sCopy);
    }
    public EPuzzleState downMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[1] == 0) {
            // System.out.println("rejected down move");
            return null;
        } else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1] - 1][emptyOnes[0]];
            sCopy[emptyOnes[1] - 1][emptyOnes[0]] = zero;
        }
        return new EPuzzleState(sCopy);
    }
}
