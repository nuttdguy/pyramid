import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int gameMax = 20;
        int gameMin = 1;
        int gameWinNumber = generateGameNumber();
        int guessCounter = 0;
        int userGuess;
        boolean playGame = true;
        String playAgain = "n";
        String gameIntro = "Hello! What is your name?\n";
        String username;
        String welcomeMessage = "Well, %s, I am thinking of a number between %s and %s.\n";
        String gameQuestion = "Take a guess.\n";
        String highGuessMessage = "Your guess is too high.\n";
        String lowGuessMessage = "your guess is too low.\n";
        String winMessage = "Good job, %s! You guessed my number in %s guesses!\n";
        String playAgainMessage = "Would you like to play again? (y or n)\n";
        String errorOutOfRange = "%s is out of range %s and %s.\n";
        String errorInvalidType = "%s is not a valid number.\n";
        String errorInvalidOption = "%s is not a valid option.\n";
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

                if (userGuess > gameWinNumber) {
                    System.out.println(highGuessMessage);
                }
                else if (userGuess < gameWinNumber) {
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
                            gameWinNumber = generateGameNumber();
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

    public static int generateGameNumber() {
        return (int) (Math.random() * 20);
    }
}