# coxautoinc
Cox Auto Code Challenge

### Overview:
This repo contans all components required to run Spring Web Application to complete the road test requirement for the company COX Auto Inc.

### Minimal requirements:
In order to run this proyect, the minimun software requeriments are:
  - _Java JDK 1.8_ (https://www.oracle.com/java/technologies/downloads/)
  - _GIT_ (https://git-scm.com/)
  - _MAVEN_ (https://maven.apache.org/)

### Usage:
- Clone repo with git CLI:
  <pre>git clone https://github.com/ElGranFacio/coxautoinc.git
  cd coxautoinc</pre>
- Compile & Package with maven:
  <pre>mvn clean package</pre>
- Run and deploy:
  - With maven:
  <pre>mvn spring-boot:run</pre>
  - As standalone java app:
  <pre>java -jar target/spring-webapp-0.0.1-SNAPSHOT.jar</pre>
- Start navigating:</br>
  In a web browser now you can go to next url:
  <pre>http://localhost:8080</pre>
  - Use <strong>Connect</strong> button to start <i>socket session</i> and type a <strong>Stock ID</strong>, after that press <strong>GO</strong> button <u>to see what happens</u>.

### Extra features:
- Unit Test:
  <pre>mvn test</pre>
  And see <i>target/surefire-reports</i> directory for results of unit tests
- Generate java doc:
  <pre>mvn javadoc:javadoc</pre>
  And see <i>target/site/apidocs/index.html</i> on web browser for Java Docs output
