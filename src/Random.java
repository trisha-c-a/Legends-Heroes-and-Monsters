import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public String cellGeneration(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
        if(randomNum < 5) return "C";
        else if(randomNum >= 5 && randomNum < 8) return "M";

        return "X";

    }

    public boolean heroDodge(Double agility, Double chance){
        double probability = (agility * chance)/(1 + (agility * chance));
        double randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        if(randomNum/100.0 <= probability) return true;
        return false;
    }

    public boolean monsterDodge(Double dodge, Double chance){
        double probability = dodge * chance;
        double randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        if(randomNum/100.0 <= probability) return true;
        return false;
    }

    public boolean isFight(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
        if(randomNum < 8){
            return true;
        }
        return  false;
    }
}