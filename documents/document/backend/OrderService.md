# OrderService
## Order entity structure
```java
class Order{
    private Long id;
    private String username;
    private Integer type;
    //type==1 : livelesson order
    private Long liveLessonCreateTime;
    //type==0 : premium order
    private Integer premiumType;
    //the num of premium. if buy one month's premium then premiumType is 1 and premium is 1
    private Integer premiumNum;

    private BigDecimal money;
    private Integer state;
    private Long createTime;
}
```
## Interface
### 1. get orders by name
method: getOrdersByName(String username)
return: ReturnEntity(code,List<Order>)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
| 5000 | database error |

### 2. create premium order
method: createPremiumOrder(String username, Order order)
return: ReturnEntity(code, Order)
__the return order contains id, you need to use this to do the following procedure(pay, cancel)__
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
| 5000 | database error |

### 3. pay premium order
__NOTICE:__ this will set premium level automatically
method: payPremiumOrder(String username, Order order)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
|4047 | order not found|
| 5001 | not enough balance |
| 5000 | database error |

### 4. cancel premium order
method: cancelPremiumOrder(Order order)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
|4047 | order not found|
| 5000 | database error |

### 5. create livelesson order
__NOTICE:__ this will book time for user and coach
method: createLiveLessonOrder(String username, Order order, LiveLesson liveLesson)
return: ReturnEntity(code, Order)
__the return order contains id, you need to use this to do the following procedure(pay, cancel)__
| return type | meaning |
|:--:|:--:|
| 200| successful|
|400|bad input time|
|4043|live lesson table not found|
|4044|coach not found|
|4042 | account not exist|
| 5000 | database error |

### 6. pay live lesson order
__NOTICE:__ this will change livelesson state
method: payLiveLessonOrder(String username, Order order, LiveLesson liveLesson, AtomicBoolean isFreeByPremium)
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
|4047 | order not found|
| 5001 | not enough balance |
|5002| no enough free lesson|
| 5000 | database error |

### 7. cancel live lesson order before payment
method: cancelLiveLessonOrderBeforePay(String username, LiveLesson liveLesson)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4043|live lesson table not found|
|4044|coach not found|
|4047 | order not found|
| 5000 | database error |

### 7. cancel live lesson order after payment
method: cancelLiveLessonOrderBeforePay(String username, LiveLesson liveLesson)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4042 | account not exist|
|4043|live lesson table not found|
|4044|coach not found|
|4047 | order not found|
| 5001 | not enough balance |
| 5000 | database error |

### 8. update a order(__Not Recommend__)
method: updateOrder(Order order)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
|4047 | order not found|
| 5000 | database error |

### 9. save a order(__Not Recommend__)
method: saveOrder(Order order)
return: int
| return type | meaning |
|:--:|:--:|
| 200| successful|
| 5000 | database error |
