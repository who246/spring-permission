<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>
<div class="panel">

 
    <ul class="nav nav-tabs">
     <c:if test="${op eq '查看'}">
     <li ${op eq '查看' ? 'class="active"' : ''}>
            <a href="#">
                <i class="icon-eye-open"></i>
                查看
            </a>
        </li>
        </c:if>
        <c:if test="${op eq '修改'}">
            <li ${op eq '修改' ? 'class="active"' : ''}>
                <a href="#">
                    <i class="icon-edit"></i>
                    修改
                </a>
            </li>
            </c:if>
              <c:if test="${op eq '新增'}">
            <li ${op eq '新增' ? 'class="active"' : ''}>
                <a href="#">
                    <i class="icon-edit"></i>
                   新增
                </a>
            </li>
            </c:if>
    </ul>
    <form:form id="editForm" method="post" commandName="m" cssClass="form-horizontal" >

        <es:showGlobalError commandName="m"/>

        <form:hidden path="id"/>
        <form:hidden path="pid"/>
         
       

        <div class="control-group">
            <form:label path="menuName" cssClass="control-label">名称</form:label>
            <div class="controls">
                <form:input path="menuName" cssClass="validate[required,custom[name]]" placeholder="小于50个字符"/>
            </div>
        </div>

 

        <div class="control-group">
            <form:label path="url" cssClass="control-label">URL地址</form:label>
            <div class="controls">
                <form:input path="url" placeholder="菜单跳转地址"/>
            </div>
        </div>
         <div class="control-group">
            <form:label path="type" cssClass="control-label">菜单类型</form:label>
            <div class="controls inline-radio">
                 
          <form:radiobuttons path="type" items="${TypeList}"  itemLabel="info" cssClass="validate[required]"/>
            </div>
        </div>
        <div class="control-group">
            <form:label path="description" cssClass="control-label">信息描述</form:label>
            <div class="controls">
                <form:textarea path="description" placeholder="信息描述"/>
            </div>
        </div>

       

 


   
            <c:if test="${op eq '修改'}">
                <c:set var="icon" value="icon-edit"/>
            </c:if>
         
            <div class="control-group">
                <div class="controls">
                    <button type="submit" id="submit" class="btn btn-primary">
                        <i class="${icon}"></i>
                            ${op}
                    </button>
                </div>
            </div>


    </form:form>
</div>
<es:contentFooter/>
<%@include file="/WEB-INF/jsp/common/admin/import-maintain-js.jspf"%>
<script type="text/javascript">
    $(function () {
        <c:choose>
        
            <c:when test="${op eq '查看'}">
                $.app.readonlyForm($("#editForm"), true);
            </c:when>
            <c:otherwise>
                $.validationEngineLanguage.allRules.name = {
                    "regex": /^.{1,50}$/,
                    "alertText": "* 小于50个字符"
                };
                var validationEngine = $("#editForm").validationEngine();
                <es:showFieldError commandName="m"/>
            </c:otherwise>
        </c:choose>
        
         
    });
</script>