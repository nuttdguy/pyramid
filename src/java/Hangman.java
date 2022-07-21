import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.*;

public class Hangman {

    WordPanel wordPanel;
    private String name;

    Hangman() {
        init();
    }
    Hangman(String wordFilePath) {
        init(wordFilePath);
    }

    private void init() {
        this.wordPanel = new WordPanel();
    }
    private void init(String wordFilePath ) {
        this.wordPanel = new WordPanel(wordFilePath);
    }
    private String keyPress(InputStream inputStream) {
        return String.valueOf(new Scanner(inputStream).next().charAt(0));
    }
    private String keyPresses(InputStream inputStream) {
        return new Scanner((inputStream)).nextLine();
    }
    private boolean isNameNotSet() {
        return this.name == null;
    }
    private void setName(String name) {
        this.name = name;
    }
    private String getName() { return this.name; }

    void run() {

        out.printf(GameText.HEADER.header());
        while (true) {

            boolean play;
            if (wordPanel.isNotEqualToGameWord()) {
                if (isNameNotSet()) {
                    out.printf(GameText.COLLECT_NAME.collectName());
                    setName(keyPresses(in));
                    out.printf(GameText.GREETING.greeting(), getName());

                    out.printf(GameText.HEADER.header());
                    wordPanel.displayTheHighScoreOf(getName());
                    out.printf(wordPanel.displayGameArt());
                    out.printf(GameText.GUESS.guess(), "");
                }
                String key = keyPress(in);
                play = wordPanel.isCorrect(key);

                out.printf(GameText.HEADER.header());
                out.printf(wordPanel.displayGameArt());
                wordPanel.displayGuessNarrative(play);
            } else {
                out.println("----");
                wordPanel.displayWinLoseNarrative();
                if (!(wordPanel.isPlayingAgain(keyPress(in), getName()))) {
                    return;
                }
                out.printf(GameText.HEADER.header());
                wordPanel.displayTheHighScoreOf(getName());
                out.printf(wordPanel.displayGameArt());
                out.printf(GameText.GUESS.guess(), "");
            }
        }
    }


}
