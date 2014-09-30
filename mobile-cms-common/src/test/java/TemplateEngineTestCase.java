import com.joinsoft.framework.security.entity.Role;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.entity.UserRole;
import com.joinsoft.mobile.cms.component.template.VelocityTemplate;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * xingsen@join-cn.com
 */
public class TemplateEngineTestCase extends TestCase {
    @Resource
    private VelocityTemplate velocityTemplate;

    @Test
    public void test() {
        Map<String, Object> model = new HashMap<String, Object>();
        User user = new User();
        user.setName("邢森");
        user.setLoginName("aaa");
        Set<UserRole> rolSet = new HashSet<UserRole>();
        UserRole ur = new UserRole();
        Role role = new Role();
        role.setName("管理员");
        ur.setRole(role);
        rolSet.add(ur);
        user.setRoleSet(rolSet);
        model.put("user", user);
        String message = velocityTemplate.renderText("xx", model);
        System.out.println(message);
    }
}
