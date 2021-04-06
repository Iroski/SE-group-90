# ReturnEntity description
## introduce

对于需要返回实体类的每个接口，如果发生错误，则不可能仅传递错误代码。 如果仅传递null，则无法判断它是哪种错误，因此此类旨在处理需要返回实体的所有接口。
## Entity
```
int code
Object object
```
code对应的是接口的状态码，具体的类型在CommunicationStatus类中。每个接口的文档会给出可能会返回什么状态码，前端可以通过查看文档确定是什么错误。
Object对应的是返回的实体，可能为单一实体也可能为list。__前端要根据接口进行强制转换__。
例： User user=（User）userService.login(username,passwd);




# ReturnEntity description
## introduce

For each interface that needs to return an entity class, if an error occurs, it is impossible to only pass the error code. If only pass null, you can’t judge what kind of error it is, so this class is designed to deal with all the interfaces that need to return entities.
## Entity
```
int code
Object object
```
Code corresponds to the status code of the interface, and the specific type is in the CommunicationStatus class. The documentation of each interface will give what status code may be returned, and the front-end can determine what error is by looking at the documentation.
Object corresponds to the returned entity, which may be a single entity or a list. The front end must be forced to convert according to the interface.