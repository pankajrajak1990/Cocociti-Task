package com.metrohospital.tgs.cococititask.fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metrohospital.tgs.cococititask.R;
import com.metrohospital.tgs.cococititask.activity.Constant;
import com.metrohospital.tgs.cococititask.activity.MainActivity;
import com.metrohospital.tgs.cococititask.adapter.frag_adapter;
import com.metrohospital.tgs.cococititask.datamodal.FeedModel;
import com.metrohospital.tgs.cococititask.retrofit.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    ProgressDialog progress_dialog;
    public String email_id, token;
    public MainFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        email_id = arguments.getString(Constant.EMAIL);
        token = arguments.getString(Constant.TOKEN);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        initview();
        return view;
    }
    private void initview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initProgressDialog();
        getServiceResponseData();
    }
    private void initProgressDialog() {
        if (null != ((MainActivity) getActivity()).getProgressDialog())
            ((MainActivity) getActivity()).getProgressDialog().setVisibility(
                    View.VISIBLE);
    }
    private void getServiceResponseData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<FeedModel> call = api.getFeed(token, email_id);
        call.enqueue(new Callback<FeedModel>() {
            @Override
            public void onResponse(Call<FeedModel> call, Response<FeedModel> response) {
                int status = response.code();
                if (status == 200) {
                    final FeedModel feedModel = response.body();
                    stopProgressDialog();
                    frag_adapter customCountryList = new frag_adapter(getActivity(), feedModel);
                    recyclerView.setAdapter(customCountryList);
                }
            }
            @Override
            public void onFailure(Call<FeedModel> call, Throwable t) {
            }
        });
    }
    private void stopProgressDialog() {
        if (null != ((MainActivity) getActivity()).getProgressDialog())
            ((MainActivity) getActivity()).getProgressDialog().setVisibility(
                    View.GONE);
    }
}
