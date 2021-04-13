package model.service;

import common.CommunicationStatus;
import model.dao.LiveLessonDao;
import model.entity.LiveLesson;
import model.entity.LiveLessonTable;
import model.entity.ReturnEntity;
import model.enumPackage.LiveLessonStatus;
import model.enumPackage.LiveSessionTimeType;
import model.exception.database.DataItemNotExists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/5 23:46
 * @description:
 * @modifiedBy By:
 */
public class LiveLessonService {
    LiveLessonDao liveLessonDao;

    public LiveLessonService() {
        liveLessonDao = new LiveLessonDao();
    }


    public void createLiveLessonTableForSignUp(String username){
        Optional<LiveLessonTable> tableWithSameName=liveLessonDao.getAllLiveLessonTable().stream().filter(table -> table.getUsername().equals(username)).findAny();
        if(tableWithSameName.isPresent())
            return;
        liveLessonDao.saveLiveLessonTable(new LiveLessonTable(username, new ArrayList<LiveLesson>()));
    }

    public int updateLiveLessonTable(LiveLessonTable liveLessonTable) {
        try {
            liveLessonDao.updateLiveLessonTable(liveLessonTable);
        } catch (DataItemNotExists e) {
            return CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity getLiveLessonTableByUsername(String username){
        Optional<LiveLessonTable> resultTable;
        try{
            resultTable=liveLessonDao.getAllLiveLessonTable().stream().filter(table-> table.getUsername().equals(username)).findFirst();
            if(resultTable.isEmpty())
                return new ReturnEntity(CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode(),null);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),resultTable.get());
    }

    public ReturnEntity getLiveLessonsByTimeCondition(String username, String conditionType) {
        List<LiveLesson> lessons;
        Optional<LiveLessonTable> resultTable;
        try {
            resultTable = liveLessonDao.getAllLiveLessonTable().stream().filter(table->table.getUsername().equals(username)).findFirst();
            if(resultTable.isEmpty())
                return new ReturnEntity(CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode(),null);
            lessons=resultTable.get().getLessonList();
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }

        long currentTime = System.currentTimeMillis();
        List<LiveLesson> result = new ArrayList<>();
        if (conditionType.equals(LiveSessionTimeType.NOT_START.getDescription()))
            result = lessons.stream().filter(liveLesson -> liveLesson.getLessonTime() >= currentTime && liveLesson.getUsername().equals(username))
                    .collect(Collectors.toList());
        else if (conditionType.equals(LiveSessionTimeType.IS_END.getDescription()))
            result = lessons.stream().filter(liveLesson -> liveLesson.getLessonTime() < currentTime && liveLesson.getUsername().equals(username))
                    .collect(Collectors.toList());
        else
            return new ReturnEntity(CommunicationStatus.BAD_REQUEST.getCode(),null);
        return new ReturnEntity(CommunicationStatus.OK.getCode(), result);
    }

    public int finishLesson(String username,LiveLesson liveLesson){
        return this.updateLessonStateByType(username,liveLesson,"FINISHED");
    }

    protected int insertLesson(String username,LiveLesson liveLesson){
        try{
            Optional<LiveLessonTable> sTableOption=this.getTableByName(username);
            if(sTableOption.isEmpty())
                return CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode();
            LiveLessonTable liveLessonTable=sTableOption.get();
            List<LiveLesson> list=liveLessonTable.getLessonList();
            list.add(liveLesson);
            liveLessonTable.setLessonList(list);
            return this.updateLiveLessonTable(liveLessonTable);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected int updateLessonStateByType(String username,LiveLesson liveLesson,String type){
        try{
            Optional<LiveLessonTable> sTableOption=this.getTableByName(username);
            if(sTableOption.isEmpty())
                return CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode();
            LiveLessonTable liveLessonTable=sTableOption.get();
            if("PAYED".equals(type))
                liveLesson.setStatus(LiveLessonStatus.IS_PAYED.getCode());
            else if("CANCELED".equals(type))
                liveLesson.setStatus(LiveLessonStatus.IS_CANCELED.getCode());
            else if("FINISHED".equals(type))
                liveLesson.setStatus(LiveLessonStatus.IS_FINISH.getCode());
            List<LiveLesson> list=liveLessonTable.getLessonList();
            list=list.stream().filter(liveLesson1 -> !liveLesson1.getCreateTime().equals(liveLesson.getCreateTime())).collect(Collectors.toList());
            list.add(liveLesson);
            liveLessonTable.setLessonList(list);
            return this.updateLiveLessonTable(liveLessonTable);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
    }

    protected Optional<LiveLessonTable> getTableByName(String username){
        return liveLessonDao.getAllLiveLessonTable().stream().filter(table->table.getUsername().equals(username)).findAny();
    }

}
