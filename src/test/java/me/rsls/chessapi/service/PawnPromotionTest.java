package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.model.GameState;
import me.rsls.chessapi.model.SelectedFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PawnPromotionTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private HandleMoveService handleMoveService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PawnPromotionService pawnPromotionService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }

    @Test
    public void testPawnChange() {
        GameState gameState = gameService.getCurrentGameState();

        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "3"}).isState());

        assertFalse(gameState.isPawnChange());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"g", "1"}).isState());

        assertTrue(gameState.isPawnChange());

        //not allowed to move, because pawn must be selected
        assertFalse(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"g", "4"}).isState());

        SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertFalse(gameState.isPawnChange());

        //allowed to move, pawn has been replaced
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"g", "4"}).isState());
    }

    @Test
    public void testSimplePawnChange() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "6"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "8"}).isState());
    }


}
