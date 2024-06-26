public class Player{
    //states
    private String name;
    private int position;

    //constructor method
    public Player (String name){
        this.name=name;
    }
    //setter methods

    public void setName (String name){
        this.name=name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    //getter methods
    public String getName() {
        return this.name;
    }


    public int getPosition() {
        return this.position;
    }

    //another method
    public int rollDice()
    {
        return (int)((Math.random()*6)+1);
    }

    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
            this.position = (boardSize - this.position) + (boardSize - x);
        }
        else {
            this.position = this.position + x;

        }
    }
}