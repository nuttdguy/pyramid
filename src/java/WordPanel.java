import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class WordPanel {

    static Logger logger = Logger.getLogger(String.valueOf(WordPanel.class));
    enum ListType { GAME_WORD, GUESS_LIST, MISS_LIST}

    private String[] wordList;
    private String[] gameWord;
    private String[] guessList;
    private String[] missList;
    private String[] drawings;

    private final String drawingFilePath = "art.txt";
    private final String wordFilePath = "words.txt";
    private final String scoreCardPath = "score_card.txt";

    WordPanel() {
        init();
    }
    WordPanel(String[] gameWord) {
        init(gameWord);
    }


    //== PUBLIC METHODS
    public void displayGuessNarrative(boolean play) {
        if (!play) {
            out.printf(GameText.MISSED.missed(), Arrays.toString(getMissList()));
            out.printf(GameText.GUESS.guess(), Arrays.toString(getGuessList()));
        }
    }
    public void displayWinLoseNarrative() {
        if (Arrays.equals(getGuessList(), getGameWord())) {
            out.printf(GameText.WIN.win(), Arrays.toString(getGameWord()));
            writeToFile(getScoreCardPath(), score("alice"));
        } else {
            out.printf(GameText.LOSE.lose(), Arrays.toString(getGameWord()));
            writeToFile(getScoreCardPath(), score("alice"));
        }
        out.printf(GameText.PLAY_AGAIN.playAgain());
    }
    public String displayGameArt() {
        int idx = indexOfLetterIn("_", getMissList());
        return getDrawings()[idx < 0 ? getDrawings().length-1 : idx];
    }
    public String[] getWordList() { return this.wordList; }
    public String[] getGuessList() {
        return this.guessList;
    }
    public String[] getMissList() {
        return this.missList;
    }
    public String[] getDrawings() { return this.drawings; }
    public boolean isCorrect(String letter) {
        if (isLetterIn(ListType.GAME_WORD, letter)) {
            addLetterTo(ListType.GUESS_LIST, letter);
        } else {
            displayDuplicateNarrative(letter);
            addLetterTo(ListType.MISS_LIST, letter);
        }
        return Arrays.equals(getGameWord(), getGuessList());
    }
    public boolean isPlayingAgain(String keyResponse) {
        return keyResponse.equals("y") && reload();
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
    private void addLetterTo(ListType type, String letter) {
        if (type.equals(ListType.GUESS_LIST)) {
            String[] foundIndexes = findAllIndexOf(letter, getGameWord());
            insertAll(foundIndexes);
        } else {
            int indexToInsert = indexOfLetterIn("_", getMissList());
            if (!(isLetterIn(ListType.MISS_LIST, letter))) {
                getMissList()[indexToInsert] = letter;
            }
        }
    }
    private void displayDuplicateNarrative(String keyPress) {
        if (isLetterIn(ListType.MISS_LIST, keyPress)) {
            out.printf(GameText.DUPLICATE.duplicate(), keyPress);
        }
    }
    private void init() {
        setWordList(readFromAFile(this.wordFilePath).split("\r\n"));
        setAGameWord();
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
        setDrawings();
    }
    private void init(String[] gameWord) {
        setWordList(readFromAFile(this.wordFilePath).split("\r\n"));
        setAGameWord(gameWord);
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
        setDrawings();
    }
    private void initGuessList(int length) {
        this.guessList = fillTheList(length, "_");
    }
    private void initMissList(int length) {
        this.missList = fillTheList(length, "_");
    }
    private void insertAll(String[] letters) {
        // check the guess list for each letter of
        Arrays.stream(letters)
                .map(arr -> arr.split(","))
                .forEach(idxOfInsert -> getGuessList()[Integer.parseInt(idxOfInsert[0])] = idxOfInsert[1]);
    }
    private void setDrawings() {
        // offset by 2 since first element does not count and need extra element for offsetting display of art
        String[] drawings = readFromAFile(drawingFilePath).split(";");
        int middle = getGameWord().length / 2;
        int a=1, b = middle+1, c = drawings.length - (middle+2), d = drawings.length;
        String[] drawingSet1 = Arrays.asList(drawings).subList(a, b).toArray(String[]::new);
        String[] drawingSet2 = Arrays.asList(drawings).subList(c, d).toArray(String[]::new);
        this.drawings = Stream.concat(Arrays.stream(drawingSet1), Arrays.stream(drawingSet2))
                .toArray(size -> (String[]) Array.newInstance(drawingSet1.getClass().getComponentType(), size));

    }
    private void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
    private void writeToFile(String path, String gameData) {
        File file = new File(path);
        try {
            FileOutputStream io = new FileOutputStream(file.getAbsolutePath(), true);
            io.write(gameData.getBytes());
            io.close();

        } catch(IOException ioe) {
            logger.log(Level.ALL, ioe.getMessage());
        }

    }
    private String getScoreCardPath() {
        return this.scoreCardPath;
    }
    private String score(String name) {
        int misses = (int) Arrays.stream(getMissList()).filter(e-> !e.equals("_")).count();
        int guesses = (int) Arrays.stream(getGuessList()).filter(e-> !e.equals("_")).count();
        return "\nName: " + name + ",\n" +
                "Word: " + Arrays.toString(getGameWord()) + ",\n" +
                "Misses: " + misses + ", Missed letters: " + Arrays.toString(getMissList()) + ",\n" +
                "Guesses: " + guesses + ", Guessed letters: " + Arrays.toString(getGuessList()) + ",\n" +
                "Score: " + (guesses - misses) + ",\n;";
    }
    private String[] findAllIndexOf(String letter, String[] array) {
        // this is for finding multiple letter occurrences and their positions in guess list
        return IntStream
                .range(0, array.length)
                .mapToObj(index -> String.format("%d,%s", index, array[index]))
                .filter(element -> element.split(",")[1].equals(letter))
                .toArray(String[]::new);
    }
    private String[] fillTheList(int length, String fill) {
        return Stream.of(new String[length]).map(e -> fill).toArray(String[]::new);
    }
    private String[] getGameWord() { return gameWord;}
    private String readFromAFile(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Path.of(fileName)));
        } catch (IOException io) {
            logger.log(Level.ALL, io.getMessage());
        }
        return data;
    }
    private String[] setAGameWord() {

        String[] gameWord = (Arrays.asList(getWordList())
                .get((int) (Math.random() * getWordList().length)))
                .split("");
        this.gameWord = gameWord.length < 4 ? setAGameWord() : gameWord;
        return gameWord;
    }
    private int indexOfLetterIn(String letter, String[] array) {
        return Arrays.asList(array).indexOf(letter);
    }
    private boolean isLetterIn(ListType type, String letter) {
        if (type.equals(ListType.GAME_WORD)) {
            return Arrays.asList(getGameWord()).contains(letter);
        } else if (type.equals(ListType.GUESS_LIST)) {
            return Arrays.asList(getGuessList()).contains(letter);
        }
        return Arrays.asList(getMissList()).contains(letter);
    }
    private boolean reload() {
        setAGameWord();
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
        setDrawings();
        return true;
    }

}
