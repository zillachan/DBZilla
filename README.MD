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
<http://greenrobot.org/android/android-orm-performance-2016/>
<https://github.com/greenrobot/android-database-performance>

### Annotation

<http://www.open-open.com/lib/view/open1476329129826.html>
<https://github.com/JakeWharton/butterknife>