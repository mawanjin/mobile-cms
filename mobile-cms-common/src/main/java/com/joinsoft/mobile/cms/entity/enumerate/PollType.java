package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public enum PollType {

    CommonPoll("投票"),
    SurveyScore("问卷调查计分型"),
    SurveyCorrect("问卷调查无误型");
    private String text;

    PollType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
