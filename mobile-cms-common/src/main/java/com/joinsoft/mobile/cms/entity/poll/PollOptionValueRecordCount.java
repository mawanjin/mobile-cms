package com.joinsoft.mobile.cms.entity.poll;

import java.math.BigInteger;

/**
 * User: wujun
 * Date: 2014/9/2
 */
public class PollOptionValueRecordCount {
    private Long count;
    private Long pollOptionValueId;

    public PollOptionValueRecordCount(BigInteger count, BigInteger pollOptionValueId) {
        if (count != null) {
            this.count = count.longValue();
        }
        if (pollOptionValueId != null) {
            this.pollOptionValueId = pollOptionValueId.longValue();
        }
    }

    public Long getCount() {
        return count;
    }

    public Long getPollOptionValueId() {
        return pollOptionValueId;
    }
}
