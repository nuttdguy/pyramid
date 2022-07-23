import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dictionary {

    static Logger logger = Logger.getLogger(String.valueOf(Dictionary.class));
    enum ListType { GUESS_LIST, MISS_LIST}
    private String[] wordList;
    private String[] gameWord;
    private String[] guessList;
    private String[] missList;

    private final String wordFilePath = "words.txt";

    Dictionary(int level) {
        init(level);
    }

    Dictionary(int level, String wordFilePath) {
        init(level, wordFilePath);
    }

    private void init(int level) {
        setWordList(readFromAFile(this.wordFilePath).split("\r\n"));
        setAGameWord(level);
    }

    private void init(int level, String wordFilePath) {
        setWordList(readFromAFile(wordFilePath).split("\r\n"));
        setAGameWord(level);
    }

    //== PUBLIC METHODS

    protected boolean isListEqual(String[] list) {
        return Arrays.equals(list, getGameWord());
    }

    protected String[] getWordOrEmpty() {
        boolean isMatch = (Arrays.stream(getMissList())
                .filter(e -> !(e.equals("_"))).count() == getGameWord().length ||
                Arrays.equals(getGuessList(), getGameWord()));

        return isMatch ? getGameWord() : new String[0];
    }

    public String[] getWordList() {
        return this.wordList;
    }

    public String[] getGuessList() {
        return this.guessList;
    }

    public String[] getMissList() {
        return this.missList;
    }

    //== PROTECTED

    protected boolean hasLetterThenAdd(String letter) {
        boolean isInGameWord = Arrays.asList(getGameWord()).contains(letter);
        if (isInGameWord) {
            addLetterTo(ListType.GUESS_LIST, letter);
        } else {
            if (isNotEqualToGameWord()) {
                addLetterTo(ListType.MISS_LIST, letter);
            }
        }
        return isInGameWord;
    }

    protected boolean isPlayingAgain(String keyResponse, String name) {
        return keyResponse.equals("y");
    }

    protected boolean isNotEqualToGameWord() {
        return !(Arrays.stream(getMissList())
                .filter(e -> !(e.equals("_"))).count() == getGameWord().length ||
                Arrays.equals(getGuessList(), getGameWord()));
    }

    protected void reload(int level) {
        this.gameWord = null;
        setAGameWord(level);
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
    }

    protected boolean isDuplicateGuess(String[] list, String letter) {
        return Arrays.asList(list).contains(letter);
    }

    //== PRIVATE METHODS
    private void setAGameWord(int level) {

        // check if there is a list before entering recursive call
        if (getWordList().length > 0) {
            int maxTries = getWordList().length;
            this.gameWord = selectRandomGameWord(level, maxTries);
            initGuessList(this.gameWord.length);
            initMissList(this.gameWord.length);
        } else {
            throw new RuntimeException("There was no word list");
        }
    }

    private void addLetterTo(ListType type, String letter) {
        if (type.equals(ListType.GUESS_LIST)) {
            String[] foundIndexes = findAllIndexOf(letter, getGameWord());
            insertAll(foundIndexes);
        } else {
            int indexToInsert = Arrays.asList(getMissList()).indexOf("_");
            getMissList()[indexToInsert] = letter;
        }
    }

    private void initGuessList(int length) {
        this.guessList = fillTheList(length, "_");
    }

    private void initMissList(int length) {
        this.missList = fillTheList(length, "_");
    }

    private void insertAll(String[] letters) {
        Arrays.stream(letters)
                .map(arr -> arr.split(","))
                .forEach(idxOfInsert -> getGuessList()[Integer.parseInt(idxOfInsert[0])] = idxOfInsert[1]);
    }

    private void setWordList(String[] wordList) {
        this.wordList = wordList;
    }

    private String[] selectRandomGameWord(int level, int maxTries) {
        if ( this.gameWord != null || maxTries == 0) {
            return new String[]{"h", "a", "n", "g", "m", "a", "n"};
        }

        String[] gameWord = (Arrays.asList(getWordList())
                .get((int) (Math.random() * getWordList().length)))
                .split("");
        return gameWord.length != level ? selectRandomGameWord(level, maxTries - 1) : gameWord;
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

    private String[] getGameWord() {
        return gameWord;
    }

    private String readFromAFile(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Path.of(fileName)));
        } catch (IOException io) {
            logger.log(Level.ALL, io.getMessage());
        }
        return data;
    }

}
