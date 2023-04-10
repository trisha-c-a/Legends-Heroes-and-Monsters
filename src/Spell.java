abstract class Spell extends Item{

    public double damage;
    public double MP;
    public Spell(long l, double p, String n, String t, double d, double mp) {
        super(l, p, n, t);
        this.damage = d;
        this.MP = mp;
    }

    public void applySpell(double dex, Monster m){
        m.setHP(m.getHP()+m.getDefenseValue() - this.damage*dex);
    }

    public double getDamage(){
        return this.damage;
    }
    public double getMP(){
        return this.MP;
    }
}
