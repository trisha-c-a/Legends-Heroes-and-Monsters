public class Potion extends Item{
    public int attributeIncrease;
    public String attributeAffected;
    public Potion(String n, double p, long l, int aI, String aA) {
        super(l, p, n, "Potion");
        this.attributeIncrease = aI;
        this.attributeAffected = aA;
    }

    public String displayAttributeAffected(){

//        String temp = attributeAffected[0];
//
//        for(int i = 0;i<attributeAffected.length;i++){
//            temp = temp + ", " + attributeAffected[i];
//        }
        return this.attributeAffected;
    }

    public int getAttributeIncrease(){
        return this.attributeIncrease;
    }
    public String getAttributeAffected(){
        return this.attributeAffected;
    }
}
