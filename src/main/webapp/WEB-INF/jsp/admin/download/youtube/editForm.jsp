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
        <div id="baseinfo">
            <h4 class="hr">任务基本信息</h4>
            <div class="control-group span4">
                <form:label path="name" cssClass="control-label">任务名称</form:label>
                <div class="controls">
                 
               
                
                      <form:input path="name" cssClass="validate[required,minSize[1],maxSize[20]]"
                                placeholder="任务名称" 
                                />
                
                 
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="control-group span4">
                <form:label path="url" cssClass="control-label">下载地址</form:label>
                <div class="controls">
                 
               
                
                      <form:input path="url" cssClass="validate[required]"
                                placeholder="例如:http://youtube.com/watch?v=u7deClndzQw" 
                                />
                
                 
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
        <c:if test="${op eq '删除'}">
            <c:set var="icon" value="icon-trash"/>
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