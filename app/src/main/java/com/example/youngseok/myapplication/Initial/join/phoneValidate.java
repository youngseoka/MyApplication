package com.example.youngseok.myapplication.Initial.join;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class phoneValidate extends StringRequest {

    final static private String URL = "http://192.168.0.208/Validate/phoneValidate.php";
    private Map<String,String> parameters;

    public phoneValidate(String phone, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("phone",phone);
    }

    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return parameters;
    }
}