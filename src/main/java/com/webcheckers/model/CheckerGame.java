package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.PlayerMoves;
import com.webcheckers.util.Type;

import java.util.*;

/**
 * SWEN-261
 * CheckerGame.java
 * Team: \v
 * Purpose: This file is a game of checkers, keeps track of the board, players, and the whose turn it is
 */
public class CheckerGame {

    private Board board;
    private Player redPlayer;
    private Player whitePlayer;
    private Color currentTurn;
    private PlayerMoves turnType;
    private Stack<Board> moveList;
    private Space previousFinishSpace;
    private boolean change;
    private boolean whiteResigned;
    private boolean redResigned;


    /**
     * Constructor for CheckerGame
     * @param redPlayer The player that will be red
     * @param whitePlayer The player that will be white
     */
    public CheckerGame(Player redPlayer, Player whitePlayer){
        this.board = new Board();
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.currentTurn = Color.RED;
        this.turnType = PlayerMoves.NONE;
        this.moveList = new Stack<>();
        this.previousFinishSpace = null;
        this.change = false;
        this.whiteResigned = false;
        this.redResigned = false;
    }


    /**
     * tell the game that one of its players has resigned
     * @param color color of the player who resigned
     * @return none
     */
    public void setResigned(Color color) {
        if (color == Color.RED){
            this.redResigned = true;
        } else if (color == Color.WHITE){
            this.whiteResigned = true;
        }
    }

    /**
     * Literally only here for the convenience of CheckerGameTest
     * @param turnType The type the turn will be set to
     */
    public void setTurnType(PlayerMoves turnType){
        this.turnType = turnType;
    }

    /**
     * Given one player, get their opponent
     * @param player original player
     * @return opponent
     */
    public Player getOpponent(Player player) {
        if(redPlayer.equals(player))
            return whitePlayer;
        else
            return redPlayer;
    }


