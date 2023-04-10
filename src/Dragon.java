public class Dragon extends Monster implements FavoredSkill{

    public void updateSkill() {
        super.setBaseDamage(super.getBaseDamage()*1.05);
    }
}
