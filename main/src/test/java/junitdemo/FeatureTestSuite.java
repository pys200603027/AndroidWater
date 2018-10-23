package junitdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hello.HelloJunitModelTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CalcutatorTest.class,
                HelloJunitModelTest.class,
                AssertTests.class
        }
)
/**
 * JUnit官方文档，展示了通过Suite批量执行测试用例的
 */
public class FeatureTestSuite {
}
