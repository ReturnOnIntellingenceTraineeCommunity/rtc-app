<@spring.formHiddenInput "course.createDate" />
<style>
    hr
    {
        float:left;
        width:91%;
        margin-bottom: 20px;
    }
</style>

<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "course.name" "course.name" "required" 'maxlength="50"'/>
        <@formMacro.rtcFormMultiSelect "course.category" "course.types" categories "" "height: 65; background-color:#FFFACD"/>
        <@formMacro.rtcFormTextInput "course.capacity" "course.capacity"  "input-small required" 'style="width:50px;" onkeydown="return isNumber(event);" size="2" maxlength="2"' />
    </div>
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "course.startDate" "course.startDate" "input-medium required" 'background-color: #FFFACD' />
        <@formMacro.rtcFormTextInput  "course.endDate" "course.endDate" "input-medium required" />
        <@formMacro.rtcFormTagsInput "course.tags" "course.tags"/>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormMultiSelect "course.experts" "course.experts" experts "" "width: 100%; background-color:#FFFACD"/>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextarea "course.description" "course.description" "required" 'style="width:425%;" rows="3" maxlength="255"'/>
    </div>
</div>

<#if !course.isPublished()>
    <hr>
    <div class="row">
        <div class="col-md-6">
            <label  class="control-label col-md-3"></label>
            <input id="ifPublish" name="ifPublish" type="checkbox" >
            <@spring.message "coursesPage.action.publishAsNews"/>
        </div>
        <div id="ifCreateNewsBlock" class="col-md-6">
            <label  class="control-label col-md-3"></label>
            <input id="ifCreateNews" name="ifCreateNews" type="checkbox" >
            <@spring.message "coursesPage.action.createNews"/>
        </div>
    </div>
<#else>
    <input id="ifPublish" name="ifPublish" type="hidden" value="True">
</#if>


<hr>
&nbsp;
<script type="text/javascript"
        src="<@spring.url'/resources/js/pages/courseForm.js'/>"></script>
<script>
    $(function () {
        $("#ifCreateNewsBlock").hide();
        $("#endDate").datepicker({
            dateFormat: "dd.mm.yy", minDate: 0
        });
        $("#endDate").attr('readonly', 'readonly');
        $("#startDate").datepicker({
            dateFormat: "dd.mm.yy", minDate: 0,
            onSelect: function (date) {
                var date1 = $('#startDate').datepicker('getDate');
                var date = new Date(Date.parse(date1));
                date.setDate(date.getDate() + 1);
                var newDate = date.toDateString();
                newDate = new Date(Date.parse(newDate));
                $('#endDate').datepicker("option", "minDate", newDate);
            }});
        $("#startDate").attr('readonly', 'readonly');
        $("#startDate").attr('style', 'background-color : #FFFACD');
        $("#endDate").attr('style', 'background-color : #FFFACD');
        $('#ifPublish').click(function() {
            $("#ifCreateNewsBlock")[this.checked ? "show" : "hide"]();
        });

    });
</script>