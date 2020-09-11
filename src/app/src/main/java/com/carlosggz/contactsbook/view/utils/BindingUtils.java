package com.carlosggz.contactsbook.view.utils;

import android.util.Log;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.carlosggz.contactsbook.model.PhoneType;

public class BindingUtils {

    @BindingAdapter("selectedPhoneType")
    public static void setSelectedPhoneType(Spinner spinner, int phoneType) {
        if (spinner != null) {
            spinner.setSelection(phoneType);
        }
    }

    @InverseBindingAdapter(attribute = "selectedPhoneType")
    public static Integer getSelectedPhoneType(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

    @BindingAdapter("selectedPhoneTypeAttrChanged")
    public static void setListeners(Spinner spinner, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.
    }
}
