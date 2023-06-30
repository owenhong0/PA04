package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * [
 *    {"x": 4, "y": 2},
 *    {"x": 7, "y": 1}
 * ]
 * </code>
 * </p>
 *
 * @param coordinates game result
 */
public record VolleyJson(
    @JsonProperty() CoordJson[] coordinates) {
}
