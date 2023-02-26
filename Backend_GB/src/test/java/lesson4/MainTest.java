package lesson4;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class MainTest extends AbstractTest {
    //{{baseUrl}} recipes/complexSearch?query=pasta&maxFat=25&number=2
    @Test
    void MyTestA() {
        given()
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch?query=pasta&maxFat=25&number=2&apiKey={apiKey}", getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestB() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=pasta&maxFat={calories}&number=2&apiKey={apiKey}", 25, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestC() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=soup&maxFat={calories}&number=2&apiKey={apiKey}", 50, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestD() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=sauce&maxFat={calories}&number=2&apiKey={apiKey}", 80, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestE() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=drink&maxFat={calories}&number=2&apiKey={apiKey}", 10, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestF() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?cuisine=italian&maxFat={calories}&number=5&apiKey={apiKey}", 20, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestG() {
        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?cuisine=mediterranean&maxFat={calories}&number=5&apiKey={apiKey}", 39, getApiKey())
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestH() {
        given().spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestI() {
        given().spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("cuisine", "Mediterranean")
                .formParam("confidence", "0.0")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }
    @Test
    void MyTestJ() {
        given().spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("cuisine", "American")
                .formParam("confidence", "0.0")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }


    @Test
    void addMealTest() {
        String id = given().spec(requestSpecification)
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
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
                .spec(responseSpecification)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .spec(responseSpecification);

    }

}
