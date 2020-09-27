package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.model.SelectedFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class RemisTest extends ChessApiApplicationTests {

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
    public void testToOftenSameMove() {
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"b", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());

        Game game = gameService.getGamePicture();

        assertEquals("Too often the same move", game.getGameState().getRemisReason());
        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertTrue(game.getGameState().isRemis());
    }

    @Test
    public void testDeadPosition() {
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"d", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"b", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"b", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"e", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"c", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"d", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "1"}).isState());

        SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"h", "2"}).isState());

        //2 KINGS & a black bishop are left
        Game game = gameService.getGamePicture();

        assertEquals("Insufficient Mating Material", game.getGameState().getRemisReason());
        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertTrue(game.getGameState().isRemis());
    }


    @Test
    public void testStaleMateRemis(){
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "3"}, new String[]{"b", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"c", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"c", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"g", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());

        Game game = gameService.getGamePicture();
        assertEquals("Stalemate", game.getGameState().getRemisReason());
        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertTrue(game.getGameState().isRemis());
    }

    @Test
    public void testRemisWithFifityRule() {
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"g", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"b", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"b", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"e", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"c", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"g", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"b", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"e", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"c", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"c", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "8"}).isState());

        SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"c", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"a", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"c", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"a", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"e", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"d", "7"}).isState());

        Game game = gameService.getGamePicture();

        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());

        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "2"}).isState());

        assertEquals("Fifty-Move Rule", game.getGameState().getRemisReason());
        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertTrue(game.getGameState().isRemis());
    }


    @Test
    public void testInsufficientMatingMaterial() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"c", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "6"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"b", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"f", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"a", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "8"}).isState());

        SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"d", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "1"}).isState());

        selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"c", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "8"}).isState());

        selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"a", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "1"}).isState());

        selectedFigure = new SelectedFigure(FigureType.QUEEN);
        pawnPromotionService.changePawn(selectedFigure);

        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"c", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"h", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"f", "5"}).isState());

        Game game = gameService.getGamePicture();

        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertFalse(game.getGameState().isRemis());

        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "5"}).isState());

        assertEquals("Insufficient Mating Material", game.getGameState().getRemisReason());
        assertFalse(game.getGameState().isCheck());
        assertFalse(game.getGameState().isCheckMate());
        assertTrue(game.getGameState().isRemis());

    }
}
