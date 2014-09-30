package com.joinsoft.mobile.cms.service.content;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.mobile.cms.component.VfsComponent;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.form.CmsDialogSearchForm;
import com.joinsoft.mobile.cms.form.NodeEditForm;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Service
@Transactional
public class NodeService {
    private Logger logger = LoggerFactory.getLogger(NodeService.class);
    @Resource
    private CmsNodeRepository nodeRepository;
    @Resource
    private SecurityService securityService;
    @Resource
    private VfsComponent vfsComponent;

    public Page<TbCmsNode> searchContentForDialog(final CmsDialogSearchForm form, final Pageable pageable) {
        Sort sort = new Sort("type").and(new Sort(Sort.Direction.DESC, "createTime"));
        Pageable pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (ArrayUtils.isNotEmpty(form.getType())) {
                    Path expression = root.get("type");
                    predicates.add(expression.in(form.getType()));
                }
                if (!StringUtils.isEmpty(form.getTitle())) {
                    predicates.add(cb.like(root.get("title"), "%"+form.getTitle().trim()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<TbCmsNode> page = nodeRepository.findAll(specification, pageRequest);
        return page;
    }

    public Page<TbCmsNode> searchContent(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbCmsNode> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbCmsNode> page = nodeRepository.findAll(spec, pageable);
        return page;
    }

    public TbCmsNode newOrUpdateNode(NodeEditForm form, CmsContentType type) {
        TbCmsNode node = new TbCmsNode();
        node.setCreateTime(new Date());
        node.setType(type.name());
        node.setTypeDescription(type.getTitle());
        node.setAuthor(securityService.getLoginUser());
        if (form.getId() != null) {
            node = nodeRepository.findOne(form.getId());
        }
        node.setTitle(form.getTitle());
        node.setSummary(form.getSummary());
        node.setAuthorName(form.getAuthorName() == null ?
                securityService.getLoginUser().getName() : form.getAuthorName());
        node.setLastModified(new Date());
        nodeRepository.save(node);
        //保存图片
        MultipartFile file = form.getPic();
        if (file != null && !file.isEmpty()) {
            node.setPicPath(vfsComponent.saveNodeImage(node, file));
        }
        nodeRepository.save(node);
        return node;
    }

    public TbCmsNode getCmsNodeById(Long nodeId) {
        return nodeRepository.findOne(nodeId);
    }

    public void deleteCmsNode(Long[] ids) {
        if (ids != null && ids.length > 0) {
            for (Long id : ids) {
                TbCmsNode node = nodeRepository.findOne(id);
                vfsComponent.deleteNodeImage(node);
                nodeRepository.delete(node);
                logger.debug("deleted nodes: {} ", id);
            }
        }
    }
}
