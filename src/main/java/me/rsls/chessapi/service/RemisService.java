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
    private ValidFieldService validFieldService;

    @Autowired
    private FigureService figureService;

    @Autowired
    private CheckService checkService;


    public void validateRemis(Field sourceField, Field targetField) {
        Game game = gameService.getGamePicture();
        Board board = game.getBoard();
        GameState gameState = game.getGameState();

        //validate if the same move was to often done
        boolean checkSameMove = this.checkSameMove(board);
        if (!checkSameMove) {
            moveExecutorService.executeMove(sourceField, targetField, true);

            boolean figureWithPossibleFields;

            if (board.getLastPlayed().equals(Color.BLACK)) {
                figureWithPossibleFields = existsPossibleFields(Color.WHITE);
            } else {
                figureWithPossibleFields = existsPossibleFields(Color.BLACK);
            }

            if (!figureWithPossibleFields) gameState.setRemis(true);

            moveExecutorService.revertLastMove(true);
        } else {
            gameState.setRemis(true);
        }

    }

    private boolean checkSameMove(Board board) {
        int historySize = board.getMoveHistory().size();
        if (historySize > 7) {
            for (int i = historySize - 1; i > 3; i--) {
                History firstHistory = board.getMoveHistory().get(i);
                History secondHistory = board.getMoveHistory().get(i - 2);
                History thirdHistory = board.getMoveHistory().get(i - 4);

                if (firstHistory.getSourceField() == secondHistory.getTargetField()
                        && secondHistory.getTargetField() == thirdHistory.getSourceField()
                        && firstHistory.getMovedFigure() == secondHistory.getMovedFigure()
                        && secondHistory.getMovedFigure() == thirdHistory.getMovedFigure()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existsPossibleFields(Color figureColor) {
        Board board = gameService.getCurrentBoard();

        List<Figure> alliesFigures = board.getFigureArrayList().stream()
                .filter(f -> f.isAlive())
                .filter(f -> f.getFigureColor().equals(figureColor))
                .collect(Collectors.toList());

        boolean possibleFields = CheckMateService.isCanProtect(alliesFigures, figureService, validFieldService, checkService);

        return possibleFields;
    }

}
