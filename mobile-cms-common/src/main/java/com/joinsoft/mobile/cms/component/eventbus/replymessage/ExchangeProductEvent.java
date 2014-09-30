package com.joinsoft.mobile.cms.component.eventbus.replymessage;

/**
 * Created by wangxulong on 14-9-23.
 */
public class ExchangeProductEvent extends ReplyMessageEvent {
    public ExchangeProductEvent(String openId) {
        super(openId, "exchange.product", "兑换礼品");
    }

    public ExchangeProductEvent() {
        this("");
    }
}
