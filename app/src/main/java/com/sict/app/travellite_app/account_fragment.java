package com.sict.app.travellite_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sict.app.travellite_app.model.AccessToken;
import com.sict.app.travellite_app.model.TokenManager;
import com.sict.app.travellite_app.model.user;
import com.sict.app.travellite_app.rest_api.client;
import com.sict.app.travellite_app.rest_api.restapi;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account_fragment extends Fragment {
    private client client;
    private restapi restapi;
    private TextView txt_login, txt_logout,txt_profile;
    private TokenManager tokenManager;
    private AccessToken a = new AccessToken();
    private user u;
    private TableRow tbr_login, tbr_logout;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static account_fragment newInstance(String param1, String param2) {
        account_fragment fragment = new account_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       client = new client();
       restapi = client.getClient().create(restapi.class);
       View view = inflater.inflate(R.layout.fragment_account_fragment, container, false);
       tbr_login = (TableRow)view.findViewById(R.id.tbrow_login);
       tbr_logout = (TableRow)view.findViewById(R.id.tbr_logout);
       txt_login = (TextView)view.findViewById(R.id.txt_login);
       txt_logout = (TextView)view.findViewById(R.id.txt_logout);
       txt_profile = (TextView)view.findViewById(R.id.txt_profile);
       tokenManager = TokenManager.getInstance(getContext().getSharedPreferences("pref",getContext().MODE_PRIVATE));
       txt_logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             tokenManager.removeToken();
             Intent i = new Intent(getContext(),login.class);
             startActivity(i);
           }
       });
       txt_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getContext(), login.class);
               startActivity(i);
           }
       });
        txt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tokenManager.getToken().getToken() !=null){
                    Intent i = new Intent(getContext(),information_account.class);
                    i.putExtra("id", u.getId());
                    i.putExtra("name",u.getName());
                    i.putExtra("address",u.getAddress());
                    i.putExtra("email",u.getEmail());
                    i.putExtra("phone",u.getPhone());
                    i.putExtra("token",tokenManager.getToken().getToken());
                    startActivity(i);
                }

            }
        });
        return view;
    }


    @Override
    public void onResume() {
        if(tokenManager.getToken().getToken() !=null){
            tbr_login.setVisibility(View.GONE);
            tbr_logout.setVisibility(View.VISIBLE);
            Call<user> calluser = restapi.calluser("Bearer "+tokenManager.getToken().getToken());
            calluser.enqueue(new Callback<com.sict.app.travellite_app.model.user>() {
                @Override
                public void onResponse(Call<com.sict.app.travellite_app.model.user> call, Response<com.sict.app.travellite_app.model.user> response) {
                    u = response.body();
                }

                @Override
                public void onFailure(Call<com.sict.app.travellite_app.model.user> call, Throwable t) {

                }
            });
        }else{
            tbr_login.setVisibility(View.VISIBLE);
            tbr_logout.setVisibility(View.GONE);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
