public class Spirit extends Monster implements FavoredSkill {
    public void updateSkill() {
        super.setDodgeAbility(super.getDodgeAbility()*1.05);
    }
}
