package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTests
{
    Faker fr;
    User payload;

    @BeforeClass
    public void setupData()
    {

        fr = new Faker();
        payload = new User();

        payload.setId(fr.idNumber().hashCode());
        payload.setEmail(fr.internet().safeEmailAddress());
        payload.setFirstName(fr.name().firstName());
        payload.setPassword(fr.internet().password());
        payload.setPhone(fr.phoneNumber().cellPhone());
        payload.setLastName(fr.name().lastName());
        payload.setUsername(fr.name().username());
    }

    @Test(priority = 1)
    public void testPostRequest()
    {
       Response response=UserEndPoints.createUser(payload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
       response.then().header("Content-Type",equalTo("application/json"));


    }


    @Test(priority = 2,dependsOnMethods = "testPostRequest")
    public void testGetUserByName()
    {
        //username as para we have to pass from payload , so we have to fetch only username
      Response response =UserEndPoints.readUser(this.payload.getUsername());
        System.out.println(payload.getUsername());
      response.then().log().all();

      response.then().body("username",equalTo(payload.getUsername()));

    }

        @Test(priority = 3)
    void testUpdateUserByName()
    {
        payload.setEmail(fr.internet().safeEmailAddress());
        payload.setFirstName(fr.name().firstName());
        payload.setLastName(fr.name().lastName());

        Response response= UserEndPoints.updateUser(this.payload.getUsername(),payload);
        //Assert.assertEquals(response.statusCode(),200);
        response.then().log().all();

        //checking data after update

        Response responseafterupdate =UserEndPoints.readUser(this.payload.getUsername());
        Assert.assertEquals(responseafterupdate.statusCode(),200);
    }

    @Test(priority = 4)
    void testDeleteUserByName()
    {

        Response response= UserEndPoints.deleteUser(this.payload.getUsername());
        response.then().log().all();
        response.then().statusCode(200);

     //response.then().body("email",notNullValue());
    //    response.then().body("id",greaterThan(0));

    }

    @Test(priority = 5)
    void verifyDeletion()
    {
        Response responseafterdeletion=UserEndPoints.readUser(payload.getUsername());

        responseafterdeletion.then().statusCode(404);


    }




}
