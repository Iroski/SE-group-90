# UserService
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
|4042 | user not exist or wrong password|
| 5000 | database error |

### 3.getUserInfo
method: getUser(String username)
return: ReturnEntity(code, User)
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist |
| 5000 | database error |

### 4.updateUserInfo
method: updateUser(User user)
return: ReturnEntity(code, User)
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4041 | user not exist|
| 5000 | database error |