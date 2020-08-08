package com.carlosggz.contactsbook.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.FragmentContactDetailsBinding;
import com.carlosggz.contactsbook.viewmodel.ContactDetailsViewModel;

public class ContactDetailsFragment extends Fragment {

    ContactDetailsViewModel viewModel;
    FragmentContactDetailsBinding detailsBinding;

    public ContactDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentContactDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_details, container, false);
        detailsBinding = binding;
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String contactId = getArguments() != null ? ContactDetailsFragmentArgs.fromBundle(getArguments()).getContactId() : null;

        viewModel = ViewModelProviders.of(this).get(ContactDetailsViewModel.class);
        viewModel.loadContact(contactId);

        viewModel.getContact().observe(getViewLifecycleOwner(), contact -> {
            if (contact != null && getContext() != null) {
                detailsBinding.setContact(contact);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.Contact_Details_Title));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit: {
                editContact();
                break;
            }
            case R.id.action_delete: {
                deleteContact();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void editContact() {
        Toast.makeText(getActivity(), "Edit selected", Toast.LENGTH_LONG).show();
    }

    private void deleteContact() {

        new AlertDialog.Builder(getActivity())
                .setMessage("Are you sure?")
                .setTitle("Delete")
                .setPositiveButton("Yes", (dialog, id) -> applyDelete())
                .setNegativeButton("No", (dialog, id) -> { })
                .create()
                .show();
    }

    private void applyDelete() {
        viewModel.deleteContact();
        //Return to list
        NavDirections action = ContactDetailsFragmentDirections.actionList();
        Navigation.findNavController(detailsBinding.getRoot()).navigate(action);
    }
}