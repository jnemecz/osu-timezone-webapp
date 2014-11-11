package info.jnemec.xdisy.ku4;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

/**
 * Created by jirka on 10.11.14.
 */
@JsonSerialize(using = ActualTimeSerializer.class)
public class ActualTime {

    private TimeZone timeZone;
    private DateTime time;

    public ActualTime(TimeZone timeZone, DateTime time) {
        this.timeZone = timeZone;
        this.time = time;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public static ActualTime create(TimeZone timeZone) {
        if (timeZone != null) {
            DateTimeZone dtz = DateTimeZone.forTimeZone(timeZone);
            DateTime dt = DateTime.now(dtz);
            ActualTime at = new ActualTime(timeZone, dt);
            return at;
        }
        return null;
    }

}
