package learningandroid.booobdai.com.constructiondiary.dao;

import android.content.Context;
import android.util.Log;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import learningandroid.booobdai.com.constructiondiary.entity.DiaryBean;

/**
 * 完成对某一张数据表的具体操作，ORM操作
 * Created by Mr.sorrow on 2017/5/5.
 */

public class DiaryDaoUtils {
    private static final String TAG = DiaryDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public DiaryDaoUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成diaryBean记录的插入，如果表未创建，先创建DiaryBean表
     *
     * @param diaryBean
     * @return
     */
    public boolean insertDiaryBean(DiaryBean diaryBean) {
        boolean flag = false;

        flag = mManager.getDaoSession().getDiaryBeanDao().insert(diaryBean) == -1 ? false : true;
        Log.i(TAG, "insert DiaryBean :" + flag + "-->" + diaryBean.toString());

        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param diaryBeanList
     * @return
     */
    public boolean insertMultDiaryBean(final List<DiaryBean> diaryBeanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (DiaryBean diaryBean : diaryBeanList) {
                        mManager.getDaoSession().insertOrReplace(diaryBean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param diaryBean
     * @return
     */
    public boolean updateDiaryBean(DiaryBean diaryBean){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(diaryBean);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param diaryBean
     * @return
     */
    public boolean deleteDiaryBean(DiaryBean diaryBean){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(diaryBean);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(DiaryBean.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<DiaryBean> queryAllDiaryBean(){
        return mManager.getDaoSession().loadAll(DiaryBean.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public DiaryBean queryDiaryBeanById(long key){
        return mManager.getDaoSession().load(DiaryBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<DiaryBean> queryDiaryBeanByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(DiaryBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<DiaryBean> queryDiaryBeanByQueryBuilder(long id){
        QueryBuilder<DiaryBean> queryBuilder = mManager.getDaoSession().queryBuilder(DiaryBean.class);
        return queryBuilder.where(DiaryBeanDao.Properties.Page.eq(id)).list();
    }
}
