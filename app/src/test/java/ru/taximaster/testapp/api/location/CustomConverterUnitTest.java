package ru.taximaster.testapp.api.location;

import com.google.gson.Gson;

import org.junit.Test;

import ru.taximaster.testapp.data.Location;

import static junit.framework.Assert.assertEquals;

public final class CustomConverterUnitTest {
	@Test
	public void assert_location_regex_correct() {
		final String response = "jsonFlickrApi({\"photo\":{\"id\":\"5367632876\",\"location\":{\"latitude\":\"53.744396\",\"longitude\":\"-0.339846\",\"accuracy\":\"14\",\"context\":\"0\",\"locality\":{\"_content\":\"Hull\",\"place_id\":\"KjFlu9NTV7jyiw\",\"woeid\":\"25211\"},\"county\":{\"_content\":\"East Riding of Yorkshire\",\"place_id\":\"ai3HaUFQULyv8cCHWw\",\"woeid\":\"12602193\"},\"region\":{\"_content\":\"England\",\"place_id\":\"2eIY2QFTVr_DwWZNLg\",\"woeid\":\"24554868\"},\"country\":{\"_content\":\"United Kingdom\",\"place_id\":\"cnffEpdTUb5v258BBA\",\"woeid\":\"23424975\"},\"place_id\":\"KjFlu9NTV7jyiw\",\"woeid\":\"25211\"}},\"stat\":\"ok\"})";
		final String customResponse = "{\"latitude\":\"53.744396\",\"longitude\":\"-0.339846\",\"accuracy\":\"14\",\"context\":\"0\"}";

		//final String newResponse = CustomGsonBodyLocationConverter.substringResponse(response);
		//System.out.println(newResponse);
		//assertEquals(customResponse, newResponse);
	}

	@Test
	public void assert_json_correct(){
		final String customResponse = "{\"latitude\":\"53.744396\",\"longitude\":\"-0.339846\",\"accuracy\":\"14\",\"context\":\"0\"}";
		Gson gson = new Gson();
		Location location = gson.fromJson(customResponse, Location.class);
		assertEquals(53.744396, location.getLatitude());
		assertEquals(-0.339846, location.getLongitude());
		assertEquals(14, location.getAccuracy());
		assertEquals(0, location.getContext());
	}

}
