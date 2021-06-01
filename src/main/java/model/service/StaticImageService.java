package model.service;

import common.CommunicationStatus;
import model.dao.StaticImageDao;
import model.entity.ReturnEntity;
import model.entity.StaticImage;
import java.util.List;


/**
 * @author :Yubo Wang
 * @date :2021-05-21 19:20
 * @description:
 * @modified By:
 * @version:
 */
public class StaticImageService {
    StaticImageDao staticImageDao;
    public ReturnEntity getDefaultProfilePhotos() {
        staticImageDao = new StaticImageDao();
        try {
            List<StaticImage> profilePhoto = staticImageDao.getAllProfilePhoto();
            if (profilePhoto.isEmpty())
                return new ReturnEntity(CommunicationStatus.PROFILE_PHOTO_NOT_FOUND.getCode(), null);
            return new ReturnEntity(CommunicationStatus.OK.getCode(), profilePhoto);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
    }
}
