
public class Main {
    public static void main(String[] args) {

        int level = Hangman.setLevel(7);
        Hangman hangman = new Hangman(level);
        hangman.run();

    }

}