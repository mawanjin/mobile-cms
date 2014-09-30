package com.joinsoft.mobile.cms.service.mobile;

import com.joinsoft.mobile.cms.dto.MobileMenuButton;
import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import com.joinsoft.mobile.cms.entity.enumerate.MobileMenuType;
import com.joinsoft.mobile.cms.form.MobileMenuEditForm;
import com.joinsoft.mobile.cms.repository.MobileMenuRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * xingsen@join-cn.com
 */
@Transactional
@Service
public class MobileMenuService {
    @Resource
    private MobileService mobileService;
    @Resource
    private MobileMenuRepository mobileMenuRepository;


    public void deleteMenuButton(Long id) {
        mobileMenuRepository.delete(id);
    }

    public void syncMobileMenu() {
        List<MobileMenuButton> buttons = new ArrayList<MobileMenuButton>();
        for (TbMobileMenu menu : getMenu()) {
            MobileMenuButton button = getButton(menu);
            buttons.add(button);
        }
        mobileService.saveMenu(buttons);
    }

    public List<TbMobileMenu> getMenu() {
        return mobileMenuRepository.findParentIdIsNull();
    }

    public TbMobileMenu getMenu(Long id) {
        return mobileMenuRepository.findOne(id);
    }

    public TbMobileMenu saveMenu(MobileMenuEditForm form) {
        TbMobileMenu menu = form.toEntity();
        return mobileMenuRepository.save(menu);
    }

    protected MobileMenuButton getButton(TbMobileMenu menu) {
        MobileMenuButton button = new MobileMenuButton();
        if (menu.getType() == MobileMenuType.View) {
            button.setType("view");
            button.setUrl(menu.getValue());
        } else {
            button.setType("click");
            button.setKey(menu.getValue());
        }
        button.setName(menu.getName());
        button.setSubButton(getSubButton(menu));
        return button;
    }

    protected List<MobileMenuButton> getSubButton(TbMobileMenu menu) {
        List<MobileMenuButton> buttons = new ArrayList<MobileMenuButton>();
        for (TbMobileMenu subMenu : menu.getSubMenu()) {
            MobileMenuButton button = getButton(subMenu);
            buttons.add(button);
        }
        return buttons;
    }
}
