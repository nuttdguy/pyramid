import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.*;

public class Hangman {

    WordPanel wordPanel;
    private Scanner scanner;
    private String name;

    Hangman() {}

    public void init() {
        wordPanel = new WordPanel();
    }
    public String keyPress(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        return String.valueOf(scanner.next().charAt(0));
    }
    public String keyPresses(InputStream inputStream) {
        return new Scanner((inputStream)).nextLine();
    }
    public boolean isNameNotSet() {
        return this.name == null;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }

    void run() {
        init();

        out.printf(GameText.HEADER.header());
        while (true) {

            boolean play;
            if (wordPanel.isNotEqualToGameWord()) {
                if (isNameNotSet()) {
                    out.printf(GameText.COLLECT_NAME.collectName());
                    setName(keyPresses(in));
                    out.printf(GameText.GREETING.greeting(), getName());

                    out.printf(GameText.HEADER.header());
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
                if (!(wordPanel.isPlayingAgain(keyPress(in)))) {
                    return;
                }
                out.printf(GameText.HEADER.header());
                out.printf(wordPanel.displayGameArt());
                out.printf(GameText.GUESS.guess(), "");
            }
        }
    }


}
