package learningandroid.booobdai.com.constructiondiary.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * 类名称：AbstractDatabaseManager
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/21 09:16
 * 描述：greendao 管理类扩展性比较好，解耦和，实现类只需要实现getAbstractDao方法
 * 最近修改时间：2016/12/21 09:16
 * 修改人：Modify by liujc
 */
public abstract class AbstractDatabaseManager<M, K> implements IDatabase<M, K>{

    private static final String DEFAULT_DATABASE_NAME = "Diary_databena.db";
    private static MySQLiteOpenHelper mHelper;
    protected static DaoSession daoSession;
    /**
     * 初始化OpenHelper
     * @param context
     */
    public static void initOpenHelper(@NonNull Context context) {
        mHelper = new MySQLiteOpenHelper(new GreenDaoContext(), DEFAULT_DATABASE_NAME, null);//GreenDaoContext为创建数据库路径使用
        daoSession = new DaoMaster(mHelper.getWritableDatabase()).newSession();
    }

    @Override
    public void clearDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public boolean dropDatabase() {
        try {
            // DaoMaster.dropAllTables(database, true); // drops all tables
            // mHelper.onCreate(database); // creates the tables
//          mDaoSession.deleteAll(BankCardBean.class); // clear all elements
            // from
            // a table
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(@NonNull M m) {
        try {
            if (m == null)
                return false;
            Logger.e("getAbstractDao().insert");
            getAbstractDao().insert(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplace(@NonNull M m) {
        try {
            if (m == null)
                return false;
            getAbstractDao().insertOrReplace(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(@NonNull M m) {
        try {
            if (m == null)
                return false;
            getAbstractDao().delete(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKey(K key) {
        try {
            if (TextUtils.isEmpty(key.toString()))
                return false;
            getAbstractDao().deleteByKey(key);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKeyInTx(K... key) {
        try {
            getAbstractDao().deleteByKeyInTx(key);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteList(List<M> mList) {
        try {
            if (mList == null || mList.size() == 0)
                return false;
            getAbstractDao().deleteInTx(mList);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            getAbstractDao().deleteAll();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NonNull M m) {
        try {
            if (m == null)
                return false;
            getAbstractDao().update(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateInTx(M... m) {
        try {
            if (m == null)
                return false;
            getAbstractDao().updateInTx(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateList(List<M> mList) {
        try {
            if (mList == null || mList.size() == 0)
                return false;
            getAbstractDao().updateInTx(mList);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public M selectByPrimaryKey(@NonNull K key) {
        try {
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            return null;
        }
    }

    @Override
    public List<M> loadAll() {
        return getAbstractDao().loadAll();
    }

    @Override
    public boolean refresh(@NonNull M m) {
        try {
            if (m == null)
                return false;
            getAbstractDao().refresh(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public void runInTx(Runnable runnable) {
        try {
            daoSession.runInTx(runnable);
        } catch (SQLiteException e) {
        }
    }

    @Override
    public boolean insertList(@NonNull List<M> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            getAbstractDao().insertInTx(list);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public boolean insertOrReplaceList(@NonNull List<M> list) {
        try {
            if (list == null || list.size() == 0)
                return false;
            getAbstractDao().insertOrReplaceInTx(list);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        return getAbstractDao().queryBuilder();
    }

    /**
     * @param where
     * @param selectionArg
     * @return
     */
    @Override
    public List<M> queryRaw(String where, String... selectionArg) {
        return getAbstractDao().queryRaw(where, selectionArg);
    }

    public Query<M> queryRawCreate(String where, Object... selectionArg) {
        return getAbstractDao().queryRawCreate(where, selectionArg);
    }

    public Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        return getAbstractDao().queryRawCreateListArgs(where, selectionArg);
    }
    /**
     * 获取Dao
     * @return
     */
    abstract AbstractDao<M, K> getAbstractDao();
}
