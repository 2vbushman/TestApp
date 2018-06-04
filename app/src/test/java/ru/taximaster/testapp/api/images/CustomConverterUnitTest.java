package ru.taximaster.testapp.api.images;

import com.google.gson.Gson;

import org.junit.Test;

import ru.taximaster.testapp.data.Photo;

import static junit.framework.Assert.assertEquals;

public final class CustomConverterUnitTest {
	@Test
	public void assert_image_regex_correct() {
		final String response = "jsonFlickrApi({\"photos\":{\"page\":1,\"pages\":118809,\"perpage\":10,\"total\":\"1188090\",\"photo\":[{\"id\":\"14179427063\",\"owner\":\"73422502@N08\",\"secret\":\"6495d5024f\",\"server\":\"7300\",\"farm\":8,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"8642535589\",\"owner\":\"15381092@N07\",\"secret\":\"4a487b1ffc\",\"server\":\"8402\",\"farm\":9,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"6053818868\",\"owner\":\"54686903@N07\",\"secret\":\"375e3fafa5\",\"server\":\"6080\",\"farm\":7,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"14612756156\",\"owner\":\"25102254@N03\",\"secret\":\"72f6fe4d10\",\"server\":\"2911\",\"farm\":3,\"title\":\"hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"479708902\",\"owner\":\"63592286@N00\",\"secret\":\"e452bf46f3\",\"server\":\"177\",\"farm\":1,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"5062936096\",\"owner\":\"63216345@N00\",\"secret\":\"66f3e8ddf4\",\"server\":\"4129\",\"farm\":5,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"6891285495\",\"owner\":\"52320199@N07\",\"secret\":\"d31350161d\",\"server\":\"7204\",\"farm\":8,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"2914313051\",\"owner\":\"10210331@N08\",\"secret\":\"6c6bc0ea83\",\"server\":\"2168\",\"farm\":3,\"title\":\"hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"2452307305\",\"owner\":\"24699953@N06\",\"secret\":\"a3940a2f8a\",\"server\":\"2388\",\"farm\":3,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"33966004962\",\"owner\":\"73422502@N08\",\"secret\":\"4c539d0307\",\"server\":\"2926\",\"farm\":3,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0}]},\"stat\":\"ok\"})";
		final String customResponse = "[{\"id\":\"14179427063\",\"owner\":\"73422502@N08\",\"secret\":\"6495d5024f\",\"server\":\"7300\",\"farm\":8,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"8642535589\",\"owner\":\"15381092@N07\",\"secret\":\"4a487b1ffc\",\"server\":\"8402\",\"farm\":9,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"6053818868\",\"owner\":\"54686903@N07\",\"secret\":\"375e3fafa5\",\"server\":\"6080\",\"farm\":7,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"14612756156\",\"owner\":\"25102254@N03\",\"secret\":\"72f6fe4d10\",\"server\":\"2911\",\"farm\":3,\"title\":\"hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"479708902\",\"owner\":\"63592286@N00\",\"secret\":\"e452bf46f3\",\"server\":\"177\",\"farm\":1,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"5062936096\",\"owner\":\"63216345@N00\",\"secret\":\"66f3e8ddf4\",\"server\":\"4129\",\"farm\":5,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"6891285495\",\"owner\":\"52320199@N07\",\"secret\":\"d31350161d\",\"server\":\"7204\",\"farm\":8,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"2914313051\",\"owner\":\"10210331@N08\",\"secret\":\"6c6bc0ea83\",\"server\":\"2168\",\"farm\":3,\"title\":\"hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"2452307305\",\"owner\":\"24699953@N06\",\"secret\":\"a3940a2f8a\",\"server\":\"2388\",\"farm\":3,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"33966004962\",\"owner\":\"73422502@N08\",\"secret\":\"4c539d0307\",\"server\":\"2926\",\"farm\":3,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0}]";

		//final String newResponse = CustomGsonBodyPhotoConverter.substringResponse(response);
		//assertEquals(customResponse, newResponse);
	}

	@Test
	public void assert_json_correct(){
		String response = "{\"id\":\"14179427063\",\"owner\":\"73422502@N08\",\"secret\":\"6495d5024f\",\"server\":\"7300\",\"farm\":8,\"title\":\"Hello\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0}";

		Gson gson = new Gson();
		Photo photo = gson.fromJson(response, Photo.class);
		assertEquals("14179427063", photo.getId());
	}

}
