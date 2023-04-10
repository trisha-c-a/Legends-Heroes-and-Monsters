public class LightSpell extends Spell{
    public LightSpell(String n, double p, long l, double d, double mp) {
        super(l, p, n, "Light Spell", d, mp);
    }

    public void applyLightSpell(Monster m, double dex){
        super.applySpell(dex,m);
        m.setDodgeAbility(m.getDodgeAbility()*0.1);
    }
}
