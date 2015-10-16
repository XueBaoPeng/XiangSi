package com.xbp.xbp.xiangsi.utis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Lenovo on 2015/9/16.
 */
public class HttpQuest {
	public static  void loadData(final Context context,String url, final Callback callback) {

		StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {

				callback.response(ChangeString.decodeUnicode(s));
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {

			}
		});
		request.setTag("abcPost");
		MyApplication.getQueue().add(request);
	}
	public interface Callback{
		public void response(String result);
	}


}
