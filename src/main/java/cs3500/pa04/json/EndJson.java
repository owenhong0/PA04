package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.GameResult;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 * "result": "WIN",
 * "reason": "Player 1 sank all of Player 2's ships"
 *  }
 * </code>
 * </p>
 *
 * @param result game result
 * @param reason reason for result
 */
public record EndJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {
}
