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
   * 추후 auth api로 분리 필요
   * 파일명, 변수 명 등 통일 필요(register/join)