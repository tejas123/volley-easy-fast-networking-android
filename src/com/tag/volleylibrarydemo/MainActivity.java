package com.tag.volleylibrarydemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity implements OnClickListener {
	private TextView txtDisplay;
	private Button btnSubmit;
	private EditText edtCityName;
	private static final String WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?q=";
	RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setWidgetReference();
		queue = Volley.newRequestQueue(this);
		btnSubmit.setOnClickListener(this);
	}

	private void setWidgetReference() {
		txtDisplay = (TextView) findViewById(R.id.txtDisplay);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		edtCityName = (EditText) findViewById(R.id.edtCityName);
	}

	@Override
	public void onClick(View v) {
		if (v == btnSubmit) {

			JsonObjectRequest jsObjRequest = new JsonObjectRequest(
					Request.Method.GET, WEATHER_API
							+ edtCityName.getText().toString(), null,
					new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {
								JSONObject main = response
										.getJSONObject("main");
								double temp_min = main.getDouble("temp_min");
								double temp_max = main.getDouble("temp_max");
								String temprature = "Minimum Temprature: "
										+ temp_min + "\n"
										+ "Maximum Temprature: " + temp_max;
								txtDisplay.setText(temprature);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {

						}
					});

			queue.add(jsObjRequest);
		}
	}
}
