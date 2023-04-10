
//Can do the following:
//create character with name, level and HP
//update HP when character receives damage/takes a potion
//increase character level
//getters and setters to access/update attributes
abstract class Character {
    public String name;
    public int level;
    public double HP;
    public String type;
    public boolean isFainted = false;

    public Character(){
        name = "Null";
        level = 1;
        HP = 1000;
        type = "Null";
    }
    public Character(String n, int l, double h, String t){
        this.name = n;
        this.level = l;
        this.HP = this.level*100;
        this.type = t;
    }

    public void checkHP(){
        if(this.HP<=0){
            this.isFainted = true;
        }
    }

    //GETTERS AND SETTERS

    public String getName(){
        return this.name;
    }
    public int getLevel(){
        return this.level;
    }

    public double getHP(){
        return this.HP;
    }
    public boolean getIsFainted(){
        return this.isFainted;
    }
    public String getType(){
        return this.type;
    }


    public void setLevel(int lev){
        this.level = lev;
    }
    public void setHP(double hp){
        this.HP = hp;
    }
    public void setIsFainted(boolean res){
        this.isFainted = res;
    }

}
