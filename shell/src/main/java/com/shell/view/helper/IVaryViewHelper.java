package com.shell.view.helper;

import android.content.Context;
import android.view.View;

public interface IVaryViewHelper {

	View getCurrentLayout();

	void restoreView();

	void showLayout(View view);

	void showLayout(int layoutId);

	View inflate(int layoutId);

	Context getContext();

	View getView();

}