package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckServiceTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private MoveService moveService;

    @Autowired
    private GameService gameService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PlayerService playerService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }

    @Test
    public void testCheckMate(){
        assertTrue(moveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "6"}, new String[]{"g", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "6"}, new String[]{"f", "2"}).isState());

        assertFalse(moveService.handleMove(new String[]{"e", "1"}, new String[]{"f", "2"}).isState());

        Board board = gameService.getGamePicture(playerService.getPlayer()).getBoard();

        assertTrue(board.getCheck().isCheck());
        assertTrue(board.getCheck().isCheckMate());
    }



    @Test
    public void testInvalidBishopMovesWithCheck(){
        assertTrue(moveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "2"}, new String[]{"c", "3"}).isState());

        assertFalse(moveService.handleMove(new String[]{"c", "8"}, new String[]{"e", "2"}).isState());
    }
}
