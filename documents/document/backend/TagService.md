# TagService
## Tag entity structure
```java
class Tag{
    private Long id
    private String tagName;
    private List<Video> videoList;
}
```
## interface
### 1. get all tags' name
method: getAllTagsName()
return: ReturnEntity(code, List<String>)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
| 5000 | null |database error |

### 2.get videos by tag
method: getVideosByTag(String tagName)
return: ReturnEntity(code, List<Video>)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
|4046| null |tag not found|
| 5000 | null |database error |

### 3. update video tag with operation
method: updateVideoTag(String tagName, Video operateVideo, String operateType)
    operateType:
    1. "add"
    2. "remove"

return: int
|opreation| return type | meaning |
|:--:|:--:|:--:|
|| 200 | insert successful |
||4045|video not found|
|add| 40022 |video already exist in tag |
|add |40021| tag already exist in video|
|remove| 40451| video is not found in tag|
|remove|40461| tag is not found in video|
||40462|operateType is wrong|
|| 5000 | database error |

### 4. save new tag
meghod: saveTag(Tag tag)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
|40024|tag already exist|
| 5000 | database error |
