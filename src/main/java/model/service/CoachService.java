package model.service;

import common.CommunicationStatus;
import model.dao.CoachDao;
import model.dao.base.DataItem;
import model.entity.Coach;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.exception.database.DataItemNotExists;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;

/**
 * @author :YanBo Zhang
 * @date :Created in 2021 2021/4/6 13:36
 * @description:
 * @modifiedBy By:
 * @version :
 */
public class CoachService {
    private CoachDao coachDao;

    public CoachService(){
        this.coachDao=new CoachDao();
    }

    public ReturnEntity getAllCoaches(){
        List<Coach> result=new ArrayList<>();
        try{
            result=coachDao.getAllCoaches();
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),result);
    }

    public int updateCoach(Coach coach){
        try{
            coachDao.updateCoach(coach);
        }catch (DataItemNotExists e){
            return CommunicationStatus.COACH_NOT_FOUND.getCode();
        }
        catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity getCoachById(long id){
        Optional<Coach> result;
        try{
            List<Coach> coaches=coachDao.getAllCoaches();
            result=coaches.stream().filter(coach ->coach.getId()==id).findFirst();
            if(result.isEmpty()){
                return new ReturnEntity(CommunicationStatus.COACH_NOT_FOUND.getCode(),null);
            }
        }catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),result.get());
    }

    public ReturnEntity getReservedTimeById(long id){
        Optional<Coach> result;
        try{
            List<Coach> coaches=coachDao.getAllCoaches();
            result=coaches.stream().filter(coach ->coach.getId()==id).findFirst();
            if(result.isEmpty()){
                return new ReturnEntity(CommunicationStatus.COACH_NOT_FOUND.getCode(),null);
            }
        }catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),result.get().getBookedTime());
    }

    public ReturnEntity blurSearchByName(String blurName) {
        try{
            List<Coach> list = coachDao.blurSearch("name", blurName);
            if(list.isEmpty()){
                return new ReturnEntity(CommunicationStatus.COACH_NOT_FOUND.getCode(),null);
            }
            return new ReturnEntity(CommunicationStatus.OK.getCode(), list);
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
    }

    public int setReservedTimeById(long id,List<Long> timetable){
        Optional<Coach> result;
        try{
            List<Coach> coaches=coachDao.getAllCoaches();
            result=coaches.stream().filter(coach ->coach.getId()==id).findFirst();
            if(result.isEmpty()){
                return CommunicationStatus.COACH_NOT_FOUND.getCode();
            }
            Coach coach=result.get();
            coach.setBookedTime(timetable);
            coachDao.updateCoach(coach);
        }catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public int saveCoach(Coach newCoach){
        Optional<Coach> coaches;
        try{
            coaches=coachDao.getAllCoaches().stream().filter(coach -> coach.equals(newCoach)).findAny();
            if(coaches.isPresent())
                return CommunicationStatus.COACH_ALREADY_EXIST.getCode();
            coachDao.saveCoach(newCoach);
        }catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected int updateTimeList(String coachName, Long time,String type){
        try{
            Optional<Coach> sCoachOption=this.getCoachByName(coachName);
            if(sCoachOption.isEmpty())
                return CommunicationStatus.COACH_NOT_FOUND.getCode();
            Coach sCoach=sCoachOption.get();
            List<Long> timeList=sCoach.getBookedTime();
            if("ADD".equals(type)){
                if(timeList.contains(time))
                    return CommunicationStatus.BAD_REQUEST.getCode();
                timeList.add(time);
            }
            else if("REMOVE".equals(type)){
                if(!timeList.contains(time))
                    return CommunicationStatus.BAD_REQUEST.getCode();
                timeList.remove(time);
            }
            sCoach.setBookedTime(timeList);
            coachDao.updateCoach(sCoach);
        }
        catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected Optional<Coach> getCoachByName(String name){
        return coachDao.getAllCoaches().stream().filter(coach -> coach.getName().equals(name)).findAny();
    }
}
