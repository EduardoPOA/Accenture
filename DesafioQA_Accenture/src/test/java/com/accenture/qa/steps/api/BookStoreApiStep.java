package com.accenture.qa.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookStoreApiStep {

    private String baseUrl = "https://demoqa.com";
    private String userId;
    private String token;
    private String username;
    private Response response;
    private List<String> availableIsbns;

    @Given("que eu tenho um usuário não cadastrado no sistema")
    public void que_eu_tenho_um_usuario_nao_cadastrado_no_sistema() {
        username = "testuser_" + System.currentTimeMillis();
        System.out.println("Usuário preparado: " + username);
    }

    @When("eu crio um novo usuário na API BookStore")
    public void eu_crio_um_novo_usuario_na_api_book_store() {
        String requestBody = String.format(
                "{\"userName\": \"%s\", \"password\": \"%s\"}",
                username, "Password123!"
        );

        response = given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl + "/Account/v1/User");

        Assert.assertEquals("Falha ao criar usuário", 201, response.getStatusCode());
        userId = response.jsonPath().getString("userID");
        Assert.assertNotNull("UserID não retornado", userId);

        System.out.println("✅ Usuário criado com ID: " + userId);
    }

    @When("gero um token de acesso para esse usuário")
    public void gero_um_token_de_acesso_para_esse_usuario() {
        String requestBody = String.format(
                "{\"userName\": \"%s\", \"password\": \"%s\"}",
                username, "Password123!"
        );

        response = given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl + "/Account/v1/GenerateToken");

        Assert.assertEquals("Falha ao gerar token", 200, response.getStatusCode());
        token = response.jsonPath().getString("token");
        Assert.assertNotNull("Token não gerado", token);
        Assert.assertFalse("Token vazio", token.isEmpty());

        System.out.println("✅ Token gerado com sucesso");
    }

    @When("verifico que o usuário está autorizado no sistema")
    public void verifico_que_o_usuario_esta_autorizado_no_sistema() {
        String requestBody = String.format(
                "{\"userName\": \"%s\", \"password\": \"%s\"}",
                username, "Password123!"
        );

        response = given()
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl + "/Account/v1/Authorized");

        Assert.assertEquals("Falha na autorização", 200, response.getStatusCode());
        boolean isAuthorized = Boolean.parseBoolean(response.getBody().asString());
        Assert.assertTrue("Usuário não está autorizado", isAuthorized);

        System.out.println("✅ Usuário autorizado no sistema");
    }

    @When("consulto a lista de livros disponíveis")
    public void consulto_a_lista_de_livros_disponiveis() {
        response = given()
                .get(baseUrl + "/BookStore/v1/Books");

        Assert.assertEquals("Falha ao buscar livros", 200, response.getStatusCode());

        availableIsbns = response.jsonPath().getList("books.isbn");
        Assert.assertNotNull("Lista de ISBNs nula", availableIsbns);
        Assert.assertTrue("Não há livros suficientes disponíveis", availableIsbns.size() >= 2);

        System.out.println("✅ Livros disponíveis consultados: " + availableIsbns.size() + " livros");
    }

    @When("seleciono e reservo dois livros da lista")
    public void seleciono_e_reservo_dois_livros_da_lista() {
        Assert.assertTrue("Livros insuficientes para reserva", availableIsbns.size() >= 2);

        String firstIsbn = availableIsbns.get(0);
        String secondIsbn = availableIsbns.get(1);

        String requestBody = String.format(
                "{\"userId\": \"%s\", \"collectionOfIsbns\": [{\"isbn\": \"%s\"}, {\"isbn\": \"%s\"}]}",
                userId, firstIsbn, secondIsbn
        );

        response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .post(baseUrl + "/BookStore/v1/Books");

        Assert.assertEquals("Falha ao reservar livros", 201, response.getStatusCode());

        System.out.println("✅ Livros reservados: " + firstIsbn + ", " + secondIsbn);
    }

    @Then("eu devo visualizar os livros reservados no perfil do usuário")
    public void eu_devo_visualizar_os_livros_reservados_no_perfil_do_usuario() {
        response = given()
                .header("Authorization", "Bearer " + token)
                .get(baseUrl + "/Account/v1/User/" + userId);

        Assert.assertEquals("Falha ao buscar perfil do usuário", 200, response.getStatusCode());

        List<Object> userBooks = response.jsonPath().getList("books");
        Assert.assertNotNull("Lista de livros do usuário nula", userBooks);
        Assert.assertEquals("Quantidade de livros reservados incorreta", 2, userBooks.size());

        String usernameResponse = response.jsonPath().getString("username");
        Assert.assertEquals("Username não corresponde", username, usernameResponse);

        System.out.println("✅ Perfil do usuário verificado com sucesso");
        System.out.println("   Usuário: " + username);
        System.out.println("   Livros reservados: " + userBooks.size());
        System.out.println("   UserID: " + userId);
    }
}