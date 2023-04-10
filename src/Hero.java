import java.util.Scanner;

public class Hero extends Character{
    public double MP;
    public long experiencePoints;
    public double strengthValue;
    public double dexterityValue;
    public double agilityValue;
    public double goldAmount;
    public Inventory bag = new Inventory();

    //Constructor for a default hero
    public Hero(){
        super();
        this.MP = 1000;
        this.experiencePoints = 0;
        this.strengthValue = 400;
        this.dexterityValue = 500;
        this.agilityValue = 450;
        this.goldAmount = 2500;
    }

    //Constructor for a default character but user defined hero attributes
    public Hero(double mp, long exp, double sVal, double dexVal, double agVal,double gAmt){
        super();
        this.MP = mp;
        this.experiencePoints = exp;
        this.strengthValue = sVal;
        this.dexterityValue = dexVal;
        this.agilityValue = agVal;
        this.goldAmount = gAmt;
    }

    //Constructor for user defined hero attributes
    public Hero(String nme, String t, double mp, long exp, double sVal, double dexVal, double agVal,double gAmt){
        super(nme,1,100,t);
        this.MP = mp;
        this.experiencePoints = exp;
        this.strengthValue = sVal;
        this.dexterityValue = dexVal;
        this.agilityValue = agVal;
        this.goldAmount = gAmt;
    }

    public void checkExperience(){
        if(this.experiencePoints>=super.getLevel()*10) {
            this.levelUp();
        }
    }

    public void levelUp(){
        super.setLevel(super.getLevel()+1); //Increase level by 1
        //Increase below based on example formulae given in assignment pdf
        super.setHP(super.getLevel()*100);
        this.MP = this.MP*1.1;
        this.agilityValue = this.agilityValue*1.05;
        this.dexterityValue = this.dexterityValue*1.05;
        this.strengthValue = this.strengthValue*1.05;
    }

    public void roundUpdate(int monsterLevel){
        super.setHP(super.getHP()*1.1);
        this.MP = this.MP*1.1;
        if(super.getIsFainted()==false){
            this.setGoldAmount(this.getGoldAmount() + ((double)monsterLevel*100));
            this.setExperiencePoints(this.getExperiencePoints()+ (long)monsterLevel*2);
            this.checkExperience();
        }
        else{
            super.setIsFainted(false);
        }
    }

    public Item chooseItem() {
        this.getBag().viewInventory();
        if(this.bag.getInventory().size()==0){
            System.out.println("You have nothing to fight monsters. Equipping you with a sword for this battle round");
            Weapon w = new Weapon("Sword",500,1,800,1);
            return w;
        }

        System.out.println("Enter the type of item you would like to use:");
        Scanner type = new Scanner(System.in);
        String itemType= type.nextLine();

        System.out.println("Enter the type of item you would like to use:");
        Scanner name = new Scanner(System.in);
        String itemName= name.nextLine();

        if(this.getBag().retrieveItem(itemType,itemName).getName().equals("IceSpell") ||
                this.getBag().retrieveItem(itemType,itemName).getName().equals("FireSpell")||
                this.getBag().retrieveItem(itemType,itemName).getName().equals("LightSpell")){
            if(((Spell)this.getBag().retrieveItem(itemType,itemName)).getMP()>this.MP){
                System.out.println("You do not have enough MP to use this spell. Please use a different item");
                return chooseItem();
            }
        }
        return this.getBag().retrieveItem(itemType,itemName);
    }

    public void consumePotion(Item product){
        boolean appliedAttribute = false;
        while(!appliedAttribute){
            System.out.println("Enter attribute that you would like to increase:");
            System.out.println(((Potion)product).displayAttributeAffected());
            Scanner type = new Scanner(System.in);
            String p = type.nextLine();
            switch (p) {
                case "Health":
                    this.setHP(this.getHP() + ((Potion) product).getAttributeIncrease());
                    this.getBag().removeItem(product.getType(), product.getName());
                    System.out.println(this.getName() + " has increased their HP with " + ((Potion) product).getName() + " potion! ");
                    appliedAttribute = true;
                    break;
                case "Strength":
                    this.setStrengthValue(this.getStrengthValue() + ((Potion) product).getAttributeIncrease());
                    this.getBag().removeItem(product.getType(), product.getName());
                    System.out.println(this.getName() + " has increased their strength with " + ((Potion) product).getName() + " potion! ");
                    appliedAttribute = true;
                    break;
                case "Mana":
                    this.setMP(this.getMP() + ((Potion) product).getAttributeIncrease());
                    this.getBag().removeItem(product.getType(), product.getName());
                    System.out.println(this.getName() + " has increased their MP with " + ((Potion) product).getName() + " potion! ");
                    appliedAttribute = true;
                    break;
                case "Agility":
                    this.setAgilityValue(this.getAgilityValue() + ((Potion) product).getAttributeIncrease());
                    this.getBag().removeItem(product.getType(), product.getName());
                    System.out.println(this.getName() + " has increased their agility with " + ((Potion) product).getName() + " potion! ");
                    appliedAttribute = true;
                    break;
                case "Dexterity":
                    this.setDexterityValue(this.getDexterityValue() + ((Potion) product).getAttributeIncrease());
                    this.getBag().removeItem(product.getType(), product.getName());
                    System.out.println(this.getName() + " has increased their dexterity with " + ((Potion) product).getName() + " potion! ");
                    appliedAttribute = true;
                    break;
                default:
                    System.out.println("This attribute does not exist. Please enter a different attribute");
                    appliedAttribute = true;
                    break;
            }

        }
    }

