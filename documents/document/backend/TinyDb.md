# Data House usage instructions

## Data House Design

- There are three classes in data house, **DataItem, DataBase, DataTable**

## Usage

### DataItem

- Have a primary key of type **Long**
- DataItem is the data in each DataTable, customized user defined data needs to inherit DataItem, and all the attributed need to have `.toString()` method.

### DataBase

- DataBase is a Singleton mode class which provides static add, update, delete, query function.
- Its table name is the same with the DataItem class' simpleClassName

#### Initiate and config

- Initiate the path of the database

```java
db = DataBase.getInstance();
db.init("src/test/resources/database");
```

#### Add Table

- `addTable(customize class)`

#### Delete Table

- `delTable(String)`

#### Query

- `query(String, HashMap<String, String))`
- return `List<DataItem>`
- support **=** query, pass the arguments through hashmap

#### Blur Query

- Set the HashMap's key to **HAS** and the value contains:
  - `ATTRIBUTENAME VALUE`

#### Delete

- `delete(Long)`

#### Modify

- `update(String, Long, customize class's object)`

#### flush

- flush current table's data into file, default calling after each operation.

