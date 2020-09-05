package sh.david.bouldertreeapi.datastore;

import java.util.List;

public class Leaf {

  private LeafCycle leafCycle;
  private LeafType leafType;
  private List<LeafFallColor> leafFallColorList;

  Leaf(String leafCycle, String leafType, String LeafFallColors) {
    if (leafCycle != null) {
      this.leafCycle = LeafCycle.valueOf(leafCycle.toUpperCase());
    }
    if (leafType != null) {
      this.leafType = LeafType.valueOf(leafType.toUpperCase());
    }

    // TODO: Implement extraction of fall color

  }

  public LeafCycle getLeafCycle() {
    return this.leafCycle;
  }

  public LeafType getLeafType() {
    return this.leafType;
  }

}

enum LeafCycle {
  EVERGREEN, DECIDUOUS;
}

enum LeafType {
  NEEDLELEAVED, BROADLEAVED
}

enum LeafFallColor {
  ORANGE, RED, YELLOW, PURPLE, BRONZE,
}
