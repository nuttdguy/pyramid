import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.*;

public class GamePanel {

    WordPanel wordPanel;
    private Scanner scanner;

    GamePanel() {}

    public void init(String filePath) {
        wordPanel = new WordPanel(filePath);
    }

    String keyPress(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        return String.valueOf(scanner.next().charAt(0));
    }

    void run() {

        while (true) {
            out.printf(GameText.HEADER.header());

            boolean play;
            if (wordPanel.isNotEqualToGameWord()) {
                String key = keyPress(in);
                play = wordPanel.isCorrect(key);
                wordPanel.displayGuessNarrative(play);
            } else {
                wordPanel.displayWinLoseNarrative();
                if (!(wordPanel.isPlayingAgain(keyPress(in)))) {
                    return;
                }
            }
        }
    }


}
