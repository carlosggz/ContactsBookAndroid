package com.carlosggz.contactsbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.FragmentContactDetailsBinding;
import com.carlosggz.contactsbook.model.ContactDetails;
import com.carlosggz.contactsbook.viewmodel.ContactDetailsViewModel;
import com.carlosggz.contactsbook.viewmodel.ContactsListViewModel;

public class ContactDetailsFragment extends Fragment {

    ContactDetailsViewModel viewModel;
    FragmentContactDetailsBinding detailsBinding;

    public ContactDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentContactDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_details, container, false);
        detailsBinding = binding;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String contactId = getArguments() != null ? ContactDetailsFragmentArgs.fromBundle(getArguments()).getContactId() : null;

        viewModel = ViewModelProviders.of(this).get(ContactDetailsViewModel.class);
        viewModel.LoadContact(contactId);

        viewModel.getContact().observe(getViewLifecycleOwner(), contact -> {
            if (contact != null && getContext() != null) {
                detailsBinding.setContact(contact);
            }
        });
    }
}