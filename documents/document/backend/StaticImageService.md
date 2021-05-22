# StaticImageService
## StaticImage entity structure
```java
public enum ImageType {
    PROFILEPHOTO
}

public class StaticImage {
    private String filePath;
    private String name;
    private ImageType type;
}
```
## Interface
### 1. get all default profile photos
method: getDefaultProfilePhotos()

return: ReturnEntity(code, List\<StaticImage>)

| return code |       return object       |                        meaning                        |
| :---------: | :-----------------------: | :---------------------------------------------------: |
|     200     | List that contains entity |                    run successful                     |
|     200     |    List that is empty     | run successful but no lesson is satisfy the condition |
|    4048     |           null            |       no available default profile photo found        |
|    5000     |           null            |                    database error                     |

