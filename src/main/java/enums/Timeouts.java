package enums;

import java.time.Duration;

/**
 * @author Ajay Talpur
 */
public enum Timeouts {

    THREE_SECONDS(Duration.ofSeconds(3)),
    FIVE_SECONDS(Duration.ofSeconds(5)),
    TEN_SECONDS(Duration.ofSeconds(10));

    private Duration duration;

    Timeouts(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
