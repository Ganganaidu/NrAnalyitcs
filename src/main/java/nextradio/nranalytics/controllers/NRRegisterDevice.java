package nextradio.nranalytics.controllers;

import android.util.Log;

import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import nextradio.nranalytics.data.PreferenceStorage;
import nextradio.nranalytics.interfaces.IDeviceDescriptor;
import nextradio.nranalytics.interfaces.ITagStationAPIClient;
import nextradio.nranalytics.objects.DeviceState;

/**
 * Created by gkondati on 11/3/2017.
 */

class NRRegisterDevice {
    private static final String TAG = "NRRegisterDevice";

    private PreferenceStorage mPrefStorage;
    private ITagStationAPIClient tagStationApiClient;
    private IDeviceDescriptor deviceDescriptor;

    NRRegisterDevice(PreferenceStorage preferenceStorage, ITagStationAPIClient tagStationApiClient, IDeviceDescriptor deviceDescriptor) {
        mPrefStorage = preferenceStorage;
        this.tagStationApiClient = tagStationApiClient;
        this.deviceDescriptor = deviceDescriptor;
    }

    /**
     * The method registers the device with the TAG service and generates
     * a device ID in the process
     *
     * @param advertisingId   : device ad id for example Google ad id for google supported devices.
     * @param radioSourceName : send "unknown" if no radio source
     * @return - the String representation of the device ID.
     */
    void registerDevice(String advertisingId, String radioSourceName) {
        String lastDeviceRegistrationString = mPrefStorage.getDeviceString();
        DeviceState deviceState = getDeviceState(advertisingId, radioSourceName);
        String newDeviceRegistrationString = deviceState.getUpdateString();
        String adID = deviceState.getAdID();

        if (!deviceState.equals()) {//do not try to register if device default values are not available
            Log.d("TSL", "No device found with default values");
        } else if (lastDeviceRegistrationString != null && adID != null && adID.equals("error") && adID.length() == 0) {
            // don't attempt to re-register if the adID is an error.
            Log.d("TSL", "adID is an error");
        } else if (!isFullyRegistered() || lastDeviceRegistrationString == null || !lastDeviceRegistrationString.equals(newDeviceRegistrationString) || mPrefStorage.getTSID().isEmpty()) {

            tagStationApiClient.registerDevice(deviceState)
                    .subscribeOn(Schedulers.io())
                    .subscribe(deviceInfo -> {
                        if (deviceInfo != null && deviceInfo.getTSD() != null && deviceInfo.getTSD().length() > 0) {
                            mPrefStorage.setDeviceRegistration(deviceInfo);
                            mPrefStorage.setTsId(deviceInfo.getTSD());
                            mPrefStorage.setDeviceString(newDeviceRegistrationString);
                            Log.w("TSL", "registration completed");
                        } else {
                            Log.w("TSL", "onRegisterFailed");
                        }
                    }, Throwable::printStackTrace);
        } else {
            Log.d(TAG, "registerDevice: ");
        }
    }

    private DeviceState getDeviceState(String advertisingId, String radioSourceName) {
        DeviceState deviceState = mPrefStorage.getDeviceState();
        if (!deviceState.equals()) {
            getDeviceInfo(advertisingId, radioSourceName);
            deviceState = mPrefStorage.getDeviceState();
        }
        deviceState.setAppVersion(deviceDescriptor.getDeviceVersionCode());
        return deviceState;
    }

    /**
     * save device info for registration info
     */
    private void getDeviceInfo(String advertisingId, String radioSourceName) {
        if (mPrefStorage.getTSID().isEmpty()) {
            Flowable.fromCallable(() -> deviceDescriptor.getDeviceDescription(advertisingId, radioSourceName))
                    .subscribeOn(Schedulers.io())
                    .subscribe(deviceState -> {
                        //deviceState.countryCode = TelephonyUtils.getInstance().loadCountryDetails(context);
                        deviceState.setCountryCode(Locale.getDefault().getISO3Country());
                        deviceState.setLanguage(Locale.getDefault().toString());
                        mPrefStorage.setDeviceState(deviceState);

                    }, Throwable::printStackTrace);
        }
    }

    private boolean isFullyRegistered() {
        String deviceId = mPrefStorage.getDeviceString();
        return deviceId != null && deviceId.length() > 0;
    }
}
