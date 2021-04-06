package model.service;

import common.CommunicationStatus;
import model.dao.CoachDao;
import model.dao.entity.Coach;
import model.dao.entity.ReturnEntity;

import java.util.ArrayList;
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
        }catch (RuntimeException e) {
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
}
