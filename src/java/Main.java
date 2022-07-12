public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        System.out.println(game.getLand().displayTheHeader("HUMANS VERSUS GOBLINS"));
        System.out.println(game.getLand().displayTheGrid(game.getLand().getGrid()));

//        land.subArray();
//        land.nthPascal(4);
    }

}