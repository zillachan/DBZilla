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


### Performance
greenrobot.org/android/android-orm-performance-2016/
[链接]<https://github.com/greenrobot/android-database-performance>