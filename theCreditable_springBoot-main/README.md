# 더 크레디터블 CMS 백엔드 (SPRING BOOT JPA)

## 목적
__더 크레디터블 CMS__ 의 백엔드 개발.  
더 크레디터블 프론트 프로젝트 와는 분리보관 합니다.  
개발 방법론은 개발정책 repository를 따릅니다.

### 1. Version Control
+ v 0.1 고고페이 CMS (JPA), http://gogopay.kr:3100 기반으로 모듈화 했습니다.
+ 고고페이 CMS : https://gitlab.com/gogofnd/gogopay-cms_springboot
+ 더 크레디터블 프론트 : https://gitlab.com/gogofnd/thecreditable_react

### 2. 테스트 서버 배포 정보
+ 서버 : dev.gogopay.kr
+ 포트 : 9400
+ 배포 디렉토리 : /home/develop2/theCreditable/CMS
+ 리소스 디렉토리 : /home/develop2/theCreditable/CMS/resource
+ swagger 주소 : http://dev.gogopay.kr:9400/api/theCreditableCMS/swagger-ui/#/

### 2-1. DB 정보
dev.gogopay.kr  
Database : theCreditable  
user : creditable  
password : dev1004

### 3. 기능
+ 고고페이 CMS 기반 공지사항 기능 우선 구현 예정  
  (로그인/등록/리스트+페이징/수정/삭제)  
+ 고고페이에서 사용하는 글로벌 기능 전부 재사용 가능하도록 패키징됨
+ 상기 기능 외 고고페이 상세 기능은 불필요하여 전부 삭제함

### 4. 추가할 것들
+ 엑셀 내보내기
+ 검색 로직
