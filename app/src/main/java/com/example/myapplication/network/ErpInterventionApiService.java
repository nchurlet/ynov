package com.example.myapplication.network;

import com.example.myapplication.model.ForecastResult;
import com.example.myapplication.model.Weather;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Nicolas Churlet on 21/03/2018.
 */

public interface ErpInterventionApiService {

    // region GETTER ALL
    // region Used

    // endregion Used
    // region Unused
//
//    @GET("/api/Affaire/list")
//     Call<List<Affaire>> affaire();
//    // Pattern de filtre : http://localhost:55174/api/intervention/list
//    // ?Filters[0].Field=Debut
//    // &Filters[0].Values[0]=2018-06-19%2000:00:00.000000
//    // &Filters[0].Op=GT
//    // &Filters[1].Field=Debut
//    // &Filters[1].Values[0]=2018-06-20%2000:00:00.000000
//    // &Filters[1].Op=LT
//    @GET("/api/Intervention/list")
//    Call<List<Intervention>>
//    getInterventionsfiltered(
//            @Query(value = "Filters[0].Field", encoded = true) String lestringDebut,
//            @Query(value = "Filters[0].Values[0]", encoded = true) String dateDebut,
//            @Query(value = "Filters[0].Op", encoded = true) String opGT,
//            @Query(value = "Filters[1].Field", encoded = true) String encoreleStringDebut,
//            @Query(value = "Filters[1].Values[0]", encoded = true) String datefin,
//            @Query(value = "Filters[1].Op", encoded = true) String opLT
//    );
    // endregion Unused
    // endregion GETTER ALL

    // region GETT BY ID
    // region Used
    /**
     * Pour chaque champ de type requete="valeur" ajoutez un @Query et le paramètre. N'oubliez pas de préciser le type.
     * @param cityId à récupérer à la main sur la doc de l'API pour ce projet. Mais il est possible de récupérer les
     *               données dans le Json fourni par l'API
     * @param apiKey
     * @param count C'est ce nombre qui va fournir une liste. Il détermine le nombre de prévisions météo rendues par la
     *              requête
     * @return Une prévision météo qui contient une liste de prévisions par créneau horaire
     */
    @GET("forecast?units=metric&lang=fr")
    Call<ForecastResult> getForecastById(
            @Query("id") Long cityId,
            @Query("APPID") String apiKey,
            @Query("cnt") int count
    );

    @GET("/api/weather/{city}")
    Call<ForecastResult> getWeatherByName(@Path("city") String cityName);

    // endregion Used
    // region Unused
//    // endregion Unused
    // endregion GETT BY ID

    // region DELETE
    // region Used
    // endregion Used
    // region Unused
//    @DELETE("/api/InterventionRapport/{id}/photo/{id2}")
//    Call<DataResponse> removeRapportPhoto(@Path("id") Long id, @Path("id2") Long id2);
//    @DELETE("/api/PreDevis/{id}/photo/{id2}")
//    Call<DataResponse> removePredevisPhoto(@Path("id") Long id, @Path("id2") Long id2);
    // endregion Unused
    // endregion DELETE

    // region PUT
    // region Used
    // endregion Used
    // region Unused
//    @PUT("/api/InterventionRapport/{id}")
//    Call<DataResponse> updateInterventionRapport(@Path("id") String id, @Body InterventionRapport interventionRapport);
//    @PUT("/api/PreDevis/{id}")
//    Call<DataResponse> updatePreDevis(@Path("id") String id, @Body PreDevis preDevis);
//    @PUT("/api/Clients/{id}")
//    Call<Clients> updateClients(@Path("id") String id, @Body Clients clients);
//    @PUT("/api/Contacts/{id}")
//    Call<Contacts> updateContacts(@Path("id") String id, @Body Contacts contacts);
//    @PUT("/api/Devis/{id}")
//    Call<Devis> updateDevis(@Path("id") String id, @Body Devis devis);
//    @PUT("/api/Intervention/{id}")
//    Call<Intervention> updateIntervention(@Path("id") String id, @Body Intervention interventions);
//    @PUT("/api/Regie/{id}")
//    Call<Regie> updateRegie(@Path("id") String id, @Body Regie regie);
//    @PUT("/api/RendezVous/{id}")
//    Call<RendezVous> updateRendezVous(@Path("id") String id, @Body RendezVous rendezVous);
//    @PUT("/api/Utilisateur/{id}")
//    Call<Utilisateur> updateUtilisateur(@Path("id") String id, @Body Utilisateur utilisateur);
    // endregion Unused
    // endregion PUT

    // region POST
    // region Used
// endregion Used
    // region Unused
//    @POST("/api/Auth/Login")
//    Call<LoginResponse> login(@Body Login login);
//    @POST("/api/Auth/Logout")
//    Call<DataResponse> logout();
//    @POST("/api/InterventionRapport/{id}/photo")
//    Call<DataResponse> sendRapportPhoto(@Path("id") Long id, @Body Photo photo);
//    @POST("/api/InterventionRapport")
//    Call<InterventionRapport> createInterventionRapport(@Body InterventionRapport interventionRapport);
//    @POST("/api/Reassort")
//    Call<DataResponse> createReassort(@Body Reassort reassort);
//    @POST("/api/PreDevis")
//    Call<PreDevis> createPreDevis(@Body PreDevis preDevis);
//    @POST("/api/PreDevis/{id}/photo")
//    Call<DataResponse> sendPredevisPhoto(@Path("id") Long id, @Body Photo photo);
    // endregion Unused
    // endregion POST

    // region PATCH
//    @PATCH("/api/Reassort/{id}")
//    Call<List<Reassort>> getReassortById(@Path("id") Integer id, Reassort reassort);
    // endregion PATCH
}
