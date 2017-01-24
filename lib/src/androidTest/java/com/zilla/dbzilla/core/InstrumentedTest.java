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

package com.zilla.dbzilla.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.snowdream.android.util.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.zilla.dbzilla.core.test", appContext.getPackageName());
    }

    @Before
    public void initDB() {
        DBHelper.init(InstrumentationRegistry.getContext().getApplicationContext(), "test.db", 1);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("zhang");
        user.setAddress("binghu");
        user.setAge(10);
        user.setSalary((short) 1);
        user.setSalary2(0.1);
        ZillaDB.getInstance().save(user);


        User localUser = ZillaDB.getInstance().findById(User.class, user.getId());

        Log.d("testSave", localUser.toString());
        System.out.print("testSave==" + localUser.toString());
        assertEquals(user.getId(), localUser.getId());
        assertEquals(user.getName(), localUser.getName());
        assertEquals(user.getAddress(), localUser.getAddress());
        assertEquals(user.getAge(), localUser.getAge());
        assertEquals(user.getBirth(), localUser.getBirth());
        assertEquals(user.getMail(), localUser.getMail());
//        assertEquals(user.getSalary(), localUser.getSalary());
//        assertEquals(user.getSalary2(), localUser.getSalary2());

    }

    @Test
    public void testSaveList() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("zhang");
            user.setAddress("binghu");
            user.setAge(i);
            user.setSalary2(0.1 + i);
            list.add(user);
        }
        long begin = System.currentTimeMillis();
        ZillaDB.getInstance().saveList(list);
        long end = System.currentTimeMillis();
        Log.d("save 10000 items, use times:" + (end - begin) + "");
    }
}
