package model.service;

import common.CommunicationStatus;
import model.dao.base.VideoDao;
import model.dao.entity.ReturnEntity;
import model.dao.entity.Video;
import model.enumPackage.TagOperateType;
import model.exception.database.DataItemNotExists;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/6 16:55
 * @description:
 * @modifiedBy By:
 */
public class VideoService {
    private VideoDao videoDao;

    public VideoService() {
        videoDao = new VideoDao();
    }

    public ReturnEntity getAllVideos() {
        List<Video> result;
        try {
            result = videoDao.getAllVideos();
        } catch (DataItemNotExists e) {
            //todo 这个错误现在还会抛出吗
            return new ReturnEntity(CommunicationStatus.VIDEO_NOT_FOUND.getCode(), null);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(), result);
    }

    public ReturnEntity getRandomVideosWithNum(int videoNum) {
        List<Video> result;
        try {
            result = videoDao.getAllVideos();
            if (result.size() < videoNum)
                return new ReturnEntity(CommunicationStatus.BAD_REQUEST.getCode(), null);
            Collections.shuffle(result);
        } catch (RuntimeException e) {
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(), null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(), result.subList(0, videoNum));
    }

    public int updateVideoTag(String tagName, Video changedVideo, String operateType) {
        Optional<Video> needVideo;
        try {
            needVideo = videoDao.getAllVideos().stream().filter(video -> video.equals(changedVideo)).findFirst();
            if (needVideo.isEmpty())
                return CommunicationStatus.VIDEO_NOT_FOUND.getCode();
            Video video = needVideo.get();
            if (operateType.equals(TagOperateType.REMOVE.getType())) {
                if (!video.getTagsName().contains(tagName))
                    return CommunicationStatus.TAG_NOT_FOUND_IN_VIDEO.getCode();
                video.setTagsName(video.getTagsName().stream().filter(name -> !name.equals(tagName)).collect(Collectors.toList()));
            } else if (operateType.equals(TagOperateType.ADD.getType())) {
                if (video.getTagsName().contains(tagName))
                    return CommunicationStatus.TAG_ALREADY_EXIST_IN_VIDEO.getCode();
                List<String> newTags = video.getTagsName();
                newTags.add(tagName);
                video.setTagsName(newTags);
            } else
                return CommunicationStatus.TAG_OPERATION_NOT_FOUND.getCode();
            videoDao.updateVideo(video);
        } catch (RuntimeException e) {
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }
}
