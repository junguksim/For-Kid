# 아이를 위한 우리 가족 집 찾기 서비스, '포키드'

### 개요

- 삼성청년SW아카데미 5기 1학기 최종 프로젝트 (2021.05.20 ~ 2021.05.27)

### Use Case Diagram

#### 1) 회원관리

회원가입, 로그인, 로그아웃, 회원탈퇴, 회원정보 조회 및 수정

![image](https://user-images.githubusercontent.com/44252639/121977949-1aafa400-cdc2-11eb-9107-602663a72a70.png)

#### 2) 아파트 검색 기능

지역(동)/아파트명/학교명으로 검색, 아파트 상세 정보, 주편 편의시설 정보, 매매/전세 거래 정보, 주변 교육시설 정보, 학교 상세 정보

![image](https://user-images.githubusercontent.com/44252639/121978023-4468cb00-cdc2-11eb-9c89-c86682c54d48.png)

#### 3) QnA 게시판

게시글 등록, 게시글 목록 조회, 게시글 상세 조회, 자신의 글 수정/삭제, 게시글 검색, 답글 등록, 자신의 답글 수정/삭제

![image](https://user-images.githubusercontent.com/44252639/121978070-606c6c80-cdc2-11eb-90d6-0a79779082d8.png)

### 2. 테이블 구조도(ERD)

![image](https://user-images.githubusercontent.com/44252639/121978088-6b270180-cdc2-11eb-9da9-12d40aae0310.png)

- BaseAddress : 시, 동, 구에 대한 위도, 경도 정보
- HouseInfo : 아파트 정보 (아파트명, 주소, 경도, 위도...)
- HouseDeal : 특정 아파트의 거래 정보 (매매/전세 구분, 거래일, 가격...)
- Comment : 특정 아파트에 대한 후기 (작성자, 내용, 아파트번호 ...)
- Member : 회원정보 (아이디, 비밀번호, 이메일, Salt값...)
- Board : 게시글 정보 (번호, 제목, 내용, 작성자, 작성일...)
- Reply : 게시글에 대한 답변 정도 (게시글번호, 작성자, 작성일...)
- School : 학교 정보 (번호, 이름, 주소, 학급수, 학급당 학생수...)

### 3. 클래스 다이어그램 (Class Diagram)

![image](https://user-images.githubusercontent.com/44252639/121978116-7d08a480-cdc2-11eb-9297-15f2774b8336.png)

- HouseInfo : 아파트 기본 정보
- HouseDeal : 아파트 거래 내역 정보
- HouseList : 특정 지역 근처 아파트 목록 반환을 위한 클래스
- HousePrice : 아파트 가격정보를 매매/전세-평수에 따라 묶기위한 클래스
- Location : 해당 위치(경도,위도), 기준점으로부터의 거리를 담는 클래스
- Comment : 특정 아파트에 대한 후기 정보
- Member : 회원 정보
- Board : 게시글 정보
- Reply : 특정 게시글에 대한 답변 정보
- School : 학교별 상세 정보
- Result : 넘겨줄 결과값을 담을 클래스 (상태코드, 데이터, 메시지)


### 4. 화면 설계서

### 1) 메인 페이지

![image](https://user-images.githubusercontent.com/44252639/121978137-87c33980-cdc2-11eb-99ed-7e01636d572d.png)

### 2) 아파트 검색 결과 페이지

![image](https://user-images.githubusercontent.com/44252639/121978149-8eea4780-cdc2-11eb-99d8-b3ee5cd0dd58.png)

### 3) 아파트 상세 정보 페이지

![image](https://user-images.githubusercontent.com/44252639/121978166-96a9ec00-cdc2-11eb-9fa9-1867376f952b.png)

### 4) 아파트 평수별 매매/전세가격 페이지

![image](https://user-images.githubusercontent.com/44252639/121978181-9c9fcd00-cdc2-11eb-9933-afcf3229df40.png)

### 5) 아파트별 코멘트 페이지

![image](https://user-images.githubusercontent.com/44252639/121978205-a6293500-cdc2-11eb-9fd1-6da07abc094f.png)

### 6) 학교 상세 정보 페이지

![image](https://user-images.githubusercontent.com/44252639/121978218-b04b3380-cdc2-11eb-838b-9c846c7cd683.png)

### 7) 네비게이션 바

![image](https://user-images.githubusercontent.com/44252639/121978228-b8a36e80-cdc2-11eb-9b19-0d339b1f76b5.png)

### 8) 회원가입 페이지

![image](https://user-images.githubusercontent.com/44252639/121978235-be00b900-cdc2-11eb-9d18-fae7ef891d9d.png)

### 9) 회원가입 완료 후 네이게이션 바

![image](https://user-images.githubusercontent.com/44252639/121978249-c48f3080-cdc2-11eb-88fa-6af55ff4d64e.png)

### 10 ) 로그인 페이지

![image](https://user-images.githubusercontent.com/44252639/121978259-cb1da800-cdc2-11eb-9547-cd923fe0ddc4.png)

### 11) 동별교육지수 순위 조회 페이지

![image](https://user-images.githubusercontent.com/44252639/121978267-d1ac1f80-cdc2-11eb-9075-222573f80fd4.png)

### 12) 마이페이지

![image](https://user-images.githubusercontent.com/44252639/121978276-d7a20080-cdc2-11eb-9d55-1ca9f98541e5.png)

### 13) QnA 게시글 목록 페이지

![image](https://user-images.githubusercontent.com/44252639/121978288-dcff4b00-cdc2-11eb-9e04-96feeb37bf1e.png)

### 14) QnA 게시글 등록 페이지

![image](https://user-images.githubusercontent.com/44252639/121978301-e2f52c00-cdc2-11eb-94d7-b7c05982d66b.png)

### 15) QnA 게시글 상세 페이지 + 답변 페이지

![image](https://user-images.githubusercontent.com/44252639/121978308-e7b9e000-cdc2-11eb-9ea8-e536e9e8f24a.png)

### 16) 답변 목록 페이지

![image](https://user-images.githubusercontent.com/44252639/121978320-ec7e9400-cdc2-11eb-8c14-0444a153b980.png)

### 17) 답변 수정 페이지

![image](https://user-images.githubusercontent.com/44252639/121978341-f2747500-cdc2-11eb-961a-c6ef3c699386.png)


## 기여자
- [심정욱](https://github.com/junguksim)
- [이상현](https://github.com/kimkuan)