    /**
     * if game is over return which player wins, otherwise return appropriate message
     * @return
     */
    public String gameOver(){
        boolean redhaspieces = false;
        boolean whitehaspieces = false;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Piece pc = this.board.getBoard()[i][j].getPiece();
                if (pc == null){
                    continue;
                } else if (pc.getColor() == Color.RED){
                    redhaspieces = true;
                } else if (pc.getColor() == Color.WHITE){
                    whitehaspieces = true;
                } else {
                    System.out.println("You shouldn't be here: CheckerGame.gameOver()");
                }
            }
        }

        if (this.whiteResigned){
            return "White resigned";
        } else if (this.redResigned){
            return "Red resigned";
        }

        if (redhaspieces && whitehaspieces){
            return "Game not over";
        } else if (redhaspieces){
            return "Red wins";
        } else if (whitehaspieces){
            return "White wins";
        } else {
            return "There are no pieces on the board for some reason...";
        }
    }


    /**
     * Getter for the red player
     * @return the red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }


    /**
     * Getter for the white player
     * @return the white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }


    /**
     * Getter for the current turn
     * @return The color of whose turn it is
     */
    public Color getCurrentTurn() {
        return currentTurn;
    }


    /**
     * Getter for the turn type
     * @return the type of turn this is being played
     */
    public PlayerMoves getTurnType() {
        return turnType;
    }


    /**
     * Used to change the current turn
     */
    public void changeCurrentTurn(){
        if (currentTurn == Color.RED){
            currentTurn = Color.WHITE;
        }
        else{
            currentTurn = Color.RED;
        }
        this.turnType = PlayerMoves.NONE;
        this.moveList = new Stack<>();
        this.previousFinishSpace = null;
        this.change = true;
    }

    /**
     * sets the change
     * @param bool
     */
    public void setChange(boolean bool){
        this.change=bool;
    }

    /**
     * gets the change
     * @return
     */
    public boolean getChange(){
        return change;
    }



    /**
     * Getter for the board
     * @return the board
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Check to see if a move a player made is valid or not
     *
     * @param playerColor color of the player who made the move
     * @param move the move that was made
     * @return "valid" if valid, otherwise the error message is returned
     */
    public String validateMove(Color playerColor, Move move){
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCol = move.getEnd().getCell();

        Space startSpace = board.getItem(startRow, startCol);
        Space finishSpace = board.getItem(endRow, endCol);

        // if the user has already made a simple move, then
        if(turnType == PlayerMoves.SIMPLE)
            return "Invalid move. You have already made a simple move this turn! No other actions can be taken.";

        // if the user made a move without moving a piece
        if(startSpace.getPiece() == null)
            return "Invalid Move. There is no piece there... how did you do that???";

        // if the user moves a piece that is not their color than INVALID
        if(startSpace.getPiece().getColor() != playerColor)
            return "Invalid Move. That is your opponent's piece, you may only move pieces of your own color!";

        // if the user moves to piece to a square that already has a piece than INVALID
        if(finishSpace.getPiece() != null)
            return "Invalid Move. You can't move to a square that already has a piece.";

        // check to see if they can make a jump move and if they can but haven't return INVALID
        if(hasJumpOption(playerColor)){
            Set<Move> jumpSet = jumpOptions(playerColor);

            if(jumpSet.contains(move)) {
                // if they have already jumped check to see that they are moving the same piece
                if(turnType == PlayerMoves.JUMP){
                    if(startSpace.equals(previousFinishSpace)){
                        moveJump(startSpace, finishSpace, playerColor);
                        return "jump";
                    } else {
                        return "Invalid Move. You have to move only one piece per turn!";
                    }
                }
                // otherwise this is their first jump
                else {
                    this.turnType = PlayerMoves.JUMP;
                    moveJump(startSpace, finishSpace, playerColor);
                    return "jump";
                }
            }
            else
                return "Invalid Move. A possible jump is available so you must make that move.";
        }
        // they must have made a single move then
        else {
            if(this.turnType == PlayerMoves.JUMP)
                return "Invalid Move. You either make jumps or simple moves, not a combination of both.";

            // check to see if the piece is single or king
            if(startSpace.getPiece().getType().equals(Type.SINGLE)){
                // check different conditions for both colors
                if(playerColor == Color.RED){
                    // if the simple move is directly forward and left or forward and right then we are good
                    if((endRow == startRow - 1 && endCol == startCol - 1) ||
                            (endRow == startRow - 1 && endCol == startCol + 1)) {
                        this.turnType = PlayerMoves.SIMPLE;
                        moveSinglePiece(startSpace, finishSpace, playerColor);
                        return "simpleMove";
                    }
                    // My attempt at hardcoding weird edge jump error (Dylan)
                    else if((endRow == startRow - 2 && endCol == startCol - 2) ||
                            (endRow == startRow - 2 && endCol == startCol + 2)){
                        this.turnType = PlayerMoves.JUMP;
                        moveJump(startSpace, finishSpace, playerColor);
                        return "jump";
                    }
                    else {
                        return "Invalid Move. Simple moves can only be one square diagonal left or right.";
                    }

                }
                if(playerColor == Color.WHITE){
                    // if the simple move is directly forward and left or forward and right then we are good
                    if((endRow == startRow + 1 && endCol == startCol - 1) ||
                            (endRow == startRow + 1 && endCol == startCol + 1)) {
                        this.turnType = PlayerMoves.SIMPLE;
                        moveSinglePiece(startSpace, finishSpace, playerColor);
                        return "simpleMove";
                    }
                    // Attempt at hardcoding weird edge jump error (Dylan)
                    else if((endRow == startRow + 2 && endCol == startCol -2 ) ||
                            (endRow == startRow + 2 && endCol == startCol + 2)){
                        this.turnType = PlayerMoves.JUMP;
                        moveJump(startSpace, finishSpace, playerColor);
                        return "jump";
                        }
                    else
                        return "Invalid Move. Simple moves can only be one square diagonal left or right.";
                }
            } else {
                //TODO Anthony: come back and clean up validation
                if(!(endRow == startRow - 1 && endCol == startCol - 1)){
                    if(!(endRow == startRow - 1 && endCol == startCol + 1)){
                        if(!(endRow == startRow + 1 && endCol == startCol - 1)){
                            if(!(endRow == startRow + 1 && endCol == startCol + 1)){
                                return "Invalid Move. Simple moves can only be one square diagonal left or right.";}}}}
                this.turnType = PlayerMoves.SIMPLE;
                moveSinglePiece(startSpace, finishSpace, playerColor);
                return "simple king move";
            }
            return "uhhhhhhhhhhhhhhhhhhhhhhhhhh";
        }
    }


    /**
     * This function moves the piece but also removes the piece in between the two spaces
     *
     * @param startSpace starting space of the piece
     * @param finishSpace ending space of the piece
     */
    private void moveJump(Space startSpace, Space finishSpace, Color playerColor){
        // first we need to save a state of the previous board so we can backtrack to it later
        moveList.push(board.copy());

        // get the middle space
        int midRow = finishSpace.getRow() - startSpace.getRow();
        int midCell = finishSpace.getCol() - startSpace.getCol();
        int midLocRow = (midRow / 2) + startSpace.getRow();
        int midLocCell = (midCell / 2) + startSpace.getCol();
        Space midSpace = board.getItem(midLocRow, midLocCell);

        // delete the middle space and move the jumper to the correct spot
        midSpace.setPiece(null);
        finishSpace.setPiece(startSpace.getPiece());
        startSpace.setPiece(null);

        // promote to a king if need be
        if(playerColor == Color.WHITE && finishSpace.getRow() == 7) {
            finishSpace.promoteToKing();
        } else if(playerColor == Color.RED && finishSpace.getRow() == 0){
            finishSpace.promoteToKing();
        }

        // update the end space
        this.previousFinishSpace = finishSpace;
    }


    /**
     * This function manipulates the pieces on the board to express changes in the domain
     *
     * @param startSpace starting space of the piece
     * @param finishSpace ending space of the piece
     */
    private void moveSinglePiece(Space startSpace, Space finishSpace, Color playerColor){
        // first we need to save a state of the previous board so we can backtrack to it later
        moveList.push(board.copy());

        // move the piece to the finish and delete it from the starting space
        finishSpace.setPiece(startSpace.getPiece());
        startSpace.setPiece(null);

        // promote to a king if need be
        if(playerColor == Color.WHITE && finishSpace.getRow() == 7) {
            finishSpace.promoteToKing();
        } else if(playerColor == Color.RED && finishSpace.getRow() == 0){
            finishSpace.promoteToKing();
        }
    }


    /**
     * This function checks to see if a user has a jump available or not
     *
     * @param playerColor player who we are checking for
     * @return true if they have a jump available, false otherwise
     */
    private boolean hasJumpOption(Color playerColor){
        //TODO: ANOTHONY YOU KNOW WHATS UP SON
        return !jumpOptions(playerColor).isEmpty();
    }


    /**
     * Creates a set of moves that are the possible jumps a piece can make
     * 04/15/19 Dylan changed access to public for testing purposes
     * @param playerColor player who we are checking for
     * @return a set of jump Moves that they can make currently
     */
    public Set<Move> jumpOptions(Color playerColor){
        // create a list of all the player's pieces
        LinkedList<Space> playerPieces = new LinkedList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board.getItem(i, j).getPiece() != null && board.getItem(i, j).getPiece().getColor() == playerColor){
                    playerPieces.add(board.getItem(i, j));
                }
            }
        }

        HashSet<Move> jumpSet = new HashSet<>();
        // for each piece check to see if it can jump anything
        for(Space piece: playerPieces){
            try{
                if(piece.getPiece().getType() == Type.SINGLE){
                    if(playerColor == Color.RED){
                        // if we are close to the opponents end we can't jump
                        if(piece.getRow() < 2)
                            continue;

                        if(checkRightJumpRed(piece, Color.WHITE)) {
                            Position start = new Position(piece.getRow(), piece.getCol());
                            Position finish = new Position(piece.getRow() - 2, piece.getCol() + 2);
                            Move move = new Move(start, finish);
                            jumpSet.add(move);
                        }
                        //we did get here
                        if(checkLeftJumpRed(piece, Color.WHITE)){
                            Position start = new Position(piece.getRow(), piece.getCol());
                            Position finish = new Position(piece.getRow() - 2, piece.getCol() - 2);
                            Move move = new Move(start, finish);
                            jumpSet.add(move);
                        }
                    }

                    // if player color is white
                    else {
                        // if we are close to the opponents end we can't jump
                        if(piece.getRow() > 5)
                            continue;

                        if (checkLeftJumpWhite(piece, Color.RED)) {
                            Position start = new Position(piece.getRow(), piece.getCol());
                            Position finish = new Position(piece.getRow() + 2, piece.getCol() + 2);
                            Move move = new Move(start, finish);
                            jumpSet.add(move);
                        }
                        if (checkRightJumpWhite(piece, Color.RED)) {
                            Position start = new Position(piece.getRow(), piece.getCol());
                            Position finish = new Position(piece.getRow() + 2, piece.getCol() - 2);
                            Move move = new Move(start, finish);
                            jumpSet.add(move);
                        }
                    }
                }

                // KING TIME
                else {
                    Color oppositeColor;
                    if(playerColor == Color.WHITE)
                        oppositeColor = Color.RED;
                    else
                        oppositeColor = Color.WHITE;

                    if(checkRightJumpRed(piece, oppositeColor)){
                        Position start = new Position(piece.getRow(), piece.getCol());
                        Position finish = new Position(piece.getRow() - 2, piece.getCol() + 2);
                        Move move = new Move(start, finish);
                        jumpSet.add(move);
                    }

                    if(checkLeftJumpRed(piece, oppositeColor)){
                        Position start = new Position(piece.getRow(), piece.getCol());
                        Position finish = new Position(piece.getRow() - 2, piece.getCol() - 2);
                        Move move = new Move(start, finish);
                        jumpSet.add(move);
                    }

                    if (checkLeftJumpWhite(piece, oppositeColor)) {
                        Position start = new Position(piece.getRow(), piece.getCol());
                        Position finish = new Position(piece.getRow() + 2, piece.getCol() + 2);
                        Move move = new Move(start, finish);
                        jumpSet.add(move);
                    }
                    if (checkRightJumpWhite(piece, oppositeColor)) {
                        Position start = new Position(piece.getRow(), piece.getCol());
                        Position finish = new Position(piece.getRow() + 2, piece.getCol() - 2);
                        Move move = new Move(start, finish);
                        jumpSet.add(move);
                    }
                }
            } catch (Exception e){
                // just let it crash and burn
            }
        }
        return jumpSet;
    }


    /**
     * Helper function for getting all valid jumps for red
     *
     * @param space starting location
     * @return true of valid, false otherwise
     */
    private boolean checkRightJumpRed(Space space, Color OppositeColor){
        // make sure space has a piece
        if(!board.getItem(space.getRow() - 1, space.getCol() + 1).hasPiece())
            return false;
        return board.getItem(space.getRow() - 1,space.getCol() + 1).getPiece().getColor() == OppositeColor &&
                (board.getItem(space.getRow() - 2, space.getCol() + 2) != null &&
                        !board.getItem(space.getRow() - 2,space.getCol() + 2).hasPiece());
    }


    /**
     * Helper function for getting all valid jumps for red
     *
     * @param space starting location
     * @return true of valid, false otherwise
     */
    private boolean checkLeftJumpRed(Space space, Color OppositeColor){
        // make sure space has a piece
        if(!board.getItem(space.getRow() - 1, space.getCol() - 1).hasPiece()) {
            return false;
        }
        return board.getItem(space.getRow() - 1,space.getCol() - 1).getPiece().getColor() == OppositeColor &&
                (board.getItem(space.getRow() - 2, space.getCol() - 2) != null &&
                        !board.getItem(space.getRow() - 2,space.getCol() - 2).hasPiece());
    }


    /**
     * Helper function for getting all valid jumps for white
     *
     * @param space starting location
     * @return true of valid, false otherwise
     */
    private boolean checkRightJumpWhite(Space space, Color OppositeColor){
        // make sure space has a piece
        if(!board.getItem(space.getRow() + 1,space.getCol() - 1).hasPiece())
            return false;
        return board.getItem(space.getRow() + 1,space.getCol() - 1).getPiece().getColor() == OppositeColor &&
                (board.getItem(space.getRow() + 2, space.getCol() - 2) != null &&
                        !board.getItem(space.getRow() + 2,space.getCol() - 2).hasPiece());
    }


    /**
     * Helper function for getting all valid jumps for white
     *
     * @param space starting location
     * @return true of valid, false otherwise
     */
    private boolean checkLeftJumpWhite(Space space, Color OppositeColor){
        // make sure space has a piece
        if(!board.getItem(space.getRow() + 1,space.getCol() + 1).hasPiece())
            return false;
        return board.getItem(space.getRow() + 1, space.getCol() + 1).getPiece().getColor() == OppositeColor &&
                (board.getItem(space.getRow() + 2, space.getCol() + 2) != null &&
                        !board.getItem(space.getRow() + 2,space.getCol() + 2).hasPiece());
    }



    public boolean backupMove(){
        // if we can't back up any farther return false
        if(moveList.empty())
            return false;

        // set the board the be the last valid board
        board = moveList.pop();

        // if they went back to the start flag the user as not having made a move yet
        if(moveList.empty())
            turnType = PlayerMoves.NONE;

        return true;
    }


    public boolean isTurnOver(Color playerColor){
        if(hasJumpOption(playerColor)){
            Set<Move> jumpSet = jumpOptions(playerColor);
            for(Move move : jumpSet){
                Space jumpStart = new Space(move.getStart().getRow(), move.getStart().getCell());
                if(jumpStart.equals(previousFinishSpace))
                    return true;
            }
            return false;
        }
        return false;
    }
}
