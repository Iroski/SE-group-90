# 数据持久储存使用方法
## 数据持久储存设计
- 数据库内共有三个类，分别为**DataItem, DataBase, DataTable**
## 使用方法
### DataItem
- 自带Long格式的主键
- DataItem为每个DataTable内的数据，用户自定义的数据项需要继承DataItem，并且所有的数据项都需要提供``.toString()``方法，用于查询比较。
### DataBase
- DataBase为单例模式的数据库入口，提供了基础增删改查的功能
- 其中表的名字和类的simpleName相同。
#### 初始化与配置
- 初始化数据储存的目录
````
db = DataBase.getInstance();
db.init("src/test/resources/database");
````
#### 添加表
- ``addTable(customize class)``
- 传入自定义类的class
#### 删除表
- ``delTable(String)``
#### 查询
- ``query(String, HashMap<String, String>)``
- 返回值``ArrayList<DataItem>``
- 如果根据id(primary key)查询，速度为O(1), 否则为O(N)。
- 目前只支持相等查询，使用hashmap传参。
#### 增加
- ``add(String, customize class's object)``
#### 删除
- ``delete(Long)``
#### 修改
- ``update(String, Long, customize class's object)``
#### flush
- 将当前table内数据写入文件，增删查改结束后默认调用。
