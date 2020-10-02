package me.rsls.chessapi.service;

import me.rsls.chessapi.model.*;
import me.rsls.chessapi.model.validation.ValidFields;
import me.rsls.chessapi.model.validation.Validation;
import me.rsls.chessapi.service.validation.PawnPromotionService;
import me.rsls.chessapi.service.validation.ValidFieldService;
import me.rsls.chessapi.service.validation.ValidateService;
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

    @Autowired
    private PawnPromotionService pawnPromotionService;

    private static final Map<FigureType, Integer> FIGURE_WORTH = new HashMap<>() {
        {
            put(FigureType.PAWN, 10);
            put(FigureType.KNIGHT, 30);
            put(FigureType.BISHOP, 30);
            put(FigureType.ROOK, 50);
            put(FigureType.QUEEN, 90);
            put(FigureType.KING, 900);
        }
    };

    public void handleBotMove() {
        GameState gameState = gameService.getCurrentGameState();
        if (!(gameState.isCheckMate() || gameState.isRemis())) {

            List<Rating> ratedList = this.getFilteredRatingList();

            Random rnd = new Random();
            int position = Math.abs(rnd.nextInt(ratedList.size()));

            Field sourceField = ratedList.get(position).getSourceField();
            Field targetField = ratedList.get(position).getTargetField();

            this.executeBotMove(sourceField, targetField);
        }

    }

    private List<Rating> getFilteredRatingList() {

        ArrayList<Rating> ratedList = this.getRatingList();
        Collections.sort(ratedList);

        Rating bestRate = ratedList.get(0);

        return ratedList.stream()
                .filter(r -> r.getRatingValue() >= bestRate.getRatingValue())
                .collect(Collectors.toList());
    }


    private ArrayList<Rating> getRatingList() {
        List<Figure> botFigures = figureService.getAtTurnFigures();
        ArrayList<Rating> ratedList = new ArrayList<>();

        for (Figure figure : botFigures) {
            List<Field> possibleFields = validateService.getAllValidFields(figure, true);

            for (Field targetField : possibleFields) {
                Field sourceField = figureService.getFigureField(figure);
                int worthLevel = this.getWorthLevel(targetField);
                ratedList.add(new Rating(sourceField, targetField, worthLevel));
            }
        }

        return ratedList;
    }

    private int getWorthLevel(Field targetField) {
        GameState gameState = gameService.getCurrentGameState();
        if (gameState.isCheckMate()) {
            return 1000;
        }

        Figure targetFigure = targetField.getFigure();
        if (targetFigure != null) {
            return FIGURE_WORTH.get(targetFigure.getFigureType());
        } else {
            return 0;
        }
    }

    private void executeBotMove(Field sourceField, Field targetField) {
        this.printBoteMove(sourceField, targetField);

        moveExecutorService.executeMove(sourceField, targetField, false);
        this.handlePawn(targetField);

//        //Validate one last, to retrieve the check state
//        Validation validation = validateService.validateMove(sourceField, targetField);
//
//        if (validation.isState()) {
//            this.printBoteMove(sourceField, targetField);
//            this.handleCastling();
//
//            moveExecutorService.executeMove(sourceField, targetField, false);
//
//            this.handlePawn(targetField);
//        } else {
//            throw new RuntimeException("Something went wrong with the bot handling!");
//        }

    }

    private void handleCastling() {
        //if castling was a possible field, reset state
        GameState gameState = gameService.getCurrentGameState();
        gameState.setCastling(false);
    }

    private void handlePawn(Field targetField) {
        if ((targetField.getHorizontalNumber() == 1 || targetField.getHorizontalNumber() == 8) &&
                (targetField.getFigure().getFigureType().equals(FigureType.PAWN))) {

            SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
            pawnPromotionService.promotePawn(selectedFigure);
        }
    }

    private void printBoteMove(Field sourceField, Field targetField) {
        System.out.println("####################################");
        System.out.println(Arrays.toString(sourceField.getFieldDesignation()));
        System.out.println(Arrays.toString(targetField.getFieldDesignation()));
        System.out.println("####################################");
    }

}

