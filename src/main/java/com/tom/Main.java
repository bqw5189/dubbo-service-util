package com.tom;

import com.tom.entity.Entity;
import com.tom.jdbc.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 16/4/18.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        Entity entity = new Entity();
        entity.setTableName("t_dealer");
        entity.setEntityName("经销商");

        //初始化字段
        initField(entity);

        //生成entity
        generateEntity(entity);

        //xml
        updateXml(entity);
    }

    private static void updateXml(Entity entity) {
        String serviceXml = Entity.OUT_PATH + "fionapet-business-service-provider/src/main/resources/META-INF/spring/dubbo-business-service-provider.xml";
        String restXml = Entity.OUT_PATH + "fionapet-business-rest-provider/src/main/resources/META-INF/spring/dubbo-business-rest-provider.xml";

        try {
            List<String> serviceXmlList  = FileUtils.readLines(new File(serviceXml));
            serviceXmlList.add(serviceXmlList.size()-1, getServiceXml(entity));
            FileUtils.writeLines(new File(serviceXml), serviceXmlList);

            List<String> restXmlList  = FileUtils.readLines(new File(restXml));
            restXmlList.add(restXmlList.size()-1, getRestXml(entity));
            FileUtils.writeLines(new File(restXml), restXmlList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRestXml(Entity entity) {
        return "\n\t<!--"+entity.getEntityName()+" api -->\n" +
                "    <dubbo:service interface=\"com.fionapet.business.facade."+entity.getEntityClassName()+"RestService\" ref=\""+entity.getFieldClassName()+"RestService\"\n" +
                "                   protocol=\"rest\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "\n" +
                "    <bean id=\""+entity.getFieldClassName()+"RestService\" class=\"com.fionapet.business.facade."+entity.getEntityClassName()+"RestServiceImpl\">\n" +
                "        <property name=\""+entity.getFieldClassName()+"Service\" ref=\""+entity.getFieldClassName()+"Service\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "    <dubbo:reference id=\""+entity.getFieldClassName()+"Service\" interface=\"com.fionapet.business.service."+entity.getEntityClassName()+"Service\"/>\n" +
                "    <!--"+entity.getEntityName()+" api -->\n";
    }

    private static String getServiceXml(Entity entity) {
        return "\n\t<!-- "+entity.getEntityName()+" api -->\n" +
                "    <bean id=\""+entity.getFieldClassName()+"Service\" class=\"com.fionapet.business.service."+entity.getEntityClassName()+"ServiceImpl\"/>\n" +
                "\n" +
                "    <dubbo:service interface=\"com.fionapet.business.service."+entity.getEntityClassName()+"Service\" ref=\""+entity.getFieldClassName()+"Service\"\n" +
                "                   protocol=\"dubbo\" validation=\"true\" timeout=\"2000\" connections=\"100\"/>\n" +
                "    <!-- "+entity.getEntityName()+" api -->\n";
    }


    private static void generateEntity(Entity entity) {
        FileOutputStream fileOutputStream = null;
        try {
            File outEntityFile = new File(entity.OUT_API_PATH + File.separatorChar + "entity" + File.separatorChar + entity.getEntityClassName() + ".java");
            File outFacadeFile = new File(entity.OUT_API_PATH + File.separatorChar + "facade" + File.separatorChar + entity.getEntityClassName() + "RestService.java");
            File outServiceFile = new File(entity.OUT_API_PATH + File.separatorChar + "service" + File.separatorChar + entity.getEntityClassName() + "Service.java");
            File outDaoFile = new File(entity.OUT_SERVICE_PATH + File.separatorChar + "repository" + File.separatorChar + entity.getEntityClassName() + "Dao.java");
            File outServiceImplFile = new File(entity.OUT_SERVICE_PATH + File.separatorChar + "service" + File.separatorChar + entity.getEntityClassName() + "ServiceImpl.java");
            File outRestFile = new File(entity.OUT_REST_PATH + File.separatorChar + "facade" + File.separatorChar + entity.getEntityClassName() + "RestServiceImpl.java");

            if (!outEntityFile.exists()) {
                fileOutputStream = new FileOutputStream(outEntityFile);
                renderTemplate(entity, "api/entity/Entity.tpl", fileOutputStream);
                logger.info("api template render:{}", outEntityFile);
            }

            if (!outFacadeFile.exists()) {
                fileOutputStream = new FileOutputStream(outFacadeFile);
                renderTemplate(entity, "api/facade/RestService.tpl", fileOutputStream);
                logger.info("api template render:{}", outFacadeFile);
            }

            if (!outServiceFile.exists()) {
                fileOutputStream = new FileOutputStream(outServiceFile);
                renderTemplate(entity, "api/service/Service.tpl", fileOutputStream);
                logger.info("api template render:{}", outServiceFile);
            }

            if (!outDaoFile.exists()) {
                fileOutputStream = new FileOutputStream(outDaoFile);
                renderTemplate(entity, "service/Dao.tpl", fileOutputStream);
                logger.info("api template render:{}", outDaoFile);
            }

            if (!outServiceImplFile.exists()) {
                fileOutputStream = new FileOutputStream(outServiceImplFile);
                renderTemplate(entity, "service/ServiceImpl.tpl", fileOutputStream);
                logger.info("api template render:{}", outServiceImplFile);
            }

            if (!outRestFile.exists()) {
                fileOutputStream = new FileOutputStream(outRestFile);
                renderTemplate(entity, "rest/RestServiceImpl.tpl", fileOutputStream);
                logger.info("api template render:{}", outRestFile);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }


    private static Template renderTemplate(Entity entity, String templateName, final FileOutputStream fileOutputStream) throws Exception {
        Map<String, String> config = new HashMap<String, String>();
        config.put("template.path", "src/main/resources/template/");
        config.put("encoding", "utf-8");

        Engine smartyEngine = new Engine(config);
        smartyEngine.setLeftDelimiter("<#");
        smartyEngine.setRightDelimiter("#>");

        Template template = smartyEngine.getTemplate(templateName);

        Context context = new Context();
        context.set("entity", entity);
        template.merge(context, fileOutputStream);

        return template;
    }

    private static void initField(Entity entity) {
        JdbcUtils jdbcUtils = new JdbcUtils();
        try {
            entity.setFields(jdbcUtils.desc(entity.getTableName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
