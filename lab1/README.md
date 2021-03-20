#### Unit testing with JUnit 5

"The test pyramid is a way of thinking about how different kinds of automated tests should be used to create a balanced portfolio."

"should have many more low-level [UnitTests](https://martinfowler.com/bliki/UnitTest.html) than high level [BroadStackTests](https://martinfowler.com/bliki/BroadStackTest.html) running through a GUI."

<img src="https://i.gyazo.com/9daef5df1e7b5832d1b40c83eccbabde.png" alt="img" style="zoom:33%;" />

"It's easy to record tests, and the tests can be recorded by people with no knowledge of programming."

"Unit testing is when you (as a programmer) write test code to verify units of (production) code. A unit
represents a small subset of a much larger solution. A true “unit” does not have depend of the
behavior of other (collaborating) components."

"Unit tests help the developers to (i) understand the module contract (what to construct); (ii)
document the intended use of a component; (iii) prevent regression errors; (iv) increase confidence
on the code."



### Jacoco

1. Permite gerar relatórios para projetos *Java* relacionados com o número total de linhas de código que são executados durante os testes automáticos.

2. Usado o plugin  **jacoco-maven-plugin**

   

### **JUnit 5**

"**JUnit 5** is composed of several different modules from three different sub-projects."

Resumindo:

1. *Framework* para desenvolvimento e execução de testes unitários em código *Java*.
2. É boa prática criar os testes unitários logo de início antes do code em si.
3. É necessário usar o  plugin **maven-surefire-plugin** 
4. Usada a dependência **junit-jupiter** 

###### JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage



**Tests examples**: https://howtodoinjava.com/junit-5-tutorial/#write-test

#### Annotations

**@Test** 
**@BeforeEach** -> executed before each @Test
**@AfterEach** -> executed aftereach@Test
**@ParameterizedTest** -> Denotes that a method is a test method. Can provide “data” to be used in the test execution.

**@DisplayName** -> Declares a custom display name for the test class or test method. 
**@Disabled** -> Disable a test class or test method ou **fail();**



```
setUp e tearDown correm antes e depois de cada metodo respetivamente.
```

#### Asserts 

(não são da linguagem Java mas sim do jupiter Assertions! )

**assertTrue( ___ )**
**assertEquals(** *expect*: 0, stack.size() ,  message error)    //example



