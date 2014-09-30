package com.joinsoft.mobile.cms.component;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * xingsen@join-cn.com
 */
@Component
public class VfsComponent {
    @Value("${config.vfs.root}")
    private String vfsPath;

    public VirtualFile getNodeImage(String nodeType, Long nodeId, String ext) {
        return getFile(nodeType.toLowerCase(), nodeId + "." + ext.toLowerCase());
    }

    public String saveProductImage(TbProduct product, MultipartFile file) {
        isImage(file);
        VirtualFile productImage = getProductImage(product, FilenameUtils.getExtension(file.getOriginalFilename()));
        try {
            IOUtils.copy(file.getInputStream(), productImage.getOutputStream(false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productImage.getRelativePath();

    }

    public String savePollImage(Long pollOptionValueId, MultipartFile file) {
        VirtualFile pollImage = getNodeImage(CmsContentType.Poll.name(), pollOptionValueId,
                FilenameUtils.getExtension(file.getOriginalFilename()));
        try {
            IOUtils.copy(file.getInputStream(), pollImage.getOutputStream(false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pollImage.getRelativePath();
    }

    public void deleteNodeImage(TbCmsNode node) {
        VirtualFile articleImage = getRoot().child(node.getPicPath());
        articleImage.delete();
    }

    public void deleteProductImage(TbProduct product) {
        VirtualFile productImage = getRoot().child(product.getPic());
        productImage.delete();
    }

    public String saveNodeImage(TbCmsNode node, MultipartFile file) {
        isImage(file);
        VirtualFile nodeImage = getNodeImage(node.getType(), node.getId(),
                FilenameUtils.getExtension(file.getOriginalFilename()));
        try {
            IOUtils.copy(file.getInputStream(), nodeImage.getOutputStream(false));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nodeImage.getRelativePath();
    }

    protected VirtualFile getRoot() {
        return new VirtualFile(new File(vfsPath), vfsPath);
    }

    protected VirtualFile getFile(String parent, String fileName) {
        VirtualFile productDir = getRoot().child(parent);
        if (!productDir.exists()) {
            productDir.mkdirs();
        }
        return productDir.child(fileName);
    }

    protected VirtualFile getProductImage(TbProduct product, String ext) {
        return getFile("product", product.getId() + "." + ext.toLowerCase());
    }

    protected VirtualFile getWifiCodeFile(String name, String ext) {
        return getFile("wifi", name + "." + ext.toLowerCase());
    }

    public VirtualFile saveWifiCodeFile(String name, MultipartFile file) {
        VirtualFile codeFile = getWifiCodeFile(name, FilenameUtils.getExtension(file.getOriginalFilename()));
        try {
            IOUtils.copy(file.getInputStream(), codeFile.getOutputStream(false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return codeFile;
    }

    public void isImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        if(!contentType.contains("image")){
            throw new RuntimeException("不是图片类型");
        }
    }
}
