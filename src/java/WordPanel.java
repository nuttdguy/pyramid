import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class WordPanel {

    enum ListType { GAME_WORD, GUESS_LIST, MISS_LIST; };
    private String[] wordList;
    private String[] gameWord;
    private String[] guessList;
    private String[] missList;
    static Logger logger = Logger.getLogger(String.valueOf(WordPanel.class));

    WordPanel(String filePath) {
        setWordList(readFromAFile(filePath));
        setAGameWord();
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
    }

    WordPanel(String filePath, String[] gameWord) {
        setWordList(readFromAFile(filePath));
        setAGameWord(gameWord);
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
    }

    //== PUBLIC METHODS
    public String[] getWordList() { return this.wordList; }
    public String[] getGuessList() {
        return guessList;
    }
    public String[] getMissList() {
        return missList;
    }
    public boolean isCorrect(String letter) {
        if (isLetterIn(ListType.GAME_WORD, letter)) {
            addLetterTo(ListType.GUESS_LIST, letter);
        } else {
            displayDuplicateNarrative(letter);
            addLetterTo(ListType.MISS_LIST, letter);
        }
        return Arrays.equals(getGameWord(), getGuessList());
    }
    public void displayGuessNarrative(boolean play) {
        if (!play) {
            out.printf(GameText.MISSED.missed(), Arrays.toString(getMissList()));
            out.printf(GameText.GUESS.guess(), Arrays.toString(getGuessList()));
        }
    }
    private void displayDuplicateNarrative(String keyPress) {
        if (isLetterIn(ListType.MISS_LIST, keyPress)) {
            out.printf(GameText.DUPLICATE.duplicate(), keyPress);
        }
    }
    public void displayWinLoseNarrative() {
        if (Arrays.equals(getGuessList(), getGameWord())) {
            out.printf(GameText.WIN.win(), Arrays.toString(getGameWord()));
        } else {
            out.printf(GameText.LOSE.lose(), Arrays.toString(getGameWord()));
        }
        out.printf(GameText.PLAY_AGAIN.playAgain());
    }
    public boolean isPlayingAgain(String keyResponse) {
        if (keyResponse.equals("y")) {
            init();
            return true;
        }
        return false;
    }
    public boolean isNotEqualToGameWord() {
        return !(Arrays.stream(getMissList())
                .filter(e -> !(e.equals("_"))).count() == getGameWord().length ||
                Arrays.equals(getGuessList(), getGameWord()));
    }

    //== PROTECTED
    /**** setAGameWord - use for setting and testing a known game word ****/
    protected void setAGameWord(String[] word) {
        if (word.length > 0) {
            this.gameWord = word;
        } else {
            setAGameWord();
        }
    }

    //== PRIVATE METHODS
    private void init() {
        setAGameWord();
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
    }
    private void setAGameWord() {
        String[] gameWord = (Arrays.asList(getWordList())
                .get((int) (Math.random() * getWordList().length)))
                .split("");
        this.gameWord = gameWord;
    }
    private void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
    private void initGuessList(int length) {
        this.guessList = fillTheList(length);
    }
    private void initMissList(int length) {
        this.missList = fillTheList(length);
    }
    private String[] fillTheList(int length) {
        return Stream.of(new String[length]).map(e -> "_").toArray(String[]::new);
    }
    private String[] getGameWord() { return gameWord;}
    private String[] readFromAFile(String path) {
        String words = "";
        try {
            words = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException io) {
            logger.log(Level.ALL, io.getMessage());
        }
        return words.split("\r\n");
    }
    private boolean isLetterIn(ListType type, String letter) {
        if (type.equals(ListType.GAME_WORD)) {
            return Arrays.asList(getGameWord()).contains(letter);
        } else if (type.equals(ListType.GUESS_LIST)) {
            return Arrays.asList(getGuessList()).contains(letter);
        }
        return Arrays.asList(getMissList()).contains(letter);
    }
    private int indexOfLetterIn(String[] array, String letter) {
        // call alternative func when length greater than 1
        return Arrays.asList(array).indexOf(letter);
    }
    private String[] findAllIndexOf(String[] array, String letter) {
        // this is for finding multiple letter occurrences and their positions in guess list
        String[] lettersFound = IntStream
                .range(0, array.length)
                .mapToObj(index -> String.format("%d,%s", index, array[index]))
                .filter(element -> element.split(",")[1].equals(letter))
                .toArray(String[]::new);

        return lettersFound;
    }
    private void insertAll(String[] letters) {
        // check the guess list for each letter of
        Arrays.asList(letters).forEach(arr -> {
            String[] elements = arr.split(",");
            int index = Integer.parseInt(elements[0]);
            String letterToMatch = elements[1];
            getGuessList()[index] = letterToMatch;
        });
    }
    private void addLetterTo(ListType type, String letter) {
        int indexToInsert = 0;
        if (type.equals(ListType.GUESS_LIST)) {
            String[] foundIndexes = findAllIndexOf(getGameWord(), letter);
            insertAll(foundIndexes);
        } else {
            indexToInsert = indexOfLetterIn(getMissList(), "_");
            if (!(isLetterIn(ListType.MISS_LIST, letter))) {
                getMissList()[indexToInsert] = letter;
            }
        }
    }

}
