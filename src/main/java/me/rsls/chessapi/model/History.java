package me.rsls.chessapi.model;

public class History {

    private final Field sourceField, targetField;
    private final Figure movedFigure, killedFigure;
    private final boolean pawnChange;
    private final GameState lastGameState;

    public History(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure, boolean pawnChange, GameState lastGameState) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.movedFigure = movedFigure;
        this.killedFigure = killedFigure;
        this.pawnChange = pawnChange;
        this.lastGameState = lastGameState;
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

    public boolean isPawnChange() {
        return pawnChange;
    }

    public GameState getLastGameState() {
        return lastGameState;
    }
}
