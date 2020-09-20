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
            moveExecutorService.executeMove(sourceField, targetField);

            boolean figureWithPossibleFields;

            if (board.getLastPlayed().equals(Color.BLACK)) {
                figureWithPossibleFields = existsPossibleFields(Color.WHITE);
            } else {
                figureWithPossibleFields = existsPossibleFields(Color.BLACK);
            }

            if (!figureWithPossibleFields) gameState.setRemis(true);

            moveExecutorService.revertLastMove();
        } else {
            gameState.setRemis(true);
        }

    }

    private boolean checkSameMove(Board board) {
        int historySize = board.getMoveHistory().size();
        if (historySize > 7) {
            for (int i = historySize - 1; i > 3; i--) {
                Move firstMove = board.getMoveHistory().get(i);
                Move secondMove = board.getMoveHistory().get(i - 2);
                Move thirdMove = board.getMoveHistory().get(i - 4);

                if (firstMove.getSourceField() == secondMove.getTargetField()
                        && secondMove.getTargetField() == thirdMove.getSourceField()
                        && firstMove.getMovedFigure() == secondMove.getMovedFigure()
                        && secondMove.getMovedFigure() == thirdMove.getMovedFigure()) {
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


        boolean possibleFields = false;

        possibleFields = CheckMateService.isCanProtect(possibleFields, alliesFigures, figureService, validFieldService, checkService);

        return possibleFields;
    }

}
