public class Exoskeleton extends Monster implements FavoredSkill{
    public void updateSkill() {
        super.setDefenseValue(super.getDefenseValue()*1.05);
    }
}
