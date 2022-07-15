public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.getMap().displayTheHeader());
        System.out.println(game.getMap().displayTheGrid(game.getMap().getGrid()));
        game.start();


    }

}