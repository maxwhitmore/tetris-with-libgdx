package csc2003.prac1;

import com.badlogic.gdx.graphics.Color;
import java.util.Random;

public class Board {
    public final Color[][] gameBoard;

    protected static final int width = 511; // values for gameBoard, obtained thru tedius arithmetic
    protected static final int height = 592;
    protected static final int gameX = 12;
    protected static final int gameY = 20;
    protected static final int tet = 25;

    public Board(int x, int y) { 
        gameBoard = new Color[x][y];
    }

    public boolean checkCrash(int a, int b, Tet curTet) {
        int tmpX = a;
        int tmpY = b;

        if (tmpX < 0 || (tmpX > gameBoard.length-curTet.tet.pieces[0].length) ||
            tmpY < 0 || (tmpY > gameBoard[0].length-curTet.tet.pieces.length)) {
            return true;
        }

        return(checkAcross(curTet));
    }

    public boolean checkAcross(Tet curTet) {
        int r = 0;
        while (r < curTet.tet.pieces.length) {
            int c = 0;
            while (c < curTet.tet.pieces[r].length) {
                if (curTet.tet.pieces[r][c]!= 0) {
                    if (gameBoard[c+curTet.x][r+curTet.y] != null) { // found!
                        return true;
                    }
                }
                c++;
            }
            r++;
        }
        return false;
    }

    public void moveTets(Tet curTet) {
        int r = 0;
        while (r < curTet.tet.pieces.length) {
            int c = 0;
            while (c < curTet.tet.pieces[r].length) {
                if (curTet.tet.pieces[r][c]!=0) {
                    gameBoard[curTet.x+c][curTet.y+r] = curTet.tet.c; 
                }
                c++;
            }
            r++;
        }
        curTet.spawnTet();
    }

    public int levelClear() {
        int count = 0;

        for (int c = gameBoard[0].length-1; c >= 0; c--) { 
            boolean clear = true;
            int r = 0;
            while (r < gameBoard.length) {
                if (gameBoard[r][c] == null) { 
                    clear = false;
                    break;
                }
                r++;
            }
            if (clear) {
                while (c < gameBoard[0].length - 1) { 
                    r = 0;
                    while (r < gameBoard.length) {
                        gameBoard[r][c] = gameBoard[r][c + 1];
                        r++;
                    }
                    c++;
                }
                r = 0;
                while (r < gameBoard.length) { 
                    gameBoard[r][gameBoard[0].length - 1] = null;
                    r++;
                }
                count++;
            }
        }
        return count;
    }
}