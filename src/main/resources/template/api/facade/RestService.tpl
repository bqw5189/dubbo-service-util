package com.fionapet.business.facade;

import cn.fiona.pet.account.facade.AuthRestService;
import cn.fiona.pet.account.facade.RestResult;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.fionapet.business.entity.<#$entity.entityClassName#>;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * <#$entity.entityName#> 接口
 *
 * @author baiqw
 */

@Path("<#$entity.path#>")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@Api(value="<#$entity.path#>", description = "<#$entity.entityName#>接口")
public interface <#$entity.entityClassName#>RestService extends CURDRestService<<#$entity.entityClassName#>>{
    /**
     * <#$entity.entityName#>
     * @return
     */
    @GET
    @Path("races")
    @ApiOperation(value = "<#$entity.entityName#>",
            notes = "<#$entity.entityName#>列表.")
    RestResult<List<<#$entity.entityClassName#>>> list(@HeaderParam(AuthRestService.HEADER_AUTHORIZATION_KEY) String token);

    /**
     * <#$entity.entityName#> 详细信息
     *
     * @return
     */
    @GET
    @Path("/{id}")
    @ApiOperation(value = "详细信息",
            notes = "<#$entity.entityName#>详细信息.")
    RestResult<<#$entity.entityClassName#>> detail(@HeaderParam(AuthRestService.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") @PathParam("id") String uuid);

    @POST
    @Path("")
    @ApiOperation(value = "添加/修改<#$entity.entityName#>", notes = "添加/修改<#$entity.entityName#>")
    RestResult<<#$entity.entityClassName#>> create(@HeaderParam(AuthRestService.HEADER_AUTHORIZATION_KEY) String token,<#$entity.entityClassName#> <#$entity.fieldClassName#>);

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "删除<#$entity.entityName#>", notes = "删除<#$entity.entityName#>")
    RestResult<String> delete(@HeaderParam(AuthRestService.HEADER_AUTHORIZATION_KEY) String token, @ApiParam("id") @PathParam("id") String uuid);
}
