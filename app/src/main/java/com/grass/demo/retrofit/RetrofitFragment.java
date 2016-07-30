package com.grass.demo.retrofit;

import java.util.List;

import com.example.anno.BindTestCase;
import com.grass.R;
import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@BindTestCase(type = 1, name = "RetrofitFragment", des = "Hello retrofit")
public class RetrofitFragment extends Fragment {
    public RetrofitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        view.findViewById(R.id.get_nc_data).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //                getData();
                new TrendingPresenter().loadData();
            }
        });
        return view;
    }

    public void getData() {
        NcuAPI ncuAPI = new NcuAPI();
        Call<List<News>> newscall = ncuAPI.getNews(3);
        newscall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> body = response.body();
                Logger.i("grass", "" + body.size());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Logger.i("grass", "onFailure");
            }
        });
    }
}
