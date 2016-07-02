package com.fionapet.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * <#$entity.entityName#>
 * <p>
 * Created by tom on 16/7/2.
 */
@Entity
@Table(name = "<#$entity.tableName#>")
@ApiModel("<#$entity.entityName#>")
public class <#$entity.entityClassName#> extends CMSEntity {
    <#foreach from=$entity.fields item=field#>
    /**
     * <#$field.name#>
     */
    @ApiModelProperty(value = "<#$field.name#>", required = false)<#if $field.isNull == "NO" #>@NotNull<#/if#>
    private <#$field.type#> <#$field.name#>;
    public <#$field.type#> get<#$field.monthName#>() {
        return <#$field.name#>;
    }
    public void set<#$field.monthName#>(<#$field.type#> <#$field.name#>) {
        this.<#$field.name#> = <#$field.name#>;
    }
    <#/foreach#>
}

