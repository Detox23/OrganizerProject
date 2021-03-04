package com.example.src.configurations;

public final class SecurityConstants {


    public static final String AUTH_LOGIN_URL = "/api/auth/signin";
    public static final String JWT_SECRET = "s78sVKoz1ot5OdvLFAgx0MK8IB5YMvCgQzd8oy4DcIIf4OPYVqeDDYAMsOkab6xnhf1Itjk9ARoNsteWzij8vZod1SFviJWBHFR8e80AeTFtZkuvqQUZDJkqQFJDxQ2ZpttGBOo5AcvVITzNc8wRGD4POJMR6oOUsSmPGA9QegDWu94UoZjdDpcXgzJlfH49tqKuAVbxmsnFDK8Em47xx1HhelR7W1xx5eQ8S9FYYNyJqVQWpiypN00KIrjSghvkGplogBhJjtv7qWjTwqHsxC722V28fNBd6wQ673wfPx0FsmW2jC65MrcOUFjT8ixwwbnbWxNcsgYFxgDt3VPSwLY4Mj1eAE12mOEq9RrJd4Lz2RlfDZYm0G5SDiROgNM8mhg0zmAY7BV6qQoVL0vz9l8DCNJKNKesaJOdua1353G86AY7PokywXIyplBiVe6jX41ggEKVbRfacnphPk5U9eEv6xB4iBW6MznX6BizVMo9RwgtzRhdTe56cqdkedFe";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstants(){
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
