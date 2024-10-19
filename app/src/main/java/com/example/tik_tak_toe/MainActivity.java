package com.example.tik_tak_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Initialize buttons array and set click listeners for each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = (Button) gridLayout.getChildAt(i * 3 + j);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (((Button) v).getText().toString().isEmpty()) {
                            if (player1Turn) {
                                ((Button) v).setText("X");
                            } else {
                                ((Button) v).setText("O");
                            }

                            if (checkForWin()) {
                                String winner = player1Turn ? "Player 1" : "Player 2";
                                Toast.makeText(MainActivity.this, winner + " wins!", Toast.LENGTH_SHORT).show();
                                resetGame();
                            } else if (checkForDraw()) {
                                Toast.makeText(MainActivity.this, "Draw!", Toast.LENGTH_SHORT).show();
                                resetGame();
                            } else {
                                player1Turn = !player1Turn;
                            }
                        }
                    }
                });
            }
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        // Get the text of each button and populate the field array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].isEmpty()) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].isEmpty()) {
                return true;
            }
        }

        // Check diagonals
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].isEmpty()) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        // Check if all buttons have been clicked
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().toString().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        // Clear the text of all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        player1Turn = true;
    }
}
