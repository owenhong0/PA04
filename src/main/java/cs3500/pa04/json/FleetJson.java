package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * [
 *   {
 *   "coord": {"x": 0, "y": 0},
 *   "length": 6,
 *   "direction": "VERTICAL"
 *   },
 *   {
 *   "coord": {"x": 1, "y": 0},
 *   "length": 5,
 *   "direction": "VERTICAL"
 *    }
 * ]
 * </code>
 * </p>
 *
 * @param fleet list of ships
 */
public record FleetJson(
    @JsonProperty() ShipJson[] fleet) {
}
