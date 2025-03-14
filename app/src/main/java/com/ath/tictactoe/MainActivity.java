package com.ath.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    int flag = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "btn" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
        }
    }

    public void check(View view) {
        Button currentButton = (Button) view;

        if (currentButton.getText().toString().equals("")) {
            count++;
            currentButton.setText(flag == 0 ? "X" : "O");
            flag = 1 - flag;
            if (count > 4) {
                String[] board = new String[9];
                for (int i = 0; i < 9; i++) {
                    board[i] = buttons[i].getText().toString();
                }

                // Winning conditions
                int[][] winPositions = {
                        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                        {0, 4, 8}, {2, 4, 6}  // Diagonals
                };

                for (int[] pos : winPositions) {
                    if (!board[pos[0]].equals("") && board[pos[0]].equals(board[pos[1]]) && board[pos[1]].equals(board[pos[2]])) {
                        Toast.makeText(this, "The Winner is: " + board[pos[0]], Toast.LENGTH_SHORT).show();
                        newGame(view);
                        return;
                    }
                }

                // Check for Draw
                if (count == 9) {
                    Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                    newGame(view);
                }
            }
        }
    }

    private void newGame(View view) {
        for (Button btn : buttons) {
            btn.setText("");
        }
        count = 0;
        flag = 0;
    }
}
