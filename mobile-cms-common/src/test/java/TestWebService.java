import com.joinsoft.online.signin.ws.OnlineSso;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
public class TestWebService extends TestCase {
    @Resource
    private OnlineSso onlineSso;
    private String mobilePhone = "";

    @Test
    public void testWsConnection() {
        System.out.println(onlineSso.userExist(mobilePhone));
    }

    @Test
    public void register() {
        String verifyCode = onlineSso.sendVerifyCode(mobilePhone);
        System.out.println(verifyCode);
        onlineSso.userReg(mobilePhone, "123", verifyCode);
    }

    @Test
    public void testLogin() {
        onlineSso.login(mobilePhone, "123");
    }

    @Test
    public void testReset() {
        String verifyCode = onlineSso.sendVerifyCode(mobilePhone);
     //   onlineSso.resetPassword(mobilePhone, "123", verifyCode);
    }

    @Test
    public void testSendVerify(){
       // onlineSso.sendVerifyCode("13321870879");
    }
}
