package model.service;

import common.CommunicationStatus;
import model.dao.LiveLessonDao;
import model.entity.*;
import model.enumPackage.LiveLessonStatus;
import model.enumPackage.TargetType;
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
    UserService userService;

    public LiveLessonService() {
        liveLessonDao = new LiveLessonDao();
        userService=new UserService();
    }


    public void createLiveLessonTableForSignUp(String username){
        Optional<LiveLessonTable> tableWithSameName=liveLessonDao.getAllLiveLessonTable().stream().filter(table -> table.getUsername().equals(username)).findAny();
        if(tableWithSameName.isPresent())
            return;
        liveLessonDao.saveLiveLessonTable(new LiveLessonTable(username, new ArrayList<LiveLesson>()));
    }

    public void createInfoForDeleteInfo(){
        List<String> usernameList=userService.getAllUsers().stream().map(User::getName).collect(Collectors.toList());
        List<String> liveNameList=liveLessonDao.getAllLiveLessonTable().stream().map(LiveLessonTable::getUsername).collect(Collectors.toList());
        for(String a:usernameList){
            if(!liveNameList.contains(a))
                createLiveLessonTableForSignUp(a);
        }
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

    public ReturnEntity getNotStartPayedLiveLessonByUsername(String username){
        List<LiveLesson> lessons;
        Optional<LiveLessonTable> resultTable;
        try {
            resultTable = this.getTableByName(username);
            if(resultTable.isEmpty())
                return new ReturnEntity(CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode(),null);
            lessons=resultTable.get().getLessonList();
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }

        long currentTime = System.currentTimeMillis()/1000; //todo this need to be decided by LZH
        List<LiveLesson> result ;
        result=lessons.stream().filter(l -> (l.getStatus()==LiveLessonStatus.NOT_PAYED.getCode()||l.getStatus()==LiveLessonStatus.IS_PAYED.getCode())&&l.getLessonTime()<=currentTime).collect(Collectors.toList());
        return new ReturnEntity(CommunicationStatus.OK.getCode(), result);
    }

    public ReturnEntity getLiveLessonsByTimeCondition(String username, String conditionType) {
        List<LiveLesson> lessons;
        Optional<LiveLessonTable> resultTable;
        try {
            resultTable = this.getTableByName(username);
            if(resultTable.isEmpty())
                return new ReturnEntity(CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode(),null);
            lessons=resultTable.get().getLessonList();
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }

        long currentTime = System.currentTimeMillis()/1000;
        List<LiveLesson> result = new ArrayList<>();
        if (conditionType.equals(LiveLessonStatus.IS_PAYED.getType()))
            result = lessons.stream().filter(liveLesson -> liveLesson.getLessonTime() >= currentTime )
                    .collect(Collectors.toList());
        else if (conditionType.equals(LiveLessonStatus.IS_FINISH.getType()))
            result = lessons.stream().filter(liveLesson -> liveLesson.getLessonTime() < currentTime )
                    .collect(Collectors.toList());
        else
            return new ReturnEntity(CommunicationStatus.BAD_REQUEST.getCode(),null);
        return new ReturnEntity(CommunicationStatus.OK.getCode(), result);
    }

    public int finishLesson(String username,LiveLesson liveLesson){
        return this.updateLessonStateByType(username,liveLesson,"FINISHED");
    }

    public ReturnEntity getTargets(){
        return new ReturnEntity(CommunicationStatus.OK.getCode(), TargetType.getAllDescription());
    }

    protected int insertLesson(String username,LiveLesson liveLesson){
        try{
            Optional<User> sUser=userService.getUserByUsername(username);
            if(sUser.isEmpty())
                return CommunicationStatus.USER_NOT_FOUND.getCode();
            User user=sUser.get();

            Optional<LiveLessonTable> sTableOption=this.getTableByName(username);
            if(sTableOption.isEmpty())
                return CommunicationStatus.LIVE_LESSON_TABLE_NOT_FOUND.getCode();


            LiveLessonTable liveLessonTable=sTableOption.get();
            List<LiveLesson> list=liveLessonTable.getLessonList();
            if(list.stream().anyMatch(liveLesson1 -> liveLesson1.getLessonTime().equals(liveLesson.getLessonTime())))
                return CommunicationStatus.BAD_REQUEST.getCode();

            if(liveLesson.getIsCustomized()){
                String plan=TargetType.getBasePlanGeneratorByDesc(liveLesson.getTarget()).generatePlan(user.getHeight(),user.getWeight());
                liveLesson.setSpecificExercise(plan);
            }

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
