import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.lang.System.*;

public class Hangman {

    static Logger logger = Logger.getLogger(String.valueOf(Hangman.class));
    Dictionary dictionary;
    private String name;
    private final String scoreCardPath = "score_card.txt";
    private String[] drawings;
    private final String drawingFilePath = "art.txt";

    Hangman(int level) {
        init(level);
    }
    Hangman(int level, String wordFilePath) {
        init(level, wordFilePath);
    }

    private void init(int level) {
        this.dictionary = new Dictionary(level);
        setDrawings();
    }
    private void init(int level, String wordFilePath) {
        this.dictionary = new Dictionary(level, wordFilePath);
        setDrawings();
    }
    public static int setLevel(int level) {
        if (level < 3 || level > 12) {
            level = (int) (Math.random() * 11);
        }
        return level;
    }
    private void setName(String name) {
        this.name = name;
    }
    private boolean isNameNotSet() {
        return this.name == null;
    }
    private String keyPress() {
        return String.valueOf(new Scanner(System.in).next().charAt(0));
    }
    private String keyPresses() {
        return new Scanner((System.in)).nextLine();
    }
    private String getName() { return this.name; }
    public String upCase(String word) {
        return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
    }
    private void displayNameNotSetGreeting() {
        out.printf(GameText.COLLECT_NAME.collectName());
        setName(keyPresses());

        out.printf(GameText.HEADER.header());
        out.printf(GameText.GREETING.greeting(), upCase(getName()));
        displayTheHighScore(upCase(getName()));
        out.printf(displayGameArt());
        out.printf(GameText.GUESS.guess(), "");
    }
    private void displayGameLoopNarrative() {

        String letter = keyPress();
        if (dictionary.isDuplicateGuess(dictionary.getGuessList(), letter)
                || dictionary.isDuplicateGuess(dictionary.getMissList(), letter)) {

            out.printf(GameText.DUPLICATE.duplicate(), letter);
        } else {
           dictionary.hasLetterThenAdd(letter);
        }

        out.printf(GameText.HEADER.header());
        out.printf(displayGameArt());
        out.printf(GameText.MISSED.missed(), Arrays.toString(dictionary.getMissList()));
        out.printf(GameText.GUESS.guess(), Arrays.toString(dictionary.getGuessList()));
    }
    private void displayPlayAgainNarrative() {
        int level;
        do {
            try {
                out.printf(GameText.DIFFICULTY.difficulty());
                level = Integer.parseInt(keyPresses());
                if (level > 12) {
                    out.println("You entered a number too high. The difficulty will be set to max.\n");
                    level = 11;
                } else if (level < 3) {
                    out.println("You entered a number too low. The difficulty will be set to the min.\n");
                    level = 3;
                }

            } catch (NumberFormatException nfe) {
                out.println("You did not enter a number. The difficulty will be set to random.\n");
                level = setLevel(Integer.MAX_VALUE);
            }
        } while ( level < 3 );

        dictionary.reload(level);
        setDrawings();

        out.printf(GameText.HEADER.header());
        displayTheHighScore(upCase(getName()));
        out.printf(displayGameArt());
        out.printf(GameText.GUESS.guess(), "");
    }
    private void displayWinLoseNarrative() {
        out.println("----");
        String[] word;
        if (dictionary.isListEqual(dictionary.getGuessList())) {
            word = dictionary.getWordOrEmpty();
            out.printf(GameText.WIN.win(), Arrays.toString(word));
        } else {
            word = dictionary.getWordOrEmpty();
            out.printf(GameText.LOSE.lose(), Arrays.toString(word));
        }
        out.printf(GameText.PLAY_AGAIN.playAgain());
        writeToFile(getScoreCardPath(), scoreTheGameFor(name, word));
    }
    public void displayTheHighScore(String playerName) {
        class Score {
            private String name;
            private String word;
            private String misses;
            private String guesses;
            private String score;

            Score() {}
            Score(String name, String word, String misses, String guesses, String score) {
                this.name = name;
                this.word = word;
                this.misses = misses;
                this.guesses = guesses;
                this.score = score;
            }
            protected Integer getScore() {
                return Integer.parseInt(this.score.split(":")[1].replace(",", "").trim());
            }

            protected String getName() {
                return this.name.split(":")[1].replace(",", "").trim();
            }

            protected String getWord() {
                return this.word.split(":")[1]
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "").trim();
            }

            @Override
            public String toString() {
                return "Highest Score is " +
                        getScore() + " guesses for a word length of " +
                        getWord().length() + " by " +
                        String.valueOf(getName().charAt(0)).toUpperCase() + getName().substring(1) +".";
            }
        }

