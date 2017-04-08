<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="localos" uri="http://com.localos/tag/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.menus" var="menus"/>
<div id="navbar" class="navbar navbar-default navbar-collapse ace-save-state" style="background: #17A500">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="welcome.do" class="navbar-brand">
                <small>
                    <i class="fa fa-map"></i>
                    Local OS
                </small>
            </a>
            <button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
                <span class="sr-only">Toggle user menu</span>

                <img src="static-resource/ace/assets/images/avatars/user.jpg" alt="Jason's Photo">
            </button>
            <button class="pull-right navbar-toggle menu-toggler" type="button" data-toggle="collapse" data-target="#sidebar" aria-expanded="true">
                <span class="sr-only">Toggle sidebar</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-buttons navbar-header pull-right navbar-collapse collapse" role="navigation">
            <ul class="nav ace-nav">
                <tag:msg/><!-- message tag -->

                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="background: #17A500">
                        <img class="nav-user-photo" src="static-resource/ace/assets/images/avatars/user.jpg" alt="Jason's Photo">
								<span class="user-info">
									<small>Welcome,</small>
									<sec:authentication property="principal" var="userOfLogin"/>
                                    <localos:i18nNameTag i18nName="${userOfLogin}" />
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="setting.do">
                                <i class="ace-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="profile.do">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>
                        <li>
                            <c:choose>
                                <c:when test="${pageContext.response.locale.toLanguageTag() == 'en'}">
                                    <a href=".?userLanguage=zh_CN"><i class="ace-icon glyphicon glyphicon-font"></i>中文</a>
                                </c:when>
                                <c:otherwise>
                                    <a href=".?userLanguage=en"><i class="ace-icon glyphicon glyphicon-font"></i>English</a>
                                </c:otherwise>
                            </c:choose>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="logout.do">
                                <i class="ace-icon fa fa-power-off"></i>
                                <spring:message code="common.logout" />
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </div><!-- /.navbar-container -->
</div>