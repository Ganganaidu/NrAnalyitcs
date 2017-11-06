package nextradio.nranalytics.interfaces;

import java.util.ArrayList;

import nextradio.nranalytics.data.ActionPayload;

public interface IDatabaseAdapter {

    boolean recordListeningReporting(int publicStationID, ActionPayload actionPayload);

    void recordVisualImpression(ActionPayload payload);

    void recordActionImpression(ActionPayload payload, int action, int source);

    void recordUtcOffset(String serverOffset);

    void recordSearchQuery(String searchString, int searchResults, int stationID);

    void recordStreamOffset(String streamSource, String trackingID);

    void recordStreamFailure(String streamSource, int failureType, int source);

    void recordLocationData(int source, int action, String latitude, String longitude, String hAccuracy, String ipAddress, String locSpeed);

    void recordNewsFeedReportData(String newsItemId, String newsItemTrackingId, int action, int source);

    void recordAppSession();

    ArrayList<String[]> getListeningReporting(String batchId);

    ArrayList<String[]> getImpressionData(String batchId);

    ArrayList<String[]> getVisualImpressionData(String batchId);

    ArrayList<String[]> getUtcOffsetData(String batchId);

    ArrayList<String[]> getLocationTrackData(String batchId);

    ArrayList<String[]> getAppSessionData(String batchId);

    ArrayList<String[]> getSearchQuries(String batchID);

    ArrayList<String[]> getStreamOffsets(String batchID);

    ArrayList<String[]> getStreamFailures(String batchID);

    ArrayList<String[]> getNewsFeedReportData(String batchId);


    void clearListeningHistory();

    void clearStationReporting();

    void clearVisualImpressionData(String batchId);

    void clearLocationData(String batchId);

    void clearUtcOffsetData(String batchId);

    void clearSearchQuries(String batchID);

    void clearStreamOffsets(String batchID);

    void closeOpenStationReporting(boolean forceUpdateAll);

    void clearImpressionData(String batchId);

    void clearClosedStationReporting(String batchId1);

    void clearAppSessionData(String batchId1);

    void clearStreamFailures(String batchID);

    void clearNewsFeed();

    void clearNewsFeedReportData(String batchId);

}
