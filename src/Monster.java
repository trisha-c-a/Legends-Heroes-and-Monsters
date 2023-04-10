
//Attack (interface?)
public class Monster extends Character{
    public double baseDamage;
    public double defenseValue;
    public double dodgeAbility;

    //Constructor for default monster
    public Monster(){
        super();
        this.baseDamage = 500;
        this.defenseValue = 600;
        this.dodgeAbility = 550;
    }

    //Constructor for default character but user defined monster attributes
    public Monster(double bD, double dV, double dA){
        super();
        this.baseDamage = bD;
        this.defenseValue = dV;
        this.dodgeAbility = dA;
    }

    //Constructor for user defined monster attributes
    public Monster(String nme, String typ, int lvl, double bD, double dV, double dA){
        super(nme,lvl,100,typ);
        this.baseDamage = bD;
        this.defenseValue = dV;
        this.dodgeAbility = dA;
    }

    public void attackHero(Hero h){
        Random random = new Random();
        double chance = 0.002;
        if(random.heroDodge(h.getAgilityValue(),chance)){
            System.out.println(h.getName() + "has dodged " + this.getName() + " attack!");
        }
        else {
            h.setHP(h.getHP() - this.getBaseDamage());
            System.out.println(this.getName() + " has attacked " + h.getName() + " for " + this.baseDamage + " damage!");
        }
    }

                                            //GETTERS AND SETTERS
    public double getBaseDamage(){
        return this.baseDamage;
    }

    public double getDefenseValue(){
        return this.defenseValue;
    }

    public double getDodgeAbility(){
        return this.dodgeAbility;
    }

    public void setBaseDamage(double bD){
        this.baseDamage = bD;
    }
    public void setDefenseValue(double dV){
        this.defenseValue = dV;
    }
    public void setDodgeAbility(double dA){
        this.dodgeAbility = dA;
    }
}
