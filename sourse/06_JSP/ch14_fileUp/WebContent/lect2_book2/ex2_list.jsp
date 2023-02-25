<%@page import="com.lec.book.BookDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lec.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String conPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="<%=conPath%>/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <%
  	String pageNum = request.getParameter("pageNum");
  	if(pageNum == null){
  		pageNum = "1";
  	}
  	int currentPage = Integer.parseInt(pageNum);
  	final int PAGESIZE=3, BLOCKSIZE=4;
  	int startRow = (currentPage - 1) * PAGESIZE + 1;
  	int endRow = startRow + PAGESIZE - 1;
  	BookDao dao = BookDao.getInstance();
  	ArrayList<BookDto> books = dao.listBook(startRow, endRow);
  %>
	<table>
		<caption>책 목록</caption>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>표지</th>
			<th>가격</th>
			<th>할인율</th>
		</tr>
		<% if(books.isEmpty()){ %>
		<tr><td colspan="5">등록된 책이 없습니다</td></tr>
				
		<% }else{ 
			for(BookDto book : books){
		%>	
			<tr>
				<td><%=book.getBid() %></td>
				<td>
					<a href='detail.jsp?bid=<%=book.getBid() %>'><%=book.getBtitle() %></a>
				</td>
				<td>
					<a href='detail.jsp?bid=<%=book.getBid() %>'>
						<img src =<%=conPath %>/bookFile/<%=book.getBimage1() %> width='30'>
					</a>
				</td>
				<td>
					<del><%=book.getBprice() %></del>
					<b><%=book.getBprice() * (100 - book.getBdiscount()) / 100 %>원</b>
				</td>
				<td>
					<%=book.getBdiscount() %>
				</td>
		<%
				}
			} 
		%>
	</table>
		<a href="ex2_list.jsp">책 1page리스트(게시판 스타일)</a><br>
		<a href="ex4_list.jsp">책 1page리스트(product list 스타일)</a><br>
		<button onclick="location.href='bookRegisterForm.html'">책 등록</button> 
	<div class="paging">
		<%
			int bookTotalCnt = dao.getBookTotalCnt();
			int pageCnt = (int)Math.ceil((double)bookTotalCnt / PAGESIZE);
			int startPage = ((currentPage - 1)/BLOCKSIZE) * PAGESIZE + 1;
		  int endPage = startPage + BLOCKSIZE - 1;
		  if(endPage > pageCnt){
			  endPage = pageCnt;
		  }
		  if(startPage > BLOCKSIZE){
		%>
				<a href='ex2_list.jsp?pageNum=<%=startPage-1 %>'>[이전]</a>	  
		  <%}
		  	for(int i=startPage ; i<=endPage ; i++){
		  		if(i == currentPage){
		  			out.println("<b>" + i + "</b>");
		  		}else{
		  			out.println("<a href='ex2_list.jsp?pageNum=" + i + "'>" + i + "</a>");
		  		}
		  	}
		  if(endPage < pageCnt){
		%>	
			<a href='ex2_list.jsp?pageNum=<%=endPage+1 %>'>[다음]</a>  
		 <%}%>
	</div>
</body>
</html>