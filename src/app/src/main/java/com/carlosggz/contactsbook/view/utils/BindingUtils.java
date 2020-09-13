package com.carlosggz.contactsbook.view.utils;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.model.PhoneType;

public class BindingUtils {

    @BindingAdapter(value = "selectedPhoneType")
    public static void setSelectedPhoneType(Spinner spinner, int phoneType) {
        if (spinner != null) {
            spinner.setSelection(phoneType);
        }
    }

    @InverseBindingAdapter(attribute = "selectedPhoneType", event = "selectedPhoneTypeAttrChanged")
    public static int getSelectedPhoneType(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

    @BindingAdapter(value = "selectedPhoneTypeAttrChanged")
    public static void setListeners(Spinner spinner, final InverseBindingListener attrChange) {
        // Set a listener for click, focus, touch, etc.

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                attrChange.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
