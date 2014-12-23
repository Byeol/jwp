2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 `http://localhost:8080`으로 접근하면 질문 목록을 확인할 수 있다. `http://localhost:8080`으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* 사용자가 `http://localhost:8080`으로 접근하면, 서버는 *welcome-file-list*에 지정된 바에 따라 `index.jsp`를 반환한다.
* 사용자의 브라우저는 `index.jsp`의 내용에 따라, `http://localhost:8080/list.next`으로 *redirect* 한다.
* `list.next`에 대한 GET 요청이 발생하면, 서버는 *FrontController servlet*이 처리를 담당한다.
* *Servlet*은 응답을 처리할 *Controller*를 찾기 위해 *RequsetMapping*의 `findController("list.next")` 메소드를 호출하고 `new ListController()`를 반환 받는다.
* *FrontController servlet*이 반환된 *ListController*의 `execute()` 메소드를 실행한다.
* *ListController*가 *QuestionDao*의 `findAll()` 메소드을 실행해 데이터베이스에서 질문 목록을 가져온다.
* 새로운 *ModelAndView*를 생성하고 `jstlView("list.jsp")`로 초기화한 다음, `addObject()` 메소드를 실행해 *Model*에 가져온 질문 목록을 담은 다음, 만들어진 *ModelAndView*를 반환한다.
* *FrontController servlet*이 반환된 *ModelAndView*의 *View*를 이용해 새로운 *View*를 만든 다음, `render()` 메소드를 이용해 질문 목록이 담긴 `list.jsp` 페이지를 클라이언트로 반환한다.