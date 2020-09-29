package me.rsls.chessapi.model;

public class Rating implements Comparable<Rating> {

    private final Field sourceField, targetField;
    private final int ratingValue;

    public Rating(Field sourceField, Field targetField, int ratingValue) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.ratingValue = ratingValue;
    }

    public Field getSourceField() {
        return sourceField;
    }

    public Field getTargetField() {
        return targetField;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    @Override
    public int compareTo(Rating o) {
        if (this.getRatingValue() == o.getRatingValue()) {
            return 0;
        } else if (this.getRatingValue() < o.getRatingValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}
