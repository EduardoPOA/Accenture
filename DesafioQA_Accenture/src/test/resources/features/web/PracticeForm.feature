Feature: Practice Form Automation
  Como usuário do DemoQA
  Eu quero preencher o formulário de prática
  Para validar a funcionalidade completa do formulário

  @web
  Scenario: Preencher e submeter formulário de prática completo
    Given que estou na página inicial do DemoQA
    When eu navego para a seção Forms e seleciono Practice Form
    And preencho todas as informações pessoais com dados válidos
    And preencho as informações educacionais e profissionais
    And faço upload de um arquivo .png
    And preencho o endereço completo
    And seleciono estado e cidade
    And submeto o formulário
    Then devo ver um popup de confirmação
    And fecho o popup de confirmação