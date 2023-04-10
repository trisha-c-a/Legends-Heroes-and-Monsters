import java.util.Scanner;

public class Game {
    
    public Game(){
        System.out.println("Welcome to the game world!");
        System.out.println("The following games exist:");
        System.out.println("(1) Legends: Heroes and Monsters");

        while(true){
            System.out.println("Please enter the number of the game you would like to play or enter 'q' to quit");
            Scanner option = new Scanner(System.in);
            String choice = option.nextLine();
            if(choice.equals("1")){
                new HerosAndMonsters();
                break;
            }
            else if(choice.equals("q")){
                break;
            }
            else{
                System.out.println("Incorrect entry!");
            }
        }
    }
}
