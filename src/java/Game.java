import java.io.InputStream;
import java.util.Scanner;

public class Game {

    private int responseSelected = -1;

    protected void setResponseSelected(int responseSelected) {this.responseSelected = responseSelected;}

    protected int getResponseSelected() {return responseSelected;}

    protected String getGameIntro() {
        return "\nYou are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n";
    }

    protected String getPromptQuestion() {
        return "Which cave will you go into?\n";
    }

    protected String[] getPromptOptions() {
        return new String[]{"1. You approach the cave...", "2. It is dark and spooky..."};
    }

    protected String[] getPromptReplies() {
        return new String[]{"A large dragon jumps out in front of you! He opens his jaws and... Gobbles you down in one bite!"};
    }

    protected String getInputStream(InputStream _inputStream) {
        Scanner scanner;
        String op = "";
        try {
            scanner = new Scanner(_inputStream);
            op = scanner.next();
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return op;
    }

    public void run() {

        System.out.println(getGameIntro());

        do {
            // ask question
            System.out.println(getPromptQuestion());

            // iterate through options
            for (String prompt_option : getPromptOptions()) {
                System.out.println(prompt_option);
            }

            try {
                setResponseSelected( Integer.parseInt(getInputStream(System.in)) );
            } catch (Exception e) {
                System.out.print("Not a valid number option. \n");
                setResponseSelected(-1);
            }

            if (getResponseSelected() > 0 && getResponseSelected() <= getPromptOptions().length) {
                System.out.println(getPromptReplies()[0]);
            } else {
                System.out.println("Invalid option, try again.\n");
            }

        } while (getResponseSelected() > getPromptOptions().length || getResponseSelected() <= 0);
    }

}
