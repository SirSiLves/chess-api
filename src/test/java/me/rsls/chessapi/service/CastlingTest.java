package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.FigureType;
import me.rsls.chessapi.model.GameState;
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

    @Autowired
    private GameService gameService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }




    @Test
    public void testWhiteKingSideCastling() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"g", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"g", "8"});
        Field rookField = boardService.getField(new String[]{"f", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testBlackKingSideCastling() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"g", "1"}).isState());

        Field kingField = boardService.getField(new String[]{"g", "1"});
        Field rookField = boardService.getField(new String[]{"f", "1"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testBlackQueenSideCastling() {
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"c", "1"}).isState());

        Field kingField = boardService.getField(new String[]{"c", "1"});
        Field rookField = boardService.getField(new String[]{"d", "1"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testWhiteQueenSideCastling() {
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"c", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"c", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"c", "8"});
        Field rookField = boardService.getField(new String[]{"d", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testCastlingPossibleDoNormaleMove() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());

        Field kingField = boardService.getField(new String[]{"e", "7"});
        Field rookField = boardService.getField(new String[]{"h", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testKingAlreadyMoved() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        //king already moved
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"f", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"g", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"g", "8"}).isState());
    }

    @Test
    public void testNotCastleWithRook() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());

        //do a king move
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"f", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"e", "8"});
        Field rookField = boardService.getField(new String[]{"f", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testAlwaysCheckQueenSideCastle() {
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"d", "7"}).isState());

        //king is in a check state
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"c", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"e", "8"});
        Field rookField = boardService.getField(new String[]{"a", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testCheckOnCastlingWay() {
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "4"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"e", "4"}).isState());

        //king is in a check state on the next field
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"c", "8"}).isState());

        Field kingField = boardService.getField(new String[]{"e", "8"});
        Field rookField = boardService.getField(new String[]{"a", "8"});

        assertEquals(kingField.getFigure().getFigureType(), FigureType.KING);
        assertEquals(rookField.getFigure().getFigureType(), FigureType.ROOK);
    }

    @Test
    public void testNotAllowedBlackKingSideCastlingWithCheck() {
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "2"}).isState());
        //king is in a check state on field f1
        assertFalse(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"g", "1"}).isState());
    }

    @Test
    public void testNotAllowedBlackKingSideCastlingWithCheck2() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"e", "2"}).isState());

        //king is in a check state on field f1
        assertFalse(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"g", "1"}).isState());
    }


    @Test
    public void testCatchBishopWithCastlingPossibility() {
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "4"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"e", "2"}).isState());
        //black king take bishop
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"e", "2"}).isState());
    }


    @Test
    public void testCastlingAfterManyMoves() {
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"g", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"c", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"c", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"c", "8"}).isState());
    }


}
