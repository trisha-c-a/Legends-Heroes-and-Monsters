import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;

public class MonsterPack implements Group{

    public int numOfMonster;
    public int highestLevel;
    public List<Monster> pack = new ArrayList<Monster>();

    public MonsterPack(int numOfHeros, int level){
        List<List<String>> filteredMonsters = new ArrayList<>();
        Reader reader = new Reader();
        this.numOfMonster = numOfHeros;
        this.highestLevel = level;
        List<List<String>> monsters = reader.getMonsterDetails();
            for (List<String> monster : monsters)
                if(Integer.parseInt(monster.get(1)) <= level){
                    filteredMonsters.add(monster);
                }

        // while(filteredMonsters.size() < numOfHeros){
        //     int randomNum = ThreadLocalRandom.current().nextInt(0, filteredMonsters.size());
        //     filteredMonsters.add(filteredMonsters.get(randomNum));
        // }

        while(numOfHeros > 0){
            int randomNum = ThreadLocalRandom.current().nextInt(0, filteredMonsters.size());
            Monster monster = new Monster(filteredMonsters.get(randomNum).get(0),filteredMonsters.get(randomNum).get(5),
                    Integer.parseInt(filteredMonsters.get(randomNum).get(1)),
                    Double.parseDouble(filteredMonsters.get(randomNum).get(2)),
                    Double.parseDouble(filteredMonsters.get(randomNum).get(3)),
                    Double.parseDouble(filteredMonsters.get(randomNum).get(4)));
            this.pack.add(monster);
            filteredMonsters.remove(randomNum);
            numOfHeros -= 1;
        }




    }

    public Monster retrieveCharacter(String name) {
        name = characterExists(name);
        for(int i=0; i<pack.size();i++){
            if(pack.get(i).getName().equals(name)){
                return pack.get(i);
            }
        }
        return null;
    }

    public List<Monster> getPack(){
        return this.pack;
    }
    @Override
    public void stats() {
        System.out.println("                            Monster Statistics");
        System.out.println();
        System.out.println("Name" + "       " + "HP" + "       " + "Level" + "       " + "Damage" + "       " + "Defense"+ "       " + "Dodge Ability");
        for(int i=0; i<pack.size();i++){
            if(!pack.get(i).getIsFainted()) {
                System.out.println(pack.get(i).getName() + "       " + pack.get(i).getHP() + "       "  + pack.get(i).getLevel() + "       "
                        + pack.get(i).getBaseDamage() + "       " + pack.get(i).getDefenseValue() + "       "
                        + pack.get(i).getDodgeAbility());
            }
        }

    }

    @Override
    public String characterExists(String name) {
        boolean exists = false;
            for (int i = 0; i < pack.size(); i++) {
                if (pack.get(i).getName().equals(name)&& !pack.get(i).getIsFainted()) {
                    exists=true;
                    return name;
                }
            }
            if(!exists){
                System.out.println("The monster you entered does not exist. The following monsters are available for battle.");
                stats();
                System.out.println("Please enter a different monster name:");
                Scanner n1 = new Scanner(System.in);
                name = n1.nextLine();
                return characterExists(name);
            }
            return null;

    }

    @Override
    public void removeCharacter() {
        for(int i=0; i<pack.size();i++){
            pack.get(i).checkHP();
            if (pack.get(i).getIsFainted()) {
                System.out.println( pack.get(i).getName() + " has fainted!");
                pack.remove(pack.get(i));
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

    @Override
    public int getHighestLevel(){
        return this.highestLevel;
    }

}
