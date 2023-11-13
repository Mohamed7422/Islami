package com.mhm.islami.API;



import com.mhm.islami.model.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("radio_ar.json")
    Call<RadioResponse> getRadioChannel();
}
