package com.prakash.infosysassignment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prakash.infosysassignment.InfosysAssignment;
import com.prakash.infosysassignment.R;
import com.prakash.infosysassignment.activity.MainMenu;
import com.prakash.infosysassignment.adapter.AboutCanadaAdapter;
import com.prakash.infosysassignment.model.ModelAboutCanada;
import com.prakash.infosysassignment.utils.InternetStatus;
import com.prakash.infosysassignment.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/* This fragment loads in MainMenu activity */

public class AboutCanada extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = "AboutCanada";

    private Context ctx;
    private View cView;
    private Snackbar snackbar;
    private ConstraintLayout cLayout;
    private SwipeRefreshLayout srLayout;
    private RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    public static AboutCanada newInstance(String param1, String param2) {
        AboutCanada fragment = new AboutCanada();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ctx = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cView = inflater.inflate(R.layout.fragment_about_canada, container, false);
        init();
        return cView;
    }

    private void init() {
        cLayout = cView.findViewById(R.id.constraint_layout);

        recyclerView = cView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        recyclerView.setHasFixedSize(true);

        srLayout = cView.findViewById(R.id.swipe_refresh_layout);
        srLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        srLayout.setOnRefreshListener(onRefreshListener);
        startRefreshing();
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            getJsonData();
        }
    };

    private void getJsonData() {
        try {
            if (!InternetStatus.getInstance().isConnectedToInternet(ctx)) {
                snackbar = Snackbar.make(cLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Try Again", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                                startRefreshing();
                            }
                        });
                snackbar.show();
                return;
            }

            String API_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
            HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
            Request request = new Request.Builder()
                    .url(urlBuilder.build().toString())
                    .build();

            InfosysAssignment.getServerInstance().newCall(request).enqueue(new Callback() {

                Handler handler = new Handler(Looper.getMainLooper());

                @Override
                public void onFailure(Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            stopRefreshing();
                            showSnack(e.getMessage());
                        }
                    });
                    LogUtils.printStackTrace(e);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String data = response.body().string().trim();
                        LogUtils.i(TAG, "getJsonData_OkHTTP_onResponse_Success: " + data);
                        final ModelAboutCanada modelAboutCanada = InfosysAssignment.getGsonInstance().fromJson(data, ModelAboutCanada.class);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((MainMenu) ctx).setCustomTitle(modelAboutCanada.getTitle());
                                if (modelAboutCanada.getRows().size() > 0) {
                                    recyclerView.setAdapter(new AboutCanadaAdapter(ctx, modelAboutCanada.getRows()));
                                } else {
                                    showSnack("No data to display");
                                }
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showSnack(response.message());
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            LogUtils.e(TAG, "getJsonData: " + e.getMessage());
        } finally {
            stopRefreshing();
        }
    }

    private void showSnack(String message) {
        Snackbar.make(cLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void startRefreshing() {
        if (!srLayout.isRefreshing())
            srLayout.setRefreshing(true);
        getJsonData();
    }

    private void stopRefreshing() {
        if (srLayout.isRefreshing())
            srLayout.setRefreshing(false);
    }
}
