package com.carlosggz.contactsbook.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.DetailsEmailItemBinding;
import com.carlosggz.contactsbook.databinding.EditMailItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactEmailsEditAdapter  extends RecyclerView.Adapter<ContactEmailsEditAdapter.ContactEmailsEditHolder> {

    private ArrayList<String> emails;
    private ItemDeleteListener listener;

    public ContactEmailsEditAdapter(List<String> emails, ItemDeleteListener listener) {
        this.emails =  new ArrayList<String>(emails);
        this.listener = listener;
    }

    public void updateList(List<String> emails) {
        this.emails.clear();
        this.emails.addAll(emails);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactEmailsEditAdapter.ContactEmailsEditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EditMailItemBinding view = DataBindingUtil.inflate(inflater, R.layout.edit_mail_item, parent, false);
        return new ContactEmailsEditAdapter.ContactEmailsEditHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactEmailsEditHolder holder, int position) {
        holder.itemView.setEmail(emails.get(position));
        ImageButton btnDelete = holder.itemView.getRoot().findViewById(R.id.btnDeleteMail);
        btnDelete.setOnClickListener(v -> listener.Delete(position));
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    class ContactEmailsEditHolder extends RecyclerView.ViewHolder {

        public EditMailItemBinding itemView;

        public ContactEmailsEditHolder(@NonNull EditMailItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
