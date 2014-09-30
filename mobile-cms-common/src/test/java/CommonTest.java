import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.joinsoft.mobile.cms.component.VirtualFile;
import com.joinsoft.mobile.cms.dto.echarts.RootOption;
import com.joinsoft.mobile.cms.dto.echarts.Title;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/8/24
 */
public class CommonTest {
    @Test
    public void testStringSub() {
        String source = "m_text_12";
        System.out.println(source.substring("m_text_".length()));
    }

    @Test
    public void testStringXml() {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        Element toUserNameElement = rootElement.addElement("ToUserName");
        toUserNameElement.addCDATA("UserName");
        Element fromUserNameElement = rootElement.addElement("FromUserName");
        fromUserNameElement.addCDATA("FromUserName");
        Element createTimeElement = rootElement.addElement("CreateTime");
        createTimeElement.setText(String.valueOf(new Date().getTime()));
        Element msgTypeElement = rootElement.addElement("MsgType");
        msgTypeElement.addCDATA("text");
        Element contentElement = rootElement.addElement("Content");
        contentElement.addCDATA("Content");
        Element articleCountElement = rootElement.addElement("ArticleCount");
        articleCountElement.setText(String.valueOf(1));
        Element articlesElement = rootElement.addElement("Articles");
        articlesElement.addElement("item").addElement("Title");
        System.out.println(document.asXML());
    }

    @Test
    public void testEChartsJsonOption() throws JsonProcessingException {
        RootOption option = new RootOption();
        Title title = new Title();
        title.setText("某站点用户访问来源");
        title.setSubtext("sub");
        option.setTitle(title);
        System.out.println(new ObjectMapper().writeValueAsString(option));
    }

    @Test
    public void testMapSetValue() throws InterruptedException {
        Map<Date, Long> stringLongMap = Maps.newHashMap();
        Date date1 = new Date();
        stringLongMap.put(date1, 1L);
        stringLongMap.put(date1, 2L);
        Thread.sleep(1000);
        Date date2 = new Date();
        stringLongMap.put(date2, 3L);
        stringLongMap.put(date2, 4L);
        System.out.println(stringLongMap);
    }

    @Test
    public void testVirtualFile(){
        VirtualFile virtualFile = new VirtualFile( new File("G:\\wang\\weixin-cms\\out\\upload\\Article"));
        List<VirtualFile> virtualFiles = virtualFile.list(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });

        System.out.println("ddd"+virtualFiles.size());

    }
}
