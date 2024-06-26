import java.util.ArrayList;
import java.util.Scanner;

public class SnL {

    // states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;
    private boolean isPlayingWithComputer;

    // constructor
    public SnL(int size) {
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players = new ArrayList<Player>();
        this.gameStatus = 0;
    }

    public void initiateGame() {
        int[][] ladders = {
                { 2, 23 },
                { 8, 34 },
                { 20, 77 },
                { 32, 68 },
                { 41, 79 },
                { 74, 88 },
                { 82, 100 },
                { 85, 95 }
        };
        setLadders(ladders);
        int[][] snakes = {
                { 47, 5 },
                { 29, 9 },
                { 38, 15 },
                { 97, 25 },
                { 53, 33 },
                { 92, 70 },
                { 86, 54 },
                { 97, 25 }
        };
        setSnakes(snakes);
    }

    public Player getTurn() {
        if (this.gameStatus == 0) {
            double r = Math.random();
            if (r < 0.5) {
                this.currentTurn = 0;
                return this.players.get(0);
            } else {
                this.currentTurn = 1;
                return this.players.get(1);
            }
        } else {
            if (currentTurn == 0) {
                this.currentTurn = 1;
                return this.players.get(1);
            } else {
                this.currentTurn = 0;
                return this.players.get(0);
            }
        }
    }

    // setter methods
    public void setSizeBoard(int size) {
        this.boardSize = size;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void setLadders(int[][] ladders) {
        int s = ladders.length;
        for (int i = 0; i < s; i++) {
            this.ladders.add(new Ladder(ladders[i][0], ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes) {
        int s = snakes.length;
        for (int i = 0; i < s; i++) {
            this.snakes.add(new Snake(snakes[i][0], snakes[i][1]));
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        initiateGame();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String firstPlayer = sc.nextLine();

        System.out.println("Play alone or with your friend? (Enter 'C' for computer, 'P' for player):");
        String choice = sc.nextLine();

        Player p1 = new Player(firstPlayer);
        Player p2;

        if (choice.equalsIgnoreCase("C")) {
            p2 = new Player("Computer");
            this.isPlayingWithComputer = true;
        } else {
            System.out.println("Enter the second player's name:");
            String secondPlayer = sc.nextLine();
            p2 = new Player(secondPlayer);
            this.isPlayingWithComputer = false;
        }

        addPlayer(p1);
        addPlayer(p2);

        Player nowPlaying;
        do {
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: " + nowPlaying.getName() + ", the current position is " + nowPlaying.getPosition());

            if (nowPlaying.getName().equals("Computer")) {
                System.out.println("Computer's turn. Rolling the dice...");
                int x = nowPlaying.rollDice();
                System.out.println("Computer rolled a " + x);
                movePlayer(nowPlaying, x);
            } else {
                System.out.println(nowPlaying.getName() + ", it's your turn. Press enter to roll dice.");
                String input = sc.nextLine();
                int x = 0;
                if (input.isEmpty()) {
                    x = nowPlaying.rollDice();
                }
                System.out.println(nowPlaying.getName() + " rolled a " + x);
                movePlayer(nowPlaying, x);
            }
            System.out.println(nowPlaying.getName() + " new position is " + nowPlaying.getPosition());
        } while (getGameStatus() != 2);

        System.out.println("The Game is Over, the winner is: " + nowPlaying.getName());
    }

    public void movePlayer(Player p, int x) {
        this.gameStatus = 1;
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (l.getFromPosition() == p.getPosition()) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getName() + " got a ladder, so jumps to " + p.getPosition());
            }
        }

        for (Snake s : this.snakes) {
            if (s.getHead() == p.getPosition()) {
                p.setPosition(s.getTail());
                System.out.println(p.getName() + " got a snake, so slides down to " + p.getPosition());
            }
        }

        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }
}
