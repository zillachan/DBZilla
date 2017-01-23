package com.zilla.dbzilla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import com.zilla.dbzilla.db.DBHelper;
import com.zilla.dbzilla.db.ZillaDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.init(getApplication(),"test.db",1);
        testSave();

    }

    private void testSave() {
        User user = new User();
        user.setName("zhang");
        user.setAddress("binghu");
        user.setAge(10);
        user.setSalary((short) 1);
        user.setSalary2(0.1);
        ZillaDB.getInstance().save(user);


        Log.d("testSave",user.toString());
    }
}
