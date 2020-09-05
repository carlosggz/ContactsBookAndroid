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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.FragmentContactDetailsBinding;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.view.adapters.ContactEmailsListAdapter;
import com.carlosggz.contactsbook.view.adapters.ContactPhonesListAdapter;
import com.carlosggz.contactsbook.view.base.BaseFragment;
import com.carlosggz.contactsbook.viewmodel.ContactDetailsViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactDetailsFragment extends BaseFragment {

    ContactDetailsViewModel viewModel;
    FragmentContactDetailsBinding detailsBinding;
    ContactEmailsListAdapter emailsListAdapter = new ContactEmailsListAdapter(new ArrayList<String>());
    ContactPhonesListAdapter phoneListAdapter = new ContactPhonesListAdapter(new ArrayList<PhoneNumber>());

    @BindView(R.id.contactEmails)
    RecyclerView contactEmails;

    @BindView(R.id.contactPhones)
    RecyclerView contactPhones;

    public ContactDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentContactDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_details, container, false);
        detailsBinding = binding;
        setHasOptionsMenu(true);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String contactId = getArguments() != null ? ContactDetailsFragmentArgs.fromBundle(getArguments()).getContactId() : null;

        viewModel = ViewModelProviders.of(this).get(ContactDetailsViewModel.class);
        viewModel.loadContact(contactId);

        contactEmails.setLayoutManager(new LinearLayoutManager(getContext()));
        contactEmails.setAdapter(emailsListAdapter);

        contactPhones.setLayoutManager(new LinearLayoutManager(getContext()));
        contactPhones.setAdapter(phoneListAdapter);

        viewModel.getContact().observe(getViewLifecycleOwner(), contact -> {
            if (contact != null && getContext() != null) {
                detailsBinding.setContact(contact);
                emailsListAdapter.updateList(contact.getEmailAddresses());
                phoneListAdapter.updateList(contact.getPhoneNumbers());
            }
        });
    }

    @Override
    protected String getTitle() {
        return getString(R.string.Contact_Details_Title);
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
        navigateToAction(ContactDetailsFragmentDirections.actionEdit(viewModel.getContact().getValue().getContactId()));
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
        navigateToAction(ContactDetailsFragmentDirections.actionList());
    }

    private void navigateToAction(NavDirections action) {
        Navigation.findNavController(detailsBinding.getRoot()).navigate(action);
    }
}