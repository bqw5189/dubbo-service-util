package com.fionapet.business.facade;

import com.fionapet.business.entity.<#$entity.entityClassName#>;
import com.fionapet.business.service.CURDService;
import com.fionapet.business.service.<#$entity.entityClassName#>Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <#$entity.entityName#>
 * User: baiqw
 * Date: 14-12-9
 * Time: 下午3:45
 */
public class <#$entity.entityClassName#>RestServiceImpl extends RestServiceBase<<#$entity.entityClassName#>> implements <#$entity.entityClassName#>RestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(<#$entity.entityClassName#>RestServiceImpl.class);

    private <#$entity.entityClassName#>Service <#$entity.fieldClassName#>Service;

    public <#$entity.entityClassName#>Service get<#$entity.entityClassName#>Service() {
        return <#$entity.fieldClassName#>Service;
    }

    public void set<#$entity.entityClassName#>Service(<#$entity.entityClassName#>Service <#$entity.fieldClassName#>Service) {
        this.<#$entity.fieldClassName#>Service = <#$entity.fieldClassName#>Service;
    }

    @Override
    public CURDService<<#$entity.entityClassName#>> getService() {
        return <#$entity.fieldClassName#>Service;
    }

}
