import java.util.*;

import static java.lang.Math.max;

public class HeroGroup implements Group{
    public String symbol = "H";
    public int numberOfHeros;
    public List<Integer> boardPos = Arrays.asList(0,0);

    public List<Hero> pack = new ArrayList<Hero>();

    public HeroGroup(String num){
        this.numberOfHeros = checkNumHeroes(num);
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        Map<String, List<List<String>>> heroes = reader.getHeroDetails();
        Set<String> names_added = new HashSet<String>();
        for(int i = 1; i <= this.numberOfHeros; i++){
            System.out.printf("Please enter the name of hero %d:", i);
            String name = scanner.nextLine();
            List<String> details = nameExists(heroes, name);
            if(details != null){
                if(names_added.contains(details.get(0))){
                    System.out.println("Duplicate heroes are not allowed!");
                    i -= 1;
                    continue;
                }
                names_added.add(details.get(0));
                Hero hero;
                switch (details.get(7)){                    
                    case "Warriors":
                     hero = new Warrior(details.get(0), details.get(7),Double.parseDouble(details.get(1)), Long.parseLong(details.get(6)), Double.parseDouble(details.get(2)), Double.parseDouble(details.get(4)), Double.parseDouble(details.get(3)), Double.parseDouble(details.get(5)));
                     break;
                    case "Paladins": 
                    hero = new Paladin(details.get(0), details.get(7),Double.parseDouble(details.get(1)), Long.parseLong(details.get(6)), Double.parseDouble(details.get(2)), Double.parseDouble(details.get(4)), Double.parseDouble(details.get(3)), Double.parseDouble(details.get(5)));
                    break;
                    case "Sorcerers":
                     hero = new Sorceror(details.get(0), details.get(7),Double.parseDouble(details.get(1)), Long.parseLong(details.get(6)), Double.parseDouble(details.get(2)), Double.parseDouble(details.get(4)), Double.parseDouble(details.get(3)), Double.parseDouble(details.get(5)));
                     break;
                    default: 
                     hero = null;
                     break;
                }
                this.pack.add(hero);
            }
            else{
                System.out.println("Wrong name entered. Please try again");
                i -= 1;
            }

        }

    }

    public int checkNumHeroes(String num){
        Scanner scnr = new Scanner(System.in);
        while(true){            
            switch(num){
                case "1": return 1;
                case "2": return 2;
                case "3": return 3;
                default: {
                    System.out.println("Incorrect entry! Please enter again:");
                    break;
                }
            }
            num = scnr.nextLine();
            
        }
    }

    public boolean checkInventory(){
        Scanner scanner = new Scanner(System.in);
        this.stats();
        System.out.println("Enter the name of the hero whose inventory you would like to see - ");
        String name = scanner.nextLine();
        Hero h = this.retrieveCharacter(name);
        h.getBag().viewInventory();
        if(h.getBag().getInventory().containsKey("Potion")){
        System.out.println("Would you like to consume a potion? (Y)");
        String choice = scanner.nextLine();
        if(choice.equals("Y")) {
            System.out.println("Enter the name of the potion: ");
            String potion = scanner.nextLine();
            Item product = h.getBag().retrieveItem("Potion",potion);
            h.consumePotion(product);
        }
    }
        return true;

    }

    public int getNumberOfHeros() {
        return this.numberOfHeros;
    }
    @Override
    public int getHighestLevel(){
        int maximum = 0;
        for(Hero hero: this.pack){
            maximum = max(maximum, hero.getLevel());
        }
        return maximum;
    }
    public List<String> nameExists(Map<String, List<List<String>>> heroes, String name) {
        for (String key : heroes.keySet()) {
            for (List<String> hero : heroes.get(key)) {
                if (hero.get(0).equals(name)) {
                    List<String> a = new ArrayList<>();
                    for(String h: hero){
                        a.add(h);
                    }
                    a.add(key);
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public void stats() {
        System.out.println("                            Hero Statistics");
        System.out.println();
        System.out.println("Name" + "       " + "HP" + "       " + "MP" + "       "
                + "Strength" + "       " + "Agility"+ "       " + "Dexterity"
                + "       " + "Gold"+ "       " + "Experience" +  "       " + "Level");
        for(int i=0; i<pack.size();i++){
            if(!pack.get(i).getIsFainted()) {
                System.out.println(pack.get(i).getName() + "       " + pack.get(i).getHP() + "       " + pack.get(i).getMP() + "       "
                        + pack.get(i).getStrengthValue() + "       " + pack.get(i).getAgilityValue() + "       "
                        + pack.get(i).getDexterityValue() + "       " + pack.get(i).getGoldAmount()
                        + "       " + pack.get(i).getExperiencePoints()+ "       " + pack.get(i).getLevel());
            }
        }
    }

    @Override
    public String characterExists(String name) {
        boolean exists = false;
            for (int i = 0; i < pack.size(); i++) {
                if (pack.get(i).getName().equals(name) && !pack.get(i).getIsFainted()) {
                    exists=true;
                    return name;
                }
            }
            if(!exists){
                System.out.println("The hero you entered does not exist/ has fainted. The following heros are part of your pack.");
                stats();
                System.out.println("Please enter a different hero name:");
                Scanner n1 = new Scanner(System.in);
                name = n1.nextLine();
                return characterExists(name);
            }
            return null;
        }

    @Override
    public void removeCharacter() {
        for(int i=0; i<pack.size();i++) {
            pack.get(i).checkHP();
            if (pack.get(i).getIsFainted()) {
                System.out.println( pack.get(i).getName() + " has fainted!");
            }
        }
    }

    @Override
    public long notFaint() {
        long count = pack.size();
        for(int i=0; i<pack.size();i++) {
            pack.get(i).checkHP();
            if (pack.get(i).getIsFainted()) {
                count -= 1;
            }
        }
        return count;
    }

    public Hero retrieveCharacter(String name) {
        name = characterExists(name);
        for(int i=0; i<pack.size();i++){
            if(pack.get(i).getName().equals(name)){
                return pack.get(i);
            }
        }
        return null;
    }

    public void updateHerosPostBattle(int monsterLevel){
        for(int i=0; i<pack.size();i++){
            pack.get(i).roundUpdate(monsterLevel);
            if(pack.get(i) instanceof Warrior){
                ((Warrior) pack.get(i)).updateSkill();
            } else if (pack.get(i) instanceof Paladin){
                ((Paladin) pack.get(i)).updateSkill();
            }
            else if(pack.get(i) instanceof Sorceror){
                ((Sorceror) pack.get(i)).updateSkill();
            }
        }
        stats();
    }

    public List<Integer> getBoardPos(){
        return this.boardPos;
    }

    public void setBoardPos(int a, int b){
        this.boardPos.set(0,a);
        this.boardPos.set(1,b);
    }
}
