package csc2003.prac1;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Arrays;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;

public class PlayTetris extends InputAdapter {

    private final ShapeRenderer renderer;
    private final SpriteBatch batch;
    private final BitmapFont font;

    private final Board board;

    private final Color screen;

    private float speed = 40;
    private int counter = 0;

    private Tet currTet;

    private int score=0;
    private int level=1;
    private int lCount = 1;

    public PlayTetris(BitmapFont font) { 
        this.renderer = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.font = font;

        board = new Board(Board.gameX, Board.gameY);

        screen = new Color(0, 0, 0, 0);

        currTet = new Tet(board);
    }

    @Override
    public boolean keyDown(int keycode) { 
        if (keycode == Keys.LEFT) {
            currTet.keyLeft();
            return true;
        } else if (keycode == Keys.RIGHT) {
            currTet.keyRight();
            return true;
        } else if (keycode == Keys.UP) {
            currTet.turn();
            return true;
        } else if (keycode == Keys.DOWN) {
            currTet.keyDown();
            return true;
        } else if (keycode == Keys.L && lCount > 0) { // *** SPECIAL FEATURE ***
            currTet.getLine();
            lCount--;
            return true;
        } else {
            return false;
        }
    }

    public void update() {
        counter++;
        if (counter >= speed) { 
            counter = 0;
            if (!currTet.keyDown()) {
                board.moveTets(currTet);
                int cleared = board.levelClear();
                score += cleared;
                checkReset();
            }
        }
        if (score > 1 && score <= 2) {
            level = 2;
            lCount++;
            speed = 20;
        } else if (score > 2 && score <= 3) {
            level = 3;
            lCount++;
            speed = 14;
        } else if (score > 3 && score <= 4) {
            level = 4;
            lCount++;
            speed = 11;
        } else if (score > 4 && score <= 5) {
            level = 5;
            lCount++;
            speed = 9;
        } else if (score > 5 && score <= 6) {
            level = 6;
            lCount++;
            speed = 8;
        } else if (score > 6 && score <= 7) {
            level = 7;
            lCount++;
            speed = 7;
        } else if (score > 7 && score <= 8) {
            level = 8;
            lCount++;
            speed = 6;
        } else if (score > 8 && score <= 9) {
            level = 9;
            lCount++;
            speed = 5;
        } else if (score > 10 && score <= 11) {
            level = 10;
            lCount++;
            speed = 4;
        }
    }

    public void checkReset() { 
        if (board.checkCrash(currTet.x, currTet.y, currTet)) {
            reset();
        }
    }

    public void renderTets() {
        renderer.setColor(screen);
        renderer.rect(158, 23, 330, 546);

        renderer.setColor(currTet.nxtTet.c);
        fillTets(0, 0, currTet.nxtTet, Board.tet, 459);
        renderer.setColor(currTet.tet.c);
        fillTets(currTet.x, currTet.y, currTet.tet, 160, Board.tet);
    }

    public void renderBoard() {
        int r = 0;
        while (r < board.gameBoard.length) {
            int c = 0;
            while (c < board.gameBoard[r].length) {
                if (board.gameBoard[r][c] != null) {
                    renderer.setColor(board.gameBoard[r][c]);
                    renderer.rect(162+(27)*r, 27+(27)*c, Board.tet, Board.tet);
                }
                c++;
            }
            r++;
        }
    }

    public void renderInfo() {
        font.setColor(1, 1, 1, 1);
        font.draw(batch, "LEVEL", 27, 334);
        font.draw(batch, "SCORE", 27, 429);
        font.draw(batch, "NEXT", 27, 562);
        font.draw(batch, Integer.toString(score), 35, 384);
        font.draw(batch, Integer.toString(level), 35, 289);
    }

    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderTets();
        renderBoard();
        renderer.end();
        batch.begin();
        renderInfo();
        batch.end();
    }

    private void fillTets(int x, int y, Tet tet, int x2, int y2) {
        int c = 0;
        while (c < tet.pieces.length) {
            int r = 0;
            while (r < tet.pieces[c].length) {
                if (tet.pieces[c][r] !=0 ) { // found!
                    renderer.rect(x2+2+(27)*(x+r), y2+2+(27)*(y+c), Board.tet, Board.tet);
                }
                r++;
            }
            c++;
        }
    }

    private void reset() {
        Arrays.fill(board.gameBoard, board.gameBoard.length, board.gameBoard.length, null);

        score = 0;
        level = 0;
        currTet.spawnTet();
    }

    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}