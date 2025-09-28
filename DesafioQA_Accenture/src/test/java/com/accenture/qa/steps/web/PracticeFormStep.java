package com.accenture.qa.steps.web;

import com.accenture.qa.frontend.pages.*;
import com.accenture.qa.frontend.pages.PracticeFormPage;
import com.accenture.qa.frontend.utils.TestDataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import static com.accenture.qa.frontend.utils.DriverFactory.driver;

public class PracticeFormStep {
    private QaHomePage homePage;
    private PracticeFormPage practiceFormPage;
    private ModalDialogPage modalDialog;

    @Given("que estou na página inicial do DemoQA")
    public void navegarParaPaginaInicial() {
        driver.get("https://www.google.com");
        homePage = new QaHomePage(driver);
        homePage.navigateToHomePage();
    }

    @When("eu navego para a seção Forms e seleciono Practice Form")
    public void navegarParaPracticeForm() {
        homePage.clickForms();
        driver.get("https://demoqa.com/automation-practice-form");
        practiceFormPage = new PracticeFormPage(driver);
    }

    @When("preencho todas as informações pessoais com dados válidos")
    public void preencherInformacoesPessoais() {
        practiceFormPage.fillPersonalInfo(
                TestDataGenerator.generateFirstName(),
                TestDataGenerator.generateLastName(),
                TestDataGenerator.generateEmail(),
                TestDataGenerator.generateMobile()
        );
        practiceFormPage.fillDateOfBirth();
    }

    @When("preencho as informações educacionais e profissionais")
    public void preencherInformacoesEducacionais() {
        practiceFormPage.fillSubjects("Maths");
        practiceFormPage.selectHobbies();
    }

    @When("faço upload de um arquivo .png")
    public void fazerUploadArquivo() {
        practiceFormPage.uploadFile();
    }

    @When("preencho o endereço completo")
    public void preencherEndereco() {
        practiceFormPage.fillAddress(TestDataGenerator.generateAddress());
    }

    @When("seleciono estado e cidade")
    public void selecionarEstadoCidade() {
        practiceFormPage.selectStateAndCity("NCR", "Delhi");
    }

    @When("submeto o formulário")
    public void submeterFormulario() {
        practiceFormPage.submitForm();
    }

    @Then("devo ver um popup de confirmação")
    public void verificarPopupConfirmacao() {
        modalDialog = new ModalDialogPage(driver);
        Assert.assertTrue("Popup de confirmação não apareceu", modalDialog.modalDisplayed());
        Assert.assertEquals("Thanks for submitting the form", modalDialog.getModalTitle());
    }

    @Then("fecho o popup de confirmação")
    public void fecharPopup() {
        modalDialog.closeModal();
        driver.quit();
    }
}