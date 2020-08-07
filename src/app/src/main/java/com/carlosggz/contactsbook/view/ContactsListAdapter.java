package com.carlosggz.contactsbook.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    private ArrayList<ContactInfo> contactsList;

    public ContactsListAdapter(List<ContactInfo> contactsList) {
        this.contactsList =  new ArrayList(contactsList);
    }

    public void updateContactList(List<ContactInfo> contacts) {
        contactsList.clear();
        contactsList.addAll(contacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        TextView firstName = holder.itemView.findViewById(R.id.contactFirstName);
        TextView lastName = holder.itemView.findViewById(R.id.contactLastName);
        ContactInfo contact = contactsList.get(position);
        firstName.setText(contact.firstName);
        lastName.setText(contact.lastName);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
