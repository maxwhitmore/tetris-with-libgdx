package csc2003.prac1;

import com.badlogic.gdx.graphics.Color;
import java.util.Random;

public class Tet {
    public int[][] pieces;
    public Color c;
    public int num;
    public static final Random rand = new Random();

    public Tet tet;
    public int x;
    public int y;

    public Tet nxtTet;

    private Board board;

    public Tet(Board board) { 
        this.board = board;
        nxtTet = Tet.getRand();
        spawnTet();
    }

    public Tet(int[][] pieces, Color c, int num) { 
        this.pieces = pieces;
        this.c = c;
        this.num = num;
    }

    private final static Tet[] allTets = { 
            new Tet(new int[][]{{1, 1, 1}, // T
                                {0, 1, 0}}, new Color(1,0.5f,0.5f,1), 0),

            new Tet(new int[][]{{1, 1}, // Square
                                {1, 1}}, new Color(1,1,0.5f,1), 1),

            new Tet(new int[][]{{1, 1, 1}, // L
                                {0, 0, 1}}, new Color(0.5f,1,0.5f,1), 2),

            new Tet(new int[][]{{1, 1, 1}, // J
                                {1, 0, 0}}, new Color(0.5f,0.5f,1,1), 3),

            new Tet(new int[][]{{1, 1, 1, 1}}, new Color(0.6f,0.6f,0.6f,1), 4),

            new Tet(new int[][]{{0, 1, 1}, // S
                                {1, 1, 0}}, new Color(0.5f,1,1,1), 5),

            new Tet(new int[][]{{1, 1, 0}, // Z
                                {0, 1, 1}}, new Color(1,0.5f,1,1), 6)
    };

    public void spawnTet() { 
        tet = nxtTet; 
        nxtTet = Tet.getRand();
        x = rand.nextInt(allTets.length); 
        if (tet.num == 4) {
            y = 19;
        }
        y = 18;
    }

    public void getLine() { // *** SPECIAL FEATURE *** method to swap current tet with the line
        nxtTet = tet;
        tet = Tet.allTets[4];
    }

    public void keyLeft() { 
        x--;
        if ((board.checkCrash(this.x, this.y, this))) {
            x++;
        }
    }
    public void keyRight() { 
        x++;
        if ((board.checkCrash(this.x, this.y, this))) {
            x--;
        }
    }

    public boolean keyDown() { 
        y--;
        if (board.checkCrash(this.x, this.y, this)) {
            y++;
            return false;
        }
        return true;
    }

    public void turn() { 
        Tet oldTet = tet;

        int[][] tmp = new int[tet.pieces[0].length][tet.pieces.length];

        int r = 0;
        while (r < tmp.length) { 
            int c = 0;
            while (c < tmp[r].length) { 
                tmp[r][tmp[r].length - 1 - c] = tet.pieces[c][r];
                c++;
            }
            r++;
        }
        tet = new Tet(tmp, tet.c, tet.num);

        if (board.checkCrash(this.x, this.y, this)) { 
            tet = oldTet;
        }
    }

    public static Tet getRand() { 
        return allTets[(rand.nextInt(allTets.length))];
    }
}