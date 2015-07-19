<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="searchForm" method="post" class="form-inline search-form" data-change-search="true">

    <label path="search_EQ_id">编号</label>
    <input name="search_EQ_id" value="${EQ_id}" class="input-small" placeholder="编号"/>
    &nbsp;&nbsp;

    <label >任务名称</label>
    <input name="search_LIKE_name" value="${LIKE_name}"  class="input-small" placeholder="模糊匹配"/>
    &nbsp;&nbsp;
 
 

    &nbsp;&nbsp;
    <input type="submit" class="btn" value="查询"/>
    <a class="btn btn-link accordion-toggle" data-toggle="collapse" href="#searchMore">高级查询</a>
    <a class="btn btn-link btn-clear-search">清空</a>


    <%--more--%>
    <div id="searchMore" class="accordion-body collapse">
        <div class="accordion-inner">
            <label path="search.createDate_gte">创建时间从</label>

            <div class="input-append date">
                <input name="search_GTE_createDate" class="input-medium"
                              data-format="yyyy-MM-dd" value="${GTE_createDate}"
                              placeholder="大于等于"/>
                <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
            </div>
            <label path="search.createDate_lte">到</label>
            <div class="input-append date">
                <input name="search_LTE_createDate" class="input-medium" value="${LTE_createDate}"
                              data-format="yyyy-MM-dd"
                              data-position="bottom-left"
                              placeholder="小于等于"/>
                <span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
            </div>
        </div>
    </div>
</form>
