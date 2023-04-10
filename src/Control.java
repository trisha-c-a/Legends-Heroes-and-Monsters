public class Control {
    public void displayControls(){
        System.out.println("Here are all the options available: ");
        System.out.println("w - move up");
        System.out.println("a - move left");
        System.out.println("s - move down");
        System.out.println("d - move right");
        System.out.println("q - quit game");
        System.out.println("i - show information");
        System.out.println("m - enter market");
        System.out.println("e - show inventory");
        System.out.println("z - display world map");
    }

    public boolean inputControl(World world, HeroGroup heroes, String key){
        int currentX = heroes.getBoardPos().get(0);
        int currentY = heroes.getBoardPos().get(1);
        switch (key) {
            case "w":
                currentX -= 1;
                return world.traverseBoard(heroes, currentX, currentY, key);
            case "a":
                currentY -= 1;
                return world.traverseBoard(heroes, currentX, currentY, key);
            case "s":
                currentX += 1;
                return world.traverseBoard(heroes, currentX, currentY, key);
            case "d":
                currentY += 1;
                return world.traverseBoard(heroes, currentX, currentY, key);
            case "i":
                heroes.stats();
                return true;
            case "m":
                return world.traverseBoard(heroes,currentX, currentY, key);
            case "e":
                return heroes.checkInventory();
            case "z":
                world.displayBoard(heroes);
                return true;
            case "q":
                return false;
            default:
                System.out.println("Incorrect move entered.");
                return true;
        }
    }
}