    public void useItem(Item product, Monster m) {
        Random random = new Random();
        double chance = 0.01;
        if (product.getType().equals("Weapon")) {
            if(random.monsterDodge(m.dodgeAbility,chance)){
                System.out.println(m.getName() + "has dodged " + this.getName() + " attack!");
            }
            else{
                ((Weapon) product).applyWeapon(m, this.strengthValue);
                System.out.println(this.getName() + " has attacked " + m.getName() + " for " + ((Weapon) product).getDamageValue() + " damage!");
            }

        } else if (product.getType().equals("Ice Spell")) {
            if(random.monsterDodge(m.dodgeAbility,chance)){
                System.out.println(m.getName() + "has dodged " + this.getName() + " attack!");
            }
            else{
                ((IceSpell) product).applyIceSpell(m,this.dexterityValue);
                System.out.println(this.getName() + " has attacked " + m.getName() + " for " + ((IceSpell) product).getDamage() + " damage!");
            }
            this.getBag().removeItem(product.getType(), product.getName());
        } else if (product.getType().equals("Light Spell")) {
            if(random.monsterDodge(m.dodgeAbility,chance)){
                System.out.println(m.getName() + "has dodged " + this.getName() + " attack!");
            }
            else{
                ((LightSpell) product).applyLightSpell(m,this.dexterityValue);
                System.out.println(this.getName() + " has attacked " + m.getName() + " for " + ((LightSpell) product).getDamage() + " damage!");
            }
            this.getBag().removeItem(product.getType(), product.getName());
        } else if (product.getType().equals("Fire Spell")) {
            if(random.monsterDodge(m.dodgeAbility,chance)){
                System.out.println(m.getName() + "has dodged " + this.getName() + " attack!");
            }
            else{
                ((FireSpell) product).applyFireSpell(m,this.dexterityValue);
                System.out.println(this.getName() + " has attacked " + m.getName() + " for " + ((FireSpell) product).getDamage() + " damage!");
            }
            this.getBag().removeItem(product.getType(), product.getName());
        } else if (product.getType().equals("Armor")) {
            this.setHP(this.getHP()+((Armor)product).getDamageReduction());
            System.out.println(this.getName() + " has protection from " + m.getName() + " with a damage reduction of " + ((Armor)product).getDamageReduction());
        }
        else{
           this.consumePotion(product);
        }
    }

    public Monster chooseMonster(MonsterPack monsters) {
        monsters.stats();
        System.out.println("Choose a monster to fight against your hero from the above list.");
        Scanner monster = new Scanner(System.in);
        String choice = monster.nextLine();
        return monsters.retrieveCharacter(choice);
    }



                                    //GETTERS AND SETTERS
    public double getMP(){
        return this.MP;
    }

    public long getExperiencePoints(){
        return this.experiencePoints;
    }

    public double getStrengthValue(){
        return this.strengthValue;
    }

    public double getDexterityValue(){
        return this.agilityValue;
    }

    public double getAgilityValue(){
        return this.agilityValue;
    }

    public double getGoldAmount(){
        return this.goldAmount;
    }

    public Inventory getBag(){
        return this.bag;
    }
    public void setMP(double mp){
        this.MP = mp;
    }

    public void setStrengthValue(double sVal){
        this.strengthValue = sVal;
    }

    public void setDexterityValue(double dVal){
        this.dexterityValue = dVal;
    }

    public void setAgilityValue(double aVal){
        this.agilityValue = aVal;
    }

    public void setGoldAmount(double g){
        this.goldAmount = g;
    }

    public void setExperiencePoints(long ex){
        this.experiencePoints = ex;
    }
}
