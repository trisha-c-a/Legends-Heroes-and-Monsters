import java.util.Scanner;

//(1) Store items
//(2) Sell items
//(3) Buy items (from heroes)
//(4) Display market
//(5) Enter market (contain options for what the player would like to do)
public class Market extends Cell{

    public Inventory store = new Inventory("M");
    public Market(String name) {
        super(name);
    }

    public boolean entrance(Hero h){
        System.out.println("You can do the following at the market:");
        System.out.println("1 - View what the market has to offer");
        System.out.println("2 - Purchase an item");
        System.out.println("3 - Sell an item");
        System.out.println("4 - Leave the market");
        System.out.println();
        System.out.println("Please enter a number between 1 and 4 to determine what you would like to do:");

        Scanner choice = new Scanner(System.in);
        String decision = "";
        if(choice.hasNextLine()){
            decision = choice.nextLine();
        }
        switch(decision){
            case "1":
                System.out.println("Market Inventory");
                store.viewInventory();
                return entrance(h);
            case "2":
                return purchaseItem(h);
            case "3":
                return sellItem(h);
            case "4":
                return true;
            default:
                System.out.println("Incorrect entry, please try again");
                return entrance(h);
        }
    }

    //Sell item to hero
    public Boolean purchaseItem(Hero h){
        System.out.println("Items available in stock:");
        store.viewInventory();
        System.out.println();
        System.out.println("Enter the type of item you would like to purchase (Weapon,Potion,etc):");
        Scanner type = new Scanner(System.in);
        String itemType= "";
        if(type.hasNextLine()){
            itemType = type.nextLine();
        }

        System.out.println("Enter the name of the item:");
        Scanner name = new Scanner(System.in);
        String itemName= "";
        if(name.hasNextLine()){
            itemName = name.nextLine();
        }

        Item temp = store.retrieveItem(itemType,itemName);
        if(temp.getLevel()<=h.getLevel()){
            if(h.getGoldAmount()>=temp.getPrice()){
                h.getBag().addItem(itemType,temp);
                h.setGoldAmount(h.getGoldAmount()-temp.getPrice());
                store.removeItem(itemType,itemName);
                System.out.println("Purchase successful.");
                System.out.println("Would you like to buy another item? Enter 1 for yes");

                Scanner option = new Scanner(System.in);
                String choice = "";
                if(option.hasNextLine()){
                    choice = option.nextLine();
                }
                if(choice.equals("1")){
                    return purchaseItem(h);
                }
                else{
                    return entrance(h);
                }
            }
        }
        else{
            System.out.println("You are not at the right level/do not have enough gold. You have the following options:");
            System.out.println("Enter '1' to buy a different item");

            Scanner option = new Scanner(System.in);
            String choice = "";
            if(option.hasNextLine()){
                choice = option.nextLine();
            }
            if (choice.equals("1")){
                return purchaseItem(h);
            }
            else{
                return entrance(h);
            }
        }
        return null;

    }

    //Buy item from hero
    public Boolean sellItem(Hero h){
        if(h.getBag().getInventory().size()==0){
            System.out.println("You have nothing to sell.");
            return entrance(h);
        }
        else {
            h.getBag().viewInventory();
            System.out.println("Enter the type of item you would like to sell (Weapon,Potion,etc):");
            Scanner type = new Scanner(System.in);
            String itemType = type.nextLine();

            System.out.println("Enter the name of the item:");
            Scanner name = new Scanner(System.in);
            String itemName = name.nextLine();

            Item temp = h.getBag().retrieveItem(itemType, itemName);
            h.setGoldAmount(h.getGoldAmount() + temp.getPrice() / 2);
            h.getBag().removeItem(itemType, itemName);
            store.addItem(itemType, temp);
            System.out.println("Sale successful.");
            System.out.println("Would you like to sell another item? Enter 1 for yes");
            Scanner option = new Scanner(System.in);
            String choice = "";
            if(option.hasNextLine()){
                choice = option.nextLine();
            }
            if (choice.equals("1")) {
                return sellItem(h);
            } else {
                return entrance(h);
            }
        }
        
    }

    public Inventory getStore(){ //Use this to create market inventory when market cell is created
        return this.store;
    }


}
