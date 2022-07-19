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
            // get letter from the player
            String key = keyPress(in);
            // check letter is in game word

            boolean play = wordPanel.isCorrect(key);
            wordPanel.displayNarrative(play, key);
            String option = keyPress(in);
            if (option.equals("n")) {
                return;
            }
        }
    }


}
