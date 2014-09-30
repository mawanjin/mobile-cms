import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * xingsen@join-cn.com
 */
@ContextConfiguration(value = {"classpath:/config/core/*.xml", "classpath:/config/component/*.xml"})
public class TestCase extends AbstractJUnit4SpringContextTests {
}
