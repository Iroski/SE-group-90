package model.service;

import common.CommunicationStatus;
import model.dao.UserDao;
import model.dao.VideoDao;
import model.dao.base.DataHouse;
import model.entity.ReturnEntity;
import model.entity.User;
import model.entity.Video;
import model.exception.database.DataItemNotExists;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/5 20:32
 * @description:
 * @modifiedBy By:
 */
public class UserService {
    String tableName;
    DataHouse db;
    UserDao userDao;
    int HISTORY_NUM=6;

    public UserService() {
        tableName = "User";
        db = DataHouse.getInstance();
        userDao = new UserDao();
    }


    public int saveUser(User newUser) {
        try {
            List<User> users = userDao.getAllUser();
            if (users.stream().anyMatch(user -> user.getName().equals(newUser.getName())))
                return CommunicationStatus.USERNAME_ALREADY_EXISTS.getCode();
            userDao.saveUser(newUser);

            new AccountService().createAccountForSignUp(newUser.getName());
            new LiveLessonService().createLiveLessonTableForSignUp(newUser.getName());
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }

        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity login(String username, String password) {
        ReturnEntity returnEntity = new ReturnEntity();
        try {
            Optional<User> sUser=getUserByUsername(username);
            if(sUser.isEmpty())
                return new ReturnEntity(CommunicationStatus.USER_NOT_FOUND.getCode(),null);
            if (password.equals(sUser.get().getPassword()))
                return new ReturnEntity(CommunicationStatus.WRONG_PASSWORD.getCode(),null);
            return new ReturnEntity(CommunicationStatus.OK.getCode(),sUser.get());
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
    }

    public ReturnEntity getUser(String username) {
        ReturnEntity returnEntity = new ReturnEntity();
        try {
            Optional<User> sUser=getUserByUsername(username);
            if (sUser.isEmpty())
                return new ReturnEntity(CommunicationStatus.USER_NOT_FOUND.getCode(),null);
            return new ReturnEntity(CommunicationStatus.OK.getCode(),sUser.get());
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return new ReturnEntity((CommunicationStatus.INTERNAL_ERROR.getCode()),null);
        }
    }

    public int updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (DataItemNotExists e) {
            return CommunicationStatus.USER_NOT_FOUND.getCode();
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public boolean isUserExist(String username) {
        AtomicReference<Boolean> isExist = new AtomicReference<>(false);
        try {
            Optional<User> sUser=getUserByUsername(username);
            if (sUser.isPresent())
                return true;
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return false;
        }
        return isExist.get();
    }

    public ReturnEntity getHistoryByName(String username){
        ReturnEntity returnEntity = new ReturnEntity();
        try {
            //check user validation
            Optional<User> sUser=getUserByUsername(username);
            if (sUser.isEmpty())
                return new ReturnEntity(CommunicationStatus.USER_NOT_FOUND.getCode(),null);

            List<Long> sVideosId=sUser.get().getHistory();
            VideoDao videoDao=new VideoDao();
            List<Video> videos=videoDao.getAllVideos();
            List<Video> sVideo=videos.stream().filter(video -> sVideosId.contains(video.getId())).collect(Collectors.toList());
            return new ReturnEntity(CommunicationStatus.OK.getCode(),sVideo);
        } catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
    }

    public int setHistoryByName(String username,Long id){
        try{
            Optional<User> sUser=getUserByUsername(username);
            if(sUser.isEmpty())
                return CommunicationStatus.USER_NOT_FOUND.getCode();
            User user=sUser.get();
            List<Long> videosId=user.getHistory();
            if(videosId.size()==HISTORY_NUM)
                videosId.remove(0);
            videosId.add(HISTORY_NUM,id);
            user.setHistory(videosId);
            userDao.updateUser(user);
        }catch (RuntimeException e) {
            System.err.println("RuntimeError occur at "+ Thread.currentThread().getStackTrace()[2].getClassName()+" "+Thread.currentThread().getStackTrace()[2].getMethodName());
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    protected Optional<User> getUserByUsername(String username){
        List<User> users = userDao.getAllUser();
        return users.stream().filter(user->user.getName().equals(username)).findAny();
    }
}
