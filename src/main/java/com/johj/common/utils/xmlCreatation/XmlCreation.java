package com.johj.common.utils.xmlCreatation;


import org.apache.log4j.Logger;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.jdom.*;
/**
 * author:zhou_wenwei
 * mail:
 * date:2017/11/8
 * description: the s
 */
public class XmlCreation {

    private Logger logger = Logger.getLogger(getClass());
    private ThreadLocal<Document> documentThreadLocal = new ThreadLocal<Document>();
    @Value("${xmldir}")
    private String xmldir;
    private String XMLS_DIR;
    private XMLOutputter outputter;



    public void init(){
        org.jdom.output.Format prettyFormat = org.jdom.output.Format.getPrettyFormat();
        prettyFormat.setEncoding("UTF-8");
        this.outputter = new XMLOutputter(prettyFormat);


        try {
            String path = getClass().getResource("/").toURI().getPath();
            XMLS_DIR = path.substring(0, path.indexOf("WEB-INF"))+xmldir;
//            logger.info("XMLS_DIR:  "+XMLS_DIR);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String generateXml(XMLSkeleton<XMLSkeleton> xmlSkeleton) {
        if (outputter==null) {
            synchronized (XmlCreation.class) {
                if (outputter==null) {
                    init();
                }
            }
        }
        composingXml(xmlSkeleton);
        createXMLS_DIR();
        return  writeXml();
    }

    /**
     * 组装xml文件document对象
     *
     * @param xmlSkeleton 数据对象
     */
    public void composingXml(XMLSkeleton<XMLSkeleton> xmlSkeleton) {

        Document document = new Document();
        documentThreadLocal.set(document);
        Element root = new Element(xmlSkeleton.getElementName());
        document.setRootElement(root);


        composeXml(xmlSkeleton, root);

    }

    /**
     * @param xmlSkeleton 节点对应的XMLSkeleton对象
     * @param part        对应的xml节点
     */
    public void composeXml(XMLSkeleton<XMLSkeleton> xmlSkeleton, Element part) {

        Map<String, String> attribureValMap = xmlSkeleton.getAttribureValMap();
        String textContent = xmlSkeleton.getTextContent();
        List<XMLSkeleton> childrenElements = xmlSkeleton.getChildrenElement();

        for (Map.Entry<String, String> entry : attribureValMap.entrySet()) {
            part.setAttribute(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
        }
        if (textContent != null) {
            part.setText(textContent);
        }


        for (int i = 0; i < childrenElements.size(); i++) {
            XMLSkeleton childrenElement = childrenElements.get(i);
            Element element = new Element(childrenElement.getElementName());
            part.addContent(element);
            composeXml(childrenElement, element);

        }


    }

    /**
     * 输出xml文件
     */
    public String writeXml() {

        Document document = documentThreadLocal.get();

        String xmlFileName=XMLS_DIR
                + document.getRootElement().getAttributeValue("analysisRequestId")
                + "_"
             //   + CreateClinicalFMIXmlService.uniqueSignThreadLocal.get()
                + ".xml";
        logger.info("xmlFileName :    "+ xmlFileName);
        ByteArrayOutputStream byteArrayOutputStream=null;
        BufferedWriter bw = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmlFileName)));
            outputter.output(document, byteArrayOutputStream);
            String content = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            content = content.replace("encoding=\"UTF-8\"", "encoding=\"UTF-8\" standalone=\"yes\"");
            content = content.replace("<analysisRequest", "<analysisRequest xmlns=\"http://foundationmedicine.com/compbio/lims\" ");

            bw.write(content);
            bw.flush();


        } catch (FileNotFoundException e) {
            xmlFileName="";
            logger.info(e);
        } catch (IOException e) {
            xmlFileName="";
            logger.info(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    logger.info(e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.info(e);
                }
            }
        }

        return xmlFileName;

    }

    /**
     * 创建存放xml的文件夹
     */
    public void createXMLS_DIR() {
        File dir = new File(XMLS_DIR);
        if (!dir.exists()) {
            synchronized (getClass()) {
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }
        }




    }


    public static void main(String[] args) {


    }
    }
