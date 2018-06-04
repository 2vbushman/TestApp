package ru.taximaster.testapp.ui.fragments;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.taximaster.testapp.data.Photo;

public interface MainView {
	ProgressDialog getDialog();

	RecyclerView getRecyclerView();

	void setPage(int page);

	List<Photo> getList();

	void setList(List<Photo> list);

	void scrollEventUnlock();
}
