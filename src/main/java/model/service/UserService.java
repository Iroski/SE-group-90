package model.service;

import common.CommunicationStatus;
import model.dao.UserDao;
import model.dao.base.DataBase;
import model.dao.entity.ReturnEntity;
import model.dao.entity.User;
import model.exception.database.DataItemNotExists;
import model.utils.CheckUserInfoValidation;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/5 20:32
 * @description:
 * @modifiedBy By:
 */
public class UserService {
    String tableName;
    DataBase db;
    UserDao userDao;

    public UserService() {
        tableName = "User";
        db = DataBase.getInstance();
        userDao = new UserDao();
    }


    public int saveUser(User newUser) {
        try {
            List<User> users = userDao.getAllUser();
            if (users.stream().anyMatch(user -> user.getName().equals(newUser.getName())))
                return CommunicationStatus.USERNAME_ALREADY_EXISTS.getCode();
            userDao.saveUser(newUser);

            //todo 这里还需要创建一个account
            new LiveLessonService().createLiveLessonTableForSignUp(newUser.getName());
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }

        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity login(String username, String password) {
        ReturnEntity returnEntity = new ReturnEntity();
        try {
            List<User> users = userDao.getAllUser();
            users.forEach(user -> {
                if (user.getName().equals(username) && user.getPassword().equals(password)) {
                    returnEntity.setCode(CommunicationStatus.OK.getCode());
                    returnEntity.setObject(user);
                }
            });
            if (returnEntity.getObject() == null)
                returnEntity.setCode(CommunicationStatus.USER_NOT_FOUND_OR_WRONG_PASSWORD.getCode());
        } catch (RuntimeException e) {
            returnEntity.setCode(CommunicationStatus.INTERNAL_ERROR.getCode());
        }
        return returnEntity;
    }

    public ReturnEntity getUser(String username) {

        ReturnEntity returnEntity = new ReturnEntity();
        try {
            List<User> users = userDao.getAllUser();
            users.forEach(user -> {
                if (user.getName().equals(username)) {
                    returnEntity.setCode(CommunicationStatus.OK.getCode());
                    returnEntity.setObject(user);
                }
            });
            if (returnEntity.getObject() == null)
                returnEntity.setCode(CommunicationStatus.USER_NOT_FOUND.getCode());
        } catch (RuntimeException e) {
            returnEntity.setCode(CommunicationStatus.INTERNAL_ERROR.getCode());
        }
        return returnEntity;
    }

    public int updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (DataItemNotExists e) {
            return CommunicationStatus.USER_NOT_FOUND.getCode();
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public boolean isUserExist(String username) {
        AtomicReference<Boolean> isExist = new AtomicReference<>(false);
        try {
            List<User> users = userDao.getAllUser();
            users.forEach(user -> {
                if (user.getName().equals(username))
                    isExist.set(true);
            });
        } catch (RuntimeException e) {
            return false;
        }
        return isExist.get();
    }
}
