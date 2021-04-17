# CoachService
## Coach entity structure
```java
class Coach{
    private Long id
    String name;
    String gender;
    Integer height;
    Integer weight;
    Integer age;
    String description;
    String course;
    String photoPath;
    List<Long> BookedTime;
}
```
## Interface
### 1. get all coaches
method: getAllCoaches()
return: ReturnEntity(code,List<Coach>)
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no lesson is satisfy the condition|
| 5000 | null |database error |

### 2. update a coach
method: updateCoach(Coach coach)
return: int
| return code| meaning |
|:--:|:--:|
| 200 | run successful |
| 5000 |database error |

### 3. get a specific coach
method: getCoachById(long id)
return: ReturnEntity(code, Coach)
| return code| meaning |
|:--:|:--:|
| 200 | run successful |
|4044 | coach not found|
| 5000 |database error |

### 4. get a specific coach's booked list
method: getReservedTimeById(long id)
return: ReturnEntity(code, List<long> )
| return code|return object | meaning |
|:--:|:--:|:--:|
| 200 |List that contains entity| run successful |
| 200 |List that is empty| run successful but no reservation  |
|4044 | null |coach not found|
| 5000 | null |database error |

### 5. set a specific coach's booked list
method: setReservedTimeById(long id, List<Long> timetable)
return: int
| return code| meaning |
|:--:|:--:|
| 200 | run successful |
|4044 | coach not found|
| 5000 |database error |

### 6. save new coach
meghod: saveCoach(Coach coach)
return: int
| return type | meaning |
|:--:|:--:|
| 200 | insert successful |
|4003|coach already exist|
| 5000 | database error |
