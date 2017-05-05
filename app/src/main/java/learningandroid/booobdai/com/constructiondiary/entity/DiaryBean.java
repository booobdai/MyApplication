package learningandroid.booobdai.com.constructiondiary.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ================================================
 * 作    者：bood
 * 版    本：1.0
 * 创建日期：2017/3/9 16:22
 * 描    述：数据库的实体bean
 * 修订历史：
 * ================================================
 */


@Entity
public class DiaryBean {

    @Id(autoincrement = true)//是否自增长
    private Long page;//页数也是记录篇数
    private String entryName;//项目名称
    private String recordTime;//记录时间
    private String morningWeather;//上午天气
    private String afternoonWeather;//下午天气
    private String morningTemperature;//上午温度
    private String afternoonTemperature;//下午温度
    private String carpenterNum;//木匠
    private String bricklayerNum;//泥水
    private String gangJinNum;//钢筋
    private String backmanNum;//杂工
    private String diaryContent;//日志
    private String safetyRecord;//安全记录
    private String recorder;//记录人
    private String beginTime;//新建时间
    private String endTime;//修订时间
    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getMorningWeather() {
        return morningWeather;
    }

    public void setMorningWeather(String morningWeather) {
        this.morningWeather = morningWeather;
    }

    public String getAfternoonWeather() {
        return afternoonWeather;
    }

    public void setAfternoonWeather(String afternoonWeather) {
        this.afternoonWeather = afternoonWeather;
    }

    public String getMorningTemperature() {
        return morningTemperature;
    }

    public void setMorningTemperature(String morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public String getAfternoonTemperature() {
        return afternoonTemperature;
    }

    public void setAfternoonTemperature(String afternoonTemperature) {
        this.afternoonTemperature = afternoonTemperature;
    }

    public String getCarpenterNum() {
        return carpenterNum;
    }

    public void setCarpenterNum(String carpenterNum) {
        this.carpenterNum = carpenterNum;
    }

    public String getBricklayerNum() {
        return bricklayerNum;
    }

    public void setBricklayerNum(String bricklayerNum) {
        this.bricklayerNum = bricklayerNum;
    }

    public String getGangJinNum() {
        return gangJinNum;
    }

    public void setGangJinNum(String gangJinNum) {
        this.gangJinNum = gangJinNum;
    }

    public String getBackmanNum() {
        return backmanNum;
    }

    public void setBackmanNum(String backmanNum) {
        this.backmanNum = backmanNum;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public String getSafetyRecord() {
        return safetyRecord;
    }

    public void setSafetyRecord(String safetyRecord) {
        this.safetyRecord = safetyRecord;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



    @Generated(hash = 2034918021)
    public DiaryBean(Long page, String entryName, String recordTime,
            String morningWeather, String afternoonWeather,
            String morningTemperature, String afternoonTemperature,
            String carpenterNum, String bricklayerNum, String gangJinNum,
            String backmanNum, String diaryContent, String safetyRecord,
            String recorder, String beginTime, String endTime) {
        this.page = page;
        this.entryName = entryName;
        this.recordTime = recordTime;
        this.morningWeather = morningWeather;
        this.afternoonWeather = afternoonWeather;
        this.morningTemperature = morningTemperature;
        this.afternoonTemperature = afternoonTemperature;
        this.carpenterNum = carpenterNum;
        this.bricklayerNum = bricklayerNum;
        this.gangJinNum = gangJinNum;
        this.backmanNum = backmanNum;
        this.diaryContent = diaryContent;
        this.safetyRecord = safetyRecord;
        this.recorder = recorder;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Generated(hash = 1749744078)
    public DiaryBean() {
    }
}
