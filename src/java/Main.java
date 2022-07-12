public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.getLand().displayTheHeader());
        System.out.println(game.getLand().displayTheGrid(game.getLand().getGrid()));
        game.start();

//        land.subArray();
//        land.nthPascal(4);
    }

}