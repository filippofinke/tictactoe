package ch.supsi.tictactoe.player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AI {
    private final Player player;
    private Player[][] board;
    private final int AI_SCORE = 10;
    private final int PLAYER_SCORE = 5;
    private final int EMPTY_SCORE = 1;

    public AI(Player player) {
        this.player = player;
    }

    public void play() {

        board = player.getGame().getBoard();
        int[] rowScores = getRowScores();

        Map<int[], Integer> cellScores = new HashMap<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == null){
                    int[] cell = {i,j};
                    int score = rowScores[i] + rowScores[j + 3];

                    if(i == j){
                        score += rowScores[6];
                    }else if(i + j == 2){
                        score += rowScores[7];
                    }
                    cellScores.put(cell, score);
                }
            }
        }

        int[] nextMoveCoord = Collections.max(cellScores.entrySet(), Map.Entry.comparingByValue()).getKey();
        player.play(nextMoveCoord[0], nextMoveCoord[1]);
    }

    public int getCellScore(int row, int col){
        if(board[row][col] == null){
            return EMPTY_SCORE;
        } else if (board[row][col] == player) {
            return AI_SCORE;
        }
        return PLAYER_SCORE;
    }

    public int[] getRowScores(){
        int[] scores = {
                getCellScore(0,0) * getCellScore(0,1) * getCellScore(0,2),
                getCellScore(1,0) * getCellScore(1,1) * getCellScore(1,2),
                getCellScore(2,0) * getCellScore(2,1) * getCellScore(2,2),
                getCellScore(0,0) * getCellScore(1,0) * getCellScore(2,0),
                getCellScore(0,1) * getCellScore(1,1) * getCellScore(2,1),
                getCellScore(0,2) * getCellScore(1,2) * getCellScore(2,2),
                getCellScore(0,0) * getCellScore(1,1) * getCellScore(2,2),
                getCellScore(0,2) * getCellScore(1,1) * getCellScore(2,0)
        };

        for (int i = 0; i < scores.length; i++){
            if(scores[i] == AI_SCORE * PLAYER_SCORE * EMPTY_SCORE){
                scores[i] = 0;
            }
        }

        return scores;
    }
}
