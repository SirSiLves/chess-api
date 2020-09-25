package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemisService {

    @Autowired
    private GameService gameService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private CheckMateService checkMateService;

    @Autowired
    private FigureService figureService;


    public void validateRemis(Field sourceField, Field targetField) {
        Game game = gameService.getGamePicture();
        Board board = game.getBoard();
        GameState gameState = game.getGameState();

        moveExecutorService.executeMove(sourceField, targetField, true);

        //validate remis situations
        if (this.isStaleMate()) {
            gameState.setRemis(true);
            gameState.setRemisReason("Stalemate");
        } else if (this.fiftyMoveRule(board)) {
            gameState.setRemis(true);
            gameState.setRemisReason("Fifty-Move Rule");
        } else if (this.isDeadPosition()) {
            gameState.setRemis(true);
            gameState.setRemisReason("Insufficient Mating Material");
        } else if (this.toOfterSameMove()) {
            gameState.setRemis(true);
            gameState.setRemisReason("Too often the same move");
        }
        //TODO Threefold Repetition https://www.spielezar.ch/blog/spielregeln/unentschieden-beim-schach#Dreifache_Stellungswiederholung
        //TODO perpetual check -> means each can go for check

        moveExecutorService.revertLastMove(true);
    }

    private boolean toOfterSameMove() {
        Board board = gameService.getCurrentBoard();

        int historySize = board.getMoveHistory().size();

        for (int i = historySize - 1; i > 4; i--) {
            History firstMove = board.getMoveHistory().get(i);
            History thirdMove = board.getMoveHistory().get(i - 2);
            History fifthMove = board.getMoveHistory().get(i - 4);

            History secondMove = board.getMoveHistory().get(i - 1);
            History fourthMove = board.getMoveHistory().get(i - 3);
            History sixthMove = board.getMoveHistory().get(i - 5);

            if (firstMove.getSourceField() == thirdMove.getTargetField()
                    && thirdMove.getTargetField() == fifthMove.getSourceField()
                    && firstMove.getMovedFigure() == thirdMove.getMovedFigure()
                    && thirdMove.getMovedFigure() == fifthMove.getMovedFigure()

                    && secondMove.getSourceField() == fourthMove.getTargetField()
                    && fourthMove.getTargetField() == sixthMove.getSourceField()
                    && secondMove.getMovedFigure() == fourthMove.getMovedFigure()
                    && fourthMove.getMovedFigure() == sixthMove.getMovedFigure()
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean isStaleMate() {
        Board board = gameService.getCurrentBoard();

        boolean figureWithPossibleFields;

        if (board.getLastPlayed().equals(Color.BLACK)) {
            figureWithPossibleFields = existsPossibleFields(Color.WHITE);
        } else {
            figureWithPossibleFields = existsPossibleFields(Color.BLACK);
        }

        return !figureWithPossibleFields;
    }

    private boolean isDeadPosition() {

        List<Figure> blackFigures = figureService.getAllies(Color.BLACK);
        List<Figure> whiteFigures = figureService.getAllies(Color.WHITE);

        int blackBishopCount = 0;
        int blackKnightCount = 0;
        int blackOtherPlayers = 0;

        for (Figure blackFigure : blackFigures) {
            if (blackFigure.getFigureType().equals(FigureType.BISHOP)) blackBishopCount++;
            else if (blackFigure.getFigureType().equals(FigureType.KNIGHT)) blackKnightCount++;
            else if (!blackFigure.getFigureType().equals(FigureType.KING)) blackOtherPlayers++;
        }

        int whiteBishopCount = 0;
        int whiteKnightCount = 0;
        int whiteOtherPlayers = 0;

        for (Figure whiteFigure : whiteFigures) {
            if (whiteFigure.getFigureType().equals(FigureType.BISHOP)) whiteBishopCount++;
            else if (whiteFigure.getFigureType().equals(FigureType.KNIGHT)) whiteKnightCount++;
            else if (!whiteFigure.getFigureType().equals(FigureType.KING)) whiteOtherPlayers++;
        }

        if (blackBishopCount == 0 && whiteBishopCount == 0 && blackKnightCount == 0 && whiteKnightCount == 0 && blackOtherPlayers == 0 && whiteOtherPlayers == 0)
            return true;
        else if (blackBishopCount == 1 && whiteBishopCount == 0 && whiteKnightCount == 0 && whiteOtherPlayers == 0)
            return true;
        else if (whiteBishopCount == 1 && blackBishopCount == 0 && blackKnightCount == 0 && blackOtherPlayers == 0)
            return true;
        else if (whiteKnightCount == 1 && blackBishopCount == 0 && whiteBishopCount == 0 && whiteOtherPlayers == 0)
            return true;
        else if (blackKnightCount == 1 && whiteBishopCount == 0 && blackBishopCount == 0 && blackOtherPlayers == 0)
            return true;
        else return false;
    }

    private boolean fiftyMoveRule(Board board) {
        int historySize = board.getMoveHistory().size();

        int fiftyCount = 0;

        for (int i = historySize - 1; i >= 0; i--) {

            History history = board.getMoveHistory().get(i);

            if (!history.getMovedFigure().getFigureType().equals(FigureType.PAWN)) {
                fiftyCount++;
            } else if (history.getKilledFigure() == null) {
                fiftyCount++;
            } else {
                break;
            }
        }

        return fiftyCount >= 50;
    }

    private boolean existsPossibleFields(Color figureColor) {
        List<Figure> alliesFigures = figureService.getAllies(figureColor);
        return checkMateService.isCanProtect(alliesFigures);
    }

}
