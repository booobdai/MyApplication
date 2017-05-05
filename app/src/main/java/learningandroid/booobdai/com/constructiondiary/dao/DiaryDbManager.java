package learningandroid.booobdai.com.constructiondiary.dao;

import org.greenrobot.greendao.AbstractDao;

import learningandroid.booobdai.com.constructiondiary.entity.DiaryBean;

/**
 * 类名称：UserDbManager
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/12/21 09:35
 * 描述：TODO
 * 最近修改时间：2016/12/21 09:35
 * 修改人：Modify by liujc
 */
public class DiaryDbManager extends AbstractDatabaseManager<DiaryBean, Long>  {
    @Override
    AbstractDao<DiaryBean, Long> getAbstractDao() {
        return daoSession.getDiaryBeanDao();
    }
}
