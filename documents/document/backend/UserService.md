# UserService
## User entity structure
```java
class User{
    private Long id
    private String email;
    private String name;
    private String password;
    private String phone;
    private String gender;
    private Double weight;
    private Integer height;
    private Integer age;
    private List<Long> history;
}
```
## Interface
### 1. sign up(save a new user)
method: saveUser(User user)
return: Int
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4001 | user name exist|
| 5000 | database error |

### 2. login
method: login(String username,String password)
return: ReturnEntity(code, User)
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4004 | wrong password|
|4041 | user not exist |
| 5000 | database error |

### 3.get user
method: getUser(String username)
return: ReturnEntity(code, User)
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist |
| 5000 | database error |

### 4.update user info
method: updateUser(User user)
return: ReturnEntity(code, User)
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist|
| 5000 | database error |

### 5. check if user is exist
method: isUserExist(String username)
return: boolean

### 6. get history from a user
method: getHistoryByName(String username)
return ReturnEntity(code,List<Video>)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4041 | user not exist|
| 5000 | database error |

### 7. set history to a user
method: setHistoryByName(String username,Long id)
return: int
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist|
| 5000 | database error |

### 8. get favorite video from a user
method: getFavoriteByName(String username)
return ReturnEntity(code,List<Video>)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4041 | user not exist|
| 5000 | database error |

### 9. set favorite video to a user
method: setFavoriteByName(String username,Long id)
return: int
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist|
| 5000 | database error |