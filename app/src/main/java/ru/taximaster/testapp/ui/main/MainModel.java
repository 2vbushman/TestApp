package ru.taximaster.testapp.ui.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.taximaster.testapp.R;

final class MainModel {
	public Map<Integer, String> getFragmentList() {
		return Collections.unmodifiableMap(new HashMap<Integer, String>() {{
			put(R.id.main_fragment, "ru.taximaster.testapp.ui.fragments.MainFragment");
		}});
	}
}
