package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.bytebuddy.asm.Advice;

import static io.restassured.RestAssured.*;

public class UserEndPoints
{

    public static Response createUser(User payload) {
        Response response = given()
                .contentType("application/json")
                .header("accept", "application/json")
                .body(payload)


                .when()
                .post(routes.post_url);
        return response;
    }

    public static Response readUser(String username)
    {
        Response response=given()
                .pathParam("username",username)   //path para bcz we geting {username} here acc to url
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)


                .when()
                .get(routes.get_url);
        return response;
    }

    public static Response updateUser(String username , User payload)
    { //here 2 para req if u see at url "username also there which want to update along with payload data

        Response response=given()
                .pathParam("username",username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .put(routes.update_url);

        return response;
    }

    public static Response deleteUser(String username)
    {
        Response response=given()
                .pathParam("username",username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)


                .when()
                .delete(routes.delete_url);

        return response;
    }
}
