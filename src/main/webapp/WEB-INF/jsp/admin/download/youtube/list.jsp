<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jspf"%>
<es:contentHeader/>

 

<div data-table="table" class="panel">

    <ul class="nav nav-tabs">
        <li ${param['search.deleted_eq'] ne 'true' and param['search.status_eq'] ne 'blocked' ? 'class="active"' : ''}>
            <a href="${ctx}/admin/sys/role/list">
                <i class="icon-table"></i>
                所有任务列表
            </a>
        </li>

    </ul>

    <es:showMessage/>


    <div class="row-fluid tool ui-toolbar">
        <div class="span3">
            <div class="btn-group">
                 <shiro:hasPermission name="/admin/sys/role/create">
                <a class="btn no-disabled btn-create">
                    <span class="icon-file-alt"></span>
                    新增
                </a>
                </shiro:hasPermission>

               <shiro:hasPermission name="/admin/sys/role/delete">
                <a class="btn btn-delete">
                    <span class="icon-trash"></span>
                    删除
                </a>
                </shiro:hasPermission>
         <shiro:hasPermission name="/admin/sys/role/delete">
                <a class="btn btn-down">
                    <span class="icon-circle-arrow-down"></span>
                                               下载
                </a>
        </shiro:hasPermission>      
 
            </div>
        </div>
        <div class="span9">
            <%@include file="searchForm.jsp" %> 
        </div>
    </div>
    <table id="table" class="sort-table table table-bordered table-hover" data-prefix-url="${ctx}/admin/download/youtube">
        <thead>
        <tr>
            
            <th style="width: 80px;">
                <a class="check-all" href="javascript:;">全选</a>
                |
                <a class="reverse-all" href="javascript:;">反选</a>
            </th>
            <th sort="id">编号</th>
            <th sort="name">任务名称</th>
            <th sort="pace">下载状态</th>
            <th >下载进度</th>
            <th sort="userSecond">用时</th>
            <th sort="createDate">创建时间</th>
            <th  >操作</th>
        </tr>
        <tbody>
        <c:forEach items="${page.content}" var="m">
            <tr>
                 

                <td class="check">
                    <input type="checkbox" name="ids" value="${m.id}" />
                </td>
                <td>
                    <shiro:hasPermission name="/admin/sys/role/detials">
                    <a href="${ctx}/admin/download/youtube/detials?id=${m.id}">
                    </shiro:hasPermission>
                        ${m.id}
                    <shiro:hasPermission name="/admin/download/youtube/detials">
                    </a>
                    </shiro:hasPermission>
                </td>
                <td>${m.name}</td>
                <td id="${m.id}_pace" >${m.pace.info}</td>
                <td>
                
                <div class="progress progress-striped active">
               <c:choose>
               <c:when test="${m.pace eq 'finish'}"><div class="bar"  id="${m.id}_percen" style="width: 100%;"></div></c:when>
               <c:otherwise><div class="bar"  id="${m.id}_percen" style="width: 0%;"></div></c:otherwise>
                </c:choose>
                
                </div>
                </td>
                <td id="${m.id}_userSecond" >${m.userSecond}</td>
                <td><spring:eval expression="m.createDate"/></td>
                <td><a target="_blank" href="${ctx}/admin/download/youtube/download?id=${m.id}">下载</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <es:page page="${page}"/>
</div>
<es:contentFooter/>
 
 
<script type="text/javascript">
    $(function() {
    	$(".btn-down").off("click").on("click", function() {
    	    var table = $("#table");
    		var checkbox = $.table.getAllSelectedCheckbox(table);
            if(!checkbox.length)  return;
            var ids = checkbox.serialize();
            var url = table.attr("data-prefix-url")+  "/startTask?"+ids;
            $.getJSON(url, function(rs) {
            	 $.app.alert({
            			 title:"提示",
            			 message :rs.message
            	           }
            			 );
            });
            
    	});
    	
    	downloadcheck();
    	setInterval(downloadcheck, 8000);
       
    });
    function downloadcheck() {  
        var table = $("#table");
		var checkbox = $.table.getAllCheckbox(table);
        if(!checkbox.length)  return;
        
        var percodes = new Array();
		for(var idx=0;idx<checkbox.length;idx++){
			var cbd = $(checkbox[idx]);
            percodes.push("ids="+cbd.val());
		}
       
       var ids = percodes.join("&");
    	var url = table.attr("data-prefix-url")+  "/common/taskCheck";
    	
     	 $.getJSON(url, ids, function(json) {
     		 for(var i=0; i<json.length; i++) {  
     			console.log("#"+json[i].id+"_pace");
     			 $("#"+json[i].id+"_pace").text(json[i].paceInfo);
     			 $("#"+json[i].id+"_percen").attr("style","width:"+json[i].percen+"%;");
     			 if(json[i].userSecond != null && json[i].userSecond != 'null')
     			$("#"+json[i].id+"_userSecond").text(json[i].userSecond);
     		}
     	 });
    	}
      
</script>

