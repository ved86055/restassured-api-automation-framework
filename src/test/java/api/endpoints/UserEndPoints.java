package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.*;

public class UserEndPoints
{
    //Method created for getting URLs from Properties file

    public static ResourceBundle getURL()
    {
       ResourceBundle routes=ResourceBundle.getBundle("routes");
       return routes;
    }


    //Method created for creating new user

    public static Response createUser(User payload)
    {
        String post_url=getURL().getString("post_url");
        Response response = given()
                .contentType("application/json")
                .header("accept", "application/json")
                .body(payload)


                .when()
                .post(post_url);
        return response;
    }

    public static Response readUser(String username)
    {
       String  get_url=getURL().getString("get_url");
        Response response=given()
                .pathParam("username",username)   //path para bcz we geting {username} here acc to url
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)


                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(String username , User payload)
    { //here 2 para req if u see at url "username also there which want to update along with payload data

       String  update_url=getURL().getString("update_url");

        Response response=given()
                .pathParam("username",username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .put(update_url);

        return response;
    }

    public static Response deleteUser(String username)
    {
       String  delete_url=getURL().getString("delete_url");
        Response response=given()
                .pathParam("username",username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)


                .when()
                .delete(delete_url);

        return response;
    }
}
