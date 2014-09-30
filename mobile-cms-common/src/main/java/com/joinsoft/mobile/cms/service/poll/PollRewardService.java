package com.joinsoft.mobile.cms.service.poll;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.dto.UserRewardDto;
import com.joinsoft.mobile.cms.entity.enumerate.PollType;
import com.joinsoft.mobile.cms.entity.poll.PollUserCorrectCount;
import com.joinsoft.mobile.cms.entity.poll.TbPollRecord;
import com.joinsoft.mobile.cms.repository.poll.PollOptionRecordRepository;
import com.joinsoft.mobile.cms.repository.poll.PollRecordRepository;
import com.joinsoft.mobile.cms.util.SqlResultSetMappingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/9/12
 */
@Service
@Transactional
public class PollRewardService {
    private Logger logger = LoggerFactory.getLogger(PollRewardService.class);
    @Resource
    private PollRecordRepository pollRecordRepository;
    @Resource
    private PollOptionRecordRepository pollOptionRecordRepository;

    public Page<UserRewardDto> searchUserRewardPage(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, Class> classMap = Maps.newHashMap();
        classMap.put("reward", Boolean.class);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams, classMap);
        Specification<TbPollRecord> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbPollRecord> pollRecordPage = pollRecordRepository.findAll(spec, pageable);
        List<Long> userIds = Lists.newArrayList();
        Map<Long, UserRewardDto> userRewardDtoMap = Maps.newHashMap();
        for (TbPollRecord pollRecord : pollRecordPage.getContent()) {
            UserRewardDto userRewardDto = new UserRewardDto();
            BeanUtils.copyProperties(pollRecord.getUser(), userRewardDto);
            userRewardDto.setReward(pollRecord.isReward());
            userRewardDtoMap.put(userRewardDto.getId(), userRewardDto);
            userIds.add(userRewardDto.getId());
        }
        //SurveyScore 类型才统计回答正确/一共回答
        if (pollRecordPage.getNumberOfElements() > 0 && PollType.SurveyScore == pollRecordPage.getContent().get(0).getPoll().getType()) {
            List<PollUserCorrectCount> pollUserCorrectCountList = Lists.newArrayList();
            List<Object[]> counts = Lists.newArrayList();
            if (userIds.size() > 0) {
                counts = pollOptionRecordRepository.findCorrectCountByUserIdIn(userIds);
            }
            if (counts.size() > 0) {
                pollUserCorrectCountList = SqlResultSetMappingUtil.map(PollUserCorrectCount.class, counts);
            }
            for (PollUserCorrectCount pollUserCorrectCount : pollUserCorrectCountList) {
                UserRewardDto userRewardDto = userRewardDtoMap.get(pollUserCorrectCount.getUserId());
                if (pollUserCorrectCount.isCorrect()) {
                    userRewardDto.increaseCorrect(pollUserCorrectCount.getCount());
                } else {
                    userRewardDto.increaseWrong(pollUserCorrectCount.getCount());
                }
            }
        }
        return new PageImpl<UserRewardDto>(Lists.newArrayList(userRewardDtoMap.values()), pageable, pollRecordPage.getTotalElements());
    }

    public void reward(Long pollId, Long[] ids) {
        Assert.notEmpty(ids);
        pollRecordRepository.updateRewardByPollIdAndUserIdIn(true, pollId, Lists.newArrayList(ids));
        logger.debug("poll: {}, reward for users: {}", pollId, Arrays.toString(ids));
    }

    public void reward(Long pollId) {
        pollRecordRepository.updateRewardByPollIdAndAllUser(true, pollId);
        logger.debug("poll: {}, all users have been rewarded", pollId);
    }
}
