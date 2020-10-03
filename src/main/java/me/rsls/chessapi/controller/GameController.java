package me.rsls.chessapi.controller;


import me.rsls.chessapi.model.Game;
import me.rsls.chessapi.service.GameService;
import me.rsls.chessapi.service.InitializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private InitializeService initializeService;


    @RequestMapping(value = "getGamePicture", method = RequestMethod.GET)
    public ResponseEntity<Game> getGamePicture() {
        Game game = gameService.getGamePicture();

        System.out.println("GET GAME PICTURE");

        if (game == null) {
            initializeService.initializeGame();
            game = gameService.getGamePicture();
        }

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @RequestMapping(value = "switchPlayer", method = RequestMethod.POST)
    public ResponseEntity<Boolean> switchPlayer() {

        Game game = gameService.getGamePicture();

        if (game == null) {
            initializeService.initializeGame();
        }

        gameService.switchPlayer();

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
