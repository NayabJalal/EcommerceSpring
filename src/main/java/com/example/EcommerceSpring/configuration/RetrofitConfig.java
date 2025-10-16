package com.example.EcommerceSpring.configuration;

import com.example.EcommerceSpring.Gateway.api.FakeStoreCategoryApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Value("${fakestore.api.url}")
    private String baseURL;

    // here we can decide how to create the object of fakeStoreCategoryAPI
    @Bean
    public Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();// from documentation
    }


    @Bean// refer this method where we are going to create the object of this particular class...to do this we need a retrofit instance
    public FakeStoreCategoryApi fakeStoreCategoryApi(Retrofit retrofit){// this method is now responsible for providing an object of fakeStoreCategoryApi
        return retrofit.create(FakeStoreCategoryApi.class);// this gives a responsibilities of creating an object of retrofit instance(above)
    }
}
