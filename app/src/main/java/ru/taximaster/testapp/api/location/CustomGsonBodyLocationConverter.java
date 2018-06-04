package ru.taximaster.testapp.api.location;

import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.taximaster.testapp.api.common.CustomGsonBodyConverter;

public final class CustomGsonBodyLocationConverter<T>
		extends CustomGsonBodyConverter<T> {


	CustomGsonBodyLocationConverter(Gson gson, TypeAdapter<T> adapter) {
		super(gson, adapter);
	}

	@VisibleForTesting
	protected String substringResponse(final String response) {
		final String regex = ".*?\"location\":\\s?(\\{[^{}]*)(?=\"locality\").*";
		final Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(response);
		if (matcher.matches()) {
			String str = matcher.group(1).trim();
			str = str.substring(0, str.length() - 1);
			return str + "}";
		}
		return "";
	}
}