package mamans.maman01.src.q1;

import java.util.ArrayList;
import java.util.ListIterator;

public class BullsAndCowsGuessArchive {

    private ArrayList<String> guessArchive = new ArrayList<>();
    public void addPlayerGuessToArchive(String playerGuess) {
        this.guessArchive.add(playerGuess);
    }

    public ListIterator<String> getGuessArchive() {
        return this.guessArchive.listIterator();
    }
}
