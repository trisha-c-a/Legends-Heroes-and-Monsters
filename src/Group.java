public interface Group {
    //Hero
    //Symbol
    //List of heros
    //Display list (include alive/not)
    //Constructor to add hero to group

    //Monster
    //List of monsters
    //Display list (include alive only)
    //Constructor to add monster to group
    //Method to remove monster from if HP<0

    //Display list
    //Remove character with HP<0

    public void stats();

    public String characterExists(String name);

    public void removeCharacter();

    public long notFaint();
    public int getHighestLevel();
}
