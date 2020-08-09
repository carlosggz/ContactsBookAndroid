package com.carlosggz.contactsbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.viewmodel.ContactDetailsViewModel;
import com.carlosggz.contactsbook.viewmodel.EditContactViewModel;

public class EditContactFragment extends Fragment {

    String contactId;
    private EditContactViewModel viewModel;

    public EditContactFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_contact, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle(Utils.isEmptyOrNull(contactId) ? getString(R.string.add_contact_title) : getString(R.string.edit_contact_title));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactId = getArguments() != null ? EditContactFragmentArgs.fromBundle(getArguments()).getContactId() : null;

        viewModel = ViewModelProviders.of(this).get(EditContactViewModel.class);

        if (Utils.isEmptyOrNull(contactId)) {
            viewModel.newContact();
        }
        else {
            viewModel.loadContact(contactId);
        }
    }
}