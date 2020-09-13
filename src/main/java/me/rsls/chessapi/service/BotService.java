package me.rsls.chessapi.service;

import me.rsls.chessapi.model.Board;
import me.rsls.chessapi.model.Field;
import me.rsls.chessapi.model.Figure;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BotService {

    @Autowired
    private GameService gameService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private MoveExecutorService moveExecutorService;

    @Autowired
    private ValidFieldService validFieldService;

    @Autowired
    private FigureService figureService;

    public Field[] executeRandomBotMove() {

        Board board = gameService.getCurrentBoard();

        List<Figure> botFigures = board.getFigureArrayList().stream()
                .filter(f -> !board.getLastPlayed().equals(f.getFigureColor()))
                .filter(f -> f.isAlive())
                .collect(Collectors.toList());

        while (true) {
            Figure randomFigure = botFigures.get(new Random().nextInt(botFigures.size()));

            Field sourceField = figureService.getFigureField(randomFigure);
            ValidFields validFields = validFieldService.validateFields(sourceField, sourceField.getFigure());

            if (validFields.getFieldList().size() > 0) {

                for (Field field : validFields.getFieldList().keySet()) {

                    Validation validation = validateService.validateMove(sourceField, field);

                    if (validation.isState()) {
                        moveExecutorService.executeMove(sourceField, field);

                        return new Field[]{
                          sourceField, field
                        };

                    }
                }
            }
        }
    }
}
