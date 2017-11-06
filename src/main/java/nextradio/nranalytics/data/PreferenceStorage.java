package nextradio.nranalytics.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import nextradio.nranalytics.objects.DeviceRegistrationInfo;
import nextradio.nranalytics.objects.DeviceState;

/**
 * Created by gkondati on 11/1/2017.
 */

public class PreferenceStorage {

    private static final String TAG = "PreferenceStorage";

    private static final String DefaultTAGURL = "http://dev-api.tagstation.com/";

    private static final String OVERRIDE_URL = "apiurl";
    private static final String DEVICE_ID = "deviceID";
    private static final String TS_ID = "tsid";
    private static final String CACHING_ID = "cachingID";
    private static final String AD_GROUP = "adGroup";
    private static final String SELECTED_COUNTRY = "country";
    private static final String DEVELOPER_MODE = "enabledev";
    private static final String DEVICE_STRING = "DeviceString";

    private Context mContext;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    /**
     * Saving data in shared preferences which will store life time of Application
     */
    public PreferenceStorage(Context context) {
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public void setDeviceID(String deviceID) {
        prefsEditor.putString(DEVICE_ID, deviceID).apply();
    }

    public String getDeviceID() {
        return appSharedPrefs.getString(DEVICE_ID, "");
    }

    public void setTsId(String tsId) {
        prefsEditor.putString(TS_ID, tsId).apply();
    }

    public String getTSID() {
        return appSharedPrefs.getString(TS_ID, "");
    }

    public void setCachingID(String ID) {
        prefsEditor.putString(CACHING_ID, ID).apply();
    }

    public void setAdGroupID(String ID) {
        prefsEditor.putString(AD_GROUP, ID).apply();
    }

    public DeviceRegistrationInfo getDeviceRegistrationInfo() {
        String tsd = appSharedPrefs.getString(DEVICE_ID, "");
        String ad = appSharedPrefs.getString(AD_GROUP, "");
        String cache = appSharedPrefs.getString(CACHING_ID, "");

        DeviceRegistrationInfo di = new DeviceRegistrationInfo();
        di.setAdGroup(ad);
        di.setCachingGroup(cache);
        di.setTSD(tsd);
        return di;
    }

//    public Location getStationDownloadLocation() {
//        SharedPreferences sharePrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
//
//        double latitude = sharePrefs.getFloat(STATION_DOWNLOAD_LAT, 0);
//        double longitude = sharePrefs.getFloat(STATION_DOWNLOAD_LONG, 0);
//        return new Location(latitude, longitude, 0);
//    }

    public void setDeviceRegistration(DeviceRegistrationInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        prefsEditor
                .putString(DEVICE_ID, deviceInfo.getTSD())
                .putString(CACHING_ID, deviceInfo.getCachingGroup())
                .putString(AD_GROUP, deviceInfo.getAdGroup())
                .apply();
    }

    public String getTagURL() {
        if (appSharedPrefs.getBoolean(DEVELOPER_MODE, false)) {
            String value = appSharedPrefs.getString(OVERRIDE_URL, null);
            if (value != null)
                return value;
            else
                return DefaultTAGURL;
        } else {
            return DefaultTAGURL;
        }
    }

    public void setTagURL(String url) {
        prefsEditor.putString(OVERRIDE_URL, url).apply();
    }

    public String getDeviceString() {
        return appSharedPrefs.getString(DEVICE_STRING, null);
    }


    public void setDeviceString(String newDeviceRegistrationString) {
        prefsEditor.putString(DEVICE_STRING, newDeviceRegistrationString).apply();
    }

    public String getCountryString() {
        return appSharedPrefs.getString(SELECTED_COUNTRY, null);
    }

    public void setCountryString(String countryString) {
        prefsEditor.putString(SELECTED_COUNTRY, countryString).apply();
    }

    public DeviceState getDeviceState() {
        DeviceState di = new DeviceState();

        di.setDevice(appSharedPrefs.getString("device", ""));
        di.setTagStationID(appSharedPrefs.getString("tagStationID", ""));
        di.setCountryCode(appSharedPrefs.getString("countryCode", ""));
        di.setLanguage(appSharedPrefs.getString("language", ""));
        di.setAdID(appSharedPrefs.getString("adID", ""));
        di.setBrand(appSharedPrefs.getString("brand", ""));
        di.setCarrier(appSharedPrefs.getString("carrier", ""));
        di.setFmAPI(appSharedPrefs.getString("fmAPI", ""));
        di.setManufacturer(appSharedPrefs.getString("manufacturer", ""));
        di.setSystemVersion(appSharedPrefs.getString("systemVersion", ""));
        di.setAppVersion(appSharedPrefs.getString("nextRadioVersion", ""));
        di.setModel(appSharedPrefs.getString("model", ""));
        di.setDisplayOn(appSharedPrefs.getBoolean("isDisplayOn", false));

        return di;
    }

    public void setDeviceState(DeviceState deviceState) {
        if (deviceState == null) {
            return;
        }
        prefsEditor
                .putBoolean("isDisplayOn", deviceState.isDisplayOn())
                .putString("device", deviceState.getDevice())
                .putString("tagStationID", deviceState.getTagStationID())
                .putString("countryCode", deviceState.getCountryCode())
                .putString("language", deviceState.getLanguage())
                .putString("adID", deviceState.getAdID())
                .putString("brand", deviceState.getBrand())
                .putString("carrier", deviceState.getCarrier())
                .putString("fmAPI", deviceState.getFmAPI())
                .putString("manufacturer", deviceState.getManufacturer())
                .putString("systemVersion", deviceState.getSystemVersion())
                .putString("nextRadioVersion", deviceState.getAppVersion())
                .putString("model", deviceState.getModel())
                .apply();
    }


    public void saveVisualImpressions(String visualImpressionData) {
        prefsEditor.putString("visual_impression", visualImpressionData).apply();
    }

    public String getVisualImpression() {
        return appSharedPrefs.getString("visual_impression", "");
    }

    public void saveUTCoffSetData(String visualImpressionData) {
        prefsEditor.putString("UTCoffSetData", visualImpressionData).apply();
    }

    public String getUtcOffSetData() {
        return appSharedPrefs.getString("UTCoffSetData", "");
    }

    /**
     * @param date: save UTC date time
     */
    public void saveUtcDateTime(String date) {
        prefsEditor.putString("utcCurrentDate", date).apply();
    }

    /**
     * @return UTC current date time
     */
    public String getUtcDateTime() {
        return appSharedPrefs.getString("utcCurrentDate", "");
    }
}

