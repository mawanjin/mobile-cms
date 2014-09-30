package com.joinsoft.mobile.cms.service.poll;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.PollException;
import com.joinsoft.mobile.cms.component.VfsComponent;
import com.joinsoft.mobile.cms.dto.echarts.*;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionType;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionValueType;
import com.joinsoft.mobile.cms.entity.enumerate.PollStatus;
import com.joinsoft.mobile.cms.entity.enumerate.PollType;
import com.joinsoft.mobile.cms.entity.poll.*;
import com.joinsoft.mobile.cms.form.PollEditForm;
import com.joinsoft.mobile.cms.repository.poll.*;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.NodeService;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import com.joinsoft.mobile.cms.util.SqlResultSetMappingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: wujun
 * Date: 2014/8/14
 */
@Service
@Transactional
public class PollService {
    private Logger logger = LoggerFactory.getLogger(PollService.class);

    @Resource
    private PollRepository pollRepository;
    @Resource
    private PollVisibilityRepository pollVisibilityRepository;
    @Resource
    private PollOptionRepository pollOptionRepository;
    @Resource
    private PollOptionValueRepository pollOptionValueRepository;
    @Resource
    private MobileSecurityService mobileSecurityService;
    @Resource
    private PollOptionValueRecordRepository pollOptionValueRecordRepository;
    @Resource
    private VfsComponent vfsComponent;
    @Resource
    private NodeService nodeService;
    @Resource
    private PollRecordRepository pollRecordRepository;
    @Resource
    private PollOptionRecordRepository pollOptionRecordRepository;

