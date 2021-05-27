package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Utils;

import android.content.Context;
import android.os.Environment;

public class FilePathUtil {
    public static String getDataFolder() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Data";
    }

    public static String getAppPrivateFolder(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }
}
