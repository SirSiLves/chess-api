package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private HandleMoveService handleMoveService;

    @Autowired
    private GameService gameService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }


    @Test
    public void testSimpleCheck(){
        GameState gameState = gameService.getCurrentGameState();

        assertFalse(gameState.isCheck());
        assertFalse(gameState.isCheckMate());

        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());

        assertTrue(gameState.isCheck());
        assertFalse(gameState.isCheckMate());
    }

    @Test
    public void testCheckMate(){
        GameState gameState = gameService.getCurrentGameState();

        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"g", "6"}).isState());

        assertTrue(gameState.isCheck());
        assertTrue(gameState.isCheckMate());
    }

    @Test
    public void testInvalidBishopMovesWithCheck(){
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"b", "5"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"c", "8"}).isState());
    }

    @Test
    public void testNotAllowedCheck(){
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"d", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"c", "6"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"e", "8"}).isState());

    }


    @Test
    public void testInvalidKingMove(){
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"e", "4"}).isState());

        //jump in front of a king is not allowed for own king
        assertFalse(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"e", "5"}).isState());

        Game game = gameService.getGamePicture();

        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());
    }


    @Test
    public void testBothKingInvalidCheckState() {
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"c", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"g", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"h", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "2"}).isState());

        Game game = gameService.getGamePicture();
        assertTrue(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());

        //not allowed, own king is in a check state
        assertFalse(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"f", "6"}).isState());

        assertTrue(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());
    }

    @Test
    public void testCheckBlackMate(){
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "3"}, new String[]{"f", "7"}).isState());

        Game game = gameService.getGamePicture();

        assertTrue(game.getGameState().isCheck());
        assertTrue(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());
    }

    @Test
    public void testCheckAndReplacceWithNewCheck() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"e", "4"}).isState());

        Game game = gameService.getGamePicture();

        assertTrue(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());

        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "4"}).isState());

        assertTrue(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());
    }

    @Test
    public void testWrongCheckMate() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"f", "5"}).isState());


        Game game = gameService.getGamePicture();

        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());
    }
}
