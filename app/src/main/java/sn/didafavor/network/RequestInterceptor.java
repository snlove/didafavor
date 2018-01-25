package sn.didafavor.network;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sn.didafavor.BuildConfig;

/**
 * Created by pc on 2018/1/18.
 */

@Singleton
public class RequestInterceptor implements Interceptor {

    @Inject
    public RequestInterceptor(){

    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orginal = chain.request();
        HttpUrl orginalUrl = orginal.url();
        HttpUrl url = orginalUrl.newBuilder().addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build();
        Request request = chain.request().newBuilder().url(url).build();
        return  chain.proceed(request);

    }
}
