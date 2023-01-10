# 패턴(디자인패턴)

## - 싱글톤 패턴(Singleton pattern)
 - 어떤 클래스의 객체는 오직 하나인 유일한 객체를 만들어 여러가지 상황에서 동일한 객체에 접근하기 위해 만들어진 패턴
 - 위키백과 曰, 싱글턴 패턴(Singleton pattern)을 따르는 클래스는, 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고 이 객체에 접근할 수 있는 전역적인 접촉점을 제공하는 패턴이다.

 ## - 스트래티지 패턴(Strategy Pattern)
- 기능 하나를 정의하고 각각을 캡슐화하여 교환해서 사용할 수 있도록 만든다
- 스트래티지를 활용하면 기능을 사용하는 클라이언트와는 독립적으로 기능을 변경 가능
- 어떤 객체를 만들 때 객체가 가지는 기능들을 추상화하여 기능을 부품화, 표준화 하는 것