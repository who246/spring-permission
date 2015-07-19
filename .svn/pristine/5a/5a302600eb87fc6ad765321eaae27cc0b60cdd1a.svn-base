<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>

<div class="panel">
     <ul class="nav nav-tabs">
       

        <li>
            <a href="<es:BackURL/>" class="btn btn-link">
                <i class="icon-reply"></i>
                              返回
            </a>
        </li>
    </ul> 

    <form:form id="editForm" method="post" commandName="m" cssClass="form-inline form-horizontal form-small">
        <es:showGlobalError commandName="m"/>
        <form:hidden path="id"/>
         <c:choose>
         <c:when test="${op eq '查看' }">
        <form:hidden path="username"/>
        </c:when>
        <c:otherwise>
        <input type="hidden" name="username" value="<shiro:principal property="username" />"/>
        </c:otherwise>
        </c:choose>
        <div id="baseinfo">
            <h4 class="hr">公告基本信息</h4>
       
            
             
              <div class="control-group span4">
                <form:label path="info"  cssClass="control-label">公告信息</form:label>
                <div class="controls">
                      <form:textarea path="info" cssClass="validate[maxSize[1000]]" rows="10" 
                                placeholder="公告信息" 
                                ></form:textarea>
                
                 
                </div>
             </div>

             
 

            <div class="clearfix"></div>
          
        </div>


        <c:if test="${op eq '新增'}">
            <c:set var="icon" value="icon-file-alt"/>
        </c:if>
        <c:if test="${op eq '修改'}">
            <c:set var="icon" value="icon-edit"/>
        </c:if>
       
       
        <div class="control-group left-group">
            <div>
                <button type="submit" class="btn btn-primary">
                    <i class="${icon}"></i>
                        ${op}
                </button>
                <a href="<es:BackURL/>" class="btn">
                    <i class="icon-reply"></i>
                    返回
                </a>
            </div>
        </div>

    </form:form>
</div>
<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/import-zTree-css.jspf"%>
<%@include file="/WEB-INF/jsp/common/import-zTree-js.jspf"%>
 
<script type="text/javascript">
    $(function () {

        <c:choose>
            <c:when test="${op eq '查看' }">
                //删除时不验证 并把表单readonly
           $.app.readonlyForm($("#editForm"), true);
                
            </c:when>
           
          


            <c:otherwise>
           
                $("#editForm").validationEngine();
                <es:showFieldError commandName="m"/>
            </c:otherwise>
        </c:choose>
    });

</script>