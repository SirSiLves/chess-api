package me.rsls.chessapi.model;

public class History {

    private final Field sourceField, targetField;
    private final Figure movedFigure, killedFigure;
    private final boolean pawnChange;

    public History(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure, boolean pawnChange) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.movedFigure = movedFigure;
        this.killedFigure = killedFigure;
        this.pawnChange = pawnChange;
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
}
