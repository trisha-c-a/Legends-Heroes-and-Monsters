import java.util.Scanner;

public class HerosAndMonsters implements GameDetails{

    public HerosAndMonsters(){
        System.out.println("Welcome to Legends: Heroes and Monsters!");
        this.rules();
        this.playGame();

    }
    @Override
    public void rules() {
        System.out.println("                            RULES");
        System.out.println("The game is played in a square world with heroes and monsters.\n" +
                "You will pick a world size and heroes for the game.\n" +
                "Your heroes will travel in a pack across the world filled with markets, common spaces and inaccessible areas.\n" +
                "Markets are marked as \"M\". Inaccessible areas are marked as \"I\". Points in the world with no marking represent common spaces. Your hero group position is determined by a \"H\".\n" +
                "The following can occur as you move across the world:\n" +
                "1 - If you enter a common space, you might have to take part in a battle. The battle is against a random pack of monsters that you will have to defeat using your heroes to end the battle. If all your heroes faint, you lose the game. If you defeat all the monsters, you win the battle and earn incentives.\n" +
                "2 - If you enter a market, you can buy and sell items using your hero's gold. These items could aid your heroes during a battle. You can only visit a market one hero at a time. Additionally, each hero has its own inventory and cannot share items. Heroes also have their own gold and cannot share amongst themselves.\n" +
                "Once your hero with the highest level levels-up, a new world is generated!\n" +
                "The following controls are available:\n" +
                "1 - \"w\": Move up\n" +
                "2 - \"a\": Move left\n" +
                "3 - \"s\": Move down\n" +
                "4 - \"d\": Move right\n" +
                "5 - \"i\": Access hero party information outside a battle. Access hero and monster pack information during battle\n" +
                "6 - \"m\": Enter a market if on market cell\n" +
                "7 - \"e\": Check hero inventory and consume a potion\n" +
                "8 - \"z\": Display world map\n" +
                "\n" +
                "Enjoy the game!");

    }

    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of heroes you would like to have in your group (Number from 1-3): ");
        String number = scanner.nextLine();
        HeroGroup heroGroup = new HeroGroup(number);
        System.out.println("Please enter the size of the world n: ");
        String size = scanner.nextLine();
        World world = new World(heroGroup, size);
        Control control = new Control();
        while (true) {
            control.displayControls();
            System.out.println("Please enter a key");
            String key = scanner.nextLine();
            Boolean res = control.inputControl(world, heroGroup, key);
            if(res){}
            else{break;}

            world.displayBoard(heroGroup);
        }
        scanner.close();
    }
}
