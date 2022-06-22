import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String gameIntro = "\nYou are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n";
        String prompt_question = "Which cave will you go into?\n";
        String[] prompt_options = new String[]{"1. You approach the cave...", "2. It is dark and spooky..."};
        String[] prompt_reply = new String[]{"A large dragon jumps out in front of you! He opens his jaws and... Gobbles you down in one bite!"};

        Scanner scanner = new Scanner(System.in);
        int responseSelected = -1;

        // intro
        System.out.println(gameIntro);

        do {
            // ask question
            System.out.println(prompt_question);

            // iterate through options
            for (String prompt_option : prompt_options) {
                System.out.println(prompt_option);
            }

            try {
                responseSelected = scanner.nextInt();
            } catch (Exception e) {
                System.out.print("Not a valid number option. \n");
                responseSelected = -1;
            }

            if (responseSelected > 0 && responseSelected <= prompt_options.length) {
                System.out.println(prompt_reply[0]);
            } else {
                System.out.println("Invalid option, try again.\n");
            }

        } while (responseSelected > prompt_options.length || responseSelected <= 0);

        scanner.close();

    }


}