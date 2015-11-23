package com.task.test.resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.task.test.controllerTest;

@RunWith(Suite.class)
@SuiteClasses({ResourceTest.class , controllerTest.class})
public class AllTests {

}
