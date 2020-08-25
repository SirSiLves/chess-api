package me.rsls.chessapi.service;

import me.rsls.chessapi.ChessApiApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;



public class MoveServiceTest extends ChessApiApplicationTests {

    @Autowired
    private InitializeService initializeService;

    @Autowired
    private MoveService moveService;


    @BeforeEach
    public void setInitializeService() {
        initializeService.initializeGame();
    }

    @Test
    public void testValidPawnMoves() {
        assertTrue(moveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "2"}, new String[]{"f", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "3"}, new String[]{"e", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "5"}, new String[]{"e", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "4"}, new String[]{"f", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "6"}, new String[]{"f", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "3"}, new String[]{"b", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "6"}, new String[]{"c", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "4"}, new String[]{"b", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "7"}, new String[]{"a", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "5"}, new String[]{"a", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "7"}, new String[]{"a", "6"}).isState());
    }

    @Test
    public void testInvalidPawnMoves(){
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "8"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "8"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "4"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"a", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "5"}).isState());

        assertTrue(moveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());

        assertFalse(moveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "5"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "4"}, new String[]{"g", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "7"}, new String[]{"h", "4"}).isState());

        assertTrue(moveService.handleMove(new String[]{"g", "7"}, new String[]{"g", "5"}).isState());

        assertFalse(moveService.handleMove(new String[]{"h", "4"}, new String[]{"f", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"e", "2"}, new String[]{"f", "3"}).isState());

        assertTrue(moveService.handleMove(new String[]{"g", "2"}, new String[]{"g", "4"}).isState());

        assertFalse(moveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "4"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "5"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "5"}, new String[]{"g", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "4"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "7"}, new String[]{"g", "4"}).isState());

        assertTrue(moveService.handleMove(new String[]{"f", "7"}, new String[]{"f", "6"}).isState());

        assertFalse(moveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "6"}, new String[]{"f", "8"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "4"}, new String[]{"g", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"g", "5"}, new String[]{"f", "6"}).isState());
    }

    @Test
    public void testValidRookMoves(){
        assertTrue(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "6"}).isState());

        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "6"}).isState());

        assertTrue(moveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "6"}, new String[]{"f", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "3"}, new String[]{"f", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "6"}, new String[]{"h", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "3"}, new String[]{"g", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "7"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "3"}, new String[]{"g", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "5"}, new String[]{"h", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "5"}).isState());
    }

    @Test
    public void testInvalidRookMoves(){
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "8"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "1"}, new String[]{"e", "1"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "1"}, new String[]{"a", "1"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "5"}).isState());

        assertTrue(moveService.handleMove(new String[]{"h", "7"}, new String[]{"h", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "2"}, new String[]{"h", "4"}).isState());

        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "5"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "8"}, new String[]{"g", "6"}).isState());

        assertTrue(moveService.handleMove(new String[]{"h", "8"}, new String[]{"h", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "1"}, new String[]{"h", "3"}).isState());

        assertFalse(moveService.handleMove(new String[]{"h", "6"}, new String[]{"h", "5"}).isState());

        assertTrue(moveService.handleMove(new String[]{"h", "6"}, new String[]{"a", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "3"}, new String[]{"a", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"h", "3"}, new String[]{"a", "4"}).isState());
    }


    @Test
    public void testValidBishopMoves(){

        assertTrue(moveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "8"}, new String[]{"a", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "1"}, new String[]{"h", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "7"}, new String[]{"d", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "2"}, new String[]{"e", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "8"}, new String[]{"h", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "1"}, new String[]{"a", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "3"}, new String[]{"c", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "6"}, new String[]{"d", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "3"}, new String[]{"g", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "6"}, new String[]{"g", "7"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "2"}, new String[]{"h", "1"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "7"}, new String[]{"f", "8"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "5"}, new String[]{"b", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "3"}, new String[]{"a", "6"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "6"}, new String[]{"d", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "6"}, new String[]{"b", "7"}).isState());

    }

    @Test
    public void testInvalidBishopMoves(){

        assertFalse(moveService.handleMove(new String[]{"c", "1"}, new String[]{"d", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"c", "1"}, new String[]{"b", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"c", "1"}, new String[]{"b", "8"}).isState());

        assertTrue(moveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "1"}, new String[]{"e", "3"}).isState());

        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"e", "5"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"c", "3"}).isState());

        assertTrue(moveService.handleMove(new String[]{"f", "8"}, new String[]{"b", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "3"}, new String[]{"d", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "4"}, new String[]{"f", "8"}).isState());

        assertFalse(moveService.handleMove(new String[]{"d", "2"}, new String[]{"d", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"f", "8"}, new String[]{"h", "6"}).isState());

        assertTrue(moveService.handleMove(new String[]{"d", "2"}, new String[]{"e", "3"}).isState());
        assertTrue(moveService.handleMove(new String[]{"f", "8"}, new String[]{"d", "6"}).isState());

        assertFalse(moveService.handleMove(new String[]{"e", "3"}, new String[]{"c", "5"}).isState());

    }

    @Test
    public void testValidQueenMoves(){
        assertTrue(moveService.handleMove(new String[]{"e", "7"}, new String[]{"e", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "8"}, new String[]{"h", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "4"}, new String[]{"c", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "4"}, new String[]{"b", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "4"}, new String[]{"a", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "5"}, new String[]{"e", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "4"}, new String[]{"h", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"e", "5"}, new String[]{"c", "7"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "4"}, new String[]{"h", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "7"}, new String[]{"b", "8"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "2"}, new String[]{"g", "1"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "8"}, new String[]{"a", "8"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "1"}, new String[]{"h", "1"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "8"}, new String[]{"a", "7"}).isState());
        assertTrue(moveService.handleMove(new String[]{"h", "1"}, new String[]{"g", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"a", "7"}, new String[]{"b", "7"}).isState());
        assertTrue(moveService.handleMove(new String[]{"g", "2"}, new String[]{"b", "7"}).isState());

    }

    @Test
    public void testInvalidQueenMoves(){
        assertFalse(moveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "8"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "8"}, new String[]{"d", "7"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "8"}, new String[]{"c", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "1"}, new String[]{"c", "2"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "1"}, new String[]{"e", "2"}).isState());

        assertTrue(moveService.handleMove(new String[]{"c", "7"}, new String[]{"c", "5"}).isState());
        assertTrue(moveService.handleMove(new String[]{"c", "2"}, new String[]{"c", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"d", "8"}, new String[]{"a", "5"}).isState());

        assertFalse(moveService.handleMove(new String[]{"a", "5"}, new String[]{"b", "5"}).isState());
        assertFalse(moveService.handleMove(new String[]{"d", "1"}, new String[]{"h", "5"}).isState());

        assertTrue(moveService.handleMove(new String[]{"d", "1"}, new String[]{"a", "4"}).isState());

        assertFalse(moveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "3"}).isState());
        assertFalse(moveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "6"}).isState());
        assertFalse(moveService.handleMove(new String[]{"a", "5"}, new String[]{"h", "4"}).isState());
        assertFalse(moveService.handleMove(new String[]{"a", "5"}, new String[]{"h", "5"}).isState());

        assertTrue(moveService.handleMove(new String[]{"a", "5"}, new String[]{"a", "4"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "2"}, new String[]{"b", "3"}).isState());

        assertFalse(moveService.handleMove(new String[]{"a", "4"}, new String[]{"c", "2"}).isState());

        assertTrue(moveService.handleMove(new String[]{"a", "4"}, new String[]{"a", "2"}).isState());
        assertTrue(moveService.handleMove(new String[]{"b", "3"}, new String[]{"b", "4"}).isState());

        assertFalse(moveService.handleMove(new String[]{"a", "2"}, new String[]{"f", "7"}).isState());

    }
}
