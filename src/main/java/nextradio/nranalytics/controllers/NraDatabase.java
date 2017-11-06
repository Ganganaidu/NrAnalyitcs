package nextradio.nranalytics.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import nextradio.nranalytics.data.ActionPayload;
import nextradio.nranalytics.data.DateFormats;
import nextradio.nranalytics.data.GsonConverter;
import nextradio.nranalytics.data.PreferenceStorage;
import nextradio.nranalytics.interfaces.IDatabaseAdapter;

/**
 * Created by gkondati on 11/1/2017.
 */

public class NraDatabase implements IDatabaseAdapter {

    private Context mContext;
    private PreferenceStorage mPrefStorage;

    NraDatabase(Context context, PreferenceStorage prefStorage) {
        this.mContext = context;
        this.mPrefStorage = prefStorage;
    }

    @Override
    public boolean recordListeningReporting(int publicStationID, ActionPayload actionPayload) {
        return false;
    }

    @Override
    public void recordVisualImpression(ActionPayload payload) {
        String savedValues = mPrefStorage.getVisualImpression();
        String data = GsonConverter.getInstance().convertObetToString(savedValues, payload);
        mPrefStorage.saveVisualImpressions(data);
    }

    @Override
    public void recordActionImpression(ActionPayload payload, int action, int source) {

    }

    @Override
    public void recordUtcOffset(String serverOffset) {
        String savedValues = mPrefStorage.getUtcOffSetData();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String currentUTCString = DateFormats.iso8601FormatUTC(cal.getTime());
        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("device_utc_time", currentUTCString);
        jsonMap.put("server_offset", serverOffset);
        jsonMap.put("batchID", "");

        String data = GsonConverter.getInstance().createMapToString(savedValues, jsonMap);
        mPrefStorage.saveUTCoffSetData(data);
    }

    @Override
    public void recordSearchQuery(String searchString, int searchResults, int stationID) {

    }

    @Override
    public void recordStreamOffset(String streamSource, String trackingID) {

    }

    @Override
    public void recordStreamFailure(String streamSource, int failureType, int source) {

    }

    @Override
    public void recordLocationData(int source, int action, String latitude, String longitude, String hAccuracy, String ipAddress, String locSpeed) {

    }

    @Override
    public void recordNewsFeedReportData(String newsItemId, String newsItemTrackingId, int action, int source) {

    }

    @Override
    public void recordAppSession() {

    }

    @Override
    public ArrayList<String[]> getListeningReporting(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getImpressionData(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getVisualImpressionData(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getUtcOffsetData(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getLocationTrackData(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getAppSessionData(String batchId) {
        return null;
    }

    @Override
    public ArrayList<String[]> getSearchQuries(String batchID) {
        return null;
    }

    @Override
    public ArrayList<String[]> getStreamOffsets(String batchID) {
        return null;
    }

    @Override
    public ArrayList<String[]> getStreamFailures(String batchID) {
        return null;
    }

    @Override
    public ArrayList<String[]> getNewsFeedReportData(String batchId) {
        return null;
    }

    @Override
    public void clearListeningHistory() {

    }

    @Override
    public void clearStationReporting() {

    }

    @Override
    public void clearVisualImpressionData(String batchId) {

    }

    @Override
    public void clearLocationData(String batchId) {

    }

    @Override
    public void clearUtcOffsetData(String batchId) {

    }

    @Override
    public void clearSearchQuries(String batchID) {

    }

    @Override
    public void clearStreamOffsets(String batchID) {

    }

    @Override
    public void closeOpenStationReporting(boolean forceUpdateAll) {

    }

    @Override
    public void clearImpressionData(String batchId) {

    }

    @Override
    public void clearClosedStationReporting(String batchId1) {

    }

    @Override
    public void clearAppSessionData(String batchId1) {

    }

    @Override
    public void clearStreamFailures(String batchID) {

    }

    @Override
    public void clearNewsFeed() {

    }

    @Override
    public void clearNewsFeedReportData(String batchId) {

    }
}
