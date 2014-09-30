import com.joinsoft.mobile.cms.task.SyncTrafficTask;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by wangxulong on 14-9-23.
 */
public class TaskTest extends TestCase {
    @Resource
    private SyncTrafficTask trafficTask;

    @Test
    public void test(){
        trafficTask.execute();
    }
}
