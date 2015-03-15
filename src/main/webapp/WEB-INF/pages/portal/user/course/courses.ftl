<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/js/bootstrap.min.js'/>" type="text/javascript"></script>


<@layout.layout>
<input type="hidden" id="withArchive" value="true">
<input type="hidden" id="varTimePeriod" value="">
<div class="panel panel-default"
     style="float: none; padding-bottom: 10px; padding-top: 10px; margin-bottom: 50px; background-color: darkseagreen;">

    <div class="row">
        <div class="col-md-6" style="text-align: center">
            <div class="btn-group" data-toggle="buttons">
                <label onclick="setPeriod('')" class="btn btn-default active">
                    <input type="radio" id="asd" name="options" value=""> <@spring.message "courses.period.ALL"/>
                </label>
                <#list periods as period>
                    <label onclick="setPeriod('${period}')" class="btn btn-default">
                        <input type="radio" name="options"
                               value="${period}"><@spring.message "courses.period.${period}"/></input>
                    </label>
                </#list>
            </div>
        </div>

        <div class="col-md-6" style="text-align: center">
            <div class="form-group"><h4 class="control-label col-md-4" for="types">
                <strong><@spring.message "courses.catalog"/></strong></h4>

                <div class="col-md-5">
                    <select id='types' class="form-control">
                        <option value=""><@spring.message "courses.types.All"/></option>
                        <#list courseTypes as type>
                            <option value="${type}"><@spring.message "courses.types.${type}"/></option>
                        </#list>
                    </select>
                </div>
            </div>
        </div>

    </div>
</div>

<div id="coursesContent">

</div>

</@layout.layout>

<script>
    $(function () {
        searchReset(1);
    });

    $("#types").change(function (event) {
        event.preventDefault();
        searchReset(1);
    });

    function setPeriod(period) {
        $("#varTimePeriod").val(period);
        searchReset(1);
    }

    $("#coursesContent").on("click", ".navButton", function (event) {
        event.preventDefault();
        var page = this.getAttribute("page");
        searchReset(page);
    });

    $(document).on("click", "#loadMore", function (event) {
        event.preventDefault();
        $(this).hide();
        var page = this.getAttribute("page");
        searchAppend(page);

    });


    //    $(function () {
    //        $.each($('.description'), function () {
    //            var str = $(this).html();
    //            $(this).html(shorten(str, 150));
    //        });
    //    });


    function appendContent(result) {
        $("#coursesContent").append(result);
    }

    function resetContent(result) {
        $("#coursesContent").html(result);
    }

    function searchAppend(page) {
        search(page, appendContent)
    }

    function searchReset(page) {
        search(page, resetContent)
    }

    function search(page, collbackSucces) {
        var withArchive = $("#withArchive").val();
        var timePeriod = $("#varTimePeriod").val();
        var type = $("#types").val();
        $.ajax({
            type: "POST",
            data: "types=" + type + "&page=" + page + "&withArchived=" + withArchive + "&timePeriod=" + timePeriod,
            url: "<@spring.url "/user/courses/courseTable"/>",
            success: function (result) {
                collbackSucces(result);
            }, error: function (xhr, status, error) {
            }
        });
    }

    function shorten(text, maxLength) {
        var ret = text;
        if (ret.length > maxLength) {
            ret = ret.substr(0, maxLength - 3) + "...";
        }
        return ret;
    }


    //Deprecated

    function setCode(courseCode) {
        document.getElementById("courseCode").value = courseCode;
        $.ajax({
            url: "<@spring.url'/user/courses/position/'/>" + courseCode,
            success: function (result) {

                var res = "";
                result.forEach(function (element, index, array) {
                    res += "<div id='position.name'> " +
                    "<input type='radio' name='position' value='" + element + "'/>&nbsp;" +
                    element +
                    "<br/></div>"
                });
                $("#positions").html(res);
            }
        });
    }

    function PopUpShow(types) {
        types.split(",").forEach(function (type) {
            $("#" + type).show()
        })
        $("#window-popup").show();
    }
    function PopUpHide() {
        $("#window-popup").hide();
    }
</script>