package edu.uwyo.toddt.tic_tac_toe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    // Variables for the activity
    ViewSwitcher vSwitcher;
    Button newGame;
    Button playAgain;
    LinearLayout winScreen;
    TextView winText;
    TextView loseText;
    Bitmap gameBoard;
    Canvas gameBoardC;
    ImageView gameBoardField;
    final int boardsize = 900;
    int boardPos = 0;
    int[] boardKey = new int[] {0,0,0,0,0,0,0,0,0}; // used to determine if a space has X, O, or nothing
                                                    // 0 = unoccupied, 1 = occupied X, 2 = occupied O
    boolean isTurnX = true;

    // Drawing resources
    Paint myColor;
    Bitmap xImage;
    Bitmap oImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vSwitcher = (ViewSwitcher) findViewById(R.id.vs_activity_main);
        winText = (TextView) findViewById(R.id.txt_winner);
        loseText = (TextView) findViewById(R.id.txt_loser);
        winScreen = (LinearLayout) findViewById(R.id.view_win);

        newGame = (Button) findViewById(R.id.btn_new_game);
        newGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetGame();
            }
        });

        playAgain = (Button) findViewById(R.id.btn_play_again);
        playAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetGame();
                vSwitcher.showPrevious();
            }
        });

        // Set up the game board
        gameBoardField = (ImageView) findViewById(R.id.boardfield);
        gameBoard = Bitmap.createBitmap(boardsize, boardsize, Bitmap.Config.ARGB_8888);
        gameBoardC = new Canvas(gameBoard);
        gameBoardC.drawColor(Color.WHITE);
        gameBoardField.setImageBitmap(gameBoard);
        gameBoardField.setOnTouchListener(new myTouchListener());

        // Set up drawing utilities
        myColor = new Paint();
        myColor.setStyle(Paint.Style.FILL);
        myColor.setStrokeWidth(10);
        myColor.setTextSize(myColor.getTextSize() * 4);

        // Draw lines on board
        drawLines();

        // Load the pictures
        xImage = BitmapFactory.decodeResource(getResources(), R.drawable.image_x);
        xImage = Bitmap.createScaledBitmap(xImage, 250, 250, false);
        oImage = BitmapFactory.decodeResource(getResources(), R.drawable.image_o);
        oImage = Bitmap.createScaledBitmap(oImage, 250, 257, false);
    }

    class myTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){

            if(event.getAction() == MotionEvent.ACTION_UP){
                placeSymbol((int) event.getX(), (int) event.getY());
                return true;
            }

            return false;
        }
    }

    void placeSymbol(float x, float y){
        // Determine board position
        if(y <= 300){
            if(x <= 300){
                boardPos = 1;
            }
            else if(x <= 600){
                boardPos = 2;
            }
            else {
                boardPos = 3;
            }
        }
        else if(y <= 600){
            if(x <= 300){
                boardPos = 4;
            }
            else if(x <= 600){
                boardPos = 5;
            }
            else {
                boardPos = 6;
            }
        }
        else {
            if(x <= 300){
                boardPos = 7;
            }
            else if(x <= 600){
                boardPos = 8;
            }
            else {
                boardPos = 9;
            }
        }
        if(isTurnX){
            switch (boardPos){
                case 1:
                    if(boardKey[0] == 0) {
                        gameBoardC.drawBitmap(xImage, 25, 25, myColor);
                        boardKey[0] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 2:
                    if(boardKey[1] == 0){
                        gameBoardC.drawBitmap(xImage, 325, 25, myColor);
                        boardKey[1] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 3:
                    if(boardKey[2] == 0){
                        gameBoardC.drawBitmap(xImage, 625, 25, myColor);
                        boardKey[2] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 4:
                    if(boardKey[3] == 0){
                        gameBoardC.drawBitmap(xImage, 25, 325, myColor);
                        boardKey[3] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 5:
                    if(boardKey[4] == 0){
                        gameBoardC.drawBitmap(xImage, 325, 325, myColor);
                        boardKey[4] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 6:
                    if(boardKey[5] == 0){
                        gameBoardC.drawBitmap(xImage, 625, 325, myColor);
                        boardKey[5] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 7:
                    if(boardKey[6] == 0){
                        gameBoardC.drawBitmap(xImage, 25, 625, myColor);
                        boardKey[6] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 8:
                    if(boardKey[7] == 0){
                        gameBoardC.drawBitmap(xImage, 325, 625, myColor);
                        boardKey[7] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                case 9:
                    if(boardKey[8] == 0){
                        gameBoardC.drawBitmap(xImage, 625, 625, myColor);
                        boardKey[8] = 1;
                        isTurnX = false;
                        checkXwin();
                    }
                    break;
                default:
                    Log.v("msg", "I broke: " + boardPos);
            }

        }
        else {
            switch (boardPos) {
                case 1:
                    if(boardKey[0] == 0) {
                        gameBoardC.drawBitmap(oImage, 25, 25, myColor);
                        boardKey[0] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 2:
                    if(boardKey[1] == 0){
                        gameBoardC.drawBitmap(oImage, 325, 25, myColor);
                        boardKey[1] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 3:
                    if(boardKey[2] == 0){
                        gameBoardC.drawBitmap(oImage, 625, 25, myColor);
                        boardKey[2] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 4:
                    if(boardKey[3] == 0){
                        gameBoardC.drawBitmap(oImage, 25, 325, myColor);
                        boardKey[3] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 5:
                    if(boardKey[4] == 0){
                        gameBoardC.drawBitmap(oImage, 325, 325, myColor);
                        boardKey[4] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 6:
                    if(boardKey[5] == 0){
                        gameBoardC.drawBitmap(oImage, 625, 325, myColor);
                        boardKey[5] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 7:
                    if(boardKey[6] == 0){
                        gameBoardC.drawBitmap(oImage, 25, 625, myColor);
                        boardKey[6] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 8:
                    if(boardKey[7] == 0){
                        gameBoardC.drawBitmap(oImage, 325, 625, myColor);
                        boardKey[7] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                case 9:
                    if(boardKey[8] == 0){
                        gameBoardC.drawBitmap(oImage, 625, 625, myColor);
                        boardKey[8] = 2;
                        isTurnX = true;
                        checkOwin();
                    }
                    break;
                default:
                    Log.v("msg", "I broke: " + boardPos);
            }

        }

        gameBoardField.setImageBitmap(gameBoard);
        gameBoardField.invalidate();
    }

    // X win condition
    void checkXwin(){

        int count = 0;
        for(int key : boardKey){
            if(key != 0){
                count++;
            }
        }

        if(boardKey[0] == 1 && boardKey[1]==1 && boardKey[2]==1){
            xWin();
        }
        else if(boardKey[3] == 1 && boardKey[4]==1 && boardKey[5]==1){
            xWin();
        }
        else if(boardKey[6] == 1 && boardKey[7]==1 && boardKey[8]==1){
            xWin();
        }
        else if(boardKey[0] == 1 && boardKey[3]==1 && boardKey[6]==1){
            xWin();
        }
        else if(boardKey[1] == 1 && boardKey[4]==1 && boardKey[7]==1){
            xWin();
        }
        else if(boardKey[2] == 1 && boardKey[5]==1 && boardKey[8]==1){
            xWin();
        }
        else if(boardKey[0] == 1 && boardKey[4]==1 && boardKey[8]==1){
            xWin();
        }
        else if(boardKey[2] == 1 && boardKey[4]==1 && boardKey[6]==1){
            xWin();
        }
        else if(count == 9){
            String text = "It's a tie!";
            winText.setText(text);
            winText.setTextColor(Color.WHITE);
            loseText.setText("");
            winScreen.setBackgroundResource(R.drawable.the_night);
            vSwitcher.showNext();
        }
    }

    void xWin(){
        String text = "Player X wins!";
        winText.setText(text);
        winText.setTextColor(Color.BLACK);
        text = "O loses";
        loseText.setText(text);
        loseText.setTextColor(Color.BLACK);
        winScreen.setBackgroundResource(R.drawable.fragonard_swing);
        vSwitcher.showNext();
    }

    // O win condition
    void checkOwin(){

        int count = 0;
        for(int key : boardKey){
            if(key != 0){
                count++;
            }
        }

        if(boardKey[0] == 2 && boardKey[1]==2 && boardKey[2]==2){
            oWin();
        }
        else if(boardKey[3] == 2 && boardKey[4]==2 && boardKey[5]==2){
            oWin();
        }
        else if(boardKey[6] == 2 && boardKey[7]==2 && boardKey[8]==2){
            oWin();
        }
        else if(boardKey[0] == 2 && boardKey[3]==2 && boardKey[6]==2){
            oWin();
        }
        else if(boardKey[1] == 2 && boardKey[4]==2 && boardKey[7]==2){
            oWin();
        }
        else if(boardKey[2] == 2 && boardKey[5]==2 && boardKey[8]==2){
            oWin();
        }
        else if(boardKey[0] == 2 && boardKey[4]==2 && boardKey[8]==2){
            oWin();
        }
        else if(boardKey[2] == 2 && boardKey[4]==2 && boardKey[6]==2){
            oWin();
        }
        else if(count == 9){
            String text = "It's a tie!";
            winText.setText(text);
            winText.setTextColor(Color.WHITE);
            loseText.setText("");
            winScreen.setBackgroundResource(R.drawable.the_night);
            vSwitcher.showNext();
        }
    }

    void oWin(){
        String text = "Player O wins!";
        winText.setText(text);
        winText.setTextColor(Color.BLACK);
        text = "X loses";
        loseText.setText(text);
        loseText.setTextColor(Color.BLACK);
        winScreen.setBackgroundResource(R.drawable.wanderer_small);
        vSwitcher.showNext();
    }

    // Draw hash lines on board
    void drawLines(){
        gameBoardC.drawLine(300, 0, 300, 900, myColor);
        gameBoardC.drawLine(0, 300, 900, 300, myColor);
        gameBoardC.drawLine(600, 0, 600, 900, myColor);
        gameBoardC.drawLine(0, 600, 900, 600, myColor);
    }

    void resetGame(){
        for(int i=0; i < 9; i++){
            boardKey[i] = 0;
        }
        isTurnX = true;
        gameBoardC.drawColor(Color.WHITE);
        drawLines();
        gameBoardField.setImageBitmap(gameBoard);
        gameBoardField.invalidate();
    }
}
