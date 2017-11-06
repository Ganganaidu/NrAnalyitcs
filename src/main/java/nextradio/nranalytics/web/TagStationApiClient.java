package nextradio.nranalytics.web;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

import io.reactivex.Observable;
import nextradio.nranalytics.interfaces.IRestClient;
import nextradio.nranalytics.interfaces.ITagStationAPIClient;
import nextradio.nranalytics.objects.DeviceRegistrationInfo;
import nextradio.nranalytics.objects.DeviceState;


/**
 * Created by gkondati on 11/1/2017.
 */

public class TagStationApiClient implements ITagStationAPIClient {

    private static final String RegisterDevice = "%sapi/v2.0/device/register";

    private static final String VERSION = "2";
    // Important! do not change this line or the build will fail. It will do a find/replace on
    // the specific text. If you do have to change this line, be sure to update the build script as
    // well.
    private static String CLIENT_VERSION = null == null ? "99999" : "{TEAMCITYBUILDNUMBER}";
    private static final String CLIENT_NAME = "nr_android";

    //only for testing purpose
    private IRestClient injectedRestClient = null;
    private String mCachingID;
    private String mHostURL;
    private String mDeviceID;
    private boolean isTestRunning;

    public TagStationApiClient withRestClient(IRestClient client) {
        injectedRestClient = client;
        return this;
    }

    public TagStationApiClient withCachingID(String ID) {
        mCachingID = ID;
        return this;
    }

    public TagStationApiClient withAPIHost(String hostURL) {
        mHostURL = hostURL;
        return this;
    }

    public TagStationApiClient withDeviceID(String deviceID) {
        mDeviceID = deviceID;
        return this;
    }

    public TagStationApiClient withDeviceRegistrationInfo(DeviceRegistrationInfo di) {
        mCachingID = di.getCachingGroup();
        mDeviceID = di.getTSD();
        return this;
    }

    public TagStationApiClient withTestIntegration(boolean testRunning) {
        isTestRunning = testRunning;
        return this;
    }

    /**
     * Adds required parameters to the request. TagStation is expecting these fields to be present
     * on all requests.
     *
     * @param restClient the current request client
     */
    private void stampRequest(IRestClient restClient) {
        restClient.AddParam("tsd", mDeviceID);
        restClient.AddParam("version", VERSION + "");
        restClient.AddParam("client_version", CLIENT_VERSION);
        restClient.AddParam("client_name", CLIENT_NAME);
        restClient.AddParam("CachingGroup", mCachingID);

        addLanguageHeader(restClient);
    }

    /**
     * Adds required parameters to the request. TagStation is expecting these fields to be present
     * on all requests.
     *
     * @param restClient the current request client
     */
    private void stampRequestAsHeaders(IRestClient restClient) {
        if (isTestRunning) {//TODO need to change this later
            stampRequest(restClient);
            return;
        }
        restClient.AddHeader("tsd", mDeviceID);
        restClient.AddHeader("version", VERSION + "");
        restClient.AddHeader("client_version", CLIENT_VERSION);
        restClient.AddHeader("client_name", CLIENT_NAME);
        restClient.AddHeader("ts_caching_group", mCachingID);

        addLanguageHeader(restClient);
    }

    //add language parameter
    private void addLanguageHeader(IRestClient restClient) {
        //Log.d(TAG, "addLanguageHeader: " + Locale.getDefault().getLanguage());
        restClient.AddHeader("Accept-Language", Locale.getDefault().getLanguage());
    }

    @Override
    public Observable<DeviceRegistrationInfo> registerDevice(final DeviceState deviceState) {
        return Observable.fromCallable(() -> register(deviceState));
    }

    @Override
    public boolean report(String key, String serializedData, String batchName, String batchId, DeviceState deviceState) {
        return false;
    }

    private DeviceRegistrationInfo register(DeviceState deviceState) {
        IRestClient restClient;
        boolean isRegistering;
        if (injectedRestClient == null) {
            restClient = new RestClient2(mHostURL, isTestRunning);
        } else {
            restClient = injectedRestClient;
        }
        restClient.setURL(String.format(RegisterDevice, mHostURL));
        restClient.ClearParams();
        restClient.AddParam("brand", deviceState.getBrand());
        restClient.AddParam("device", deviceState.getDevice());
        restClient.AddParam("manufacturer", deviceState.getManufacturer());
        restClient.AddParam("model", deviceState.getModel());
        restClient.AddParam("carrier", deviceState.getCarrier());
        restClient.AddParam("other_info", deviceState.getOtherInfo());
        restClient.AddParam("fmapi", deviceState.getFmAPI());
        restClient.AddParam("country", deviceState.getCountryCode());
        restClient.AddParam("locale", deviceState.getLanguage());
        restClient.AddParam("ad_id", deviceState.getAdID());
        restClient.AddParam("system_version", deviceState.getSystemVersion());
        restClient.AddParam("nextradio_version", deviceState.getAppVersion());

        stampRequestAsHeaders(restClient);
        restClient.AddHeader("Accept", "application/json");

        String response;
        DeviceRegistrationInfo item;

        try {
            if (mDeviceID == null || mDeviceID.isEmpty() || mDeviceID.equals("N/A")) {
                response = restClient.ExecuteForString(RequestMethod.POST);
                isRegistering = true;
            } else {
                response = restClient.ExecuteForString(RequestMethod.PUT);
                isRegistering = false;
            }
            Log.d("TSL", "responseXML(): " + response);
            item = buildGson().fromJson(response, DeviceRegistrationInfo.class);
            item.setRegisteringFirstTime(isRegistering);
            return item;

        } catch (Exception e) {
            Log.d("TSL", "error getting device registration");
            e.printStackTrace();
        }
        return null;
    }

    private Gson buildGson() {
        return new GsonBuilder().create();
    }
}
