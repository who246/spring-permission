<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>
<%@include file="/WEB-INF/jsp/common/import-calendar-css.jspf"%>
<style>

    legend {
        cursor: pointer;
    }
    .fc-button-add {
        margin-right: 10px!important;
    }

    #loading {
        position: absolute;
        top: 5px;
        right: 5px;
    }

    .ui-dialog {
        overflow: visible!important;
    }
    .ui-dialog-content {
        overflow: visible!important;
    }

    #calendar {
        width: 750px;
        margin: 0 auto;
    }
</style>
<div  style="margin: 5px;margin-top: 10px;">
     

    <fieldset>
        

       
                  <div  id="menu-collapse"  >
                    <div >
                        <h3><a href="#" >welcome</a></h3>
                        <div>${welcome}</div>
                    </div>
                    <div>
                        <h3><a href="#">公告</a></h3>
                        <div>
                        <p>发布人： ${m.username}</p>
                        <p>内    容：<font color="blue"> ${m.info}</font></p>
                        <p>时    间：<c:if test="${m != null}"><spring:eval expression="m.createDate"/></c:if></p>
                    </div>
                    
                </div>
    </fieldset>

    <br/>
    <br/>
    <br/>

</div>
<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/import-calendar-js.jspf"%>
<script>
    $(function() {
    	$("#menu-collapse").accordion({
    		header:"h3",
            heightStyle:"content",
            icons : {
                header: "icon-caret-right",
                activeHeader: "icon-caret-down"
            },
            animate : {
                easing : "easeOutQuart"
            }
    	});

    })
</script>
