package com.joinsoft.mobile.cms.service.mobile;

import com.joinsoft.framework.security.entity.Role;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.repository.RoleRepository;
import com.joinsoft.framework.security.repository.UserRepository;
import com.joinsoft.framework.security.service.SecurityServiceImp;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.component.eventbus.reward.SystemEventBound;
import com.joinsoft.mobile.cms.component.eventbus.reward.SystemEventInvite;
import com.joinsoft.mobile.cms.dto.RewardEventResult;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import com.joinsoft.mobile.cms.repository.UserProfileRepository;
import com.joinsoft.online.signin.ws.OnlineSso;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
public class MobileSecurityService extends SecurityServiceImp {
    private Logger logger = LoggerFactory.getLogger(MobileSecurityService.class);

    @Resource
    private OnlineSso onlineSso;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserProfileRepository userProfileRepository;
    @Resource
    private MobileService mobileService;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private RoleRepository roleRepository;

    public RewardEventResult loginAndBind(String mobilePhone, String password, String code) {
        User user = getLoginUser();
        if (null == user) {
            throw new RuntimeException("用户没有登录");
        }
        String openId = user.getLoginName();
        //热线已经存在
        onlineSso.login(mobilePhone, password);
        //我们本地已经存在
        TbUserProfile isBind = userProfileRepository.findByMobile(mobilePhone);
        if (isBind != null) {
            if (isBind.getMobile().equalsIgnoreCase(mobilePhone)) {
                throw new RuntimeException("您已经绑定成功，无须重复绑定");
            }
            throw new RuntimeException("您的手机号已经绑定到其他账号");
        }
        TbUserProfile haveBind = userProfileRepository.findByUser(user.getId());
        if (haveBind != null) {
            throw new RuntimeException("该帐号已经绑定，不能再绑定其他号码");
        }

        TbUserProfile profile = new TbUserProfile();
        profile.setUser(user);
        profile.setMobile(mobilePhone);
        userProfileRepository.save(profile);

        return eventBusService.fireRewardEvent(new SystemEventBound());
    }

    public void restoreAndBind(String mobilePhone, String newPassword, String verifyCode, String openId) {
        onlineSso.resetPassword(mobilePhone, newPassword, verifyCode);

        //如果已经在我们的数据库中，则说明已经绑定无需再去绑定了
        TbUserProfile userProfile = userProfileRepository.findByMobile(mobilePhone);
        if (null == userProfile) {
            loginAndBind(mobilePhone, newPassword, openId);
        }
    }

    public RewardEventResult regAndBind(String mobilePhone, String password, String verifyCode, String openId, String invite) {
        onlineSso.userReg(mobilePhone, password, verifyCode);
        return loginAndBind(mobilePhone, password, openId, invite);
    }

    public boolean ifBindThenLogin(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return false;
        }
        User user = userRepository.findByLoginName(openId);
        if (user == null) {
            return false;
        }
        TbUserProfile profile = userProfileRepository.findByUser(user.getId());
        if (profile == null) {
            return false;
        }
        // String name = mobileService.getNickName(openId);
        String name = null;
        logger.info("login: {}{}", openId, name);
        UsernamePasswordToken token = new UsernamePasswordToken(openId, openId);
        SecurityUtils.getSubject().login(token);
        return true;
    }

    public User registerUser(String openId) {
        //  String name = mobileService.getNickName(openId);
        String name = null;
        Role userRole = roleRepository.findByName("user");//普通用户
        if (userRole == null) {
            logger.warn("普通用户不存在,注册失败");
        }
        User user = userRepository.findByLoginName(openId);
        Long userId = null;
        if (user != null) {
            userId = user.getId();
        }
        logger.info("register: {}{}", openId, name);
        return this.registerOrUpdateUser(userId, openId, openId, name, new Long[]{userRole.getId()});
    }

    public void deleteUserFromOpenId(String openId) {
        User user = userRepository.findByLoginName(openId);
        this.deleteUser(new Long[]{user.getId()});
    }

    public boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole("admin");
    }

    public RewardEventResult loginAndBind(String mobilePhone, String password, String code, String invite) {
        RewardEventResult eventResult = loginAndBind(mobilePhone, password, code);
        if (!StringUtils.isEmpty(invite) && !mobilePhone.equals(invite)) {
            TbUserProfile userProfile = userProfileRepository.findByMobile(invite);
            if (null != userProfile && null != userProfile.getUser()) {
                eventBusService.fireRewardEvent(new SystemEventInvite(), userProfile.getUser().getId());
            }
        }
        return eventResult;
    }

    public boolean isBind() {
        if(1==1)
        return true;
        User user = getLoginUser();
        if (null == user) {
            throw new RuntimeException("用户没有登录");
        }
        TbUserProfile profile = userProfileRepository.findByUser(user.getId());
        if (profile == null) {
            return false;
        }
        return true;
    }
}
