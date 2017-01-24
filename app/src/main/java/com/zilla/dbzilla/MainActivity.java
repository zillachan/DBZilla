/*
 * Copyright 2017. Zilla Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
