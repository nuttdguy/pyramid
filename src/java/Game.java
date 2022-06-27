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

    private void incrementGuessCounter() {
        this.guessCounter++;
    }

    private void setGuessCounter() {
        this.guessCounter = 0;
    }

    private int getGuessCounter() {
        return guessCounter;
    }

    private void incrementUserGuess() {
        this.userGuess++;
    }
    private void setUserGuess() {this.userGuess = 0;}
    public int getUserGuess() {
        return userGuess;
    }
    private void setWinNumber(int _winNumber) {
        this.winNumber = this.generateGameNumber(_winNumber);;
    }

    private int getWinNumber() {
        return winNumber;
    }
    private int generateGameNumber(int _max) {
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

    private void setUsername(String _username) {
        this.username = _username;
    }

    public String getUsername() {
        return username;
    }

    public void StartGame() {

        Scanner scanner = new Scanner(System.in);

        // print the game intro
        System.out.println(gameIntro);

        // request the users name
        username = scanner.nextLine();

        // welcome / greet the user and ask question to pick number
        System.out.printf((welcomeMessage) + "%n", username, gameMin, gameMax);

        do {
            System.out.println(gameQuestion);

            try {
                userGuess = scanner.nextInt();
                System.out.println();

                guessCounter++;

                if (userGuess > winNumber) {
                    System.out.println(highGuessMessage);
                }
                else if (userGuess < winNumber) {
                    System.out.println(lowGuessMessage);
                }
                else {
                    System.out.printf((winMessage) + "%n", username, guessCounter);

                    // play again prompt
                    System.out.println(playAgainMessage);

                    try {
                        playAgain = scanner.next();

                        if (playAgain.equals(("n"))) {
                            playGame = false;
                        } else {

                            // welcome / greet the user and ask question to pick number
                            System.out.println();
                            winNumber = generateGameNumber(gameMax);
                            System.out.printf((welcomeMessage) + "%n", username, gameMin, gameMax);
                        }


                    } catch (Exception e) {
                        System.out.printf((errorInvalidOption) + "%n", playAgain);
                    }
                }

            } catch (Exception e) {
                System.out.println(errorInvalidType);
            }

        } while (playGame);

        scanner.close();
    }

}
