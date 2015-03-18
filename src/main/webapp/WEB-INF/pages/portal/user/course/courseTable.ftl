<#import "../../../fieldMacro.ftl" as formMacro/>
<#import "../../../datatables.ftl" as datatables/>


<div id="courseTable" class="row-fluid">
<#list courses as course>

        <div class="row" style=" background-color: #F5F5F5; margin-bottom: 30px;  height: 203px; padding-left: 0;">

            <div class="col-md-12"
                 style="padding-left: 0; text-align: left;
                     <#if course.status == "ARCHIVED">background-color: #f3eaea;</#if>">

                <div class="col-md-3" style="padding-left: 0;">
                    <@formMacro.courseImage course.types "width: 100%; max-width: 242px; display: block; margin-left: auto; margin-right: auto;" />
                </div>


                <div class="col-md-9" style="margin-top: 8px">
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
                                <a class="btn btn-success btn-sharp btn-to-course" type="button" style="float: left"
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

</#list>
</div>


<div class="row" style="text-align: center">
    <#if lastPage gt 1>
        <#if currentPage != lastPage>
            <a href="#" type="button" class="btn btn-info btn-load-more btn-sharp" id="loadMore" page="${currentPage+1}"><@spring.message "course.loadMore" /></a>
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
