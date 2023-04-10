public class Armor extends Item{

    public long damageReduction;
    public Armor(String n,  double p, long l, long dR) {
        super(l, p, n, "Armor");
        this.damageReduction = dR;
    }

    public long getDamageReduction(){
        return this.damageReduction;
    }

}
