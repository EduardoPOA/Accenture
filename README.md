# Desafio QA Accenture

Descrição do Projeto
Este projeto é uma solução de automação de testes desenvolvida em Java, utilizando o Maven como ferramenta de gerenciamento de projetos. Ele abrange a automação de testes para APIs e interfaces web, empregando frameworks e bibliotecas populares no ecossistema de automação de testes.
O objetivo principal é demonstrar a capacidade de criar e executar testes automatizados para validar funcionalidades de sistemas, tanto no backend (API) quanto no frontend (Web).

# Tecnologias Utilizadas

As seguintes tecnologias e frameworks foram utilizados no desenvolvimento deste projeto:

Tecnologia/Framework Versão Descrição
Java                 11     Linguagem de programação principal.
Maven                last   Ferramenta de automação de build e gerenciamento de dependências.
Selenium WebDriver   4.15.0 Framework para automação de navegadores web.
WebDriverManager     5.6.0  Gerenciador automático de drivers de navegador.
Cucumber             7.14.0 Framework BDD (Behavior-Driven Development) para escrita de testes em linguagem natural (Gherkin).
JUnit                4.13.2 Framework de testes unitários para Java, utilizado para executar os testes Cucumber.
Rest-Assured         5.3.1  Biblioteca Java para testar e validar serviços REST.
GSON                 2.10.1 Biblioteca Java para serialização/desserialização de objetos Java para JSON.
SLF4J Simple         2.0.9  Simple Logging Facade for Java, para logging.

# Pré-requisitos

Para executar este projeto, você precisará ter o seguinte instalado:

•Java Development Kit (JDK): Versão 11 ou superior.
•Apache Maven: Versão 3.x ou superior.
•Navegador Web: Google Chrome (o WebDriverManager irá gerenciar o driver automaticamente).

# Como Executar os Testes

Navegue até o diretório raiz do projeto (DesafioQA_Accenture) no terminal.

- Executar Testes Web

Para executar os testes de interface web, utilize o seguinte comando Maven:

Bash      mvn test -Dcucumber.options="--tags @web"

Ou, para executar o WebTestRunner.java diretamente:

Bash      mvn test -Dtest=WebTestRunner


- Executar Testes de API

Para executar os testes de API, utilize o seguinte comando Maven:

Bash      mvn test -Dcucumber.options="--tags @api"

Ou, para executar o ApiTestRunner.java diretamente:

Bash      mvn test -Dtest=ApiTestRunner


- Executar Todos os Testes

Para executar todos os testes (Web e API), utilize o comando padrão do Maven:

Bash      mvn test


# Cenários de Teste

- Testes de API (BookStore API)

O arquivo BookStoreApi.feature descreve o fluxo completo de gerenciamento de livros via API, incluindo:

•Criação de um novo usuário.
•Geração de token de acesso.
•Verificação de autorização do usuário.
•Consulta da lista de livros disponíveis.
•Seleção e reserva de livros.
•Validação dos livros reservados no perfil do usuário.

- Testes Web (Practice Form - DemoQA)

O arquivo PracticeForm.feature detalha a automação do preenchimento e submissão de um formulário de prática no site DemoQA, cobrindo:

•Navegação para a página do formulário.
•Preenchimento de informações pessoais, educacionais e profissionais.
•Upload de um arquivo.
•Preenchimento de endereço, estado e cidade.
•Submissão do formulário e verificação do popup de confirmação.

# Relatórios

Após a execução dos testes, os relatórios serão gerados no diretório target/surefire-reports e target/cucumber-reports (se configurado) no formato padrão do Maven e Cucumber, respectivamente. Estes relatórios fornecem detalhes sobre a execução dos testes, incluindo status (passou/falhou) e informações de depuração.

