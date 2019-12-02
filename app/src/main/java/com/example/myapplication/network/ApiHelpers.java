package com.example.myapplication.network;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.myapplication.model.Client;
import com.example.myapplication.model.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Nicolas Churlet on 21/03/2018.
 */

public class ApiHelpers {

    public static final String ENDPOINT = "api.openweathermap.org/data/2.5/";

    private ErpInterventionApiService apiservice;

    final private Context _context;

    SharedPreferences preferences;
     public ApiHelpers(Context context) {

        this._context = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        // region OkHttpClient
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        final Request request = chain.request();

                        preferences = _context.getSharedPreferences("login", MODE_PRIVATE);
                        String token = preferences.getString("token", "");

                        final Request newRequest;
                        // cas de l'auhtnetification
                        if (request.url().encodedPathSegments().get(0).equals("api") &&
                            request.url().encodedPathSegments().get(1).equals("Auth") &&
                            request.url().encodedPathSegments().get(2).equals("Login")) {
                            newRequest = request.newBuilder()
                                    .build();
                        } else if (token == null) {
                            return null;
                        } else {
                            newRequest = request.newBuilder()
                                    //ajoute "baerer: token" en header de chaque requête
                                    .addHeader("Authorization", "Bearer " + token)
                                    .build();
                        }
                        System.out.println("request = " + request);

                        return chain.proceed(newRequest);
                    }
                })
                .build();
        // endregion OkHttpClient

        // region API service
        apiservice = new Retrofit
                .Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
                .create(ErpInterventionApiService.class);
        // endregion API service
    }

    // region Async methods
    // region Getters
    // region Lists
    public void getWeatherAsync(ApiRequestCallback<List<Client>> callback) {
        (new ApiRequest<List<Client>>()).requestAsync(apiservice.getWeathesdcsdcrById(""), callback);
    }
    public void getInterventionsSelectionAsync(
            Calendar debut,
            Calendar fin,
            ApiRequestCallback<List<Intervention>> callback) {
        (new ApiRequest<List<Intervention>>())
                .requestAsync(
                        apiservice
                                .getInterventionsfiltered(
                                        "ArriveeDebut",
                                        getWsFormattedDate(debut),
                                        "GT",
                                        "ArriveeDebut",
                                        getWsFormattedDate(fin),
                                        "LT"
                                )
                        , callback
                );
    }
    // endregion Lists
    // region By id
    public void getWeatherByNameAsync(String cityName, ApiRequestCallback<Weather> callback) {
        (new ApiRequest<Weather>()).requestAsync(apiservice.getWeatherByName(cityName), callback);
    }
    // endregion By id
    // endregion Getters
    // region Post create
    public void loginAsync(String login, String password, String pushToken, ApiRequestCallback<LoginResponse> callback) {
        Login l = new Login();
        l.username = login;
        l.password = password;
        l.pushToken = pushToken;

        (new ApiRequest<LoginResponse>()).requestAsync(apiservice.login(l), callback);
    }
    public void logoutAsync(ApiRequestCallback<DataResponse> callback) {
        (new ApiRequest<DataResponse>()).requestAsync(apiservice.logout(), callback);
    // endregion Post create
    // region Update Put
    public void updateInterventionAsync(InterventionRapport interventionRapport, ApiRequestCallback<DataResponse> apiRequestCallback) {
        (new ApiRequest<DataResponse>()).requestAsync(apiservice.updateInterventionRapport(interventionRapport.getIdToString(), interventionRapport), apiRequestCallback);
    }
    // endregion Update Put
    // region Delete
    public void removeRapportPhotoAsync(Photo photo, ApiRequestCallback<DataResponse> apiRequestCallback) {
        (new ApiRequest<DataResponse>()).requestAsync(apiservice.removeRapportPhoto(photo.getInterventionRapportId(), Long.valueOf(photo.getId())), apiRequestCallback);
    }
    public void removePredevisPhotoAsync(PreDevis preDevis, Photo photo, ApiRequestCallback<DataResponse> apiRequestCallback) {
        (new ApiRequest<DataResponse>()).requestAsync(apiservice.removePredevisPhoto(preDevis.getId(), Long.valueOf(photo.getId())), apiRequestCallback);
    }
    // endregion Delete
    // endregion Async methods
    // region Specifics methods
    public void killToken() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", null);
        editor.commit();
    }
    // endregion Specifics methods
}