    public Page<PollEditForm> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, Class> classMap = Maps.newHashMap();
        classMap.put("type", PollType.class);
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams, classMap);
        Specification<TbPoll> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbPoll> page = pollRepository.findAll(spec, pageable);
        List<PollEditForm> formList = Lists.newArrayList();
        for (TbPoll poll : page.getContent()) {
            PollEditForm form = new PollEditForm();
            form.fromEntity(poll);
            formList.add(form);
        }
        return new PageImpl<PollEditForm>(formList, pageable, page.getTotalElements());
    }

    public TbPoll getPollByMeta(Long nodeId) {
        TbPoll poll = pollRepository.findByMeta(nodeId);
        Assert.notNull(poll, "投票不存在");
        autoFixPollStatus(poll);
        return poll;
    }

    public TbPoll getPollById(Long pollId) {
        TbPoll poll = pollRepository.findOne(pollId);
        Assert.notNull(poll, "投票不存在");
        autoFixPollStatus(poll);
        return poll;
    }

    public PollEditForm getPollForm(Long nodeId) {
        TbPoll poll;
        if (nodeId == null) {
            poll = new TbPoll();
            poll.setStartTime(new Date());
            TbCmsNode node = new TbCmsNode();
            node.setAuthor(mobileSecurityService.getLoginUser());
            Date now = new Date();
            node.setLastModified(now);
            node.setCreateTime(now);
            node.setType(CmsContentType.Poll.name());
            node.setTypeDescription(CmsContentType.Poll.getTitle());
            node.setAuthorName(mobileSecurityService.getLoginUser().getName());
            poll.setNode(node);
        } else {
            poll = getPollByMeta(nodeId);
        }
        PollEditForm form = new PollEditForm();
        form.fromEntity(poll);
        return form;
    }

    public void publishOrEditPoll(PollEditForm form) {
        TbPoll poll;
        if (form.getId() != null) {
            poll = getPollByMeta(form.getId());
        } else {
            poll = new TbPoll();
        }
        TbCmsNode node = nodeService.newOrUpdateNode(form, CmsContentType.Poll);
        poll = form.toEntity(poll, node);
        pollRepository.save(poll);
    }

    public void delete(Long[] ids) {
        nodeService.deleteCmsNode(ids);
    }

    public Page<TbPollOption> searchPollOptions(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbPollOption> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return pollOptionRepository.findAll(spec, pageable);
    }

    public Page<TbPollOption> getPollOptions(Long pollNodeId, Pageable pageable) {
        return pollOptionRepository.findByPollNodeId(pollNodeId, pageable);
    }

    public List<TbPollOption> getAllPollOptions(Long pollNodeId) {
        return pollOptionRepository.findByPollNodeId(pollNodeId);
    }

    public TbPollOption getPollOption(Long optionId) {
        return pollOptionRepository.findOne(optionId);
    }

    public void savePollOption(Long optionId, String title, PollOptionType type, Float score, Long pollId) {
        TbPollOption pollOption;
        if (optionId != null) {
            pollOption = pollOptionRepository.findOne(optionId);
            Assert.notNull(pollOption, "投票选项不存在");
            if (PollOptionType.Text != pollOption.getType() && PollOptionType.Text == type) {
                //clear Radio or CheckBox values
                pollOptionValueRepository.deleteByPollOption_Id(optionId);
            }
        } else {
            pollOption = new TbPollOption();
        }
        pollOption.setTitle(title);
        pollOption.setType(type);
        pollOption.setScore(score);
        TbPoll poll = new TbPoll();
        poll.setId(pollId);
        pollOption.setPoll(poll);
        pollOptionRepository.save(pollOption);
    }

    public void deletePollOption(Long[] optionIds) {
        if (optionIds != null && optionIds.length > 0) {
            pollOptionRepository.deleteInId(Arrays.asList(optionIds));
            logger.debug("deleted poll options: {} ", optionIds);
        }
    }

    public List<TbPollOptionValue> getAllPollOptionValues(Long optionId) {
        return pollOptionValueRepository.findByPollOption_Id(optionId);
    }

    public TbPollOptionValue getPollOptionValue(Long valueId) {
        TbPollOptionValue pollOptionValue = pollOptionValueRepository.findOne(valueId);
        if (pollOptionValue != null) {
            if (pollOptionValue.getType() == PollOptionValueType.Pic) {
                pollOptionValue.setPic(pollOptionValue.getValue());
            } else if (pollOptionValue.getType() == PollOptionValueType.Video) {
                pollOptionValue.setVideo(pollOptionValue.getValue());
            }
        }
        return pollOptionValue;
    }

    public void saveValue2PollOption(Long valueId, PollOptionValueType type, String value, MultipartFile picFile,
                                     String video, Boolean answer, Long pollOptionId) {
        TbPollOptionValue pollOptionValue;
        if (valueId != null) {
            pollOptionValue = getPollOptionValue(valueId);
            Assert.notNull(pollOptionValue, "投票选项值不存在");
        } else {
            pollOptionValue = new TbPollOptionValue();
            TbPollOption pollOption = new TbPollOption();
            pollOption.setId(pollOptionId);
            pollOptionValue.setPollOption(pollOption);
        }
        if (answer == null) {
            answer = false;
        }
        pollOptionValue.setAnswer(answer);
        pollOptionValue.setType(type);
        if (type == PollOptionValueType.Video) {
            value = video;
        }
        pollOptionValue.setValue(value);
        pollOptionValue = pollOptionValueRepository.save(pollOptionValue);
        if (type == PollOptionValueType.Pic && !picFile.isEmpty()) {
            value = vfsComponent.savePollImage(pollOptionValue.getId(), picFile);
            pollOptionValue.setValue(value);
            pollOptionValueRepository.save(pollOptionValue);
        }
    }

    public void deletePollOptionValue(Long[] valueIds) {
        if (valueIds != null && valueIds.length > 0) {
            pollOptionValueRepository.deleteInId(Arrays.asList(valueIds));
            logger.debug("deleted poll option values: {} ", valueIds);
        }
    }

    public RootOption getOptionEChartData(Long optionId) {
        TbPollOption tbPollOption = pollOptionRepository.findOne(optionId);
        if (tbPollOption == null) {
            logger.warn("id: {} PollOption does not exist.", optionId);
            return null;
        }
        List<Object[]> records = pollOptionValueRecordRepository.findCountAndValueIdByPollOptionId(optionId);
        if (records == null || records.size() < 1) {
            logger.warn("id: {} PollOption does not exist any records.", optionId);
            return null;
        }
        List<PollOptionValueRecordCount> pollOptionValueRecordCounts = SqlResultSetMappingUtil.map(PollOptionValueRecordCount.class,
                records);
        Map<TbPollOptionValue, Long> pollOptionValueCountMap = Maps.newHashMap();
        if (tbPollOption.getType() == PollOptionType.Radio || tbPollOption.getType() == PollOptionType.CheckBox) {
            //统计每个选项值的选中次数
            Set<TbPollOptionValue> pollOptionValueList = tbPollOption.getValues();
            TbPollOptionValue tbPollOptionValue = null;
            for (PollOptionValueRecordCount pollOptionValueRecordCount : pollOptionValueRecordCounts) {
                for (TbPollOptionValue pollOptionValue : pollOptionValueList) {
                    if (pollOptionValueRecordCount.getPollOptionValueId() == pollOptionValue.getId()) {
                        tbPollOptionValue = pollOptionValue;
                        break;
                    }
                }
                if (tbPollOptionValue != null) {
                    pollOptionValueCountMap.put(tbPollOptionValue, pollOptionValueRecordCount.getCount());
                }
            }
        }

        //title
        Title title = new Title();
        title.setText(tbPollOption.getTitle());
//        title.setSubtext("");
        //legend and series
        Legend legend = new Legend();
        PieSeries series = new PieSeries();
        series.setName("投票统计");
        series.setCenter(new String[]{"50%", "60%"});
        series.setRadius("55%");
        for (Map.Entry<TbPollOptionValue, Long> pollOptionValueLongEntry : pollOptionValueCountMap.entrySet()) {
            String value = pollOptionValueLongEntry.getKey().getValue();
            legend.addData(value);
            String countStr = String.valueOf(pollOptionValueLongEntry.getValue());
            series.addData(new ASeriesData(value, countStr));
        }

        RootOption option = new RootOption();
        option.setTitle(title);
        option.setLegend(legend);
        option.setSeries(Lists.newArrayList(series));
        return option;
    }

    public boolean isSubmitEnable() {
        return !mobileSecurityService.isAdmin();
    }

    public void savePollRecord(Long pollId, Map<String, Object> valueParams) throws PollException {
        TbPoll poll = getPollById(pollId);
        Assert.notNull(poll);
        if (poll.getStatus() != PollStatus.Running) {
            throw new PollException("投票状态是" + poll.getStatus().getText());
        }
        User user = mobileSecurityService.getLoginUser();
        //如果对投票已经设置了访问权限，检查当前用户是否有权限参与
        if (pollVisibilityRepository.countByPollId(pollId) != 0 &&
                pollVisibilityRepository.countByPollIdAndUserId(pollId, user.getId()) == 0) {
            throw new PollException("对不起，您没有权限参与");
        }
        if (pollRecordRepository.countByPollIdAndUserId(pollId, user.getId()) > 0) {
            throw new PollException("对不起，不能重复参与");
        }
        TbPollRecord pollRecord = new TbPollRecord();
        pollRecord.setPoll(poll);
        pollRecord.setUser(user);
        pollRecord = pollRecordRepository.save(pollRecord);

        List<TbPollOptionRecord> pollOptionRecordList = Lists.newArrayList();
        for (Map.Entry<String, Object> valueParam : valueParams.entrySet()) {
            String key = valueParam.getKey();
            TbPollOption pollOption = new TbPollOption();
            TbPollOptionRecord pollOptionRecord = new TbPollOptionRecord();
            pollOptionRecord.setPollRecord(pollRecord);
            Long optionId = 0L;
            boolean correct = false;
            Set<TbPollOptionValueRecord> pollOptionValueRecordSet = Sets.newHashSet();
            if (key.startsWith("checkbox_")) {
                optionId = Long.valueOf(key.substring("checkbox_".length()));
                String[] optionValueIds;
                if (valueParam.getValue() instanceof String) {
                    optionValueIds = new String[]{(String) valueParam.getValue()};
                } else {
                    optionValueIds = (String[]) valueParam.getValue();
                }
                int submitAnswer = 0;
                for (String optionValueIdStr : optionValueIds) {
                    Long valueId = Long.valueOf(optionValueIdStr);
                    TbPollOptionValue pollOptionValue = pollOptionValueRepository.findOne(valueId);
                    Assert.notNull(pollOptionValue);
                    if (pollOptionValue.getAnswer()) {
                        submitAnswer++;
                    }
                    TbPollOptionValueRecord pollOptionValueRecord = new TbPollOptionValueRecord();
                    pollOptionValueRecord.setOptionRecord(pollOptionRecord);
                    pollOptionValueRecord.setOptionValue(pollOptionValue);
                    pollOptionValueRecordSet.add(pollOptionValueRecord);
                }
                if (poll.getType() == PollType.SurveyScore) {
                    pollOption = pollOptionRepository.findOne(optionId);
                    int answer = 0;
                    for (TbPollOptionValue pollOptionValue : pollOption.getValues()) {
                        if (pollOptionValue.getAnswer()) {
                            answer++;
                        }
                    }
                    correct = (submitAnswer == answer);
                }
            } else if (key.startsWith("radio_")) {
                optionId = Long.valueOf(key.substring("radio_".length()));
                Long optionValueId = Long.valueOf((String) valueParam.getValue());
                TbPollOptionValue pollOptionValue = pollOptionValueRepository.findOne(optionValueId);
                Assert.notNull(pollOptionValue);
                correct = pollOptionValue.getAnswer();
                TbPollOptionValueRecord pollOptionValueRecord = new TbPollOptionValueRecord();
                pollOptionValueRecord.setOptionRecord(pollOptionRecord);
                pollOptionValueRecord.setOptionValue(pollOptionValue);
                pollOptionValueRecordSet.add(pollOptionValueRecord);
            } else if (key.startsWith("text_")) {
                optionId = Long.valueOf(key.substring("text_".length()));
                TbPollOptionValueRecord pollOptionValueRecord = new TbPollOptionValueRecord();
                pollOptionValueRecord.setOptionRecord(pollOptionRecord);
                pollOptionValueRecord.setTextValue((String) valueParam.getValue());
                pollOptionValueRecordSet.add(pollOptionValueRecord);
            }
            pollOptionRecord.setValueRecords(pollOptionValueRecordSet);
            pollOptionRecord.setCorrect(correct);
            pollOption.setId(optionId);
            pollOptionRecord.setPollOption(pollOption);
            pollOptionRecordList.add(pollOptionRecord);
        }
        pollOptionRecordRepository.save(pollOptionRecordList);
    }

    protected void autoFixPollStatus(TbPoll poll) {
        if (poll.getStatus() != PollStatus.Canceled) {
            Date now = new Date();
            PollStatus status = poll.getStatus();
            if (now.after(poll.getEndTime())) {
                status = PollStatus.Finished;
            } else if (now.after(poll.getStartTime())) {
                status = PollStatus.Running;
            }
            if (status != poll.getStatus()) {
                poll.setStatus(status);
                poll = pollRepository.save(poll);
                logger.debug("auto fix the status of poll[{}] to {}", poll.getId(), poll.getStatus());
            }
        }
    }
}
