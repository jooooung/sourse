# 배열
## 1. 배열이란
- 동일한 자료의 집합
- 하나의 이름으로 여러가지 데이터 사용 가능
- 여러 개의 변수를 모아 놓은 또 하나의 변수라 생각할 수 있다
## 2. 문법(배열 선언과 생성)
- 여러 개의 데이터가 모여 있어 ‘{ }’를 이용하여 초기화한다.
- 배열의 크기는 최초에 한번 설정되면 변경이 불가 하다.
- 배열을 객체로 취급.
- 배열선언 → 배열의 메모리 할당(배열 생성) → 배열이용  
```String[] arrName={"영희","철수","길동","영수","말자"};```
## 3. 배열의 메모리 구조의 이해
- int i=10; 메모리에 i주머니가 만들어 지고, i 주머니 안에 10이라는 데이터가 들어있다.
-  int[] iArr = {10,20,30}; 메모리에 iArr 주머니가 만들어지고 iArr주머니안에는 배열을 구성하고 있는 데이터의 주소값이 들어있다. 
## 4. 다차원 배열
- 배열이 가르키는 데이터에 또 다른 배일이 들어있는 구조  
  
```
ex)
iArr[0][0] = 10;
iArr[0][1] = 20;
iArr[1][0] = 110;
iArr[1][1] = 120;
iArr[2][0] = 210;
iArr[2][1] = 220;
```

