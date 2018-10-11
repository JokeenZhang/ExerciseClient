package com.zzq.gankclient.net;

import com.zzq.gankclient.data.FuliDataBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApiService {

    @GET("data/福利/{pageAccount}/{pageIndex}")
    Observable<FuliDataBean> getFuliData(@Path("pageAccount") int account, @Path("pageIndex") int index);

    @GET("data/福利/{pageAccount}/{pageIndex}")
    Call<FuliDataBean> getFuliPicData(@Path("pageAccount") int account, @Path("pageIndex") int index);

}
