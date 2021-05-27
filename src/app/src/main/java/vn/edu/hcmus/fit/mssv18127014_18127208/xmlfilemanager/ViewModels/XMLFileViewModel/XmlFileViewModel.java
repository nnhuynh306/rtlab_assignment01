package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel;

import android.app.Application;
import android.content.Context;

import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;


public class XmlFileViewModel extends AndroidViewModel {
    XMLFileRepository xmlFileRepository;

    LiveData<List<XMLFile>> xmlFileListLiveData;

    public XmlFileViewModel(@NonNull @NotNull Application application) {
        super(application);
        xmlFileRepository = new XMLFileRepository(application);
    }

    public LiveData<List<XMLFile>> getXmlFileListLiveData() {
        return xmlFileRepository.getXmlFileListLiveData();
    }

    public ArrayList<String> getXmlFilesName(Context context) {
        return xmlFileRepository.getXmlFilesName(context);
    }

    public void importXMLFile(Context context, String XMLFileName, Handler handler, ProgressBar progressBar) {
        xmlFileRepository.importXMLFile(context, XMLFileName, handler, progressBar);
    }
}
