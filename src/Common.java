import java.util.Scanner;

public class Common extends Cell implements Battle{
    public Common(String name) {
        super(name);
    }

    public boolean entrance(HeroGroup heroes){
        Random random = new Random();
        System.out.println("You have arrived at the common space");
        if(random.isFight()) {
            System.out.println("YOU HAVE ENCOUNTERED MONSTERS!!! PREPARE TO FIGHT!!!!");
            return this.Fight(heroes);
        }
        else{
            System.out.println("You are safe, you may proceed onwards in your conquest!");
            return true;
        }
    }

    @Override
    public boolean Fight(HeroGroup heroes) {
        MonsterPack monsters = new MonsterPack(heroes.getNumberOfHeros(), heroes.getHighestLevel());
        while(heroes.notFaint()>0 && monsters.notFaint()>0){
            System.out.println("Enter the name of the hero you would like to fight against a monster: ");
            heroes.stats();
            Scanner name = new Scanner(System.in);
            String decision = "";
            if(name.hasNextLine()){
                decision = name.nextLine();
            }
            Hero h1 = heroes.retrieveCharacter(decision);
            Monster m1 = h1.chooseMonster(monsters);
            Item tool = h1.chooseItem();
            h1.useItem(tool,m1);
            monsters.removeCharacter();

            if(!m1.getIsFainted()){
                m1.attackHero(h1);
                heroes.removeCharacter();
            }

        }

        return hasWon(heroes,monsters);

    }

    @Override
    public boolean hasWon(HeroGroup heroes, MonsterPack monsters) {
        if (heroes.notFaint()>0){
            heroes.updateHerosPostBattle(monsters.getHighestLevel());
            System.out.println("You have won the battle! Your attributes have been upgraded!!");
            return true;
        }
        System.out.println("You have lost the battle...Game over.");
        return false;
    }


}
