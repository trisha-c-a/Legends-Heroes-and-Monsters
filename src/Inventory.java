import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


//Can do the following:
//(1) Store items in the format {ItemType:{Item:NumberOfSuchItems}}
//(2) Add item to inventory
//(3) Remove item from inventory or remove one of item if multiple of the same item exists
//(4) Display inventory
public class Inventory{
    public HashMap<String, HashMap<Item,Integer>> inventory = new HashMap<>();

    public Inventory(){

    }

    public Inventory(String type){
        Reader reader = new Reader();
        List<List<List<String>>> marketOptions = reader.getMarketDetails();
        //For Armor
        List<List<String>> armor = marketOptions.get(0);
        int randomNum = ThreadLocalRandom.current().nextInt(0, armor.size());
        this.addItem("Armor", new Armor(armor.get(randomNum).get(0), Double.parseDouble(armor.get(randomNum).get(1)), Long.parseLong(armor.get(randomNum).get(2)), Long.parseLong(armor.get(randomNum).get(3))));

        //For Potion
        List<List<String>> potion = marketOptions.get(1);
        randomNum = ThreadLocalRandom.current().nextInt(0, potion.size());
        this.addItem("Potion", new Potion(potion.get(randomNum).get(0), Double.parseDouble(potion.get(randomNum).get(1)), Long.parseLong(potion.get(randomNum).get(2)), Integer.parseInt(potion.get(randomNum).get(3)), potion.get(randomNum).get(4)));

        //For Fire Spell
        List<List<String>> fireSpell = marketOptions.get(2);
        randomNum = ThreadLocalRandom.current().nextInt(0, fireSpell.size());
        this.addItem("Fire Spell", new FireSpell(fireSpell.get(randomNum).get(0), Double.parseDouble(fireSpell.get(randomNum).get(1)), Long.parseLong(fireSpell.get(randomNum).get(2)), Double.parseDouble(fireSpell.get(randomNum).get(3)), Double.parseDouble(fireSpell.get(randomNum).get(4))));

        //For Ice Spell
        List<List<String>> iceSpell = marketOptions.get(3);
        randomNum = ThreadLocalRandom.current().nextInt(0, iceSpell.size());
        this.addItem("Ice Spell", new IceSpell(iceSpell.get(randomNum).get(0), Double.parseDouble(iceSpell.get(randomNum).get(1)), Long.parseLong(iceSpell.get(randomNum).get(2)), Double.parseDouble(iceSpell.get(randomNum).get(3)), Double.parseDouble(iceSpell.get(randomNum).get(4))));

        //For Light Spell
        List<List<String>> lightSpell = marketOptions.get(4);
        randomNum = ThreadLocalRandom.current().nextInt(0, lightSpell.size());
        this.addItem("Light Spell", new LightSpell(lightSpell.get(randomNum).get(0), Double.parseDouble(lightSpell.get(randomNum).get(1)), Long.parseLong(lightSpell.get(randomNum).get(2)), Double.parseDouble(lightSpell.get(randomNum).get(3)), Double.parseDouble(lightSpell.get(randomNum).get(4))));

        //For Weapon
        List<List<String>> weapon = marketOptions.get(5);
        randomNum = ThreadLocalRandom.current().nextInt(0, weapon.size());
        this.addItem("Weapon", new Weapon(weapon.get(randomNum).get(0), Double.parseDouble(weapon.get(randomNum).get(1)), Long.parseLong(weapon.get(randomNum).get(2)), Long.parseLong(weapon.get(randomNum).get(3)), Integer.parseInt(weapon.get(randomNum).get(4))));

    }

    public String[] doesExist(String itemType, String itemName) {
        String[] names = new String[2];
        //check if itemType exists
        while(!inventory.containsKey(itemType)){
            System.out.println("This type of item does not exist. Please re-enter type of item:");
            Scanner type = new Scanner(System.in);
            itemType= "";
            if(type.hasNextLine()){
                itemType = type.nextLine();
            }
        }

        //check if item with given name exists
        boolean check = false;
        while(true) {
            for (Map.Entry<Item, Integer> entry : inventory.get(itemType).entrySet()) {
                if(((Item)entry.getKey()).getName().equals(itemName)) {
                    check  = true;
                    names[0] = itemType;
                    names[1] = itemName;
                    break;
                }
            }
            if(check){
                return names;
            }
            else {
                System.out.println("The item name does not exist. Please re-enter item name:");
                Scanner name = new Scanner(System.in);
                itemName = "";
                if(name.hasNextLine()){
                    itemName = name.nextLine();
                }
            }
        }

    }

