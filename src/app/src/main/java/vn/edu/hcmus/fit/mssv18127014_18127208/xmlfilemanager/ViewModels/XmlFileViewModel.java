package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import pub.devrel.easypermissions.EasyPermissions;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.RequestCode;

public class XmlFileViewModel extends ViewModel {

    public XmlFileViewModel() {
        super();


    }

    public ArrayList<String> getXmlFilesName(Context context) {
        File dataDirectory = new File(getDataFolder());
        String[] filesName = dataDirectory.list();

        if (filesName == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(filesName));
        }
    }

    private String getDataFolder() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Data/";
    }
}
