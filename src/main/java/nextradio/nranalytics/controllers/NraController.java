package nextradio.nranalytics.controllers;

import android.content.Context;

import nextradio.nranalytics.data.ActionPayload;
import nextradio.nranalytics.data.DeviceDescriptor;
import nextradio.nranalytics.data.PreferenceStorage;
import nextradio.nranalytics.interfaces.IDeviceDescriptor;
import nextradio.nranalytics.interfaces.IReportingEvents;
import nextradio.nranalytics.interfaces.ITagStationAPIClient;
import nextradio.nranalytics.utils.DateUtils;
import nextradio.nranalytics.web.TagStationApiClient;

/**
 * Created by gkondati on 11/1/2017.
 */

public class NraController implements IReportingEvents {

    private static final String TAG = "NraController";

    private static NraController instance;

    private Context context;
    private PreferenceStorage mPrefStorage;
    private ITagStationAPIClient tagStationApiClient;
    private IDeviceDescriptor deviceDescriptor;
    private NRRegisterDevice nrRegisterDevice;
    private NraDatabase nraDatabase;


    public static NraController getInstance() {
        if (instance == null) {
            instance = new NraController();
        }
        return instance;
    }

    public NraController init(Context context) {
        this.context = context;
        mPrefStorage = new PreferenceStorage(context);
        tagStationApiClient = new TagStationApiClient()
                .withAPIHost(mPrefStorage.getTagURL());
        deviceDescriptor = new DeviceDescriptor(context);
        nraDatabase = new NraDatabase(context, mPrefStorage);
        nrRegisterDevice = new NRRegisterDevice(mPrefStorage, tagStationApiClient, deviceDescriptor);
        return this;
    }


    public void registerDevice(String advertisingId, String radioSourceName) {
        nrRegisterDevice.registerDevice(advertisingId, radioSourceName);
    }

    @Override
    public boolean recordListeningReporting(int publicStationID, ActionPayload actionPayload) {
        return nraDatabase.recordListeningReporting(publicStationID, actionPayload);
    }

    @Override
    public void recordVisualImpression(ActionPayload payload) {
        nraDatabase.recordVisualImpression(payload);
    }

    @Override
    public void recordActionImpression(ActionPayload payload, int action, int source) {
        nraDatabase.recordActionImpression(payload, action, source);
    }

    @Override
    public void recordUtcOffset() {
        //need to send UTC offset data only once per day
        if (!mPrefStorage.getUtcDateTime().equals(DateUtils.getCurrentDate())) {
            //saving utc offset
            long offSet = DateUtils.getUTCTime();
            if (offSet == -1) {
                return;
            }
            mPrefStorage.saveUtcDateTime(DateUtils.getCurrentDate());
            nraDatabase.recordUtcOffset(String.valueOf(offSet));
        }
    }

    @Override
    public void recordSearchQuery(String searchString, int searchResults, int stationID) {
        nraDatabase.recordSearchQuery(searchString, searchResults, stationID);
    }

    @Override
    public void recordStreamOffset(String streamSource, String trackingID) {
        nraDatabase.recordStreamOffset(streamSource, trackingID);
    }

    @Override
    public void recordStreamFailure(String streamSource, int failureType, int source) {
        nraDatabase.recordStreamFailure(streamSource, failureType, source);
    }

    @Override
    public void recordLocationData(int source, int action, String latitude, String longitude, String hAccuracy, String ipAddress, String locSpeed) {
        nraDatabase.recordLocationData(source, action, latitude, longitude, hAccuracy, ipAddress, locSpeed);
    }

    @Override
    public void recordNewsFeedReportData(String newsItemId, String newsItemTrackingId, int action, int source) {
        nraDatabase.recordNewsFeedReportData(newsItemId, newsItemTrackingId, action, source);
    }

    @Override
    public void recordAppSession() {
        nraDatabase.recordAppSession();
    }
}
