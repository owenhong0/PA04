package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty; // import Jackson library

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "x": 0,
 * "y": 4
 * }
 * </code>
 * </p>
 *
 * @param x column coordinate
 * @param y row coordinate
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
