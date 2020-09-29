package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;



public class MoveTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private HandleMoveService handleMoveService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }

    @Test
    public void testValidPawnMoves() {
        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "5"}, new String[]{"e", "4"}).isState());
    }

    @Test
    public void testInvalidPawnMovesAtStart(){
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"c", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
    }

    @Test
    public void testInvalidPawnMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"a", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"h", "4"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "4"}, new String[]{"f", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"f", "3"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "4"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "6"}).isState());
    }

    @Test
    public void testValidRookMoves(){
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "6"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "6"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "5"}).isState());
    }

    @Test
    public void testInvalidRookMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"e", "1"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"a", "1"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "6"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"a", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"a", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"a", "4"}).isState());
    }


    @Test
    public void testValidBishopMoves(){
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"h", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"f", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "5"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "7"}).isState());
    }

    @Test
    public void testInvalidBishopMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"b", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"b", "8"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "1"}, new String[]{"e", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"f", "8"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "7"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "8"}, new String[]{"a", "6"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"a", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"h", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"f", "8"}).isState());
    }

    @Test
    public void testValidQueenMoves(){
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"g", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "6"}, new String[]{"g", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "2"}, new String[]{"h", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "6"}, new String[]{"h", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "3"}, new String[]{"a", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "3"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"h", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"b", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "8"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "1"}).isState());
    }

    @Test
    public void testInvalidQueenMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"c", "2"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"e", "2"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "5"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"h", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"h", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"h", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"c", "2"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "3"}, new String[]{"b", "4"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"f", "7"}).isState());
    }

    @Test
    public void testInvalidQueenMoves2(){
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "1"}, new String[]{"a", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "3"}).isState());
    }

    @Test
    public void testValidKnightMoves(){
        assertTrue(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"d", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"g", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"d", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "1"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "5"}, new String[]{"f", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "3"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "5"}, new String[]{"c", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "4"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "4"}, new String[]{"b", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"h", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "6"}, new String[]{"a", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"h", "5"}, new String[]{"g", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "4"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"g", "3"}, new String[]{"f", "1"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"f", "6"}, new String[]{"e", "4"}).isState());
    }

    @Test
    public void testInvalidKnightMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"e", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"g", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"d", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "8"}, new String[]{"e", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"f", "4"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"b", "8"}, new String[]{"c", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "1"}, new String[]{"c", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"d", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"c", "4"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"c", "6"}, new String[]{"b", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"b", "5"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"h", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"h", "3"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"h", "4"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"g", "5"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"b", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"b", "5"}, new String[]{"c", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"a", "2"}, new String[]{"c", "1"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"c", "1"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"c", "3"}, new String[]{"b", "8"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"g", "1"}, new String[]{"a", "3"}).isState());
    }

    @Test
    public void testValidKingMoves(){
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"e", "2"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "6"}, new String[]{"d", "6"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "3"}, new String[]{"d", "3"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "6"}, new String[]{"c", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"d", "3"}, new String[]{"e", "2"}).isState());
    }

    @Test
    public void testInvalidKingMoves(){
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"d", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"f", "7"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"d", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"f", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "6"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "7"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "1"}, new String[]{"e", "2"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"d", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"f", "5"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"g", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"c", "6"}).isState());
        assertFalse(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "1"}).isState());

        assertTrue(handleMoveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "8"}).isState());
        assertTrue(handleMoveService.handleMove(new String[]{"e", "2"}, new String[]{"d", "3"}).isState());

        assertFalse(handleMoveService.handleMove(new String[]{"e", "8"}, new String[]{"e", "1"}).isState());
    }


}
