<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="Sorry...">
    <div class="ui container">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
            <tr>
                <td>
                    <TABLE width="100%" height="100%">
                        <TR>
                            <TD align="center" valign="middle"><br><br><br><br><br><br><br><br><br><br><br><br>
								<B> 
									<c:forEach items="${multiple_messages}" var="msg">
									${msg}<br>
									</c:forEach>
									${single_messages}
								</B>
                            </TD>
                        </TR>
                    </TABLE>
                </td>
            </tr>
        </table>
    </div>
</depotnextdoor:page>
