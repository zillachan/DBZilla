### init
```
DBHelper.init(getApplication(),"test.db",1);
```
### Useage

- save model
```
User user = new User();
user.setName("zhang");
user.setAddress("binghu");
ZillaDB.getInstance().save(user);
```