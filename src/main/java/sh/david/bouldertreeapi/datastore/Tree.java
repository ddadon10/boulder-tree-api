package sh.david.bouldertreeapi.datastore;

import java.util.List;

public class Tree {
    private int id;
    private String commonName;
    private String latinName;
    private Species species;
    private Genus genus;
    private Leaf leaf;
    private Dimensions dimensions;
    private List<Form> formList;
    private String flower;
    private String fruit;
    private WaterNeed waterNeed;

    Tree() {
        // Todo: Implement Constructor;
    }

}

class Dimensions {
    private final int maxHeight;
    private final int minWidth;
    private final int minHeight;
    private final int maxWidth;

    public int getMinHeight() {
        return this.minHeight;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    Dimensions(int minHeight, int maxHeight, int minWidth, int maxWidth) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
    }
}

enum Form {
    OVAL, ROUNDED, PYRIMIDAL, IRREGULAR, VASE, UPRIGHT;
}

enum WaterNeed {
    LOW, LOW_TO_MEDIUM, MEDIUM, MEDIUM_TO_HIGH, HIGH;
}