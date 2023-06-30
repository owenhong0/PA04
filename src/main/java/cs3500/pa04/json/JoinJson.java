package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "name": "github_username",
 * "game-type": "SINGLE"
 * }
 * </code>
 * </p>
 *
 * @param name name of player
 * @param type game type
 */
public record JoinJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String type
) {
}
