package com.example.a.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
<<<<<<< HEAD
import com.example.a.model.MateInfo;
import com.example.a.model.MateReq;
=======
import com.example.a.model.DogDetails;
>>>>>>> b83921942a3618c01a95b13923708251cde21266
import com.example.a.model.OwnerDetails;
import com.example.a.model.WalkInfo;
import com.example.a.model.WalkReq;
import com.example.a.woofui.AvailableMate;
import com.example.a.woofui.AvailableWalk;
import com.example.a.woofui.MateActivity;
import com.example.a.woofui.PostMate;
import com.example.a.woofui.PostWalk;
import com.example.a.woofui.PostWalkInfo;
import com.example.a.woofui.ProfileEditActivity;
import com.example.a.woofui.R;
import com.example.a.woofui.RequestedMate;
import com.example.a.woofui.RequestedWalk;
import com.example.a.woofui.RequestsMate;
import com.example.a.woofui.RequestsWalk;
import com.example.a.woofui.SignUpDetails;
import com.example.a.woofui.WalkActivity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.spec.ECField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Callable;

/**
 * Created by A on 3/6/2018.
 */

public class ApiVolley  {

    static RequestQueue queue;
    static ImageLoader mImageLoader;

    public ApiVolley(Context context){

        if(queue==null)
            queue = Volley.newRequestQueue(context);

    }
    public ApiVolley(){

    }

