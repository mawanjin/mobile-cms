package com.joinsoft.mobile.cms.entity.poll;

/**
 * User: wujun
 * Date: 2014/9/12
 */
public class PollUserCorrectCount {
    private Long userId;
    private boolean correct;
    private Long count;

    public PollUserCorrectCount(Long userId, Boolean correct, Long count) {
        this.userId = userId;
        this.correct = correct;
        this.count = count;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "PollUserCorrectCount{" +
                "userId=" + userId +
                ", correct=" + correct +
                ", count=" + count +
                '}';
    }
}