        String[] scoreRecords = readFromAFile(getScoreCardPath()).split(";\n");
        Score[] scores;
        if (!scoreRecords[0].equals("")) {
            scores = Arrays.stream(scoreRecords)
                    .map(arr -> {
                        String[] record = arr.split("\n");
                        if (record.length < 6) {
                            return new Score(record[0], record[1], record[2], record[3], record[4]);
                        } else {
                            out.println("The score file has been modified, delete the file and try again.");
                            return new Score();
                        }
                    })
                    .toArray(Score[]::new);

            Score playerWithHighestScore = Stream.of(scores).reduce((a, c) -> a.getScore() > c.getScore() ? a : c).orElse(scores[0]);
            out.printf(playerWithHighestScore.toString());
            out.printf("\n%s, you %s have the highest score.\n", playerName,
                    playerWithHighestScore.getName().equalsIgnoreCase(playerName) ? "do" : "do not");
            out.println("----");

        }
        // no records exist, do nothing
    }
    private void setDrawings() {

        String[] drawings = readFromAFile(drawingFilePath).split(";");
        int middle = dictionary.getGuessList().length / 2;
        int wordLength = dictionary.getGuessList().length;
        int a = 0, b = middle + 1, c = drawings.length - (middle+1), d = drawings.length;

        if(wordLength<= 3) {
            this.drawings = new String[]{drawings[0], drawings[5], drawings[9], drawings[drawings.length-1]};
        } else if (wordLength==4) {
            this.drawings = new String[]{drawings[0], drawings[3], drawings[5], drawings[9], drawings[drawings.length-1]};
        } else if (wordLength == drawings.length) {
            this.drawings = drawings;
        } else {
            String[] drawingSet1 = Arrays.asList(drawings).subList(a, b).toArray(String[]::new);
            String[] drawingSet2 = Arrays.asList(drawings).subList(c, d).toArray(String[]::new);
            this.drawings = Stream.concat(Arrays.stream(drawingSet1), Arrays.stream(drawingSet2))
                    .toArray(size -> (String[]) Array.newInstance(drawingSet1.getClass().getComponentType(), size));
        }
    }
    private void writeToFile(String path, String gameData) {
        File file = new File(path);
        boolean append = false;
        try {
            if (file.exists()) {
                append = true;
            }
            FileOutputStream io = new FileOutputStream(file.getAbsolutePath(), append);
            io.write(gameData.getBytes());
            io.close();

        } catch(IOException ioe) {
            logger.log(Level.ALL, ioe.getMessage());
        }

    }
    private String readFromAFile(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Path.of(fileName)));
        } catch (IOException io) {
//            logger.log(Level.ALL, io.getMessage());
        }
        return data;
    }
    private String getScoreCardPath() {
        return this.scoreCardPath;
    }
    private String scoreTheGameFor(String name, String[] word) {
        int misses = (int) Arrays.stream(dictionary.getMissList()).filter(e-> !e.equals("_")).count();
        int guesses = (int) Arrays.stream(dictionary.getGuessList()).filter(e-> !e.equals("_")).count();
        int score = Arrays.equals(word, dictionary.getGuessList()) ? guesses + misses : -misses;

        return "Name:" + name + "\n" +
                "Word:" + Arrays.toString(word) + "\n"+
                "Misses:" + misses + ", Missed letters: " + Arrays.toString(dictionary.getMissList()) + "\n" +
                "Guesses:" + guesses + ", Guessed letters: " + Arrays.toString(dictionary.getGuessList()) + "\n" +
                "Score:" + score + "\n;\n";
    }
    public String displayGameArt() {
        int idx = Arrays.asList(dictionary.getMissList()).indexOf("_");
        return getDrawings()[idx < 0 ? getDrawings().length-1 : idx] +"\n";
    }
    public String[] getDrawings() {
        return this.drawings;
    }

    void run() {

        out.printf(GameText.HEADER.header());
        while (true) {

            if (dictionary.isNotEqualToGameWord()) {
                if (isNameNotSet()) {
                    displayNameNotSetGreeting();
                }
                displayGameLoopNarrative();
            } else {

                displayWinLoseNarrative();
                if (!(dictionary.isPlayingAgain(keyPress(), getName()))) {
                    return;
                }
                displayPlayAgainNarrative();
            }
        }
    }


}
