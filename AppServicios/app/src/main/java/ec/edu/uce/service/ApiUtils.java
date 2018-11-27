package ec.edu.uce.service;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://odmontalvo.000webhostapp.com/";

    public static ApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
