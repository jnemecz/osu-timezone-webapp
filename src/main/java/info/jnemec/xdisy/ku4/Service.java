package info.jnemec.xdisy.ku4;

import java.util.*;

/**
 * Created by jirka on 10.11.14.
 */
public class Service implements IService {

    private HashMap<String, TimeZone> timeZones;

    public Service() {
        timeZones = new HashMap<String, TimeZone>();
    }

    @Override
    public ActualTime get(String timeZoneId) {
        if (isValid(timeZoneId) && timeZones.containsKey(timeZoneId)) {
            return ActualTime.create(timeZones.get(timeZoneId));
        }
        return null;
    }

    @Override
    public int clear() {
        this.timeZones.clear();
        return timeZones.size();
    }

    @Override
    public ArrayList<ActualTime> getActive() {
        ArrayList<ActualTime> times = new ArrayList<ActualTime>();
        for (Map.Entry<String, TimeZone> entry : timeZones.entrySet()) {
            times.add(ActualTime.create(entry.getValue()));
        }
        return times;
    }

    @Override
    public TimeZone add(String timeZoneId) {

        if (!isValid(timeZoneId)) {
            throw new IllegalArgumentException("Časová zóna \"" + timeZoneId + "\" neexistuje.");
        }

        TimeZone tz = TimeZone.getTimeZone(timeZoneId);

        if (!timeZones.containsKey(tz.getID())) {
            timeZones.put(tz.getID(), tz);
        }

        return tz;
    }

    /**
     * Overuje, jestli casova zona podle ID existuje.
     *
     * @param timeZoneId
     * @return true pokud existuje, jinak false
     */
    private boolean isValid(String timeZoneId) {

        if (timeZoneId == null || timeZoneId.trim().isEmpty()) {
            throw new IllegalArgumentException("Casova zona musi byt vyplnena");
        }

        String[] tzs = TimeZone.getAvailableIDs();

        for (int i = 0; i < tzs.length; i++) {

            System.out.println(tzs[i]);

            if (tzs[i].equalsIgnoreCase(timeZoneId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<ActualTime> getRandom() {

        ArrayList<ActualTime> times = new ArrayList<ActualTime>();
        Random rand = new Random();
        String[] tzs = {"PST", "Pacific/Midway", "Pacific/Samoa", "US/Samoa", "Pacific/Honolulu", "US/Hawaii", "Pacific/Marquesas", "US/Alaska", "America/Los_Angeles", "Canada/Pacific",
                "Canada/Yukon", "US/Arizona", "US/Mountain", "America/Chicago", "America/Mexico_City", "Canada/Central", "America/Montreal", "America/New_York", "America/Toronto", "Cuba", "America/Dominica", "America/Santiago", "GMT", "UTC", "CET", "Europe/Berlin", "Europe/Bratislava", "Europe/Paris", "Europe/Prague", "Asia/Novokuznetsk", "Asia/Jayapura", "Australia/Brisbane", "Pacific/Wake", "Pacific/Tongatapu", "Pacific/Kiritimati"};

        int randomNum;

        do {
            randomNum = rand.nextInt(tzs.length + 1);
            TimeZone tz = TimeZone.getTimeZone(tzs[randomNum]);
            times.add(ActualTime.create(tz));
        } while (times.size() < 10);

        return times;

    }

}
