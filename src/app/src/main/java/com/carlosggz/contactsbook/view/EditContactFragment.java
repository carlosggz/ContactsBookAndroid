package com.carlosggz.contactsbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
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
import android.widget.Button;
import android.widget.Toast;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.FragmentEditContactBinding;
import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.view.adapters.ContactEmailsEditAdapter;
import com.carlosggz.contactsbook.view.adapters.ContactEmailsListAdapter;
import com.carlosggz.contactsbook.view.adapters.ContactPhonesEditAdapter;
import com.carlosggz.contactsbook.view.adapters.ContactPhonesListAdapter;
import com.carlosggz.contactsbook.view.base.BaseFragment;
import com.carlosggz.contactsbook.viewmodel.EditContactViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditContactFragment extends BaseFragment {

    String contactId;
    private EditContactViewModel viewModel;
    FragmentEditContactBinding editBinding;

    @BindView(R.id.emailsList)
    RecyclerView emailsList;

    @BindView(R.id.phonesList)
    RecyclerView phonesList;

    @BindView(R.id.btnAddEmail)
    Button btnAddEmail;

    @BindView(R.id.btnAddPhone)
    Button btnAddPhone;

    private ContactEmailsEditAdapter emailsAdapter ;
    private ContactPhonesEditAdapter phonesAdapter ;

    public EditContactFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_contact, container, false);
        editBinding = binding;
        setHasOptionsMenu(true);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViewModel();
        initializeLists();
        setEventHandlers();
        setObservers();
    }

    private void setEventHandlers() {
        btnAddEmail.setOnClickListener(v -> viewModel.addEmail());
        btnAddPhone.setOnClickListener(v -> viewModel.addPhone());
    }

    private void setObservers() {

        LifecycleOwner lco = getViewLifecycleOwner();

        viewModel.getEmailAddresses().observe(lco, emails ->{
            if (emails != null) {
                emailsAdapter.updateList(emails);
            }
        });

        viewModel.getPhoneNumbers().observe(lco, phones ->{
            if (phones != null) {
                phonesAdapter.updateList(phones);
            }
        });

        viewModel.getErrorLoading().observe(lco, error -> {
            if (error != null && error) {
                displayMessage(getString(R.string.error), getString(R.string.error_loading_contact), this::back);
            }
        });

        viewModel.getErrorSaving().observe(lco, error -> {
            if (!Utils.isNullOrWhiteSpace(error)) {
                displayMessage(getString(R.string.error), getString(R.string.error_saving_contact) + ": " + error);
            }
        });

        viewModel.getSavedSuccessfully().observe(lco, saved -> {
            if (saved != null && saved) {
                back();
            }
        });

        viewModel.getValidationErrors().observe(lco, errors ->{
            if (errors != null && errors.size() > 0) {
                displayList("Verify the following errors:", errors.stream().toArray(String[]::new));
            }
        });
    }

    private void initializeLists() {
        emailsAdapter = new ContactEmailsEditAdapter(new ArrayList<>(), index -> viewModel.deleteEmail(index));
        phonesAdapter = new ContactPhonesEditAdapter(new ArrayList<>(), index -> viewModel.deletePhone(index));

        emailsList.setLayoutManager(new LinearLayoutManager(getContext()));
        emailsList.setAdapter(emailsAdapter);
        emailsList.setNestedScrollingEnabled(false);

        phonesList.setLayoutManager(new LinearLayoutManager(getContext()));
        phonesList.setAdapter(phonesAdapter);
        phonesList.setNestedScrollingEnabled(false);
    }

    private void initializeViewModel() {
        contactId = getArguments() != null ? EditContactFragmentArgs.fromBundle(getArguments()).getContactId() : null;

        viewModel = ViewModelProviders.of(this).get(EditContactViewModel.class);

        if (Utils.isEmptyOrNull(contactId)) {
            viewModel.newContact();
        }
        else {
            viewModel.loadContact(contactId);
        }

        editBinding.setViewmodel(viewModel);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save: {
                viewModel.save();
                break;
            }
            case R.id.action_cancel: {
                back();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String getTitle() {
        return Utils.isEmptyOrNull(contactId) ? getString(R.string.add_contact_title) : getString(R.string.edit_contact_title);
    }

    private void back() {
        Navigation.findNavController(editBinding.getRoot()).popBackStack();
    }
}