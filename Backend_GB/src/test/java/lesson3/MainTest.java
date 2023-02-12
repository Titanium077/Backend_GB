package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

public class MainTest extends AbstractTest {

    //{{baseUrl}} recipes/complexSearch?query=pasta&maxFat=25&number=2
    @Test
    void MyTestA() {
        given()
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch?query=pasta&maxFat=25&number=2&apiKey={apiKey}", getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestB() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=pasta&maxFat={calories}&number=2&apiKey={apiKey}", 25, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestC() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=soup&maxFat={calories}&number=2&apiKey={apiKey}", 50, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestD() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=sauce&maxFat={calories}&number=2&apiKey={apiKey}", 80, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestE() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=drink&maxFat={calories}&number=2&apiKey={apiKey}", 10, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestF() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?cuisine=italian&maxFat={calories}&number=5&apiKey={apiKey}", 20, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestG() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?cuisine=mediterranean&maxFat={calories}&number=5&apiKey={apiKey}", 39, getApiKey())
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestH() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestI() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("cuisine", "Mediterranean")
                .formParam("confidence", "0.0")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void MyTestJ() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("cuisine", "European")
                .formParam("confidence", "0.0")
                .when()
                .post(getBaseUrl() + "recipes/cuisine=mediterranean")
                .then()
                .statusCode(405);
    }

    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);

    }
}


