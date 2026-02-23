package api.endpoints;

/*
swagger URI=https://petstore.swagger.io

create user(post)=   https://petstore.swagger.io/v2/user
get user=            https://petstore.swagger.io/v2/user/{username}
update=               https://petstore.swagger.io/v2/user/{username}
delete=               https://petstore.swagger.io/v2/user/{username}
*/

public class routes
{
    public static String base_url="https://petstore.swagger.io/v2";


    //User module
    //created user we wil get using usernmae we passing in another req so its path para

    public static String post_url=base_url+ "/user";
    public static String get_url=base_url+ "/user/{username}";
    public static String update_url=base_url+"user/{username}";
    public static String delete_url=base_url+"/user/{username}";
}
