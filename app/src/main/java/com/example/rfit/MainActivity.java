package com.example.rfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button sbmt;
    EditText name,Job;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sbmt = findViewById(R.id.sbmt);
        name = findViewById(R.id.name);
        Job = findViewById(R.id.job);
        loadingPB = findViewById(R.id.idLoadingPB);

        sbmt.setOnClickListener(new View.OnClickListener() {
            @Overrid
            public void onClick(View view) {


                postd(name.getText().toString(), Job.getText().toString());
            }
            private void postd(String name, String Job) {
                loadingPB.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://reqres.in/api/")
                        //                        // as we are sending data in json format so
                        // we have to add Gson converter factory
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api class.
                Apin retrofitAPI = retrofit.create(Apin.class);

                Model model = new Model(name,Job);
                Call<Model> call = retrofitAPI.createPost(model);
                call.enqueue(new Callback<Model>() {

                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        loadingPB.setVisibility(View.GONE);

                        Toast.makeText(MainActivity.this,"suucess",Toast.LENGTH_SHORT).show();
                        Log.d("suc:", response.code()+"");
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.d("eroor:","eoor");
                        Toast.makeText(MainActivity.this,"sorry", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}