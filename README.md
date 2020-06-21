# sotongE2
대학 프로젝트 (데이터베이스 프로그래밍)

1.프로젝트 설명 : 학교 내에서 교수님과 학생들이 서로 상담신청을 하고, 상담신청 완료된 데이터들을 기반으로 학생들에게 상담 적합한 교수님을 추천해주는 웹 사이트

2.프로젝트 개요
  - 개발기간 : 2016년도2학기(데이터베이스프로그래밍)
  - 기획의도 : 교수와 학생 상호간의 상담신청이 원활하지 못한 교내를 인지하고 이를 개선 할 수 있는 웹페이지를 개발하게됨
  - 개발환경 : Eclipse,Tomcat 8,Oracle,ERWin
  - 사용한 Skill 또는 지식 : Java,JavaCollectionsFramework,JSP/Servlet, JavaScript, CSS, HTML, MVC와 DTO/DAO 패턴
  - 주요기능:
    * 재학생이 상담하고자 하는 교수를 검색 또는 자신의 시간표를 통해 해당 과목 교수님을 선택해 시간표와 일정을 보고 상담신청 기능
    * 교수도 자신의 수업을 듣는 학생들 또는 외부 학생들에게 면담신청을 보낼 수 있는 기능
    * 교수님의 일정을 관리하기 위해 일정 등록 기능
    * 상담신청 내역 수정 또는 삭제, 읽음의 확인여부, 수락 또는 거절 기능
    * 상담완료 후 결과를 남길 수 있는 기능
    * 상담이 완료된 데이터 기반으로 교수를 추천 받을 수 있는 기능
    
3.프로젝트 완료 후 동덕소통이를 수정한 부분
  - 리팩토링 중 페이지 처리하는 코드가 data가 많아지면 과부하가 걸리는 이슈를 발견
    (AS-IS) 상담내역을 전체 조회해서 WAS안에서 필요 데이터를 필터링했음
    (TO-BE) DB내에서 필터링해오는 걸로 변경(rownum이용), 전체 데이터안에서 start값 (동적) 으로 변화를 줘서 불러온 데이터에서 보여줄 데이터만 DTO에 넣음.
