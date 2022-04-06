import java.util.ArrayList;
public class EPuzzleStateAStar extends SearchState{
    int[][] currentState;
    int emptyXCoord;
    int emptyYCoord;
    int[][] target;

    public EPuzzleStateAStar(int[][] c, int lc, int erc){
        currentState = c;
        localCost = lc;
        estRemCost = erc;
    }

    public int[][] getCurrentState(){
        return currentState;
    }

    @Override
    boolean goalPredicate(Search searcher) {
        EPuzzleSearchAStar bob = (EPuzzleSearchAStar) searcher;
        int[][] tar = bob.getTarget();
        for(int x = 0; x < tar.length; x++){
            for(int y = 0; y < tar[x].length; y++){
                if (tar[x][y] != currentState[x][y]){
                    return false;
                }
            }
        }
        return true;
    }

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
        ;
        return "Puzzle : " + out;
    }

    public int[] empty(int[][] c) {
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

    public EPuzzleStateAStar leftMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[0] == 2) {
            return null;
        } else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1]][emptyOnes[0] + 1];
            sCopy[emptyOnes[1]][emptyOnes[0] + 1] = zero;
            System.out.println(sCopy.toString());
            estRemCost = hamming(sCopy, target);
            System.out.println("Hamming for LEFt is: " + estRemCost);
        }
        
        return new EPuzzleStateAStar(sCopy, 1, estRemCost);
    }

    public EPuzzleStateAStar rightMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[0] == 0) {
            // System.out.println("rejected right move");
            return null;
        } else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1]][emptyOnes[0] - 1];
            sCopy[emptyOnes[1]][emptyOnes[0] - 1] = zero;
            System.out.println(sCopy.toString());
            estRemCost = hamming(sCopy, target);
            System.out.println("hahaRight" + estRemCost);

        }
        return new EPuzzleStateAStar(sCopy, 1, estRemCost);
        // return new EPuzzleStateAStar(sCopy, this.getLocalCost(), this.getestRemCost());

        // return new EPuzzleStateAStar(sCopy);
    }

    public EPuzzleStateAStar upMove(int[][] s, int[] emptyOnes) {
        int[][] sCopy = new int[s.length][s[0].length];
        if (emptyOnes[1] == 2) {
            // System.out.println("rejected up move");
            return null;
        } else {
            for (int i = 0; i < s.length; i++) {
                System.arraycopy(s[i], 0, sCopy[i], 0, s[i].length);
            }
            int zero = sCopy[emptyOnes[1]][emptyOnes[0]];
            sCopy[emptyOnes[1]][emptyOnes[0]] = sCopy[emptyOnes[1] + 1][emptyOnes[0]];
            sCopy[emptyOnes[1] + 1][emptyOnes[0]] = zero;
            System.out.println(sCopy.toString());
            estRemCost = hamming(sCopy, target);
            System.out.println("HAMMING UP: " + estRemCost);
        }
        return new EPuzzleStateAStar(sCopy, 1, estRemCost);
        // return new EPuzzleStateAStar(sCopy, this.getLocalCost(), this.getestRemCost());

        // return new EPuzzleStateAStar(sCopy);
    }

    public EPuzzleStateAStar downMove(int[][] s, int[] emptyOnes) {
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
            System.out.println(sCopy.toString());
            estRemCost = hamming(sCopy, target);
            System.out.println("HAMMING DOWN: " + estRemCost);

        }
        return new EPuzzleStateAStar(sCopy, 1, estRemCost);
        // return new EPuzzleStateAStar(sCopy, this.getLocalCost(), this.getestRemCost());

        // return new EPuzzleStateAStar(sCopy);
    }

    public int hamming(int[][] src, int[][] t) {
        int numOutOfPlace = 0;
        for (int x = 0; x < src.length; x++) {
            for (int y = 0; y < src[x].length; y++) {
                if (src[x][y] != t[x][y]) {
                    numOutOfPlace++;
                }
            }
        }
        return numOutOfPlace;
    }
}