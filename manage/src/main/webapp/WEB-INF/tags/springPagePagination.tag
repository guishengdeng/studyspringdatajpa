<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="url" required="true" type="java.lang.String" description="页面地址，请不分包含参数，分页会自动从请求中获取参数" %>
<%@ attribute name="springPage" required="true" type="org.springframework.data.domain.Page" %>
<%@ attribute name="defaultPageSize" required="false" type="java.lang.Integer" %>
<%

    String fixedUrl = url;
    if(url.contains("?")){
        fixedUrl = url.split("\\?")[0];
    }
    String queryString = request.getQueryString();
    if(queryString != null){
        String otherParams = queryString.replaceAll("page=\\d*", "").replaceAll("pageSize=\\d*", "").replaceAll("&&+","&");
        if(otherParams.startsWith("&")){
            otherParams = otherParams.substring(1);
        }
        request.setAttribute("pageOtherParams", otherParams);
    }

    request.setAttribute("fixedUrl", fixedUrl);
    request.setAttribute("pageSize", springPage == null ? defaultPageSize == null ? 10 : defaultPageSize : springPage.getSize());
    request.setAttribute("currentPage", Math.max(1, springPage == null ? 0 : springPage.getNumber() + 1));
    int totalPage = Math.max(1, springPage == null ? 0 : springPage.getTotalPages());
    request.setAttribute("totalPage", totalPage);
    request.setAttribute("fromPage", Math.max(1, springPage == null ? 0 : springPage.getNumber() - 1));
    request.setAttribute("endPage", Math.min(totalPage ,Math.max(1, springPage == null ? 0 : springPage.getNumber() + 3)));
%>
<nav style="text-align: right">
    <ul class="pagination">
        <c:if test="${fromPage > 1}">
            <li>
                <a href="${fixedUrl}?page=1&pageSize=${pageSize}${empty pageOtherParams ? '' : '&'}${pageOtherParams}" >
                    <span>
                        <i class="fa fa-step-backward"></i>
                    </span>
                </a>
            </li>
        </c:if>
        <c:choose>
            <c:when test="${springPage.hasPrevious()}" >
                <li>
                    <a href="${fixedUrl}?page=${currentPage - 1}&pageSize=${pageSize}${empty pageOtherParams ? '' : '&'}${pageOtherParams}">
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a>
                        <span aria-hidden="true">上一页</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="${fromPage}" end="${endPage}" step="1" var="pageNumber">
            <c:choose>
                <c:when test="${pageNumber eq currentPage}" >
                    <li class="active">
                        <a>
                            <span aria-hidden="true">${pageNumber}</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${fixedUrl}?page=${pageNumber}&pageSize=${pageSize}${empty pageOtherParams ? '' : '&'}${pageOtherParams}">
                            <span>${pageNumber}</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${springPage.hasNext()}" >
                <li>
                    <a href="${fixedUrl}?page=${currentPage + 1}&pageSize=${pageSize}${empty pageOtherParams ? '' : '&'}${pageOtherParams}">
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a>
                        <span aria-hidden="true">下一页</span>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:if test="${endPage < totalPage}">
            <li>
                <a href="${fixedUrl}?page=${totalPage}&pageSize=${pageSize}${empty pageOtherParams ? '' : '&'}${pageOtherParams}" >
                    <span>
                        <i class="fa fa-step-forward"></i>
                    </span>
                </a>
            </li>
        </c:if>

    </ul>
</nav>