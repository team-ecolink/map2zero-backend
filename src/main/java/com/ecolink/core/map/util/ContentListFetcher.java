package com.ecolink.core.map.util;

import com.ecolink.core.map.service.ContentListService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentListFetcher {

	private final ContentListService contentListService;

	@Autowired
	public ContentListFetcher(ContentListService contentListService) {
		this.contentListService = contentListService;
	}

	public void fetchDataAndStore() {
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsListAll&key=90d9ef047980430297ec7fa3a8377710&theme_id=11103395");

		try {
			HttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				String responseData = EntityUtils.toString(response.getEntity());

				contentListService.saveContentsListAll(responseData);
			} else {
				System.err.println("Failed to fetch data, Status Code: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}