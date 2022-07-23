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

public class Dictionary {

    static Logger logger = Logger.getLogger(String.valueOf(Dictionary.class));
    enum ListType { GAME_WORD, GUESS_LIST, MISS_LIST}
    private String[] wordList;
    private String[] gameWord;
    private String[] guessList;
    private String[] missList;
    private String[] drawings;

    private final String drawingFilePath = "art.txt";
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
        setDrawings();
    }
    /* Initializes game words from a provided file path */
    private void init(int level, String wordFilePath) {
        setWordList(readFromAFile(wordFilePath).split("\r\n"));
        setAGameWord(level);
        setDrawings();
    }

    //== PUBLIC METHODS
    public void displayGuessNarrative(boolean play) {
        if (!play) {
            out.printf(GameText.MISSED.missed(), Arrays.toString(getMissList()));
            out.printf(GameText.GUESS.guess(), Arrays.toString(getGuessList()));
        }
    }

    public String[] displayWinLoseNarrative() {
        if (Arrays.equals(getGuessList(), getGameWord())) {
            out.printf(GameText.WIN.win(), Arrays.toString(getGameWord()));
        } else {
            out.printf(GameText.LOSE.lose(), Arrays.toString(getGameWord()));
        }
        out.printf(GameText.PLAY_AGAIN.playAgain());
        return getGameWord();
    }

    public String displayGameArt() {
        int idx = indexOfLetterIn("_", getMissList());
        return getDrawings()[idx < 0 ? getDrawings().length-1 : idx] +"\n";
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
    public String[] getDrawings() {
        return this.drawings;
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

    public boolean isPlayingAgain(String keyResponse, String name) {
        return keyResponse.equals("y");
    }

    public boolean isNotEqualToGameWord() {
        return !(Arrays.stream(getMissList())
                .filter(e -> !(e.equals("_"))).count() == getGameWord().length ||
                Arrays.equals(getGuessList(), getGameWord()));
    }

    //== PROTECTED

    protected boolean isLetterIn(ListType type, String letter) {
        if (type.equals(ListType.GAME_WORD)) {
            return Arrays.asList(getGameWord()).contains(letter);
        } else if (type.equals(ListType.GUESS_LIST)) {
            return Arrays.asList(getGuessList()).contains(letter);
        }
        return Arrays.asList(getMissList()).contains(letter);
    }

    protected void reload(int level) {
        this.gameWord = null;
        setAGameWord(level);
        initGuessList(getGameWord().length);
        initMissList(getGameWord().length);
        setDrawings();
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
    private void setDrawings() {

        String[] drawings = readFromAFile(drawingFilePath).split(";");
        int middle = getGameWord().length / 2;
        int a = 0, b = middle + 1, c = drawings.length - (middle+1), d = drawings.length;

        if(getGameWord().length <= 3) {
            this.drawings = new String[]{drawings[0], drawings[5], drawings[9], drawings[drawings.length-1]};
        } else if (getGameWord().length==4) {
            this.drawings = new String[]{drawings[0], drawings[3], drawings[5], drawings[9], drawings[drawings.length-1]};
        } else if (getGameWord().length == drawings.length) {
            this.drawings = drawings;
        } else {
            String[] drawingSet1 = Arrays.asList(drawings).subList(a, b).toArray(String[]::new);
            String[] drawingSet2 = Arrays.asList(drawings).subList(c, d).toArray(String[]::new);
            this.drawings = Stream.concat(Arrays.stream(drawingSet1), Arrays.stream(drawingSet2))
                    .toArray(size -> (String[]) Array.newInstance(drawingSet1.getClass().getComponentType(), size));
        }
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
    private int indexOfLetterIn(String letter, String[] array) {
        return Arrays.asList(array).indexOf(letter);
    }

}
