package com.example.youngseok.myapplication.invite.phone_Validate;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class phone_validate extends StringRequest {

    final static private String URL = "http://192.168.43.34/Invite/invite_btn.php";
    private Map<String,String> parameters;

    public phone_validate(String phone, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("phone",phone);
    }

    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return parameters;
    }
}
