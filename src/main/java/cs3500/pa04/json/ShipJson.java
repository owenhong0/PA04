package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *"coord": {"x": 0, "y": 0},
 *"length": 4,
 *"direction": "VERTICAL"
 * }
 * </code>
 * </p>
 *
 * @param coordJson starting coordinate
 * @param length length of ship
 * @param direction orientation of ship
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coordJson,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
