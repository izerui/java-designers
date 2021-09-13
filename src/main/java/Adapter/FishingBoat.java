package Adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * Device class (adaptee in the pattern). We want to reuse this class. Fishing boat moves by
 * sailing.
 */
@Slf4j
final class FishingBoat {

  void sail() {
    LOGGER.info("The fishing boat is sailing");
  }

}
