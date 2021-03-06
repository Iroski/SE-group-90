# VideoService
## Video entity structure
```java
class Video{
    private Long id
    private String videoName;
    private String author;
    private Long length;
    private Long views;
    private List<String> tagsName;
    private String videoPath;
    private String coverPath;
    private AtomicBoolean isPremium;
} 
```
## interface
### 1. get all videos
method: getAllVideos()
return: ReturnEntity(code,List<Video>)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
| 4045|null| video not found|
| 5000 | null |database error |

### 2. get Random Videos With specific video num
method: getRandomVideosWithNum(int videoNum)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
|400| null| invalid input|
| 5000 | null |database error |

### 3. update Video Tag
__Not recommend to use this method as it will not trigger the update in Tag class. Instead, user the updateVideoTag method in TagService.__ By zyb
method: updateVideoTag(String tagName, Video changedVideo, String operateType)
return: int

|opreation| return type | meaning |
|:--:|:--:|:--:|
|| 200 | insert successful |
||4045|video not found|
|add |40021| tag already exist in video|
|remove|40461| tag is not found in video|
||40462|operateType is wrong|
|| 5000 | database error |

### 4. save new video
meghod: saveVideo(Video video)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
|40022|tag already exist|
| 5000 | database error |

### 5. get video by id
method: getVideoById(Long id)
return: ReturnEntity(code,Video)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |video| run successful |
| 4045|null| video not found|
| 5000 | null |database error |

### 6. get video by name
method: getVideoById(String name)
return: ReturnEntity(code,Video)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |video| run successful |
| 4045|null| video not found|
| 5000 | null |database error |

### 7. check if the video is premium only
method: isVideoPremium(Long id)
return: AtomicBoolean

### 8. blur search by video name

method: blurSearchByName(String blurName)

return: ReturnEntity(code,List\<Video>)

| return code |       return object       |                        meaning                        |
| :---------: | :-----------------------: | :---------------------------------------------------: |
|     200     | List that contains entity |                    run successful                     |
|     200     |    List that is empty     | run successful but no lesson is satisfy the condition |
|    5000     |           null            |                    database error                     |
