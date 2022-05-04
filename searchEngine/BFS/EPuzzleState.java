import java.util.ArrayList;
public class EPuzzleState extends SearchState{
    int[][] currentState;
    int emptyXCoord;
    int emptyYCoord;

    /**
     * constructor which has an array
     * @param c
     */
    public EPuzzleState(int [][] c){
        currentState = c;
    }

    public int[][] getCurrentState(){
        return currentState;
    }

    /**
     * Checks if we have reached the target configuration
     * 
     * @param searcher
     * @return true if we have reached target, false if not
     */
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
    
    /**
     * Calls other methods which return the possible states.
     * Adds the states to a list and converts each to a SearchState
     * 
     * @param searcher
     * @return list of SearchStates that are the possible moves -- slis
     */
    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {        
        ArrayList<EPuzzleState> eslis = new ArrayList<>();
        ArrayList<SearchState> slis = new ArrayList<>();
        int[] emptyCoordinates = empty(currentState);

        
        
        
        EPuzzleState afterDownMove = downMove(currentState, emptyCoordinates);
        if (afterDownMove != null) {
            eslis.add(afterDownMove);
        }
        
        EPuzzleState afterRightMove = rightMove(currentState, emptyCoordinates);
        if (afterRightMove != null){
            eslis.add(afterRightMove);
        }
        
        EPuzzleState afterUpMove = upMove(currentState, emptyCoordinates);
        if (afterUpMove != null) {
            eslis.add(afterUpMove);
        }
        EPuzzleState afterLeftMove = leftMove(currentState, emptyCoordinates);
        if (afterLeftMove != null){
            eslis.add(afterLeftMove);
        }
        
        for (EPuzzleState p : eslis){
            if (p != null){
                slis.add((SearchState) p);
            }
        }
        return slis;
    }

    /**
     * Checks if the arrays have any difference, if so then returns false.
     * 
     * @param n2
     * @return true if arrays are equal, false if not
     */
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

    /**
     * Provides a duplicate of the array passed into the method
     * 
     * @param s
     * @return sCopy
     */
    private int[][] duplicatedArray(int[][] s) {
        int[][] sCopy = new int[s.length][s[0].length];
        for (int i = 0; i < s.length; i++) {
            System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
        }
        return sCopy;
    }

    /**
     * Calculates coordinates of empty space
     * 
     * @param c
     * @return array of coordinates
     */
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
    
    /**
     * Checks if an element from right can be moved left into the space.
     * If so, returns the state after that move has been made
     * 
     * @param s
     * @param emptyOnes
     * @return state after move
     */
    public EPuzzleState leftMove(int [][] s, int[] emptyCoordinates){
        if (emptyCoordinates[0] == 2){
            return null;
        }
        else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyCoordinates[1]][emptyCoordinates[0]];
            toReturn[emptyCoordinates[1]][emptyCoordinates[0]] = toReturn[emptyCoordinates[1]][emptyCoordinates[0] + 1];
            toReturn[emptyCoordinates[1]][emptyCoordinates[0]+1] = zero;
            return new EPuzzleState(toReturn);
        }
    }


    /**
     * Checks if an element from left can be moved right into the space.
     * If so, returns the state after that move has been made
     * 
     * @param s
     * @param emptyOnes
     * @return state after move
     */
    public EPuzzleState rightMove(int[][] s, int[] emptyCoordinates) {
        //first checks whether a number can be moved to the right into the empty space
        //if not, returns null
        if (emptyCoordinates[0] == 0) {
            return null;
        }
        else {
            //returns a copy of the current array puzzle
            int[][] toReturn = duplicatedArray(s);
            //'moves' the number right, into the empty slot
            int zero = toReturn[emptyCoordinates[1]] [emptyCoordinates[0]];
            toReturn[emptyCoordinates[1]] [emptyCoordinates[0]] = toReturn[emptyCoordinates[1]] [emptyCoordinates[0] - 1];
            toReturn[emptyCoordinates[1]] [emptyCoordinates[0] - 1] = zero;
            return new EPuzzleState(toReturn);
        }
    }

    /**
     * Checks if an element from below can be moved up into the space.
     * If so, returns the state after that move has been made
     * 
     * @param s
     * @param emptyOnes
     * @return state after move
     */
    public EPuzzleState upMove(int[][] s, int[] emptyCoordinates) {
        if (emptyCoordinates[1] == 2) {
            return null;
        }
        else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyCoordinates[1]][emptyCoordinates[0]];
            toReturn[emptyCoordinates[1]][emptyCoordinates[0]] = toReturn[emptyCoordinates[1] + 1][emptyCoordinates[0]];
            toReturn[emptyCoordinates[1]+1][emptyCoordinates[0]] = zero;
            return new EPuzzleState(toReturn);
        }
    }
    
    /**
     * Checks if an element from above can be moved down into the space.
     * If so, returns the state after that move has been made
     * 
     * @param s
     * @param emptyOnes
     * @return state after move
     */
    public EPuzzleState downMove(int[][] s, int[] emptyCoordinates) {
        if (emptyCoordinates[1] == 0) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyCoordinates[1]][emptyCoordinates[0]];
            toReturn[emptyCoordinates[1]][emptyCoordinates[0]] = toReturn[emptyCoordinates[1] - 1][emptyCoordinates[0]];
            toReturn[emptyCoordinates[1] - 1][emptyCoordinates[0]] = zero;
            return new EPuzzleState(toReturn);
        }
    }
}
