package me.rsls.chessapi.model;

public class History {

    private final Field sourceField, targetField;
    private final Figure movedFigure, killedFigure;
    private final MoveType moveType;
    private final GameState gameState;

    public History(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure, MoveType moveType, GameState gameState) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.movedFigure = movedFigure;
        this.killedFigure = killedFigure;
        this.moveType = moveType;
        this.gameState = gameState;
    }

    public Field getSourceField() {
        return sourceField;
    }

    public Field getTargetField() {
        return targetField;
    }

    public Figure getMovedFigure() {
        return movedFigure;
    }

    public Figure getKilledFigure() {
        return killedFigure;
    }

    public MoveType isMoveType() {
        return moveType;
    }

    public GameState getGameState() {
        return gameState;
    }
}
