import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            // then check if letter is in correctLetter
            // if not in correctLetter, check the incorrect letter list
            if (!isLetterIn(ListType.GUESS_LIST, letter)) {
                addLetterTo(ListType.GUESS_LIST, letter);
            }
        } else {
            addLetterTo(ListType.MISS_LIST, letter);
        }

        return Arrays.equals(getGameWord(), getGuessList());
    }
    public void displayNarrative(boolean play, String keyPress) {
        if (play) {
            if (!Arrays.asList(getGuessList()).contains(keyPress)) {
                out.printf(GameText.GUESS.guess(), Arrays.toString(getGuessList()));
            } else {
                out.printf(GameText.MISSED.missed(), Arrays.toString(getMissList()));
            }
        } else if (!play) {
            out.printf(GameText.WIN.win(), Arrays.toString(getGuessList()));
            out.printf(GameText.PLAY_AGAIN.playAgain());
        }
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
        return Arrays.asList(array).indexOf(letter);
    }
    private void addLetterTo(ListType type, String letter) {
        int indexToInsert = 0;
        if (type.equals(ListType.GUESS_LIST)) {
            indexToInsert = indexOfLetterIn(getGameWord(), letter);
            getGuessList()[indexToInsert] = letter;
        } else {
            indexToInsert = indexOfLetterIn(getMissList(), "_");
            getMissList()[indexToInsert] = letter;
        }
    }

}
