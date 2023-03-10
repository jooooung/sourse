# ✨JSP
- JSP model1 = MVC패턴 없이 
- JSP model2 = MVC패턴(협업, 유지보수에 용이)
- JSP는 Servlet과 반대로 HTML코드에 JAVA언어를 삽입하여 동적 문서를 만들 수 있다

## ✔ JSP 태그

1. 스크립트릿 `<%  java 코드  %>` : JAVA 코드, 스크립트릿 내의 변수는 지역변수

   - 스크립트릿 내의 변수를 초기화하지 않으면 쓰레기 데이터가 된다

2. 지시자 `<%@  %>` : 페이지 속성을 지정할 때 사용, (page, include, taglib)

   - page : 해당 페이지의 전체적인 속성 지정. 주로 사용되는 언어 지정 및 import문을 많이 사용, 문서 타입(html, excel..)
   - include : 별도의 페이지를 현재 페이지에 사용 (footer 등 모든 페이지에서 반복되는 작업 등에 이용)  
     Ex) `<%@ include file="파일경로" %>`
   - taglib : 태그라이브러리(사용자가 만든 tag들)의 태그 사용. 태그라이브러리를 사용하기 위해 taglib지시자 사용.

3. 선언 `<%! java 코드 기술 %>` : 전역변수 및 메소드 선언

   - 선언부에서는 초기화하지 않아도 수는 0, 객체는 null로 자동 초기화
   - 브라우저를 꺼도 전역변수는 남아 있다

4. 주석 `<%-- --%>` : 응답 페이지에서는 이 주석은 보이지 않는다.

   - WAS에서 이 주석은 제거하고 응답페이지를 만든다.
   - cf. HTML 주석 : `<!-- -->`

5. 표현식 `<%= java 코드 기술 %>` : JSP 페이지 내에서 사용되는 변수의 값 또는 메소드 호출 결과값 출력

6. 액션태그(jsp에서만 쓸 수 있는 태그)
   - `<jsp:action></jsp:action>` : 표준액션태그 ex.파일연결 및 자바빈 연결
   - `<c:set></c:set>` : 커스텀액션태그

## ✔ 내부객체

- 객체를 생성하지 않고 바로 사용할 수 있는 객체이다

## 대표적인 내부 객체

- 입출력 객체 : `request`, `response`, `out`
- 서블릿 객체 : `application`, `page`, `config`
- 세션 객체 : `session`
- 예외 객체 : `exception`

### `request` 객체 : 웹브라우저를 통해 서버에 어떤 정보를 요청하는 것

- `request 객체의 메소드
  1. `request.getRemoteAddr()` : 웹서버에 연결한 클라이언트의 IP주소(예전에는 IPv4, 요즘은 IPv6)
     - `Ipv6` => `Ipv4로` 바꾸기 : eclipse > Run > Run Configurations > Apache Tomcat
       > Tomcat v9.0 Server >Arguiments >  
       > Program arguments에 `-Djava.net.preferIPv4Stack=true` 입력  
       > VM arguments:에 `-Djava.net.preferIPv4Stack=true` 입력
  2. `request.getContextPath()` : contextPath를 리턴(프로젝트명)
  3. `getParameter()`: 파라미터 값을 가져온다
  4. `getParameterValues()` : 해당하는 파라미터값들을 구함
  5. `getMethod()` : get방식과 post방식을 구분
  6. `getSession()` : 세션 객체를 얻어온다
  7. `getProtocol()` : 해당 프로토콜을 얻어온다
  8. `getRequestURL()` : 요청 URL를 얻어온다(http부터 끝까지)
  9. `getRequestURI()` : 요청 URI를 얻어온다(http와 포트번호를 제외한 주소)
  10. `getServerPort()`: 포트번호를 얻어온다

### `response` 객체 : 웹브라우저의 요청에 응답하는 것

- `getCharacterEncoding()` : 응답할때 문자의 인코딩.
- `addCookie(Cookie)` : 쿠키를 지정.
- `sendRedirect(URL)` : 지정한 URL로 이동

## out

- `out.getBufferSize()` : 버퍼 크기 가져오기
- `out.getRemaining()` : 남은 버퍼 크기  
버퍼 크기를 다 사용하여도 플러쉬(화면에 뿌림) 후 다시 파싱
- `application.getContextPath()` : 프로젝트 명
- `application.getRealPath(".")` : 현재 프로젝트가 실행되는 절대 경로


## ※ 게시판 파일 첨부 시
- server 폴더에 올라간다. 만약 소스를 수정 시 수정한 파일만이 아닌 전체를 다 서버에 재업로드한다
- JSP는 try-catch를 안 하여도 에러가 안 난다

## ※ 배포하기
- 내가 사용자가 사용하도록 웹서버에 배포 : 나의 톰캣의 webapps 폴더에 x라는 이름의 context root로 배포  
1. 톰캣의 webapps폴더 밑에 x 폴더 생성
2. sourse 폴더의 webContent폴더에 있는 모든 내용을 x폴더로 복사
3. sourse 폴더의 build/classes 폴더를 x/WEB-INF 밑에 복사
4. 톰캣 부팅 (tomcat/bin에서 cmd 실행 - startup (서버 실행, 안된다면 이클립스 내에서 서버 실행) 
5. 브라우저 url에 다음을 임력하여 실행 : http://localhost:8090/x     (port를 80으로 한다면 생략가능)