# AccountService
## Account entity structure
```java
class Account{
    private Long id
    private String username;
    private BigDecimal balance;
    private List<Long> orderId;
    private Integer premiumLevel;
    private Integer freeLiveLessonNum; //this can be used when by series lessons
    private Long createTime;
    private Long premiumEndTime;
}
```
## Interface
### 1. create account
It will automatically generated account when using signup method in userService

### 2. update account
__not recommend__
method: updateAccount(Account account)
return: int
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4042 | account not exist|
| 5000 | database error |

### 3. update balance
method: updateBalance(String username,BigDecimal orderMoney)
__orderMoney is positive when you want to buy some service; when you want to deposit, orderMoney should be negative__
return: int
| return type | meaning |
|:--:|:--:|
| 200| insert successful|
|4042 | account not exist|
| 5001 | not enough balance |
| 5000 | database error |

### 4. get account by name
method: getAccount(String username)
return: ReturnEntity(code, Account)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
| 5000 | database error |

### 5. check account is premium
method: isPremium(String username)
return: ReturnEntity(code, AtomicBoolean)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
| 5000 | database error |

### 6. set account  premium
method: setPremium(String username, Integer type, Integer premiumNum)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
| 5000 | database error |

### 7. check account by premium
method: getBargainByUsername(String username)
return: ReturnEntity(code, BigDecimal)
| return type | meaning |
|:--:|:--:|
| 200| successful|
| 4042 | account not exist|
| 5000 | database error |