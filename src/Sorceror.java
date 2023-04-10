public class Sorceror extends Hero implements FavoredSkill{

    public Sorceror(String nme, String type, double mp, long exp, double sVal, double dexVal, double agVal,double gAmt){
        super(nme, type, mp, exp, sVal, dexVal, agVal,gAmt);
    }

    public void updateSkill() {
        super.setDexterityValue(super.getDexterityValue() * 1.05);
        super.setAgilityValue(super.getAgilityValue() * 1.05);
    }
}
