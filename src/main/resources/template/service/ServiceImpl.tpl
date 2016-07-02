package com.fionapet.business.service;

import com.fionapet.business.entity.<#$entity.entityClassName#>;
import com.fionapet.business.repository.DaoBase;
import com.fionapet.business.repository.<#$entity.entityClassName#>Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  <#$entity.entityName#>
 * @author baiqw
 */
public class <#$entity.entityClassName#>ServiceImpl extends CURDServiceBase<<#$entity.entityClassName#>> implements <#$entity.entityClassName#>Service {
    @Autowired
    private <#$entity.entityClassName#>Dao <#$entity.fieldClassName#>Dao;

    @Override
    public DaoBase<<#$entity.entityClassName#>> getDao() {
        return <#$entity.fieldClassName#>Dao;
    }
}
