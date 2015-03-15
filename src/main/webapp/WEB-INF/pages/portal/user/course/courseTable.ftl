<#import "../../../fieldMacro.ftl" as formMacro/>
<#import "../../../datatables.ftl" as datatables/>


<div id="courseTable" class="row-fluid">
<#list courses as course>
    <div class="span9">

        <div class="panel panel-default" style=" background-color: CEE3F6 ; border: 3px solid #81BEF7;">

            <div class="panel-body"
                 style="text-align:left; padding-left: 25px; padding-right: 25px; padding-bottom: 0px;
                     <#if course.status == "ARCHIVED">background-color: #f3eaea;</#if>">

                <div class="col-md-3" style="padding-right: 10px">
                    <@formMacro.courseImage course.types "width: 100%; max-width: 242px; display: block; margin-left: auto; margin-right: auto " />
                </div>


                <div class="col-md-9" style="">
                    <div class="row">

                        <a style="font-size: large;"
                           href="<@spring.url'/user/courses/courseDetails/${course.code}'/>">${course.name} </a>
                        <br/> <!--  name course-->

                    </div>

                    <div class="row">

                        <div class="col-md-9" style="padding-left: 0;">
                            <div class="" style="padding-top: 10px">
                                <span style="font-style: italic;">with ${course.experts?first.name}
                                    &nbsp;${course.experts?first.surname}</span>
                            </div>
                            <!-- expert name -->

                            <div class="" style="padding-top: 10px; padding-bottom: 10px">
                                <span class="description">${course.description}</span>
                            </div>
                            <!-- description -->
                        </div>

                        <div class="col-md-3" style="text-align: right; float: right">
                            <span style="">${course.startDate?date?string('dd MMMM yyyy')}</span> <!-- startDate -->
                            <br/>
                            <@formMacro.weekSpan course.startDate course.endDate/><#--week-->
                        </div>

                    </div>

                    <div class="row" style="padding-top: 20px">
                        <div class="col-md-12" style="padding-left: 0;">
                            <#if course.status != "ARCHIVED">
                                <a class="btn btn-success" type="button" style="float: left"
                                   href="<@spring.url'/user/courses/courseDetails/${course.code}'/>">Go to course</a>
                            </#if>

                            <div style="text-align: right">
                                <@formMacro.capacityIndicator course.acceptedOrders  course.capacity/> <#-- capacity -->
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</#list>
</div>

<div class="row" style="text-align: center">
    <#if lastPage gt 1>
        <#if currentPage != lastPage>
            <a href="#" type="button" class="btn btn-primary" id="loadMore" page="${currentPage+1}">load more</a>
        </#if>
    </#if>
</div> <#-- load more button-->


<#--CURRENT STATE: allways show archived course after published course-->

<#--<#if currentPage == lastPage>-->
<#--<div class="row">-->
<#--<div  class="col-md-offset-6 col-md-6">-->
<#--<button id="withArchiveButton" type="button" class="btn btn-primary btn-xs"><@spring.message "courses.archive"/></button>-->
<#--</div>-->
<#--</div>-->
<#--</#if>-->

<#--<script>-->
<#--$("#withArchiveButton").click(function (event) {-->
<#--var val = $("#withArchive").val();-->
<#--var withArchive;-->
<#--if (val == "false") {-->
<#--withArchive = true;-->
<#--$("#withArchive").val("true");-->
<#--} else {-->
<#--withArchive = false;-->
<#--$("#withArchive").val("false");-->
<#--}-->
<#--//$("#loadMore").setAttribute("page",1);-->
<#--var page = $("#loadMore").getAttribute("page");-->
<#--searchAppend($("#types").val(), page);-->
<#--});-->
<#--</script>-->
