package nextradio.nranalytics.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import nextradio.nranalytics.interfaces.IDeviceDescriptor;
import nextradio.nranalytics.objects.DeviceState;

public class DeviceDescriptor implements IDeviceDescriptor {
    private static final String TAG = "DeviceDescriptor";


    private Context mContext;
    private DeviceState returnVal;
    private String mTSD = "";

    public DeviceDescriptor(Context context) {
        mContext = context;
    }

    @Override
    public DeviceState getDeviceDescription(String adId, String radioSourceName) {
        if (returnVal != null) {
            return returnVal;
        }
        returnVal = new DeviceState();
        returnVal.setDisplayOn(true);
        returnVal.setBrand(Build.BRAND);
        returnVal.setDevice(Build.DEVICE);
        returnVal.setManufacturer(Build.MANUFACTURER);
        returnVal.setModel(Build.MODEL);
        returnVal.setSystemVersion(Build.FINGERPRINT);
        returnVal.setFmAPI(radioSourceName);
        returnVal.setTagStationID(mTSD);

        String carrierName = "unknown";
        try {
            TelephonyManager manager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (manager != null) {
                carrierName = manager.getNetworkOperatorName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        returnVal.setCarrier(carrierName);
        returnVal.setAdID(adId);

        try {
            String versionName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            returnVal.setAppVersion(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        returnVal.setAdID("error");
        return returnVal;
    }

    @Override
    public void setDeviceID(String deviceID) {
        this.mTSD = deviceID;
    }

    @Override
    public String getDeviceVersionCode() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return String.valueOf(pInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DeviceDescriptor() {

    }
}
