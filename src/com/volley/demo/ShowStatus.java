/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.volley.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.zxing.Result;
import com.volley.demo.util.MyVolley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Demonstrates how to use Volley along with Gson
 *
 */
public class ShowStatus extends Activity  {
    private static String API_URL = "http://www.example.com?";

    private String getEncodedString(String s)  {
        try {
            return URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    TextView textView;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.show_status);
                        // Set the scanner view as the content view
        textView = (TextView) findViewById(R.id.status);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");

        final String arr[] = data.split("[,]");

        RequestQueue x = MyVolley.getRequestQueue();
        x.add(new StringRequest(Request.Method.GET, API_URL + "id=" + getEncodedString(arr[0]) + "&name=" + getEncodedString(arr[1]) + "&car_number=" + getEncodedString(arr[2]), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response+ "###" + "id=" + getEncodedString(arr[0]) + "&name=" + getEncodedString(arr[1]) + "&car_number=" + getEncodedString(arr[2]) );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
