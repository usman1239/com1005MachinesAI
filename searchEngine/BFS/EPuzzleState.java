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
        int[][] es2CurrentState = es2.getCurrentState();
        
        for (int x = 0; x < es2CurrentState.length; x++){
            for (int y = 0; y < es2CurrentState[x].length; y++){
                if (currentState[x][y] != es2CurrentState[x][y]){
                    return false;
                }
            }
        }
        return true;
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

    private int[][] duplicatedArray(int[][] s) {
        int[][] sCopy = new int[s.length][s[0].length];
        for (int i = 0; i < s.length; i++) {
            System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
        }
        return sCopy;
    }

    public int[] empty(int[][] c){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (c[i][j] == 0) {
                    emptyXCoord = j;
                    emptyYCoord = i;
                    return new int[] {emptyXCoord, emptyYCoord};
                }
            }
        }
        return null;
    }

    public EPuzzleState leftMove(int [][] s, int[] emptyOnes){
        if (emptyOnes[0] == 2){
            return null;
        }
        else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1]][emptyOnes[0] + 1];
            toReturn[emptyOnes[1]][emptyOnes[0]+1] = zero;
            return new EPuzzleState(toReturn);
        }
    }

    public EPuzzleState rightMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[0] == 0) {
            return null;
        }
        else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]] [emptyOnes[0]];
            toReturn[emptyOnes[1]] [emptyOnes[0]] = toReturn[emptyOnes[1]] [emptyOnes[0] - 1];
            toReturn[emptyOnes[1]] [emptyOnes[0] - 1] = zero;
            return new EPuzzleState(toReturn);
        }
    }

    public EPuzzleState upMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[1] == 2) {
            return null;
        }
        else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1] + 1][emptyOnes[0]];
            toReturn[emptyOnes[1]+1][emptyOnes[0]] = zero;
            return new EPuzzleState(toReturn);
        }
    }
    
    public EPuzzleState downMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[1] == 0) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1] - 1][emptyOnes[0]];
            toReturn[emptyOnes[1] - 1][emptyOnes[0]] = zero;
            return new EPuzzleState(toReturn);
        }
    }
}
