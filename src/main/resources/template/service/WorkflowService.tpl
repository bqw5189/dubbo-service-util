package com.daosheng.field.service.backlog.detail.webparse;


import com.daosheng.field.service.backlog.relation.BacklogType;
import com.daosheng.field.service.backlog.relation.BacklogUriData;
import com.daosheng.field.service.backlog.strategy.ApproveBacklogPageVo;
import com.daosheng.field.service.webParse.entity.RegixEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <#$workflowName#> 服务
 * Created by tom on <#$now#>.
 */
@Component
public class <#$workflowType#>Service extends FlowBaseService {
    public final static String FLOW_TYPE = "<#$workflowType#>";
    public final static String FLOW_NAME = "<#$workflowName#>";

    public final static String FLOW_DETAIL_URI = "/static/custom/secco/backlogs/<#$workflowType#>/detail.html";
    public final static String FLOW_H3_DETAIL_URI = "/WorkFlow/CheckDetail.aspx";

        static{
            //初始化 渲染页面信息
            BacklogUriData backlogUriData = new BacklogUriData(FLOW_TYPE,FLOW_DETAIL_URI,FLOW_H3_DETAIL_URI);
            BacklogType.putUriData(FLOW_TYPE, backlogUriData);

            ApproveBacklogPageVo.putBacklogNameAndType(FLOW_NAME, FLOW_TYPE);
        }

    @Override
    protected List<RegixEntity> buildRegixEntity() {
        List<RegixEntity> regixEntityList = new ArrayList<RegixEntity>();

        <#foreach from=$panels item=panel#>
        //<#$panel.title#><#foreach from=$panel.elements item=element#>
        regixEntityList.add(RegixEntity.create("<#$element.id#>", "<#$element.tagName#>", "#<#$element.valueId#>"));<#/foreach#>
        <#/foreach#>

        return regixEntityList;
    }

    @Override
    protected String detailPageUri() {
        return "secco/<#$workflowType#>/SC_<#$workflowType#>View.aspx";
    }


    /**
     *描述：<#$workflowName#>
     *@param @param url
     *@param @param type
     *@param @param token
     *@author nimin
     */
    IServiceResponse parse<#$workflowType#>(String url, String type, String token);

    @Autowired
	private <#$workflowType#>Service <#$workflowType#>Service;

    @Override
    public IServiceResponse parse<#$workflowType#>(String url, String type, String token) {
        JsonResponse resp = new JsonResponse();

        try {
            Map<String, Object> result=	<#$workflowType#>Service.parseResult(url, type,token);

            resp.setJsonVal(FieldUtils.gson.toJson(result));

            this.success(resp, "success");
        } catch (Exception e) {
            e.printStackTrace();
            this.error(resp, "error");
        }
        return resp;
    }



    @RequestMapping(value="/<#$workflowType#>",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        @LogForDbAnnotation(title="<#$workflowName#>数据爬取",operationType="第三方服务日志",level="中级")
        public ResponseEntity<?> parse<#$workflowType#>(
                @RequestParam("parseUrl") String parseUrl,
                @RequestParam("type") String type,
                @RequestParam("token") String token) {
            if ("0:1".equals(type) || "0:2".equals(type)) {
                return new ResponseEntity(webParseService.parse<#$workflowType#>(parseUrl,type,token),HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

}
