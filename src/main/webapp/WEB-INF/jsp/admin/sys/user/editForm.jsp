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
        
        <form:hidden path="salt"/>
        <div id="baseinfo">
            <h4 class="hr">用户基本信息</h4>
            <div class="control-group span4">
                <form:label path="username" cssClass="control-label">用户名</form:label>
                <div class="controls">
            
               
                
                      <form:input path="username" cssClass="validate[required,custom[username],ajax[ajaxCall]]"
                                placeholder="5到20个汉字、字母、数字或下划线" 
                               
                                
                                />
                 
                </div>
            </div>

             

            <div class="control-group span4">
                <form:label path="createDate" cssClass="control-label">创建时间</form:label>
                <div class="controls input-append date">
                    <form:input path="createDate"
                                  data-format="yyyy-MM-dd hh:mm:ss"
                                  data-position="bottom-left"
                                  placeholder="默认当前时间"/>
                    <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
                </div>
            </div>

            <c:choose>
                <c:when test="${op eq '新增' }">
                    <div class="control-group span4">
                        <form:label path="password" cssClass="control-label">初始密码</form:label>
                        <div class="controls">
                            <form:password path="password" cssClass="validate[required,minSize[5],maxSize[100]]"
                                             placeholder="请输入至少5位的初始密码"/>
                        </div>
                    </div>
                    <div class="control-group span4">
                        <form:label path="password" cssClass="control-label">确认密码</form:label>
                        <div class="controls">
                            <input  type="password" id="password2" class="validate[condRequired[password],equals[password]]"
                                             placeholder="请输入至少5位的确认密码"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <form:hidden path="password"/>
                </c:otherwise>
            </c:choose>

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
                <button type="submit" class="btn btn-primary btn-disable">
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
<%@include file="/WEB-INF/jsp/common/admin/import-sys-js.jspf"%>
<script type="text/javascript">
    $(function () {
    	 
        <c:choose>
            <c:when test="${op eq '查看'}">
                //删除时不验证 并把表单readonly
                $.app.readonlyForm($("#editForm"), true);
                
            </c:when>
           
            <c:otherwise>
                $.sys.user.initValidator($("#editForm"));
                <es:showFieldError commandName="m"/>
            </c:otherwise>
        </c:choose>

      //  $.sys.organization.initSelectForm("organizationId", "jobId");

    });

</script>