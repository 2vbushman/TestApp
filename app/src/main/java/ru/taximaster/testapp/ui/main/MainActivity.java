package ru.taximaster.testapp.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.taximaster.testapp.FlickrApplication;
import ru.taximaster.testapp.R;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prepareActionBar();

		if (getApplication() instanceof FlickrApplication) {
			FlickrApplication application = (FlickrApplication) getApplication();
			application.setActivity(this);
		}

		MainPresenter mainPresenter = new MainPresenter(new MainModel(), this);
		if (savedInstanceState == null) mainPresenter.loadMainFragment();
	}

	private void prepareActionBar() {
		setSupportActionBar(findViewById(R.id.toolbar));
	}

	@Override
	public void onBackPressed() {
		final int fragments = getSupportFragmentManager().getBackStackEntryCount();
		if (fragments == 1) {
			finish();
		} else {
			if (getFragmentManager().getBackStackEntryCount() > 1) {
				getFragmentManager().popBackStack();
			} else {
				super.onBackPressed();
			}
		}
	}
}