    public  void postSignUp( final SignUpDetails activity,final OwnerDetails ownerDetails)
    {
        String url =activity.getResources().getString(R.string.signup_api);
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        JSONObject obj=null;
            try {
            obj =new JSONObject( objectMapper.writeValueAsString(ownerDetails));
        }catch (Exception e)
        {
            Log.e("JSONPARSE", e.getMessage());
        }

        // Request a string response from the provided URL.
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Calling function after getting response.
                        activity.showToast(response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){
            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
    // Add the request to the RequestQueue.
            queue.add(jsonRequest);

}

    public  void getOwnerDetails(final SignUpDetails activity,final OwnerDetails ownerDetails)
    {

        String url =activity.getResources().getString(R.string.signup_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/15";
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();

        // Request a string response from the provided URL.
        final JsonObjectRequest  stringRequest = new JsonObjectRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        activity.showToast(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                try {


                    params.put("json", objectMapper.writeValueAsString(ownerDetails));
                }
                catch(Exception e)
                {
                    Log.e("JSON ERROR",e.getMessage());
                }

                return params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public  void putOwnerDetails(final SignUpDetails activity,final OwnerDetails ownerDetails)
    {

        String url =activity.getResources().getString(R.string.signup_api);
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        JSONObject obj=null;
        try {
             obj =new JSONObject( objectMapper.writeValueAsString(ownerDetails));
        }catch (Exception e)
        {
                Log.e("JSONPARSE", e.getMessage());
        }

        // Request a string response from the provided URL.
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        activity.showToast(response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){
            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                 Response res= super.parseNetworkResponse(response);
                 if(response.statusCode>=200 || response.statusCode<=204)
                 {
                     try {
                     return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                     }
                     catch (Exception e)
                     {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                     }
                 }
                 else
                     return res;
            }


        };
// Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }


    public  void getAllOwners(final SignUpDetails activity,final OwnerDetails ownerDetails)
    {

        String url =activity.getResources().getString(R.string.signup_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/15";
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();

        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        activity.showToast(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                try {


                    params.put("json", objectMapper.writeValueAsString(ownerDetails));
                }
                catch(Exception e)
                {
                    Log.e("JSON ERROR",e.getMessage());
                }

                return params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


<<<<<<< HEAD
    //----------------Dog Walk Api class start----------------------
    public  void postDogWalk(final WalkActivity activity, final WalkInfo walkInfo)
=======

    public  void getDogDetailsDD(final PostWalkInfo activity,int id)
    {

        String url =activity.getResources().getString(R.string.dogDetails_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/1";
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();

        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<DogDetails> list;
                            list = objectMapper.readValue(response.toString(), new TypeReference<List<DogDetails>>() {
                            });
                            activity.populateDD(list);
                        }catch (Exception e)
                        {
                            Log.e("ParseError",e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }

            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    //Dog Walk Api class start
    public  void postDogWalk(final PostWalk activity, final WalkInfo walkInfo,final int method)
>>>>>>> b83921942a3618c01a95b13923708251cde21266
    {
        String url =activity.getResources().getString(R.string.postWalk_api);
        if(method== Request.Method.PUT || method== Request.Method.DELETE )
            url+="/"+walkInfo.getWalkInfoId();
        final List<String> stList=new ArrayList<>();

        final ObjectMapper objectMapper=new ObjectMapper();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(sdf);
//        Gson gson = new Gson();
//        JsonObject obj1 = new JsonParser().parse(gson.toJson(walkInfo)).getAsJsonObject();
        JSONObject obj=null;
        try {
            obj =new JSONObject( objectMapper.writeValueAsString(walkInfo));
        }catch (Exception e)
        {
            Log.e("JSONPARSE", e.getMessage());
        }

        if(method==Request.Method.DELETE)
            obj=null;
        // Request a string response from the provided URL.
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(method, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Calling function after getting response.
                        activity.walkPosted(true,method);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                Log.e("J",error.toString());
                activity.walkPosted(false,method);

            }


        }){
            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }


    public  void acceptAWalk(final RequestsWalk activity, final WalkInfo walkInfo)
    {
        String url =activity.getResources().getString(R.string.postWalk_api);
        url+="/"+walkInfo.getWalkInfoId();
        final List<String> stList=new ArrayList<>();

        final ObjectMapper objectMapper=new ObjectMapper();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(sdf);
        JSONObject obj=null;
        try {
            obj =new JSONObject( objectMapper.writeValueAsString(walkInfo));
        }catch (Exception e)
        {
            Log.e("JSONPARSE", e.getMessage());
        }

        // Request a string response from the provided URL.
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Calling function after getting response.
                        activity.walkAccepted(true);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                Log.e("J",error.toString());
                activity.walkAccepted(true);

            }


        }){
            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }




    public  void getAvailableWalkList(final AvailableWalk activity, final int ownerId,final int zip)
    {

        String url =activity.getResources().getString(R.string.availableWalkList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+"2"+ "/"+"1";
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<WalkInfo> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkInfo>>(){});
                            activity.populateData(list);
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


<<<<<<< HEAD
    public  void getPostWalkList(final PostWalk activity, final int id) {
=======
    public  void requestAWalk(final AvailableWalk activity, final WalkReq walkReq)
    {

        String url =activity.getResources().getString(R.string.requestAWalk_api);
        //url+="/"+ownerDetails.getOwnerId();
        //url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(sdf);
        JSONObject obj=null;
        try {

            obj = new JSONObject(objectMapper.writeValueAsString(walkReq));
        }
        catch(Exception e){
            Log.e("JOSNPARSE",e.getMessage());
    }
        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<WalkInfo> list=null;
                        try {


                            //list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkInfo>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {

                            activity.walkRequested(true);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());
                activity.walkRequested(false);

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public  void cancelAWalk(final Fragment activity, final int id)
    {


        String url =activity.getResources().getString(R.string.requestAWalk_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        objectMapper.setDateFormat(sdf);
        JSONObject obj=null;
        try {

          //  obj = new JSONObject(objectMapper.writeValueAsString(walkReq));
        }
        catch(Exception e){
            Log.e("JOSNPARSE",e.getMessage());
        }
        final JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url,null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<WalkInfo> list=null;
                        try {


                            //list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkInfo>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {

                            if(activity.getTag().toString().equals("requestsWalk"))
                                ((RequestsWalk)activity).walkCanceled(true);
                            else
                                ((RequestedWalk)activity).walkCanceled(true);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());
                if(activity.getTag().toString().equals("requestsWalk"))
                    ((RequestsWalk)activity).walkCanceled(false);
                else
                    ((RequestedWalk)activity).walkCanceled(false);

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public  void getPostWalkList(final PostWalk activity, final int id)
    {
>>>>>>> b83921942a3618c01a95b13923708251cde21266

        String url =activity.getResources().getString(R.string.walkList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<WalkInfo> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkInfo>>(){});
                            activity.populateData(list);
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public  void   getRequestedWalkList(final RequestedWalk activity, final int id)
    {

        String url =activity.getResources().getString(R.string.requestedWalkList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<WalkReq> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkReq>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }



    public  void   getPendingRequestsWalkList(final RequestsWalk activity, final int id)
    {

        String url =activity.getResources().getString(R.string.pendingRequestWalkList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<WalkReq> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkReq>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    //------------------Dog Walk Api class ends-----------------------

    //------------------Dog Mate Api class starts--------------------
    public  void postDogMate(final MateActivity activity, final MateInfo mateInfo)
    {
        String url = activity.getResources().getString(R.string.postMate_api);

        final List<String> stList=new ArrayList<>();

        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
//        Gson gson = new Gson();
//        JsonObject obj1 = new JsonParser().parse(gson.toJson(walkInfo)).getAsJsonObject();
        JSONObject obj=null;
        try {
            obj =new JSONObject( objectMapper.writeValueAsString(mateInfo));
        }catch (Exception e)
        {
            Log.e("JSONPARSE", e.getMessage());
        }

        // Request a string response from the provided URL.
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url,obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Calling function after getting response.
                        activity.matePosted(true);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                Log.e("J",error.toString());
                activity.matePosted(false);

            }


        }){
            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response res= super.parseNetworkResponse(response);
                if(response.statusCode>=200 || response.statusCode<=204)
                {
                    try {
                        return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                    }
                    catch (Exception e)
                    {
                        Log.e("JSONParse", e.getMessage());
                        return  res;
                    }
                }
                else
                    return res;
            }


        };
        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }

    public  void getAvailableMateList(final AvailableMate activity, final int ownerId, final int zip)
    {

        String url =activity.getResources().getString(R.string.availableMateList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+"2"+ "/"+"1";
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MateInfo> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<MateInfo>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public  void getPostMateList(final PostMate activity, final int id)
    {

        String url =activity.getResources().getString(R.string.mateList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MateInfo> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<MateInfo>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void getRequestedMateList(final RequestedMate activity, final int id)
    {

        String url =activity.getResources().getString(R.string.requestedMateList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MateReq> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<MateReq>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }



    public  void   getPendingRequestsMateList(final RequestsMate activity, final int id)
    {

        String url =activity.getResources().getString(R.string.pendingRequestMateList_api);
        //url+="/"+ownerDetails.getOwnerId();
        url+="/"+id;
        final List<String> stList=new ArrayList<>();
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // Request a string response from the provided URL.
        final JsonArrayRequest stringRequest = new JsonArrayRequest (Request.Method.GET, url,null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<MateReq> list=null;
                        try {


                            list=objectMapper.readValue(response.toString(),new TypeReference<List<WalkReq>>(){});
                        }
                        catch (Exception e)
                        {
                            Log.e("JSONPARSE", e.getMessage());
                        }
                        finally {
                            activity.populateData(list);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("J",error.toString());

            }


        }){

            @Override
            public Map<String,String> getHeaders()
            {

                Map<String, String>  params = new HashMap<>();
                params.put("Accept","application/json");
                return  params;
            }
            @Override
            public String getBodyContentType()
            {
                return "application/json";
            }

        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    //------------------Dog Mate Api class ends---------------------



    public void uploadImage(final ProfileEditActivity activity, Bitmap bitmap)
    {
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Uploading, please wait...");
//        progressDialog.show();

        //converting image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        String url=activity.getResources().getString(R.string.picUpload_api);
        //sending image to server
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                //progressDialog.dismiss();
                if(s.equals("true")){
                    Toast.makeText(activity.getApplicationContext(), "Uploaded Successful", Toast.LENGTH_LONG).show();
                }
                else{
                    Log.e("JPG","ERERE");
                    Toast.makeText(activity.getApplicationContext(), "Some error occurred!", Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("JPG","ERERE");
                Toast.makeText(activity.getApplicationContext(), "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
            }
        }) {
            //adding parameters to send
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("image", imageString);
                return parameters;
            }
        };

        //RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
    public static ImageLoader getImageLoader() {

        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(queue,
                    new LruBitmapCache());
        }
        return mImageLoader;
    }




}
