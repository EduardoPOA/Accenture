Feature: BookStore API Automation
  Como usuário do sistema BookStore
  Eu quero executar o fluxo completo de gerenciamento de livros via API
  Para validar a integração e funcionalidades do sistema

  Scenario: Fluxo completo de criação de usuário e reserva de livros
    Given que eu tenho um usuário não cadastrado no sistema
    When eu crio um novo usuário na API BookStore
    And gero um token de acesso para esse usuário
    And verifico que o usuário está autorizado no sistema
    And consulto a lista de livros disponíveis
    And seleciono e reservo dois livros da lista
    Then eu devo visualizar os livros reservados no perfil do usuário