package com.joinsoft.mobile.cms.dto;

import com.joinsoft.framework.security.entity.User;

/**
 * User: wujun
 * Date: 2014/9/12
 */
public class UserRewardDto extends User {
    private Long correct = 0L;
    private Long wrong = 0L;
    private boolean reward = false;

    public Long getCorrect() {
        return correct;
    }

    public void increaseCorrect(Long correct) {
        this.correct += correct;
    }

    public Long getWrong() {
        return wrong;
    }

    public void increaseWrong(Long wrong) {
        this.wrong += wrong;
    }

    public boolean isReward() {
        return reward;
    }

    public void setReward(boolean reward) {
        this.reward = reward;
    }
}
