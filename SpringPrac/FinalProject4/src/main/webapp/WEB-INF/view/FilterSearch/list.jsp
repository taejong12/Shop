<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<style type="text/css">
 .pageInfo{
      list-style : none;
      display: inline-block;
    margin: 50px 0 0 100px;      
  }
  .pageInfo li{
      float: left;
    font-size: 20px;
    margin-left: 18px;
    padding: 7px;
    font-weight: 500;
  }
 a:link {color:black; text-decoration: none;}
 a:visited {color:black; text-decoration: none;}
 a:hover {color:black; text-decoration: underline;}
 
 .active{
      background-color: #cdd5ec;
  }

  .search_area{
    display: inline-block;
    margin-top: 30px;
    margin-left: 260px;
  }
  .search_area input{
      height: 30px;
    width: 250px;
  }
  .search_area button{
     width: 100px;
    height: 36px;
  }
  
</style>

</head>
<body>
	
	<table class="table">
		<thead>
			<tr>
				<th>주종이름</th>
				<th>주류사진</th>
				<th>주류이름</th>
				<th>도수(%)</th>
				<th>가격(원)</th>
				<th>재고(개)</th>
				<th>단맛</th>
				<th>신맛</th>
				<th>쓴맛</th>
				<th>농도</th>
				<th>탄산</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="list">
           <tr>
               <td><c:out value="${list.kiName}"/></td>
               <td><img src="${list.alOpic}" referrerpolicy="no-referrer"></td>
               <td><a href='/alView?alNum=${list.alNum}'>${list.alName}</a></td>
               <td><c:out value="${list.alAbv}"/>%</td>
               <td><c:out value="${list.alPrice}"/>원</td>
               <td><c:out value="${list.alStock}"/>개</td>
               <td><c:out value="${list.flDanmat}"/></td>
               <td><c:out value="${list.flSinmat}"/></td>
               <td><c:out value="${list.flSsunmat}"/></td>
               <td><c:out value="${list.flBody}"/></td>
               <td><c:out value="${list.flTansan}"/></td>
           </tr>
       	</c:forEach>
		</tbody>
	</table>
	
	 <div class="search_wrap">
        <div class="search_area">
        	<select name="type">
                <option value="" <c:out value="${pageMaker.cri.type == null?'selected':'' }"/>>--</option>
                <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':'' }"/>>제목</option>
            </select>
            <input type="text" name="keyword" value="${pageMaker.cri.keyword }">
            <button>Search</button>
        </div>
    </div>    
	
	
	<div class="pageInfo_wrap" >
        <div class="pageInfo_area">
         	<!-- 이전페이지 버튼 -->
            <c:if test="${pageMaker.prev}">
                <li class="pageInfo_btn previous"><a href='/store/search?pageNum=${pageMaker.startPage-1}'>Previous</a></li>
            </c:if>
                
			<!-- 각 번호 페이지 버튼 -->
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="pageInfo_btn ${pageMaker.cri.pageNum == num ? "active":"" }"><a href='/store/search?pageNum=${num}'>${num}</a></li>
            </c:forEach>

			
			<!-- 다음페이지 버튼 -->
            <c:if test="${pageMaker.next}">
                <li class="pageInfo_btn next"><a href='/store/search?pageNum=${pageMaker.endPage + 1 }'>Next</a></li>
            </c:if>   
			
        </div>
    </div>
 
	<form id="moveForm" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
		<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }"> 
		<input type="hidden" name="type" value="${pageMaker.cri.type }">   
	</form>
 
<script>

let moveForm = $("#moveForm");

$(".move").on("click", function(e){
	e.preventDefault();
	moveForm.find("<input type='hidden' name='alNum' value='"+ $(this).attr("href")+"'>");
	moveForm.attr("action", "/alView");
	moveForm.submit();  
});

$(".pageInfo a").on("click", function(e){
	 e.preventDefault();
     moveForm.find("input[name='pageNum']").val($(this).attr("href"));
     moveForm.attr("action", "/store/search");
     moveForm.submit();  
});

$(".search_area button").on("click", function(e){
    e.preventDefault();
    
    
    let type = $(".search_area select").val();
    let keyword = $(".search_area input[name='keyword']").val();
    
    if(!type){
        alert("검색 종류를 선택하세요.");
        return false;
    }
    
    if(!keyword){
        alert("키워드를 입력하세요.");
        return false;
    }        
    
    moveForm.find("input[name='type']").val(type);
    moveForm.find("input[name='keyword']").val(keyword);
    moveForm.find("input[name='pageNum']").val(1);
    moveForm.submit();
});

</script>
 
</body>
</html>