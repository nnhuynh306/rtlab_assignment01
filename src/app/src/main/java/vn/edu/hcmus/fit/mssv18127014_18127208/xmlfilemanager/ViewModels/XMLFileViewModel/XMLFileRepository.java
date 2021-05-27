package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;

import org.apache.commons.io.FileUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Codes.ErrorCode;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Utils.FilePathUtil;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileDatabase;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.XMLParser.InstanceIDParser;

public class XMLFileRepository {

    private XMLFileDao xmlFileDao;
    private LiveData<List<XMLFile>> xmlFileListLiveData;

    public XMLFileRepository(Application application) {
        XMLFileDatabase xmlFileDatabase = XMLFileDatabase.getInstance(application);
        this.xmlFileDao = xmlFileDatabase.xmlFileDao();
        xmlFileListLiveData = this.xmlFileDao.getAllXMLFiles();
    }

    public LiveData<List<XMLFile>> getXmlFileListLiveData() {
        return xmlFileListLiveData;
    }

    public ArrayList<String> getXmlFilesName(Context context) {
        File dataDirectory = new File(FilePathUtil.getDataFolder());
        String[] filesName = dataDirectory.list();

        if (filesName == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(filesName));
        }
    }

    public void importXMLFile(Context context, String XMLFileName, Handler handler, ProgressBar progressBar) {
        String XMLFileAbsolutePath = FilePathUtil.getDataFolder() + "/" + XMLFileName;
        String XMLFileDestinationAbsolutePath = FilePathUtil.getAppPrivateFolder(context) + "/official-data/" + XMLFileName;
        new ImportXMLFileAyncTask(progressBar, handler).execute(
                XMLFileAbsolutePath, XMLFileDestinationAbsolutePath
        );
    }

    private class ImportXMLFileAyncTask extends AsyncTask<String, Integer, Void> {
        private ProgressBar progressBar;
        private Handler handler;

        public ImportXMLFileAyncTask(ProgressBar progressBar, Handler handler) {
            this.progressBar = progressBar;
            this.handler = handler;
        }

        @Override
        protected Void doInBackground(String... strings) {
            if (strings.length <= 1) {
                handler.sendEmptyMessage(0);
                return null;
            }

            File importingXMLFile = new File(strings[0]);
            try {

                FileInputStream importingXMLInputStream = new FileInputStream(importingXMLFile);
                InstanceIDParser xmlParser = new InstanceIDParser();
                String instanceID = xmlParser.parse(importingXMLInputStream);
                publishProgress(33);

                XMLFile insertingXMLFile = new XMLFile(instanceID, strings[1]);
                xmlFileDao.insert(insertingXMLFile);

                publishProgress(66);

                File destination = new File(strings[1]);
                try
                {
                    FileUtils.copyFile(importingXMLFile, destination);
                    publishProgress(100);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    this.handler.sendEmptyMessage(ErrorCode.copyFileFail);
                    return null;
                }

                handler.sendEmptyMessage(1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                this.handler.sendEmptyMessage(ErrorCode.fileNotFound);
                return null;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                this.handler.sendEmptyMessage(ErrorCode.parseXMLFail);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                this.handler.sendEmptyMessage(ErrorCode.parseXMLFail);
                return null;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values.length > 0) {
                progressBar.setProgress(values[0]);
            }
        }
    }

}
