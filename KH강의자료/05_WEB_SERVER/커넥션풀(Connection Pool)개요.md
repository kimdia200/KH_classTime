> Connection 은 데이터베이스서버에 연결하기 위해 사용되어지는 객체이다.
> 객체는 새롭게 만들어질때 시스템의 자원(CPU, 메모리)의 많은 소모와 시간이 걸리게 된다.
> DAO 클래스에서 생성되어지는 메소드(insert, select, update, delete 등)를 작성하려면 매번 Connection 객체 생성을 필요로 하게된다.
> 그런데 DAO 클래스의 메소드를 호출할때 마다 매번 Connection 객체를 생성해서 사용해야 한다는것은 시스템의 자원(CPU, 메모리)의 많은 소모와 시간이 걸리게 되므로, 이를 해결하기 위해 나온 방법이 Connection Pooling 기법이다.
> 
> Connection Pool 은 미리 처음부터 여러개의 Connection 객체를 만들어 두고서 DAO 클래스의 메소드를 호출할때 마다 미리 만들어둔 Connection 객체를 공유해서 사용하는 것이다. Connection Pooling 기법을 사용하면 시스템의 자원(CPU, 메모리)의 절약과 더불어 시간을 절약할 수 있게 된다. 
> 
> 톰캣서버에서도 이러한 Connection Pooling 기법을 제공하고 있는데 이것을 자카르타톰캣 DBCP(DB Connection Pool)라고 부른다.

## Connection Pool(커넥션 풀)
데이터 베이스와 연결될 커넥션을 미리 만들어서 풀(pool) 속에 저장해 두고 있다가 
  필요할 때에 커넥션을 풀에서 가져다 쓰고 다시 풀에 반환하는 기법을 의미한다.
  (수영장에서 튜브를 빌려다 쓰는 것과 비슷한 개념이다.)


### Connection Pool(커넥션 풀)

![](http://d.pr/i/137dd+)                                                      
                                                       
![](http://d.pr/i/1gOk7+)
                                                                                           
### Connection Pool(커넥션 풀)의 장점
 
- 풀 속에 이미 커넥션이 생성되어져 있으므로
   커넥션을 생성하는 데 필요한 연결 시간이 소모되지 않음.
- 커넥션을 계속해서 재사용하기 때문에 특별한 케이스를 제외하면 
   새로이 생성되는 커넥션 수는 없다고 봐도 무관함.
- 커넥션 풀을 사용하면 커넥션을 생성하고 닫는 시간이 필요하지 않으므로 
   그만큼 어플리케이션의 실행 속도가 빨라지고, 
- 또한 WAS의 server.xml 에 기술하는 값에 따라 한 번에 생성될 수 있는 커넥션 수를 
   제어할 수 있으므로 동시 접속자 수가 몰려도 웹 어플리케이션이 쉽게 다운되지 않음.

> 동시에 많은수의 접속자 처리를 하기 위해서는 커넥션의 갯수를 어떻게 조절할까?
> 
> 커넥션 풀에 들어있는 커넥션의 갯수는 한정적이므로
    커넥션 풀은 누군가 접속하면 커넥션 풀에 남아 있는 커넥션을 제공하는 방법으로 운영된다.
    하지만 커넥션 풀에 남아있는 커넥션이 없을 경우 해당 클라이언트는 대기 상태로 전환이 되고, 
    커넥션이 반환되면 대기하고 있는 순서대로 커넥션이 제공된다.
    그러므로 동시에 많은수의 접속자 처리를 하기 위해서는 WAS 서버의 물리적인 메모리(RAM)의
    크기를 증설한후 server.xml에 maxActive 와 maxIdle 값을 적절하게 조절하면 된다.


	<!-- MyDBCP Setting ========================================== -->
  		<Resource name="jdbc/myoracle" auth="Container"
              type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
              url="jdbc:oracle:thin:@127.0.0.1:1521:xe"
              username="student" password="student " maxActive="20" maxIdle="10"
              maxWait="-1"/>
	<!-- ========================================================= -->


* auth : 컨테이너를 자원 관리자로 기술
* name : JNDI에서 사용할 JDBC이름, 변경 가능
* driverClassName : JDBC 드라이버클래스
* type : 웹에서 이 리소스를 사용할 때 DataSource로 리턴됨
* username : 접속계정
* password : 접속할 계정 비밀번호
* loginTimeout : 연결 끊어지는 시간
* maxActive(maxTotal) : 최대 연결 가능한 Connection수 (기본 20개)
* maxIdle : 사용되지 않고 커넥션 풀에 남아 있을 수 있는 최대 커넥션 갯수.
          음수일 경우, 사용되지 않고 커넥션 풀에 남아 있을 수 있는 최대 커넥션 갯수는 제한이 없다.
* maxWait : 커넥션 풀에서 가져올 수 있는 커넥션이 없을 때 대기 시간.
          단위는 1/1000초, 0보다 작을 경우 무한히 대기.모래시계가 돌게된다.
* testOnBorrow : db에 test를 해볼 것인지



