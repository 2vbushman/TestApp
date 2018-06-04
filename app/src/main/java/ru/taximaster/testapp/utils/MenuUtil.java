package ru.taximaster.testapp.utils;

import android.support.v7.widget.PopupMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class MenuUtil {
	public static void showContextMenuIcons(PopupMenu popupMenu) {
		try {
			Field[] fields = popupMenu.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals("mPopup")) {
					field.setAccessible(true);
					Object menuPopupHelper = field.get(popupMenu);
					Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
					Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
					setForceIcons.invoke(menuPopupHelper, true);
					break;
				}
			}
		} catch (Exception ignored) {
		}
	}
}