    public void addItem(String itemType,Item thing){
        boolean add = false;
//        System.out.println(inventory.containsKey(itemType));
        if(inventory.containsKey(itemType)){ //If item type exists in inventory
            for (Map.Entry<Item, Integer> entry : inventory.get(itemType).entrySet()) {
                if (((Item)entry.getKey()).getName()==((Item)thing).getName()) { // if item exists in inventory
                    inventory.get(itemType).put(entry.getKey(), inventory.get(itemType).get(entry.getKey()) + 1);
                    add = true;
                    break;
                }
            }
            if(add==false){ //Item does not exist in inventory
                inventory.get(itemType).put(thing, 1);
            }
        }
        else{ //If item type does not exist
            HashMap<Item,Integer> temp = new HashMap<>();
            temp.put(thing,1);
            inventory.put(itemType,temp);
        }

    }
    public void removeItem(String itemType, String itemName){
        String[] names = this.doesExist(itemType, itemName);
        itemType = names[0];
        itemName = names[1];
        for (Map.Entry<Item, Integer> entry : inventory.get(itemType).entrySet()) {
            if (((Item)entry.getKey()).getName().equals(itemName)) {
                if (entry.getValue() > 1) {
                    //inventory.get(itemType) = value aka hashmap belonging to item type
                    //entry.getKey() = key of inner hashmap we want to update
                    //inventory.get(itemType).get(...) = value of inner hashmap being updated
//                    inventory.get(itemType).put(entry.getKey(), inventory.get(itemType).get(entry.getKey()) +1);
                    inventory.get(itemType).put(entry.getKey(), inventory.get(itemType).get(entry.getKey())-1);
                } else {
                    //inventory.get(itemType) = value aka hashmap belonging to item type
                    //remove = used to remove key-value pair if only one of item is available in inventory
                    inventory.get(itemType).remove(entry.getKey());
                    inventory.remove(itemType);
                }
                break;
            }
        }

    }

    public Item retrieveItem(String itemType, String itemName){
        String[] names = this.doesExist(itemType, itemName);
        itemType = names[0];
        itemName = names[1];
        for (Map.Entry<Item, Integer> entry : inventory.get(itemType).entrySet()) {
            if (((Item)entry.getKey()).getName().equals(itemName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void viewInventory(){
        if(inventory.size() == 0) System.out.println("Your inventory is empty!");
        else{
            for (Map.Entry<String, HashMap<Item,Integer>> entry1 : inventory.entrySet()) {
                if (entry1.getKey().equals("Armor")) {
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Damage Reduction" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Armor").entrySet()){
                        Armor a = (Armor) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getDamageReduction() + "       " + entry2.getValue());
                    }
    
                } else if (entry1.getKey().equals("Potion")) {
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Attribute Increase" + "       " + "Attribute Affected" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Potion").entrySet()){
                        Potion a = (Potion) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getAttributeIncrease() + "       " + a.getAttributeAffected() + "       " + entry2.getValue());
                    }
    
                }
                else if (entry1.getKey().equals("Fire Spell")) {
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Damage" + "       " + "MP" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Fire Spell").entrySet()){
                        FireSpell a = (FireSpell) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getDamage() + "       " + a.getMP() + "       " + entry2.getValue());
                    }
    
                }
                else if (entry1.getKey().equals("Ice Spell")) {
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Damage" + "       " + "MP" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Ice Spell").entrySet()){
                        IceSpell a = (IceSpell) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getDamage() + "       " + a.getMP() + "       " + entry2.getValue());
                    }
    
                }
                else if (entry1.getKey().equals("Light Spell")) {
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Damage" + "       " + "MP" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Light Spell").entrySet()){
                        LightSpell a = (LightSpell) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getDamage() + "       " + a.getMP() + "       " + entry2.getValue());
                    }
    
                }
                else if(entry1.getKey().equals("Weapon")){
                    System.out.println();
                    System.out.println("                                " + entry1.getKey());
                    System.out.println("Name" + "       " + "Price" + "       " + "Level" + "       " + "Damage Value" + "       " + "Number of Hands" + "       " + "Stock");
                    for (Map.Entry<Item, Integer> entry2 : inventory.get("Weapon").entrySet()){
                        Weapon a = (Weapon) entry2.getKey();
                        System.out.println(a.getName() + "       " + a.getPrice() + "       " + a.getLevel() + "       " + a.getDamageValue() + "       " + a.getNumHands() + "       " + entry2.getValue());
                    }
                }
    
            }
    
        
        }
        }
    public HashMap<String, HashMap<Item,Integer>> getInventory(){
        return this.inventory;
    }

}
