package com.zilla.dbzilla.core;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    @Before
    public void init() {
        System.out.println("------method init called------");
    }

    @BeforeClass
    public static void prepareDataForTest() {
        System.out.println("------method prepareDataForTest called------");
    }

    @Test
    public void test1() {
        System.out.println("------method test1 called------");
    }

    @Test
    public void test2() {
        System.out.println("------method test2 called------");
    }

    @Test
    public void test3() {
        System.out.println("------method test3 called------");
    }

    @After
    public void clearDataForTest() {
        System.out.println("------method clearDataForTest called------");
    }

    @AfterClass
    public static void finish() {
        System.out.println("------method finish called------");
    }
}