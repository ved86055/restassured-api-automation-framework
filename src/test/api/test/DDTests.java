

package api.test;

import api.payload.User;
import api.utilities.DataProviders;
import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String UserId, String username, String firstName,
                             String lastName, String email, String password, String phone) {

        User payload = new User();
        payload.setId(Integer.parseInt(UserId));
        payload.setUsername(username);
        payload.setFirstName(firstName);
        payload.setLastName(lastName);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(phone);

        Response response = UserEndPoints.createUser(payload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 2,dataProvider= "UserNames",dataProviderClass = DataProviders.class )
    public void testGetUserbyUserName(String username)
    {
        Response response=UserEndPoints.readUser(username);


        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username) {

        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}