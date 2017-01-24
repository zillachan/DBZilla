package com.zilla.dbzilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper.init(getApplication(), "test.db", 1);
//        testSave();
//        testSaveList();
    }

//    private void testSave() {
//        User user = new User();
//        user.setName("zhang");
//        user.setAddress("binghu");
//        user.setAge(10);
//        user.setSalary((short) 1);
//        user.setSalary2(0.1);
//        ZillaDB.getInstance().save(user);
//
//
//        Log.d("testSave", user.toString());
//    }
//
//
//    private void testSaveList() {
//
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            User user = new User();
//            user.setName("zhang");
//            user.setAddress("binghu");
//            user.setAge(i);
//            user.setSalary2(0.1 + i);
//            list.add(user);
//        }
//        long begin = System.currentTimeMillis();
//        ZillaDB.getInstance().saveList(list);
//        long end = System.currentTimeMillis();
//        Log.d("save 10000 items, use times:" + (end - begin) + "");
//    }
}
