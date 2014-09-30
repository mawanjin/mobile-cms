import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.service.WifiCodeService;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by wangxulong on 14-9-13.
 */
public class MassTest extends TestCase {

    @Resource
    private ResponseMessageBuilder responseMessageBuilder;

    @Resource
    private WifiCodeService wifiCodeService;

    @Test
    public void test(){
        responseMessageBuilder.sendArticleGroupForMass(0L,74L);
    }

    @Test
    public void testWifiCode(){
        File file = new File("C:\\test.csv");
        //wifiCodeService.importWifiCode(file);
    }
    @Test
    public void sendNews(){
        responseMessageBuilder.buildAndSendNews("oXM8DuJjAoKkM-638smVE5CYgmN0",new Long("74"));
    }
}
