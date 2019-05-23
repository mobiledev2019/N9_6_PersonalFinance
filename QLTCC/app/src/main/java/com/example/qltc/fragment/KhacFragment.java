package com.example.qltc.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qltc.R;
import com.example.qltc.activity.YoutubeActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class KhacFragment extends Fragment {
    private LinearLayout nganHang;
    private LinearLayout item1;
    private LinearLayout dangNhap;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private int RC_SIGN_IN =123;
    private CircleImageView userimage;
    private TextView username;
    private TextView name;
    private LinearLayout dangXuat;
    private LoginButton loginButton;
    private CallbackManager  callbackManager;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();

            username.setText(acct.getEmail());
            name.setText(acct.getDisplayName());
            Picasso.with(getContext()).load(acct.getPhotoUrl()).into(userimage);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_khac, container, false);
        item1 = view.findViewById(R.id.item1);
        userimage = view.findViewById(R.id.userimage);
        username = view.findViewById(R.id.username);
        name = view.findViewById(R.id.name);
        dangXuat = view.findViewById(R.id.dangXuat);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), YoutubeActivity.class);
                startActivity(intent);
            }
        });
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                userimage.setImageResource(R.drawable.user_50px);
                username.setText("NoName@gmail.com");
                name.setText("No Name");
            }
        });
        dangNhap = view.findViewById(R.id.dangNhap);
//        dangNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DangNhapFragment hangMucChiFragment = new DangNhapFragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, hangMucChiFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(),gso);
        signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
            }
        });

        loginButton = view.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email","public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Thành Công");
            }

            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException error) {
                Log.d("abc", error.getMessage());
                System.out.println("Message From Facebook"+error.getMessage());
            }
        });

        nganHang = view.findViewById(R.id.nganHang);
        nganHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NganHangFragment nganHangFragment = new NganHangFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,nganHangFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;

    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null){
                System.out.println("11111111abc");
                name.setText("");
                userimage.setImageResource(0);
                username.setText("");
            }else{
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(AccessToken newAccessToken){
        GraphRequest request =GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    System.out.println("OBJECTNOW"+object.toString());
                    String first_name =object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email =object.getString("email");
                    String id = object.getString("id");

                    String image_url = "https://graph.facebook.com/"+id+"/picture?type=normal";

                    name.setText(first_name+" "+last_name);
                    username.setText(email);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(getActivity()).load(image_url).into(userimage);


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        });
        Bundle paramaters = new Bundle();
        paramaters.putString("fields","first_name,last_name,email,id");
        request.setParameters(paramaters);
        request.executeAsync();

    }

}
