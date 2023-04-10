public class Weapon extends Item{

    public double damageValue;
    public int numHands;
    public Weapon(String n, double p, long l, long dV,int numH) {
        super(l, p, n, "Weapon");
        this.damageValue = dV;
        this.numHands = numH;
    }

    public double getDamageValue(){
        return this.damageValue;
    }
    public int getNumHands(){
        return this.numHands;
    }

    public void applyWeapon(Monster m, double strength){
        m.setHP(m.getHP()+m.getDefenseValue() - this.damageValue*strength);
    }
}
