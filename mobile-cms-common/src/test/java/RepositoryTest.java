import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.entity.poll.PollUserCorrectCount;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;
import com.joinsoft.mobile.cms.repository.poll.PollOptionRecordRepository;
import com.joinsoft.mobile.cms.repository.poll.PollOptionValueRecordRepository;
import com.joinsoft.mobile.cms.repository.poll.PollRepository;
import com.joinsoft.mobile.cms.util.SqlResultSetMappingUtil;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/9/2
 */
public class RepositoryTest extends TestCase {
    @Resource
    private PollOptionValueRecordRepository pollOptionValueRecordRepository;
    @Resource
    private PollRepository pollRepository;
    @Resource
    private PollOptionRecordRepository pollOptionRecordRepository;

    @Test
    public void testValueRecordRepositoryCountNativeQuery() {
        List<Object[]> pollOptionValueRecords = pollOptionValueRecordRepository.findCountAndValueIdByPollOptionId(2L);
        for (Object[] pollOptionValueRecord : pollOptionValueRecords) {
            System.out.println(pollOptionValueRecord[0]);
        }
    }

    @Test
    public void testDynamicSpecifications() {
        Map<String, Object> searchParams = Maps.newHashMap();
        searchParams.put("NOTIN_id", Lists.newArrayList(1L, 2L));
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbPoll> spec = DynamicSpecifications.bySearchFilter(filters.values());
        List<TbPoll> polls = pollRepository.findAll(spec);
        System.out.println(polls.size());
    }

    @Test
    public void testPollOptionRecordRepository() {
        List<Object[]> pollUserCorrectCounts = pollOptionRecordRepository.findCorrectCountByUserIdIn(Lists.newArrayList(17L, 20L, 21L));
        System.out.println(pollUserCorrectCounts.size());
        List<PollUserCorrectCount> pollUserCorrectCountList = null;
        if (pollUserCorrectCounts.size() > 0) {
            pollUserCorrectCountList = SqlResultSetMappingUtil.map(PollUserCorrectCount.class, pollUserCorrectCounts);
            for (PollUserCorrectCount pollUserCorrectCount : pollUserCorrectCountList) {
                System.out.println(pollUserCorrectCount);
            }
        }
    }
}
