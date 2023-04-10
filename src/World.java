import java.util.List;
import java.util.Scanner;

public class World {
    public Cell [][] board;
    public int dimension;

    public World(HeroGroup group, String size){
        Scanner scnr = new Scanner(System.in);
        this.dimension = checkMapSize(size);
        while(true){
            this.createWorld(dimension);
            this.displayBoard(group);
            System.out.println("Would you like to refresh the world? (Y)");
            String choice = scnr.nextLine();
            Boolean flag = false;
            switch(choice) {
                case "Y":
                 break;
                default:
                 flag = true;
                 break;
            }
            if(flag) break;
        }
        
    }


    public int checkMapSize(String size){
        Scanner scnr = new Scanner(System.in);
        while(true){
            try {
                int num = Integer.parseInt(size);
                if(num <= 10 && num >= 3) return num;
                else System.out.println("Please enter a number between 3-10: ");
            } catch (Exception e) {
                System.out.println("Please enter a valid integer: ");
            }
            size = scnr.nextLine();
        }        
            
        }

    public void createWorld(int size){
        Random random = new Random();
        board = new Cell[this.dimension][this.dimension];
        for(int i = 0; i < this.dimension; i++){
            for(int j = 0; j < this.dimension; j++){
                String type = random.cellGeneration();
                switch (type) {
                    case "C":
                        board[i][j] = new Common(type);
                        break;
                    case "M":
                        board[i][j] = new Market(type);
                        break;
                    default:
                        board[i][j] = new Inaccessible(type);
                }

            }
        }
    }

    public void displayBoard(HeroGroup group){
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if(i == group.getBoardPos().get(0) && j == group.getBoardPos().get(1) ) System.out.print("H ");
                else if(board[i][j].name == "C") System.out.print("  ");
                else System.out.print(board[i][j].name + " ");
            }
            System.out.println();
        }
    }

    public boolean traverseBoard(HeroGroup group, int currentX, int currentY, String token){
        if(currentX < 0 || currentX >= this.dimension || currentY < 0 || currentY >= this.dimension){
            System.out.println("You are trying to exit the world boundary!");
            return true;
        }
        else if(board[currentX][currentY].name == "X"){ System.out.println("Inaccessible zone encountered!"); return true;}
        else if(board[currentX][currentY].name == "M"){
            Scanner name = new Scanner(System.in);
            group.setBoardPos(currentX, currentY);
            System.out.println("Enter the name of the hero who would enter the market: ");
            String decision = name.nextLine();
            Hero h1 = group.retrieveCharacter(decision);
            return ((Market)board[currentX][currentY]).entrance(h1);
        }
        else if(board[currentX][currentY].name == "C") {
            if (token.equals("m")) {System.out.println("You are not on a market cell!"); return true;}
            else{
                group.setBoardPos(currentX, currentY);
                return ((Common)board[currentX][currentY]).entrance(group);
            }

        }
        return false;
    }

    public int getDimension(){
        return this.dimension;
    }

    public void setDimension(int dim){
        this.dimension = dim;
    }
}