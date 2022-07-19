public enum GameText {

    HEADER, GUESS, DUPLICATE, MISSED, WIN, LOSE, PLAY_AGAIN;

    String header() {
        return "H A N G M A N\n";
    }

    String guess() {
        return "Guess a letter: %s\n";
    }

    String missed() {
        return "Missed letters: %s\n";
    }

    String duplicate() {
        return "You have already guessed %s. Choose again. \n";
    }

    String win() {
        return "Yes! The secret word is \"%s\"! \nYou have won!\n";
    }

    String lose() { return "You have lost. The secret word was \"%s\".\n"; }

    String playAgain() {
        return "Do you want to play again? (yes or no) \n";
    }

}
