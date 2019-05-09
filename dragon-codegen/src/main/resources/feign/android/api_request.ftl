package ${packagePath?replace('.'+name,'')};

import com.levin.commons.service.domain.Sign;
import com.oaknt.android.sign.NeedSignEvt;
import com.oaknt.common.service.support.model.ServiceEvt;
import lombok.Data;

import javax.validation.constraints.NotNull;

<#if dependencies??>
<#--依赖导入处理-->
    <#list dependencies as key,val >
        import ${customize_method.pathoResolve(packagePath,val.packagePath)};
    </#list>
</#if>

<#if comments??>
    /**
    <#list comments as cmment>
        * ${cmment}
    </#list>
    **/
</#if>

<#if filedMetas??>
    <#list annotations as annotation>
        @${annotation.name}({
        <#list annotation.namedArguments as name,val>
            ${name}=${val}
        </#list>
        })
    </#list>
</#if>

@Data
public class  ${finallyClassName}<#if superClass??> extends ${superClass.finallyClassName}</#if> {

<#if filedMetas??>
    <#list filedMetas as field>
        /**
        <#list field.comments as cmment>
            *${cmment}
        </#list>
        **/
       ${field.accessPermissionName} ${customize_method.combineType(field.filedTypes)} ${field.name};
    </#list>
</#if>
}