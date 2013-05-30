<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<c:if test="${not empty menuitems}">
	<div class="span2">
		<div id="menu" class="well visible-desktop">
			<ul class="nav nav-list">
				<c:if test="${not empty menutitle}">
					<li class="nav-header">${menutitle}</li>
				</c:if>
				<c:forEach items="${menuitems}" var="item">
					<li id="menu-${item.menutitle}" class=""><a href="${item.urlpath}" target="_self">${item.menutitle}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</c:if>
	<c:if test="${not empty stagemenu}">
	<div class="span2">
		<div id="menu" class="well visible-desktop">
			<ul class="nav nav-list">
				<c:if test="${not empty menutitle}">
					<li class="nav-header">${menutitle}</li>
				</c:if>
				<li id="menu-Top" class=""><a href="#top" target="_self"><spring:message
							code="label.stage.menutop" /></a></li>
				<c:if test="${not empty stage.stageProfileImage}">
					<li id="menu-stageProfile" class=""><a href="#streckenprofil" target="_self"><spring:message
								code="label.stage.stageProfile" /></a></li>
				</c:if>
				<c:if test="${not empty valuecontainers}">
					<li id="menu-distance" class=""><a href="#abstand" target="_self"><spring:message
								code="label.stage.deficiteTime" /></a></li>
				</c:if>
					<c:if test="${not empty images || not empty videos}">
					<li id="menu-live" class=""><a href="#livestream" target="_self"><spring:message
								code="label.stage.liveStream" /></a></li>
				</c:if>
				<c:if test="${not empty valuecontainers}">
					<li id="menu-live" class=""><a href="#karte" target="_self"><spring:message
								code="label.stage.map" /></a></li>
				</c:if>
				<c:if test="${not empty liveTickerItems}">
					<li id="menu-liveticker" class=""><a href="#liveticker" target="_self"><spring:message
								code="label.stage.liveticker" /></a></li>
				</c:if>
				<c:if test="${not empty situation}">
					<li id="menu-situation" class=""><a href="#rennsituation" target="_self"><spring:message
								code="label.stage.raceSituation" /></a></li>
				</c:if>
				<c:if test="${not empty riders}">
					<li id="menu-ranking" class=""><a href="#rangliste" target="_self"><spring:message
								code="label.stage.liveRanking" /></a></li>
				</c:if>
				<c:if test="${not empty marchtable}">
					<li id="menu-marchtable" class=""><a href="#marschtabelle" target="_self"><spring:message
								code="label.stage.marchtable" /></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</c:if>
	<c:if test="${not empty adminmenu}">
	<div class="span2">
		<div id="menu" class="well visible-desktop" style="padding: 8px 0;">
			<ul class="nav nav-list">
				<li class="nav-header">Admin</li>
				<li id="menu-races" class=""><a href="/admin/race" target="_self"><spring:message
							code="label.race" /></a></li>
				<li id="menu-devices" class=""><a href="/admin/device" target="_self"><spring:message
							code="label.admin.devices" /></a></li>
			</ul>
		</div>
	</div>
	</c:if>
