package cl.slash.myownstuff.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cl.slash.myownstuff.obj.Category;
import cl.slash.myownstuff.obj.Item;

/**
 * Created by waltercool on 7/9/14.
 */
public class StuffDatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String TAG = StuffDatabaseHelper.class.getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myownstuff.db";

    private RuntimeExceptionDao<Category, Integer> categoryDao = null;
    private RuntimeExceptionDao<Item, Integer> itemDao = null;

    public StuffDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Item.class);
            Log.d(TAG, "Database created");
        }
        catch(SQLException e)
        {
            Log.d(TAG, "Problem creating database");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            onCreate(database, connectionSource);
        }
        catch(SQLException e)
        {
            Log.d(TAG, "Problem with database drop");
            throw new RuntimeException(e);
        }
    }

    public RuntimeExceptionDao<Category, Integer> getCategoryDao()
    {
        if(categoryDao == null)
        {
            categoryDao = getRuntimeExceptionDao(Category.class);
        }
        return categoryDao;
    }

    public RuntimeExceptionDao<Item, Integer> getItemDao() throws SQLException
    {
        if(itemDao == null)
        {
            itemDao = getRuntimeExceptionDao(Item.class);
        }
       return itemDao;
    }

    @Override
    public void close()
    {
        super.close();
        categoryDao = null;
        itemDao = null;
    }
}
