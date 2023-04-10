import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {

    public void readFile(List<List<String>> all, String path){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words
                String[] words = line.split("\\s+");
                if(words.length > 1){
                    all.add(Arrays.asList(words));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO create clean looking printing table
    public Map<String, List<List<String>>> getHeroDetails(){

        Map<String, List<List<String>>> final_heroes = new HashMap<>();
        List<List<String>> heroes1 = new ArrayList<>();
        List<List<String>> heroes2 = new ArrayList<>();
        List<List<String>> heroes3 = new ArrayList<>();

        readFile(heroes1, "Legends_Monsters_and_Heroes/Paladins.txt");
        final_heroes.put("Paladins", heroes1);

        readFile(heroes2, "Legends_Monsters_and_Heroes/Warriors.txt");
        final_heroes.put("Warriors", heroes2);

        readFile(heroes3, "Legends_Monsters_and_Heroes/Sorcerers.txt");
        final_heroes.put("Sorcerers", heroes3);

        System.out.println("Here are all the heroes available to you ->");

        for (String key : final_heroes.keySet()) {
            System.out.println(key + "->");
            for (List<String> hero : final_heroes.get(key)) {
                System.out.format("Name: %s, Mana: %s, Strength: %s, Agility: %s, Dexterity: %s, Starting_Money: %s, Starting_Experience: %s\n", hero.get(0), hero.get(1), hero.get(2), hero.get(3), hero.get(4), hero.get(5), hero.get(6));
            }
        }


        return final_heroes;

    }

    public List<List<String>> getMonsterDetails(){
        Map<String, List<List<String>>> final_monsters = new HashMap<>();
        List<List<String>> monsters = new ArrayList<>();
        List<List<String>> monsters1 = new ArrayList<>();
        List<List<String>> monsters2 = new ArrayList<>();
        List<List<String>> monsters3 = new ArrayList<>();

        readFile(monsters1, "Legends_Monsters_and_Heroes/Dragons.txt");
        final_monsters.put("Dragons", monsters1);

        readFile(monsters2, "Legends_Monsters_and_Heroes/Exoskeletons.txt");
        final_monsters.put("Exoskeletons", monsters2);

        readFile(monsters3, "Legends_Monsters_and_Heroes/Spirits.txt");
        final_monsters.put("Spirits", monsters3);

        for (String key : final_monsters.keySet()) {
            for (List<String> monster : final_monsters.get(key)) {
                List<String> a = new ArrayList<>();
                for(String m: monster){
                    a.add(m);
                }
                a.add(key);
                monsters.add(a);
            }
        }

        return monsters;

    }

    public List<List<List<String>>> getMarketDetails(){
        List<List<List<String>>> market = new ArrayList<>();

        List<List<String>> armory = new ArrayList<>();
        readFile(armory, "Legends_Monsters_and_Heroes/Armory.txt");
        market.add(armory);

        List<List<String>> potion = new ArrayList<>();
        readFile(potion, "Legends_Monsters_and_Heroes/Potions.txt");
        market.add(potion);

        List<List<String>> fireSpell = new ArrayList<>();
        readFile(fireSpell, "Legends_Monsters_and_Heroes/FireSpells.txt");
        market.add(fireSpell);

        List<List<String>> iceSpell = new ArrayList<>();
        readFile(iceSpell, "Legends_Monsters_and_Heroes/IceSpells.txt");
        market.add(iceSpell);

        List<List<String>> lightSpell = new ArrayList<>();
        readFile(lightSpell, "Legends_Monsters_and_Heroes/LightningSpells.txt");
        market.add(lightSpell);

        List<List<String>> weapon = new ArrayList<>();
        readFile(weapon, "Legends_Monsters_and_Heroes/Weaponry.txt");
        market.add(weapon);

        return market;

    }

}
