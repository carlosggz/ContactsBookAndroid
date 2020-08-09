package com.carlosggz.contactsbook.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.ContactItemBinding;
import com.carlosggz.contactsbook.model.ContactInfo;
import com.carlosggz.contactsbook.view.ContactSelectedListener;
import com.carlosggz.contactsbook.view.ContactsListFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> implements ContactSelectedListener {

    private ArrayList<ContactInfo> contactsList;

    public ContactsListAdapter(List<ContactInfo> contactsList) {
        this.contactsList =  new ArrayList<ContactInfo>(contactsList);
    }

    public void updateContactList(List<ContactInfo> contacts) {
        contactsList.clear();
        contactsList.addAll(contacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ContactItemBinding view = DataBindingUtil.inflate(inflater, R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.itemView.setContact(contactsList.get(position));
        holder.itemView.setListener(this);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    @Override
    public void onContactSelected(View v) {
        String contactId = ((TextView)v.findViewById(R.id.contactId)).getText().toString();
        ContactsListFragmentDirections.ActionDetails actionDetails = ContactsListFragmentDirections.actionDetails();
        actionDetails.setContactId(contactId);
        Navigation.findNavController(v).navigate(actionDetails);
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        public ContactItemBinding itemView;

        public ContactViewHolder(@NonNull ContactItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
