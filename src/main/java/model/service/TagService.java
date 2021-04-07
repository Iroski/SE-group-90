package model.service;

import common.CommunicationStatus;
import model.dao.TagDao;
import model.entity.ReturnEntity;
import model.entity.Tag;
import model.entity.Video;
import model.enumPackage.TagOperateType;
import model.exception.database.DataItemNotExists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author :YanBo Zhang
 * @version :
 * @date :Created in 2021 2021/4/6 18:53
 * @description:
 * @modifiedBy By:
 */
public class TagService {
    private TagDao tagDao;

    public TagService() {
        tagDao = new TagDao();
    }

    public ReturnEntity getVideosByTag(String tagName){
        Optional<Tag> returnTag;
        try{
            returnTag=tagDao.getAllTag().stream().filter(tag -> tag.getTagName().equals(tagName)).findFirst();
            if(returnTag.isEmpty())
                return new ReturnEntity(CommunicationStatus.TAG_NOT_FOUND.getCode(),null);
        }catch (DataItemNotExists e){
            //todo 这个错误现在还会抛出吗
            return new ReturnEntity(CommunicationStatus.TAG_NOT_FOUND.getCode(),null);
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),returnTag.get().getVideoList());
    }

    public int updateVideoTag(String tagName, Video operateVideo, String operateType){
        int codeFromVideoService=new VideoService().updateVideoTag(tagName,operateVideo,operateType);
        if(codeFromVideoService!=CommunicationStatus.OK.getCode())
            return codeFromVideoService;

        Optional<Tag> changedTag;
        try{
            changedTag=tagDao.getAllTag().stream().filter(tag -> tag.getTagName().equals(tagName)).findFirst();
            Tag resultTag;
            if(changedTag.isEmpty())
                resultTag=new Tag(tagName,new ArrayList<Video>());
            else
                resultTag=changedTag.get();
            if(operateType.equals(TagOperateType.ADD.getType())){
                if(resultTag.getVideoList().contains(operateVideo))
                    return CommunicationStatus.VIDEO_ALREADY_EXIST_IN_TAG.getCode();
                List<Video> videoList=resultTag.getVideoList();
                videoList.add(operateVideo);
                resultTag.setVideoList(videoList);
            }else if(operateType.equals(TagOperateType.REMOVE.getType())){
                if(!resultTag.getVideoList().contains(operateVideo))
                    return CommunicationStatus.VIDEO_NOT_FOUND_IN_TAG.getCode();
                resultTag.setVideoList(resultTag.getVideoList().stream().filter(video -> !video.equals(operateVideo))
                        .collect(Collectors.toList()));
            }
            tagDao.updateTag(resultTag);
        }catch (RuntimeException e){
            return CommunicationStatus.INTERNAL_ERROR.getCode();
        }
        return CommunicationStatus.OK.getCode();
    }

    public ReturnEntity getAllTagsName(){
        List<String> tagList;
        try{
            tagList=tagDao.getAllTag().stream().map(Tag::getTagName).collect(Collectors.toList());
        }catch (RuntimeException e){
            return new ReturnEntity(CommunicationStatus.INTERNAL_ERROR.getCode(),null);
        }
        return new ReturnEntity(CommunicationStatus.OK.getCode(),tagList);
    }
     public int saveTag(Tag newTag){
         Optional<Tag> tagList;
         try{
             tagList=tagDao.getAllTag().stream().filter(tag -> tag.getTagName().equals(newTag.getTagName() )).findAny();
             if(tagList.isPresent())
                 return CommunicationStatus.TAG_ALREADY_EXIST.getCode();
             tagDao.saveTag(newTag);
         }catch (RuntimeException e){
             return CommunicationStatus.INTERNAL_ERROR.getCode();
         }
         return CommunicationStatus.OK.getCode();
     }
}
