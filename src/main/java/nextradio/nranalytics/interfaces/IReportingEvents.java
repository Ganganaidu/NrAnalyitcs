package nextradio.nranalytics.interfaces;

import nextradio.nranalytics.data.ActionPayload;

/**
 * Created by gkondati on 11/3/2017.
 */

public interface IReportingEvents {

    void registerDevice(String advertisingId, String radioSourceName);

    boolean recordListeningReporting(int publicStationID, ActionPayload actionPayload);

    void recordVisualImpression(ActionPayload payload);

    void recordActionImpression(ActionPayload payload, int action, int source);

    void recordSearchQuery(String searchString, int searchResults, int stationID);

    void recordStreamOffset(String streamSource, String trackingID);

    void recordStreamFailure(String streamSource, int failureType, int source);

    void recordLocationData(int source, int action, String latitude, String longitude, String hAccuracy, String ipAddress, String locSpeed);

    void recordNewsFeedReportData(String newsItemId, String newsItemTrackingId, int action, int source);

    void recordAppSession();

    void recordUtcOffset();
}
