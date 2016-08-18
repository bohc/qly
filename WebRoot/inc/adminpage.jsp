<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.util.page.*"%>

<%
  PageControl pg=(PageControl)request.getAttribute("pc");
  //String formName=request.getParameter("formName");
  String formName="listForm";
  System.out.print(pg);
  if(pg!=null){
%>
	共<%=pg.getMaxRowCount()%>条记录 
	<%=pg.getFirstPage(formName)%> 
	<%=pg.getBackPage(formName)%> 
	<%=pg.getNextPage(formName)%> 
	<%=pg.getEndPage(formName)%> 转到第
	<INPUT type="hidden" name="count" value="<%=pg.getMaxRowCount()%>">
	<select name="currentpage" onchange="toPage();">
		<%if(pg.getMaxPage()<=1){ %>
		<option value="1">1</option>
		<%}else{ 
		for(int i=1;i<=pg.getMaxPage();i++){
		if(i==pg.getCurPage()){%>
		<option value="<%=i %>" selected="selected"><%=i %></option>
		<%}else{ %>
		<option value="<%=i %>"><%=i %></option>
		<%}}} %>
	</select>
	页 页次:　<%=pg.getCurPage()%>/<%=pg.getMaxPage()%>页 <%=pg.getRowsPerPage()%>条/页

<% } %>
<script>
  function toPage(){
     document.<%=formName%>.submit();
  }
</script>