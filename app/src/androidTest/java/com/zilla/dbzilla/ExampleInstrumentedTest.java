package com.zilla.dbzilla;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.github.snowdream.android.util.Log;
import com.zilla.dbzilla.db.DBHelper;
import com.zilla.dbzilla.db.ZillaDB;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @Before 该方法在每次测试方法调用前都会调用
 * @Test 说明了该方法需要测试
 * @BeforeClass 该方法在所有测试方法之前调用，只会被调用一次
 * @After 该方法在每次测试方法调用后都会调用
 * @AfterClass 该方法在所有测试方法之后调用，只会被调用一次
 * @Ignore 忽略该方法
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    ///////////////////demo
    @Before
    public void init() {
        Log.i("------method init called------");
    }

    @BeforeClass
    public static void prepareDataForTest() {
        Log.i("------method prepareDataForTest called------\n");
    }

    @Test
    public void test1() {
        Log.i("------method test1 called------");
    }

    @Test
    public void test2() {
        Log.i("------method test2 called------");
    }

    @Test
    public void test3() {
        Log.i("------method test3 called------");
    }

    @After
    public void clearDataForTest() {
        Log.i("------method clearDataForTest called------");
    }

    @AfterClass
    public static void finish() {
        Log.i("------method finish called------");
    }

    ///////////////////end
//    @Before
//    public void initDB() {
//        DBHelper.init(getApplication(), "test.db", 1);
//    }
//
//    @Test
//    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.zilla.dbzilla", appContext.getPackageName());
//    }
//
//    @Test
//    public void testSave() {
//        User user = new User();
//        user.setName("zhang");
//        user.setAddress("binghu");
//        user.setAge(10);
//        user.setSalary((short) 1);
//        user.setSalary2(0.1);
//        ZillaDB.getInstance().save(user);
//
//        Log.d("testSave", user.toString());
//    }
//
//    @Test
//    public void testSaveList() {
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
