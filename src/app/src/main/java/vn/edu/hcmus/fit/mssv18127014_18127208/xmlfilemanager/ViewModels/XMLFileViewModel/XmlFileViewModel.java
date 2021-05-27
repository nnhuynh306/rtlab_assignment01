package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import pub.devrel.easypermissions.EasyPermissions;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.RequestCode;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.XMLParser.InstanceIDParser;

public class XmlFileViewModel extends AndroidViewModel {
    XMLFileRepository xmlFileRepository;

    public XmlFileViewModel(@NonNull @NotNull Application application) {
        super(application);

        xmlFileRepository = new XMLFileRepository(application);
    }

    public ArrayList<String> getXmlFilesName(Context context) {
        return xmlFileRepository.getXmlFilesName(context);
    }

    public void importXMLFile(Context context, String XMLFileName, Handler handler, ProgressBar progressBar) {
        xmlFileRepository.importXMLFile(context, XMLFileName, handler, progressBar);
    }
}
