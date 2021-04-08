Developing Selenium tests with JUnit 5 by Boni García
https://youtu.be/JFpcL_it2yA

Some notes:
JUnit 5 oferece uma "brand-new programming" extenção chamada Jupiter.

Pode ser executado em diferentes formas:
1) Usando buil toolds: Maven, ...
2) Usando um IDE: Intellij IDEA, eclipse, ...

3) Usando a consola

"Selenium is an umbrella project for a range of tools and libraries that enable and support 
the automation of web browsers."

Dependendies needed: Jupiter
Plugin: m aven.plugins

Assertion: é um predicado (função boleana) que deve ser avaliado como true
para continuar com a execução do programa ou do teste.
	ex: assertTrue, assertFalse, assertEquals...

***Selenium-Jupiter***
Selenium-Jupiter é uma extenção do JUnit 5 que permite facilitar o uso
do selenium a partir de testes Java

Features:
-> Clean test code;
-> Permite a integração do Docker sem esforço;
-> Advanced features para tetses


Docker is a software tech wich allows to pack and run any app as a lightweight
and portable container.
Has 2 main components: 
	Docker Engine to create and execute containers
	Docker Hub, a cloud service for distributing containers

