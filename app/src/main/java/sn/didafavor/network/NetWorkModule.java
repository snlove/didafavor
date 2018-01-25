package sn.didafavor.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.didafavor.BuildConfig;

/**
 * Created by pc on 2018/1/18.
 */

@Module
public class NetWorkModule {

    public static final long CONNECT_TIME_OUT = 3000;

    @Singleton
    @Provides
    Interceptor provideInterceptor(RequestInterceptor requestInterceptor){
        return  requestInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(loggingInterceptor)
                .readTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient httpClient){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    TmdbWebService provideTmdbService(Retrofit retrofit){
        return  retrofit.create(TmdbWebService.class);
    }
}
