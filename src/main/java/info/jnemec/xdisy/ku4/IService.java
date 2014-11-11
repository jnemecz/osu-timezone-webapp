package info.jnemec.xdisy.ku4;

import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by jirka on 11.11.14.
 */
public interface IService {

    /**
     * Vytvori aktualni cas pro danou casovou zonu.
     *
     * @param timeZoneId
     * @return
     */
    ActualTime get(String timeZoneId);

    /**
     * Odstrani vsechny vytvorene casove zony.
     *
     * @return
     */
    int clear();

    /**
     * Vrati seznam casu podle vytvorenych casovych zon.
     *
     * @return
     */
    ArrayList<ActualTime> getActive();

    /**
     * Prida novou casovou zonu podle ID.
     *
     * @param timeZoneId
     * @return
     */
    TimeZone add(String timeZoneId);

    /**
     * Vrati casy podle nahodne vybranych casovych zon.
     *
     * @return
     */
    ArrayList<ActualTime> getRandom();

}
