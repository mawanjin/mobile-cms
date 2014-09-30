import com.joinsoft.mobile.cms.service.AutoReplyService;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by wangxulong on 14-9-9.
 */
public class AutoReplyServiceTest extends TestCase {
    @Resource
    private AutoReplyService autoReplyService;
    @Resource
    private MobileService mobileService;

    @Test
    public void testKeyword(){
        autoReplyService.getByKeyword("test");
    }

    @Test
    public void testGroup(){
        mobileService.getAllGroup();
    }


}