//
//    public void executeRandomBotMove() {
//
//        List<Figure> botFigures = figureService.getAtTurnFigures();
//        Field bestRatedSourceField = null;
//        Field bestRatedTargetField = null;
//        int rateIndex = 0;
//
//        for(Figure figure : botFigures) {
//
//            Field sourceField = figureService.getFigureField(figure);
//            ValidFields validFields = validFieldService.validateFields(sourceField, figure);
//
//            for(Field targetField : validFields.getFieldList().keySet()) {
//
//                Validation validation = validateService.validateMove(sourceField, targetField);
//
//                if(validation.isState()) {
//
//                    moveExecutorService.executeMove(sourceField, targetField, true);
//
//                    //**//
//                    HashMap<Figure, Map<Field, Integer>> ratedFigures = this.executeMinMaxBot();
//                    int tempRate = this.evaluateRatedFigures(ratedFigures);
//                    //**//
//
//                    if(tempRate >= rateIndex) {
//                        bestRatedSourceField = sourceField;
//                        bestRatedTargetField = targetField;
//                        rateIndex = tempRate;
//                    }
//
//                    moveExecutorService.revertLastMove(true);
//                }
//
//            }
//
//        }
//
//        Validation validation = validateService.validateMove(bestRatedSourceField, bestRatedTargetField);
//
//        this.executeBotMove(bestRatedSourceField, bestRatedTargetField);
//
//
//
////        this.executeRandomBot();
//    }
//
//
////    private void executeRandomBot() {
////        GameState gameState = gameService.getCurrentGameState();
////        List<Figure> botFigures = figureService.getAtTurnFigures();
////
////        boolean moveExecuted = false;
////        while (!moveExecuted && !gameState.isCheckMate() && !gameState.isRemis()) {
////            Figure randomFigure = botFigures.get(new Random().nextInt(botFigures.size()));
////
////            Field sourceField = figureService.getFigureField(randomFigure);
////            ValidFields validFields = validFieldService.validateFields(sourceField, sourceField.getFigure());
////
////            if (this.validateBotMove(sourceField, validFields)) {
////                moveExecuted = true;
////            }
////        }
////
////    }
//
////    private boolean validateBotMove(Field sourceField, ValidFields validFields) {
////
////        if (validFields.getFieldList().size() > 0) {
////            for (Field targetField : validFields.getFieldList().keySet()) {
////
////                Validation validation = validateService.validateMove(sourceField, targetField);
////
////                if (validation.isState()) {
////                    moveExecutorService.executeMove(sourceField, targetField, false);
////
////                    //Check if pawn reaches one of the border and replace it
////                    this.handlePawn(targetField);
////
////                    return true;
////                }
////            }
////        }
////        return false;
////    }
//
//    private void handlePawn(Field targetField) {
//        if (targetField.getFigure().getFigureType().equals(FigureType.PAWN) &&
//                (targetField.getHorizontalNumber() == 1 || targetField.getHorizontalNumber() == 8)) {
//
//            SelectedFigure selectedFigure = new SelectedFigure(FigureType.QUEEN);
//            pawnPromotionService.changePawn(selectedFigure);
//        }
//    }
//
//    private HashMap<Figure, Map<Field, Integer>> executeMinMaxBot() {
//
//        List<Figure> botFigures = figureService.getAtTurnFigures();
//        HashMap<Figure, Map<Field, Integer>> ratedFigures = new HashMap<>();
//
//        for (Figure figure : botFigures) {
//
//            Field sourceField = figureService.getFigureField(figure);
//            ValidFields validFields = validFieldService.validateFields(sourceField, figure);
//            HashMap<Field, Integer> ratedFields = new HashMap<>();
//
//            for (Field targetField : validFields.getFieldList().keySet()) {
//
//                int currentWorthLevel = this.getWorthLevel(targetField);
//                ratedFields.put(targetField, currentWorthLevel);
//            }
//
//            Map<Field, Integer> sortedRatedFields = sortedByFieldRate(ratedFields);
//
//            ratedFigures.put(figure, sortedRatedFields);
//
//        }
//
//        return ratedFigures;
//    }
//
//    private int evaluateRatedFigures(HashMap<Figure, Map<Field, Integer>> ratedFigures) {
//
//        Map<Figure, Map<Field, Integer>> sortedRatedFigure = sortedByFigure(ratedFigures);
////        boolean moveExecuted = false;
//
//        for (Figure figure : sortedRatedFigure.keySet()) {
////            if(moveExecuted) break;
//
//            Field sourceField = figureService.getFigureField(figure);
//
//            for (Field targetField : sortedRatedFigure.get(figure).keySet()) {
////                if(moveExecuted) break;
//
//                Validation validation = validateService.validateMove(sourceField, targetField);
//
//                if (validation.isState()) {
//
////                    this.executeBotMove(figure, targetField);
//                    //return rated value
//                    return sortedRatedFigure.get(figure).get(targetField);
////                    moveExecuted = true;
//                }
//            }
//        }
//        throw new RuntimeException("It must have a valid field for the bot move!");
//    }
//
//    private int getWorthLevel(Field targetField) {
//        Figure targetFigure = targetField.getFigure();
//
//        if (targetFigure != null) {
//            return FIGURE_WORTH.get(targetFigure.getFigureType());
//        } else {
//            return 0;
//        }
//    }
//

//
//
//    public static HashMap<Field, Integer> sortedByFieldRate(HashMap<Field, Integer> hashMap) {
//        // Create a list from elements of HashMap
//        List<Map.Entry<Field, Integer>> list =
//                new LinkedList<Map.Entry<Field, Integer>>(hashMap.entrySet());
//
//        // Sort the list
//        list.sort(new Comparator<Entry<Field, Integer>>() {
//            public int compare(Entry<Field, Integer> o1,
//                               Entry<Field, Integer> o2) {
//                return (o2.getValue()).compareTo(o1.getValue());
//            }
//        });
//
//        // put data from sorted list to hashmap
//        HashMap<Field, Integer> temp = new LinkedHashMap<Field, Integer>();
//        for (Map.Entry<Field, Integer> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//    }
//
//    public static HashMap<Figure, Map<Field, Integer>> sortedByFigure(HashMap<Figure, Map<Field, Integer>> hashMap) {
//        // Create a list from elements of HashMap
//        List<Map.Entry<Figure, Map<Field, Integer>>> list =
//                new LinkedList<Map.Entry<Figure, Map<Field, Integer>>>(hashMap.entrySet());
//
//        // Sort the list
//        list.sort(new Comparator<Entry<Figure, Map<Field, Integer>>>() {
//            public int compare(Entry<Figure, Map<Field, Integer>> o1,
//                               Entry<Figure, Map<Field, Integer>> o2) {
//
//
//                Integer int1 = 0;
//                Integer int2 = 0;
//
//                if (o1.getValue().values().iterator().hasNext()) {
//                    int1 = o1.getValue().values().iterator().next();
//                }
//                if (o2.getValue().values().iterator().hasNext()) {
//                    int2 = o2.getValue().values().iterator().next();
//                }
//
//                return (int2).compareTo(int1);
//            }
//        });
//
//        // put data from sorted list to hashmap
//        HashMap<Figure, Map<Field, Integer>> temp = new LinkedHashMap<Figure, Map<Field, Integer>>();
//        for (Map.Entry<Figure, Map<Field, Integer>> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//    }
//
//}
