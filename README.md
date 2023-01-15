# project-backend
잇타 프로젝트  ALLPASStival 백엔드

# November
## DB 설계
* ### ERD Cloud <https://www.erdcloud.com/d/BQjNaRXTsrfnRAQjR>

## API 설계
## Cloud 선택 및 서버 생성
* ### Aws EC2
* ### RDS


# September
## 프로젝트 초기 설정
* Swaggger 적용(예정)
* IntelliJ 환경 변수를 통해 DB 연동
## EndPoint 구현
### week1
1. #### user id를 통해 유저 정보 받아오는 api 구현
   * localhost:8080/users/{userId}
   * 추후 userId가 아닌 accessToken을 통해 유저 정보를 받아오게 수정해야함
   * 추후 endpoint 주소 수정해야함
   
2. #### 회원가입 api 구현
   * localhost:8080/users/register
   * 비밀번호는 hash후 저장함
   * 이메일과 닉네임을 중복확인한 후 없을때만 회원가입하는 로직으로 작성함
   * 추후 auth api로 분리 필요 -> week2에서 수정함
   * 파일명, 변수 명 등 통일 필요(register/join) -> week2에서 수정함

### week2
1. #### 회원가입 api 수정
   * 파일명, 변수 명 등 통일
   * endpoint 수정 (api/v1/auth/register)
   
2. #### 테스트를 위한 서버 배포
   * jar 파일을 통한 배포
   * 추후 도커로 빌드하는것으로 수정할 예정
   * 서버 램 2GB로 늘림 (스왑 파일 이용)

### week2
1. #### cors 오류 해결
   * 이유는 DB컬럼의 조건과 요청값의 차이에서 생김
   * email이 없이 요청되었으나 email값은 not null로 지정되었음
   * 요청값을 수정하는것으로 해결
   * 추가적 cors오류 해결을 위한 config파일 추가

### week3
1. #### 유저 정보 수정 구현
2. #### 토큰 필터 수정
   * 모든 api가 permitall로 되어있어서 필터에 걸리지 않는 현상이 나타났다. -> 해당 줄 삭제처리