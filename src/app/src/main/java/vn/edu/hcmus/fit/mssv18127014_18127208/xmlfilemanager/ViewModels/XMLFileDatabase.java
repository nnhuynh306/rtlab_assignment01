package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel.XMLFileDao;

@Database(entities = {XMLFile.class}, version = 1)
public abstract class XMLFileDatabase extends RoomDatabase {
    private static XMLFileDatabase instance = null;

    public abstract XMLFileDao xmlFileDao();

    public static XMLFileDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    XMLFileDatabase.class, "XMLFileDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
