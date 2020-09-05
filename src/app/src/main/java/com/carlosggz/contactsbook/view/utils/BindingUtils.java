package com.carlosggz.contactsbook.view.utils;

import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.carlosggz.contactsbook.model.PhoneType;

public class BindingUtils {

    @BindingAdapter("selectedPhoneType")
    public static void setSelectedPhoneType(Spinner spinner, PhoneType phoneType) {
        spinner.setSelection(phoneType.ordinal());
    }

    @InverseBindingAdapter(attribute = "selectedPhoneType")
    public static PhoneType getSelectedPhoneType(Spinner spinner) {
        return (PhoneType)spinner.getSelectedItem();
    }

    @BindingAdapter("selectedPhoneTypeAttrChanged")
    public static void setListeners(Spinner spinner, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.
    }
}
