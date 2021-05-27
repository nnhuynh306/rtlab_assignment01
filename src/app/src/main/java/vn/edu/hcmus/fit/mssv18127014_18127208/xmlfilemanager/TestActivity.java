package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Codes.RequestCode;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.request_permission)
                    , RequestCode.READ_EXTERNAL_STORAGE, perms);
        }

        File dataDirectory = new File(getDataFolder());

        String[] xmlFilesName = dataDirectory.list();
    }

    private String getDataFolder() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Data/";
    }
}