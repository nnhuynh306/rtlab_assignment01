package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;

@Dao
public interface XMLFileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(XMLFile xmlFile);

    @Update
    void update(XMLFile xmlFile);

    @Delete
    void delete(XMLFile xmlFile);

    @Transaction
    @Query("DELETE FROM XMLFiles")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM XMLFiles")
    LiveData<List<XMLFile>> getAllXMLFiles();
}
