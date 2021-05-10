# LiveLessonTableService
## LiveLesson entity structure
```java
class LiveLesson{
    private String username;
    private String coachName;
    private Long lessonTime;
    private Integer status;
    private Boolean isCustomized;
    private String target;
    private String specificExercise;
    private Long createTime;
}
class LiveLessonTable{
    private Long id;
    private String username;
    private List<LiveLesson> lessonList;
}
```
## Interface
### 1. save new LiveLessonTable
(__not an interface__) it will automatically generated when user sign up

### 2. update live lesson table 
method: updateLiveLessonTable(LiveLessonTable liveLessonTable)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 4043 | liveLesson table not exist |
| 5000 | database error |

### 3. get live lesson table by username
method: getLiveLessonTableByUsername(String username)
return: ReturnEntity(code,LiveLessonTable )
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 4043 | liveLesson table not exist |
| 5000 | database error |

### 4.  get specific user's lessons by condition
method: getLiveLessonsByTimeCondition(String username, String conditionType)
    conditionType now have two type:
1. "notStart" to find all lesson startTime<currentTime
2. "isEnd" to find all lesson startTime>currentTime
    this is not consider the condition where if the lesson is pay or is canceled
return: ReturnEntity(code,List<LiveSession>)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
| 5000 | null |database error |

### 5. create LiveLesson Table For SignUp (__used for backend__)
method: createLiveLessonTableForSignUp(String username)
return: void

### 6. get targets
method: getTargets()
return: ReturnEntity(code, List<String>)

### 7. finish a lesson
method: finishLesson(String username,LiveLesson liveLesson)
return: refer update live lesson table

### 8. insert a lesson
method: insertLesson(String username,LiveLesson liveLesson)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 400 | the time is crashed |
| 4043 | liveLesson table not exist |
| 5000 | database error |

### 9. get live lessons which not start  by username
method: getNotStartPayedLiveLessonByUsername(String username)
return: ReturnEntity(code,LiveLessonTable )
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 4043 | liveLesson table not exist |
| 5000 | database error |