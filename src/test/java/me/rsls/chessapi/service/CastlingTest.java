package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.FigureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


public class CastlingTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private HandleMoveService handleMoveService;

    @Autowired
    private BoardService boardService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }




    @Test
    public void testKingCastling() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"g", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"g", "8"});
        Field rookField = boardService.getField(new String[]{"e", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }
}
