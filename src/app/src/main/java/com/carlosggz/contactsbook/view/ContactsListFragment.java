package com.carlosggz.contactsbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.view.adapters.ContactsListAdapter;
import com.carlosggz.contactsbook.view.base.BaseFragment;
import com.carlosggz.contactsbook.viewmodel.ContactsListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsListFragment extends BaseFragment {

    private ContactsListViewModel viewModel;
    private ContactsListAdapter contactsAdapter = new ContactsListAdapter(new ArrayList<>());

    @BindView(R.id.contactsList)
    RecyclerView contactsList;

    @BindView(R.id.errorMessage)
    TextView errorMessage;

    @BindView(R.id.loadingProgressBar)
    ProgressBar loadingProgress;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.btnAdd)
    FloatingActionButton btnAdd;

    public ContactsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ContactsListViewModel.class);
        viewModel.reloadContacts();

        contactsList.setLayoutManager(new LinearLayoutManager(getContext()));
        contactsList.setAdapter(contactsAdapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.reloadContacts();
            refreshLayout.setRefreshing(false);
        });

        btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(ContactsListFragmentDirections.actionAdd(""));
        });

        setObservers();
    }

    private void setObservers() {
        viewModel.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            if (contacts == null){
                return;
            }

            contactsList.setVisibility(View.VISIBLE);
            contactsAdapter.updateContactList(contacts);
            btnAdd.setVisibility(View.VISIBLE);
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading == null) {
                return;
            }

            loadingProgress.setVisibility(isLoading ? View.VISIBLE: View.GONE);
            btnAdd.setVisibility(isLoading ? View.GONE: View.VISIBLE);

            if (isLoading) {
                contactsList.setVisibility(View.GONE);
                errorMessage.setVisibility(View.GONE);
            }
        });

        viewModel.getContactsLoadingError().observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                errorMessage.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected String getTitle() {
        return getString(R.string.Contacts_List_Title);
    }
}