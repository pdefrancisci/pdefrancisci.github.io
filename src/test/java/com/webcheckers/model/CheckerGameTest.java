package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.PlayerMoves;
import com.webcheckers.util.Type;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**"Invalid Move. Simple moves can only be one square diagonal left or right.";
 * Test CheckerGame class belonging to model tier
 */
@Tag("Model-tier")
public class CheckerGameTest {

    @Test
    public void testCheckerGame(){

    }

    @Test
    public void getOpponentTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        assertEquals(checkerGame.getOpponent(p1),p2);
        assertEquals(checkerGame.getOpponent(p2),p1);
    }

    @Test
    public void getRedPlayerTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        assertEquals(checkerGame.getRedPlayer(),p1);
    }

    @Test
    public void getWhitePlayerTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        assertEquals(checkerGame.getWhitePlayer(),p2);
    }

    @Test
    public void getCurrentTurnTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        assertEquals(checkerGame.getCurrentTurn(),Color.RED);
        checkerGame.changeCurrentTurn();
        assertEquals(checkerGame.getCurrentTurn(),Color.WHITE);
    }

    @Test
    public void changeCurrentTurnTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        assertEquals(checkerGame.getCurrentTurn(),Color.RED);
        checkerGame.changeCurrentTurn();
        assertEquals(checkerGame.getCurrentTurn(),Color.WHITE);
    }

    @Test
    public void getBoardTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);
        Board board = checkerGame.getBoard();
        // Basically just gotta check that the board was made correctly
        assertNotNull(board);
    }

    @Test
    public void gameOverTest() {
        Player p1 = new Player("test");
        Player p2 = new Player("test2");
        CheckerGame checkerGame = new CheckerGame(p1,p2);

        // Check condition where game should not be over(initial board state)
        String gameOverNot = checkerGame.gameOver();
        String notOver = "Game not over";
        assertEquals(notOver, gameOverNot);

        // Check condition where game should be won by white
        Piece whitePiece = new Piece(Color.WHITE, Type.SINGLE);
        setPiece(whitePiece, checkerGame);
        String whiteOver = "White wins";
        String gameOverWhite = checkerGame.gameOver();
        assertEquals(whiteOver, gameOverWhite);

        // Check condition where game should be won by red
        Piece redPiece = new Piece(Color.RED, Type.SINGLE);
        setPiece(redPiece, checkerGame);
        String redOver = "Red wins";
        String gameOverRed = checkerGame.gameOver();
        assertEquals(redOver, gameOverRed);

        // Check condition where there are no pieces on board somehow
        setPiece(null, checkerGame);
        String bonk = "There are no pieces on the board for some reason...";
        String bonkOver = checkerGame.gameOver();
        assertEquals(bonk, bonkOver);
    }

    // Helper function to set the color of pieces on the board
    public void setPiece(Piece piece, CheckerGame checkerGame){
        checkerGame.getBoard().getBoard()[0][1].setPiece(piece);
        checkerGame.getBoard().getBoard()[0][3].setPiece(piece);
        checkerGame.getBoard().getBoard()[0][5].setPiece(piece);
        checkerGame.getBoard().getBoard()[0][7].setPiece(piece);
        checkerGame.getBoard().getBoard()[0][1].setPiece(piece);
        checkerGame.getBoard().getBoard()[1][0].setPiece(piece);
        checkerGame.getBoard().getBoard()[1][2].setPiece(piece);
        checkerGame.getBoard().getBoard()[1][4].setPiece(piece);
        checkerGame.getBoard().getBoard()[1][6].setPiece(piece);
        checkerGame.getBoard().getBoard()[2][1].setPiece(piece);
        checkerGame.getBoard().getBoard()[2][3].setPiece(piece);
        checkerGame.getBoard().getBoard()[2][5].setPiece(piece);
        checkerGame.getBoard().getBoard()[2][7].setPiece(piece);
        checkerGame.getBoard().getBoard()[7][0].setPiece(piece);
        checkerGame.getBoard().getBoard()[7][2].setPiece(piece);
        checkerGame.getBoard().getBoard()[7][4].setPiece(piece);
        checkerGame.getBoard().getBoard()[7][6].setPiece(piece);
        checkerGame.getBoard().getBoard()[6][1].setPiece(piece);
        checkerGame.getBoard().getBoard()[6][3].setPiece(piece);
        checkerGame.getBoard().getBoard()[6][5].setPiece(piece);
        checkerGame.getBoard().getBoard()[6][7].setPiece(piece);
        checkerGame.getBoard().getBoard()[5][0].setPiece(piece);
        checkerGame.getBoard().getBoard()[5][2].setPiece(piece);
        checkerGame.getBoard().getBoard()[5][4].setPiece(piece);
        checkerGame.getBoard().getBoard()[5][6].setPiece(piece);
    }



    /**
     * check validateMove() with multiple unique cases
     * Cases:
     *   -(DONE)piece on startSpace is null
     *   -(DONE)color of piece on startSpace is not the player's color
     *   -(DONE)piece on finishSpace is NOT null
     *   -player has jump option, takes it
     *   -player has jump option, doesn't take it
     *   -Player RED moves SINGLE forward-left
     *   -Player RED moves SINGLE forward-right
     *   -Player RED tries to move SINGLE backward-left
     *   -Player RED tries to move SINGLE backward-left
     *   -Player WHITE moves SINGLE forward-left
     *   -Player WHITE moves SINGLE forward-right
     *   -Player WHITE tries to move SINGLE backward-left
     *   -Player WHITE tries to move SINGLE backward-left
     *   -Piece is a KING
     *
     *
     *
     *   (We still need more cases but the code isn't done yet)
     */
    @Test
    public void validateMoveTest() {
        Player p1 = new Player("test");
        Player p2 = new Player("test2");

        // Checking that player already made a simple move
        CheckerGame simpleCheckerGame = new CheckerGame(p1, p2);
        simpleCheckerGame.setTurnType(PlayerMoves.SIMPLE);
        Move nsimpleMove = new Move(new Position(2, 7), new Position(3, 6));
        String alreadySimple = "Invalid move. You have already made a simple move this turn! No other actions can be taken.";
        String alreadySimpleCheck = simpleCheckerGame.validateMove(Color.WHITE, nsimpleMove);
        assertEquals(alreadySimple, alreadySimpleCheck);

        // Checking that startspace is null
        CheckerGame nullCheckerGame = new CheckerGame(p1, p2);
        Move nullMove = new Move(new Position(2, 7), new Position(3, 6));
        Piece nullPiece = null;
        nullCheckerGame.getBoard().getBoard()[2][7].setPiece(nullPiece);
        String nullStart = "Invalid Move. There is no piece there... how did you do that???";
        String nullStartCheck = nullCheckerGame.validateMove(Color.WHITE, nullMove);
        assertEquals(nullStart, nullStartCheck);

        // Checking that color on startSpace is not color of current Player
        CheckerGame startCheckerGame = new CheckerGame(p1, p2);
        Move startMove = new Move(new Position(2,7), new Position(3,6));
        String badStartColor = "Invalid Move. That is your opponent's piece, you may only move pieces of your own color!";
        String badStartColorCheck = startCheckerGame.validateMove(Color.RED, startMove);
        assertEquals(badStartColor, badStartColorCheck);

        // Checking that piece on finish space is not null
        CheckerGame finishCheckerGame = new CheckerGame(p1, p2);
        Move finishMove = new Move(new Position(1, 6), new Position(2, 7));
        String notNullFinish = "Invalid Move. You can't move to a square that already has a piece.";
        String notNullFinishCheck = finishCheckerGame.validateMove(Color.WHITE, finishMove);
        assertEquals(notNullFinish, notNullFinishCheck);

        // Checking if player makes a normal move
        CheckerGame simpleMoveGame = new CheckerGame(p1, p2);
        simpleMoveGame.setTurnType(PlayerMoves.NONE);
        Move simpleMove = new Move(new Position(5, 6), new Position(4, 7));
        String simple = "simpleMove";
        String simpleCheck = simpleMoveGame.validateMove(Color.RED, simpleMove);
        assertEquals(simple, simpleCheck);

        // Checking if player has a simple jump, they take it
        CheckerGame simpleJumpGame = new CheckerGame(p1, p2);
        Piece whitePiece = new Piece(Color.WHITE, Type.SINGLE);
        simpleJumpGame.getBoard().getBoard()[4][5].setPiece(whitePiece);
        Move simpleJump = new Move(new Position(5,6), new Position(3, 4));
        String sJump = "jump";
        String sJumpCheck = simpleJumpGame.validateMove(Color.RED, simpleJump);
        assertEquals(sJump, sJumpCheck);

        // Checking if player has a multi jump, they take it
        // Just going to reuse previous board for multi-jump
        simpleJumpGame.getBoard().getBoard()[1][2].setPiece(null);
        Move multiJump = new Move(new Position(3,4), new Position(1,2));
        String mJump = "jump";
        String mJumpCheck = simpleJumpGame.validateMove(Color.RED, multiJump);
        assertEquals(mJump, mJumpCheck);

        // Checking if player has multi jump, but messes up
        // Using same board as before
        Move multiErrJump = new Move(new Position(5,4), new Position(3, 2));
        simpleJumpGame.getBoard().getBoard()[4][3].setPiece(whitePiece);
        String mErrJump = "Invalid Move. You have to move only one piece per turn!";
        String mErrJumpCheck = simpleJumpGame.validateMove(Color.RED, multiErrJump);
        assertEquals(mErrJump, mErrJumpCheck);


        // Check if player tries to do jump and move
        CheckerGame simpMulGame = new CheckerGame(p1, p2);
        Move simpMulJump = new Move(new Position(5, 6), new Position(4, 5));
        simpMulGame.setTurnType(PlayerMoves.JUMP);
        String simpMul = "Invalid Move. You either make jumps or simple moves, not a combination of both.";
        String simpMulCheck = simpMulGame.validateMove(Color.RED, simpMulJump);
        assertEquals(simpMul, simpMulCheck);

        // Check if player tries to make a simpleMove when a jump is available
        CheckerGame simpleJMGame = new CheckerGame(p1, p2);
        Move simpleJMove = new Move(new Position(5,2), new Position(4,1));
        simpleJMGame.getBoard().getBoard()[4][5].setPiece(whitePiece);
        String simpleJMErr = "Invalid Move. A possible jump is available so you must make that move.";
        String simpleJMErrCheck = simpleJMGame.validateMove(Color.RED, simpleJMove);
        assertEquals(simpleJMErr, simpleJMErrCheck);

        // Check if player tries to jump vertically or horizontally
        CheckerGame vHGame = new CheckerGame(p1, p2);
        Move vHMove = new Move(new Position(5,6), new Position(4, 6));
        String vHErr = "Invalid Move. Simple moves can only be one square diagonal left or right.";
        String vHErrCheck = vHGame.validateMove(Color.RED, vHMove);
        assertEquals(vHErr, vHErrCheck);

        // Checks correct and invalid simple move for white
        // Uses previous board
        Move simpleWMove = new Move(new Position(2, 7), new Position(3, 6));
        String simpleW = "simpleMove";
        String simpleWCheck = vHGame.validateMove(Color.WHITE, simpleWMove);
        assertEquals(simpleW, simpleWCheck);

        CheckerGame wErrMoveGame = new CheckerGame(p1, p2);
        Move wErrMove = new Move(new Position(2, 5), new Position(3, 5));
        String errW = "Invalid Move. Simple moves can only be one square diagonal left or right.";
        String errWCheck = wErrMoveGame.validateMove(Color.WHITE, wErrMove);
        assertEquals(errW, errWCheck);

        // I'm gonna leave the last case of validateMove alone for now as it seems to require work
    }

    @Test
    public void jumpOptionsTest(){
        Player p1 = new Player("test");
        Player p2 = new Player("test2");

        // Tests for if simple white piece
        CheckerGame lRWGame = new CheckerGame(p1, p2);
        Piece redPiece = new Piece(Color.RED, Type.SINGLE);
        lRWGame.getBoard().getBoard()[3][2].setPiece(redPiece);
        lRWGame.getBoard().getBoard()[3][4].setPiece(redPiece);
        HashSet<Move> lRWMoveSet = new HashSet<Move>();
        Move wMove1 = new Move(new Position(2,3), new Position(4, 5));
        Move wMove2 = new Move(new Position(2, 3), new Position(4, 1));
        Move wMove3 = new Move(new Position(2, 1), new Position(4,3));
        Move wMove4 = new Move(new Position(2,5), new Position(4,3));
        lRWMoveSet.add(wMove1);
        lRWMoveSet.add(wMove2);
        lRWMoveSet.add(wMove3);
        lRWMoveSet.add(wMove4);
        Set<Move> lRWMoveSetCheck = lRWGame.jumpOptions(Color.WHITE);
        assertEquals(lRWMoveSet, lRWMoveSetCheck);

        // Tests for white king moves
        CheckerGame wKingGame = new CheckerGame(p1, p2);
        Piece whiteKing = new Piece(Color.WHITE, Type.KING);
        wKingGame.getBoard().getBoard()[2][3].setPiece(whiteKing);
        wKingGame.getBoard().getBoard()[3][2].setPiece(redPiece);
        wKingGame.getBoard().getBoard()[3][4].setPiece(redPiece);
        HashSet<Move> wKingMoveSet = new HashSet<Move>();
        Move wKingMove1 = new Move(new Position(2,3), new Position(4, 5));
        Move wKingMove2 = new Move(new Position(2, 3), new Position(4, 1));
        Move wKingMove3 = new Move(new Position(2, 1), new Position(4,3));
        Move wKingMove4 = new Move(new Position(2,5), new Position(4,3));
        wKingMoveSet.add(wKingMove1);
        wKingMoveSet.add(wKingMove2);
        wKingMoveSet.add(wKingMove3);
        wKingMoveSet.add(wKingMove4);
        Set<Move> wKingMoveSetCheck = wKingGame.jumpOptions(Color.WHITE);
        assertEquals(wKingMoveSet, wKingMoveSetCheck);

        // Tests for red king moves
        CheckerGame rKingGame = new CheckerGame(p1, p2);
        Piece redKing = new Piece(Color.RED, Type.KING);
        Piece whitePiece = new Piece(Color.WHITE, Type.SINGLE);
        rKingGame.getBoard().getBoard()[5][4].setPiece(redKing);
        rKingGame.getBoard().getBoard()[4][3].setPiece(whitePiece);
        rKingGame.getBoard().getBoard()[4][5].setPiece(whitePiece);
        HashSet<Move> rKingMoveSet = new HashSet<Move>();
        Move rKingMove1 = new Move(new Position(5,4), new Position(3, 2));
        Move rKingMove2 = new Move(new Position(5, 4), new Position(3, 6));
        Move rKingMove3 = new Move(new Position(5, 2), new Position(3,4));
        Move rKingMove4 = new Move(new Position(5,6), new Position(3,4));
        rKingMoveSet.add(rKingMove1);
        rKingMoveSet.add(rKingMove2);
        rKingMoveSet.add(rKingMove3);
        rKingMoveSet.add(rKingMove4);
        Set<Move> rKingMoveSetCheck = rKingGame.jumpOptions(Color.RED);
        assertEquals(rKingMoveSet, rKingMoveSetCheck);
    }
}
