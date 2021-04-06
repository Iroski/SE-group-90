# LiveLessonTableService
## Interface
### 1. save new LiveLessonTable
method: saveLiveLessonTable(LiveLessonTable liveLessonTable)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 5000 | database error |
```
class LiveLessonTable{
	String username;
	List<LiveLesson> list;
}
class LiveLesson{
	private String username;
    private String coachName;
    private long lessonTime;
    private int status;
    private long createTime;
}
```

### 2. update live lessonTable 
method: updateLiveLessonTable(LiveLessonTable liveLessonTable)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
| 4043 | liveLesson table not exist |
| 5000 | database error |

### 3. get  live lessonTable by username
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


