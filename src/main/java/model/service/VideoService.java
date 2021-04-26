package model.service;

import common.CommunicationStatus;
import model.dao.VideoDao;
import model.entity.ReturnEntity;
import model.entity.Video;
import model.enumPackage.TagOperateType;
import model.exception.database.DataItemNotExists;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
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
            if (result.size() < videoNum||videoNum<1)
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

    public int saveVideo(Video newVideo){
        Optional<Video> videoList;
        try{
            videoList=videoDao.getAllVideos().stream().filter(video -> video.equals(newVideo)).findAny();
            if(videoList.isPresent())
                return CommunicationStatus.VIDEO_ALREADY_EXIST.getCode();
            videoDao.saveVideo(newVideo);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity getVideoById(Long id){
        try{
            List<Video> list=videoDao.getAllVideos();
            Optional<Video> videoOptional=list.stream().filter(r-> r.getId().equals(id)).findAny();
            if(videoOptional.isEmpty()){
                return new ReturnEntity(CommunicationStatus.VIDEO_NOT_FOUND.getCode(),null);
            }
            return new ReturnEntity(CommunicationStatus.OK.getCode(),videoOptional.get());
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
    }

    public ReturnEntity getVideoByName(String videoName){
        try{
            List<Video> list=videoDao.getAllVideos();
            Optional<Video> videoOptional=list.stream().filter(r-> r.getStaticVideo().getVideoName().equals(videoName)).findAny();
            if(videoOptional.isEmpty()){
                return new ReturnEntity(CommunicationStatus.VIDEO_NOT_FOUND.getCode(),null);
            }
            return new ReturnEntity(CommunicationStatus.OK.getCode(),videoOptional.get());
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
    }

    public AtomicBoolean isVideoPremium(Long id){
        List<Video> list=videoDao.getAllVideos();
        Optional<Video> videoOptional=list.stream().filter(r-> r.getId().equals(id)).findAny();
        if(videoOptional.isPresent())
            return videoOptional.get().getIsPremium();
        return new AtomicBoolean(false);
    }


}
