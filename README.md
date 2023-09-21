# RedisBoard
<img src="https://img.shields.io/badge/Springboot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white"/>

## Redis 데이터베이스를 이용한 게시판 만들기(2023.09.19 ~ 2023.09.21)

## 목차
- [기능](#기능)
- [구조](#구조)
- [설정](#설정)

## 기능
### 1. 게시판
- 1-1. 게시판 글 작성
  - 회원만 작성 가능
- 1-2. 게시판 글 상세보기
  - 로그인을 하지 않아도 볼 수 있음
- 1-3. 게시판 글 수정
  - 직접 작성한 유저 이외에 수정 불가능
- 1-4. 게시판 글 삭제
  - 직접 작성한 유저 이외에 수정 불가능
### 2. 로그인
- 2-1. 회원가입
  - 중복 가입 방지
- 2-2. 로그인

## 구조
### Java
- config
  - RedisConfig
  - SecurityConfig
- controller
  - BoardController
  - MainController
  - MemberController
- entity
  - Board
  - Member
- repository
  - BoardRepository
  - MemberRepository
- service
  - BoardSerivce
  - MemberService
### resources
- js
  - postPage.js
  - signUp.js
- html
  - board
    - add.html
    - post.html
    - update.html
  - member
    - login.html
    - signUp.html
  - main.html

## 설정
### 사용 언어
- Java
  - version : 1.8.0
### 프레임워크
- Spring-Boot
  - version : 2.7.15
### DB
- Redis
  - version : 7.2.1
### IDE 및 툴
- IntelliJ
- VS_Code