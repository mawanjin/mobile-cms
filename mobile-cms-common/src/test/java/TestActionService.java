import com.joinsoft.mobile.cms.service.ActionService;
import com.joinsoft.mobile.cms.service.RewardService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@ContextConfiguration(value = {"classpath:/config/core/*.xml", "classpath:/config/component/*.xml"})
public class TestActionService extends TestCase {
    @Resource
    private ActionService actionService;
    @Resource
    private DefaultSecurityManager securityManager;
    @Resource
    private RewardService rewardService;

    @Test
    public void test() {
        ThreadContext.bind(securityManager);
        AuthenticationToken token = new UsernamePasswordToken("oXM8DuJ8K9kVqy1O3iCWRZdoGaDY", "oXM8DuJ8K9kVqy1O3iCWRZdoGaDY");
        SecurityUtils.getSubject().login(token);

        int score = actionService.calcAction("user.sign");
        /*while (score != 0) {
            System.out.println(score);
            score = actionService.calcAction("user.sign");
        }*/
        System.out.println(score);
    }

    @Test
    public void testReward(){

        rewardService.fireEvent("user.sign", "oXM8DuJjAoKkM-638smVE5CYgmN0");

    }
}
