lab04
exc1)

**a)**

Por um lado podemos identificar o uso do AssertJ por exemplo na class **EmployeeService_UnitTest** no método **given3Employees_whengetAll_thenReturn3Records** o seguinte exemplo:

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

Por outro lado na class **EmployeeRepositoryTest** no método
**givenSetOfEmployees_whenFindAll_thenReturnAllEmployees()**** o seguinte exemplo:

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

**b)**

É possível verificar que fazemos partido do comportamento do repository sem fazer uso da database e como foi explicado na aula prática, por exemplo na class **EmployeeService_UnitTest** no método **setUp** verfica-se:

```
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
```

O que acontece é que quando chamamos o employeeRepository incita-se o retorno de um determinado valor sem ser envolver a database.

**c)**

**<u>@Mock vs @MockBean</u>**

Como foi  dito nas aulas teóricas os **Unit Tests** são projetados para testar um componente isolado de outros componentes e  também têm orequisito de serem rápidos em termos de tempo de execução.

Devemos utilizar a anaotação **@Mock** quando queremos minimizar o código de criação de simulação repetitiva,quando queremos tornaa a classe de teste mais legível o que facilita a leitura do erro de verificação porque o nome do campo é usado para identificar a simulação.

Quando falamos na anotação **@MockBean** esta pode ser usada para adicionar mocks ao **Spring ApplicationContext**, pode ser usado como anotação ao nível da class ou como camplo em classes @Configuration. Por outro lado quando **@MockBean** for utilizado em um campo, além de ser registrado no contexto da aplicação, o mock também será injetado no campo.

**d)**

O papel do fle  **“application-integrationtest.properties”** é de facto garantir que todas as propriedades para o teste de integração funcionem. Pode ser usado por testes de integração de forma a garantir a coneccção à base de dados.

---------------

O exercicio 2 e 3 não consegui terminar a tempo de entrega.