package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

@Entity(tableName = "XMLFiles")
public class XMLFile {

    @NonNull
    @PrimaryKey
    private String instanceID;

    private String filePath;

    public XMLFile(@NonNull String instanceID, String filePath) {
        this.instanceID = instanceID;
        this.filePath = filePath;
    }

    public String getInstanceID() {
        return instanceID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
