<#macro layout menu content="" header="">
<html>
    <#import "/spring.ftl" as spring/>
    <#import "../../fieldMacro.ftl" as formMacro />
<head>

    <@formMacro.rtcIncludeLink />
    <@formMacro.rtcIncludeScript/>
</head>

<body>
<div id="header" class="navbar navbar-inverse navbar-fixed-top"
     role="navigation">
    <#include "${header}" />
</div>

<div class="container-fluid" style="margin-bottom: 60px">
    <div class="row-fluid">
        <#if menu?length!=0>
            <div id="menu" class="col-sm-3 col-md-2 sidebar">
                <#include "${menu}" />
            </div>
            <#if content?length!=0>
                <div id="content" class="col-sm-offset-3 col-md-offset-2 main">
                    <#include "${content}">
                </div>
            <#else>
                <#nested>
            </#if>
        <#else>
            <#if content?length!=0>
                <div id="content" class="col-md-12 main">
                    <#include "${content}">
                </div>
            <#else>
                <#nested>
            </#if>
        </#if>
    </div>
</div>
<div id="footer" class="container">

    <#include "footer.ftl" />
</div>
</body>

</html>
</#macro>

