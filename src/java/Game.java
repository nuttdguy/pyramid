import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.Scanner;

public class Game {

    private static final String gameIntro = "Hello! What is your name?\n";
    private static final String welcomeMessage = "Well, %s, I am thinking of a number between %s and %s.\n";
    private static final String gameQuestion = "Take a guess.\n";
    private static final String highGuessMessage = "Your guess is too high.\n";
    private static final String lowGuessMessage = "your guess is too low.\n";
    private static final String winMessage = "Good job, %s! You guessed my number in %s guesses!\n";
    private static final String playAgainMessage = "Would you like to play again? (y or n)\n";
    private static final String errorInvalidType = "%s is not a valid number.\n";
    private static final String errorInvalidOption = "%s is not a valid option.\n";

    private int gameMin;
    private int gameMax;
    private int guessCounter;
    private int userGuess;
    private int winNumber;
    private boolean playGame;
    private String playAgain;
    private String username;
    private Scanner scanner;

    public Game() {
        setGameMin(1);
        setGameMax(20);
        setGuessCounter();
        setUserGuess();
        setWinNumber(20);
        setPlayGame(true);
        setPlayAgain("n");
        setUsername("");
    }
    public Game(int _min, int _max) {
        setGameMin(_min);
        setGameMax(_max);
        setGuessCounter();
        setUserGuess();
        setWinNumber(_max);
        setPlayGame(true);
        setPlayAgain("n");
        setUsername("");
    }
    public String getGameIntro() {
        return gameIntro;
    }
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
    public String getGameQuestion() {
        return gameQuestion;
    }
    public String getHighGuessMessage() {
        return highGuessMessage;
    }
    public String getLowGuessMessage() {
        return lowGuessMessage;
    }
    public String getWinMessage() {
        return winMessage;
    }
    public String getPlayAgainMessage() {
        return playAgainMessage;
    }
    public String getErrorInvalidType() {
        return errorInvalidType;
    }
    public String getErrorInvalidOption() {
        return errorInvalidOption;
    }

    private void setGameMin(int gameMin) {
        this.gameMin = gameMin;
    }

    public int getGameMin() {
        return gameMin;
    }
    private void setGameMax(int gameMax) {
        this.gameMax = gameMax;
    }

    public int getGameMax() {
        return gameMax;
    }

    public void incrementGuessCounter() {
        this.guessCounter++;
    }

    private void setGuessCounter() {
        this.guessCounter = 0;
    }

    public int getGuessCounter() {
        return guessCounter;
    }

    private void setUserGuess() {this.userGuess = 0;}
    public void setUserGuessByInput() {
        this.userGuess = scanner.nextInt();
    }

    public int getUserGuess() {
        return userGuess;
    }
    public void setWinNumber(int _max) {
        this.winNumber = this.generateGameNumber(_max);;
    }
    public int getWinNumber() {
        return winNumber;
    }
    public int generateGameNumber(int _max) {
        return (int) (Math.random() * _max);
    }

    private void setPlayGame(boolean playGame) {
        this.playGame = playGame;
    }

    public boolean getPlayGame() {
        return this.playGame;
    }

    private void setPlayAgain(String _yesOrNo) {
        this.playAgain = _yesOrNo;
    }

    public String getPlayAgain() {
        return playAgain;
    }

    public void setUsername(String _username) {
        this.username = _username;;
    }

    public void initInputStream(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public void setUsernameByInput() {
        this.username = scanner.next();
    }

    public String getUsername() {
        return username;
    }

    public String printGameIntro() {
        return getGameIntro();
    }

    public String printWelcomeMessage() {
        return String.format(getWelcomeMessage(), getUsername(), getGameMin(), getGameMax());
    }

    public String isGuessCorrect(int _guess, int _winNumber) {
        if (_guess == _winNumber) {
            return String.format(getWinMessage(), getUsername(), getGuessCounter());
        }
        return _guess > _winNumber ? getHighGuessMessage() : getLowGuessMessage();
    }

    public void startGame() {

        initInputStream(System.in);

        // print the game intro
        System.out.println(printGameIntro());

        // set the users name by input
        setUsernameByInput();

        // welcome / greet the user and ask question to pick number
        System.out.println(printWelcomeMessage());

        do {
            System.out.println(gameQuestion);

            try {
                setUserGuessByInput(); // get users guess
                incrementGuessCounter(); // increment the guess counter
                System.out.println();

                String gameMessage = isGuessCorrect(getUserGuess(), getWinNumber());

                if (gameMessage.equals(String.format(getWinMessage(), getUsername(), getGuessCounter()))) {

                    System.out.println(gameMessage); // print winning game message
                    System.out.println(playAgainMessage); // play again prompt

                    try {
                        playAgain = scanner.next();

                        if (playAgain.equals(("n"))) {
                            playGame = false;
                        } else {

                            // welcome / greet the user and ask question to pick number
                            System.out.println();
                            generateGameNumber(gameMax);
                            setGuessCounter();

                            System.out.printf((welcomeMessage) + "%n", username, gameMin, gameMax);
                        }


                    } catch (Exception e) {
                        System.out.printf((errorInvalidOption) + "%n", playAgain);
                    }
                }

                if (gameMessage.equals(getHighGuessMessage())) {
                    System.out.println(getHighGuessMessage());
                }

                if (gameMessage.equals(getLowGuessMessage())) {
                    System.out.println(getLowGuessMessage());
                }

            } catch (Exception e) {
                System.out.println(errorInvalidType);
            }

        } while (playGame);

        scanner.close();
    }

}
