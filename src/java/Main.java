import java.io.File;

public class Main {
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();
        File file = new File("words.txt");
        gamePanel.init(file.getAbsolutePath());
        gamePanel.run();

    }

}