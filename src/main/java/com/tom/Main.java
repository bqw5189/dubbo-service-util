package com.tom;

import com.tom.entity.Entity;
import com.tom.jdbc.JdbcUtils;
import org.apache.commons.io.IOUtils;
import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.Engine;
import org.lilystudio.smarty4j.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
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
    }

    private static void generateEntity(Entity entity) {
        FileOutputStream fileOutputStream = null;
        try {
            File outFile = new File(entity.OUT_PATH + File.separatorChar + entity.getEntityClassName() + ".java");

            fileOutputStream = new FileOutputStream(outFile);

            if (!outFile.exists()) {
                renderTemplate(entity, fileOutputStream);
            }

            logger.info("template render:{}", outFile.canRead());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }


    private static Template renderTemplate(Entity entity, final FileOutputStream fileOutputStream) throws Exception {
        String templateName = entity.TEMP_PATH;

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
