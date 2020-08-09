package com.carlosggz.contactsbook.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.DetailsEmailItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactEmailsListAdapter  extends RecyclerView.Adapter<ContactEmailsListAdapter.ContactEmailsHolder> {

    private ArrayList<String> emails;

    public ContactEmailsListAdapter(List<String> emails) {
        this.emails =  new ArrayList<String>(emails);
    }

    public void updateList(List<String> emails) {
        this.emails.clear();
        this.emails.addAll(emails);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactEmailsListAdapter.ContactEmailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DetailsEmailItemBinding view = DataBindingUtil.inflate(inflater, R.layout.details_email_item, parent, false);
        return new ContactEmailsListAdapter.ContactEmailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactEmailsListAdapter.ContactEmailsHolder holder, int position) {
        holder.itemView.setEmail(emails.get(position));
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    class ContactEmailsHolder extends RecyclerView.ViewHolder {

        public DetailsEmailItemBinding itemView;

        public ContactEmailsHolder(@NonNull DetailsEmailItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
