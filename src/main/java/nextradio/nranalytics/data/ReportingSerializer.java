package nextradio.nranalytics.data;

import java.util.ArrayList;

/**
 * Created by gkondati on 10/31/2017.
 */

public class ReportingSerializer {

    public static String serializeDataToCSV(ArrayList<String[]> returnVal) {
        StringBuilder sb = new StringBuilder();
        for (String[] set : returnVal) {
            for (int i = 0; i < set.length - 1; i++) {
                sb.append(set[i]).append(",");
            }
            sb.append(set[set.length - 1]).append("|");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }
}
