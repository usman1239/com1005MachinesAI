import java.util.ArrayList;
public class EPuzzleStateAStar extends SearchState{
    int[][] currentState;
    int emptyXCoord;
    int emptyYCoord;
    int[][] target;
    /**
     * constructor which has an array, local cost and estimated cost
     * @param c
     * @param lc
     * @param erc
     */
    public EPuzzleStateAStar(int[][] c, int lc, int erc){
        currentState = c;
        localCost = lc;
        estRemCost = erc;
    }

    public int[][] getCurrentState(){
        return currentState;
    }

    /**
     * Checks if we have reached the target configuration
     * @param searcher
     * @return true if we have reached target, false if not
     */
    @Override
    boolean goalPredicate(Search searcher) {
        EPuzzleSearchAStar esearcher = (EPuzzleSearchAStar) searcher;
        int[][] tar = esearcher.getTarget();
        for(int x = 0; x < tar.length; x++){
            for(int y = 0; y < tar[x].length; y++){
                if (tar[x][y] != currentState[x][y]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Calls other methods which return the possible states.
     * Adds the states to a list and converts each to a SearchState
     * @param searcher
     * @return list of SearchStates that are the possible moves -- slis
     */
    @Override
    ArrayList<SearchState> getSuccessors(Search searcher) {
        EPuzzleSearchAStar esearcher = (EPuzzleSearchAStar) searcher;
        target = esearcher.getTarget();
        ArrayList<EPuzzleStateAStar> eslis = new ArrayList<>();
        ArrayList<SearchState> slis = new ArrayList<>();
        int[] emptyCoordinates = empty(currentState);

        EPuzzleStateAStar afterLeftMove = leftMove(currentState, emptyCoordinates);
        if (afterLeftMove != null) {
            eslis.add(afterLeftMove);
        }

        EPuzzleStateAStar afterRightMove = rightMove(currentState, emptyCoordinates);
        if (afterRightMove != null) {
            eslis.add(afterRightMove);
        }

        EPuzzleStateAStar afterUpMove = upMove(currentState, emptyCoordinates);
        if (afterUpMove != null) {
            eslis.add(afterUpMove);
        }

        EPuzzleStateAStar afterDownMove = downMove(currentState, emptyCoordinates);
        if (afterDownMove != null) {
            eslis.add(afterDownMove);
        }

        for (EPuzzleStateAStar p : eslis) {
            if (p != null) {
                slis.add((SearchState) p);
            }
        }
        return slis;
    }

    /**
     * Checks if the arrays have any difference, if so then returns false.
     * @param n2
     * @return true if arrays are equal, false if not
     */
    @Override
    boolean sameState(SearchState n2) {
        EPuzzleStateAStar s = (EPuzzleStateAStar) n2;
        int[][] es2CurrentState = s.getCurrentState();
        for (int x = 0; x < es2CurrentState.length; x++) {
            for (int y = 0; y < es2CurrentState[x].length; y++) {
                if (currentState[x][y] != es2CurrentState[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        String out = "";
        for (int x = 0; x < currentState.length; x++) {
            for (int y = 0; y < currentState[x].length; y++) {
                out += currentState[x][y];
            }
        }
        return "Puzzle : " + out;
    }
    
    /**
     * Provides a duplicate of the array passed into the method
     * @param s
     * @return sCopy
     */
    private int[][] duplicatedArray(int[][] s){
        int[][] sCopy = new int[s.length][s[0].length];
        for (int i = 0; i < s.length; i++) {
            System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
        }
        return sCopy;
    }
    
    /**
     * Calculates coordinates of empty space
     * @param c
     * @return array of coordinates
     */
    private int[] empty(int[][] c) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (c[i][j] == 0) {
                    emptyXCoord = j;
                    emptyYCoord = i;
                    return new int[] { emptyXCoord, emptyYCoord };
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
    private EPuzzleStateAStar leftMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[0] == 2) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1]][emptyOnes[0] + 1];
            toReturn[emptyOnes[1]][emptyOnes[0] + 1] = zero;
            estRemCost = hamming(toReturn, target);
            // estRemCost = manhattan(toReturn, target);
            return new EPuzzleStateAStar(toReturn, 1, estRemCost);
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
    private EPuzzleStateAStar rightMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[0] == 0) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1]][emptyOnes[0] - 1];
            toReturn[emptyOnes[1]][emptyOnes[0] - 1] = zero;
            estRemCost = hamming(toReturn, target);
            // estRemCost = manhattan(toReturn, target);
            return new EPuzzleStateAStar(toReturn, 1, estRemCost);
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
    private EPuzzleStateAStar upMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[1] == 2) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1] + 1][emptyOnes[0]];
            toReturn[emptyOnes[1] + 1][emptyOnes[0]] = zero;
            estRemCost = hamming(toReturn, target);
            // estRemCost = manhattan(toReturn, target);
            return new EPuzzleStateAStar(toReturn, 1, estRemCost);
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
    private EPuzzleStateAStar downMove(int[][] s, int[] emptyOnes) {
        if (emptyOnes[1] == 0) {
            return null;
        } else {
            int[][] toReturn = duplicatedArray(s);
            int zero = toReturn[emptyOnes[1]][emptyOnes[0]];
            toReturn[emptyOnes[1]][emptyOnes[0]] = toReturn[emptyOnes[1] - 1][emptyOnes[0]];
            toReturn[emptyOnes[1] - 1][emptyOnes[0]] = zero;
            estRemCost = hamming(toReturn, target);
            // estRemCost = manhattan(toReturn, target);
            return new EPuzzleStateAStar(toReturn, 1, estRemCost);
        }
    }

    /**
     * Uses the current array and target array to calculate number of elements
     * out of place, stored in an int variable
     * @param src
     * @param t
     * @return numOutOfPlace
     */
    private int hamming(int[][] src, int[][] t) {
        int numOutOfPlace = 0;
        for (int x = 0; x < src.length; x++) {
            for (int y = 0; y < src[x].length; y++) {
                if (src[x][y] != t[x][y] && src[x][y]!=0) {
                    numOutOfPlace++;
                }
            }
        }
        return numOutOfPlace;
    }

    /**
     * Calculates the absolute distance each element has to move to get to its correct
     * position in the array. Calculates using current state and target state.
     * @param s
     * @param t
     * @return sum of distance -- totalManhattan
     */
    private int manhattan(int[][] s, int[][] t) {
        int totalManhattan = 0;
        int si = 0;
        int sj = 0;
        int ti = 0;
        int tj = 0;

        //exclude the empty space
        for (int x = 1; x < 9 ; x++) {
            int i;
            int j;
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (s[i][j] == x) {
                        si = i;
                        sj = j;
                    }
                }
            }
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3;j++) {
                    if (t[i][j] == x) {
                        ti = Math.abs(i-si);
                        tj = Math.abs(j-sj);
                        totalManhattan += ti + tj;
                    }
                }
            }
        }
        return totalManhattan;
    }
}