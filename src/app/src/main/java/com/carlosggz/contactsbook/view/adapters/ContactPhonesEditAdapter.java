package com.carlosggz.contactsbook.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.DetailsPhoneItemBinding;
import com.carlosggz.contactsbook.databinding.EditPhoneItemBinding;
import com.carlosggz.contactsbook.model.PhoneNumber;
import com.carlosggz.contactsbook.view.listeners.ItemDeleteListener;

import java.util.ArrayList;
import java.util.List;

public class ContactPhonesEditAdapter extends RecyclerView.Adapter<ContactPhonesEditAdapter.ContactPhoneEditHolder> {

    private ArrayList<PhoneNumber> phones;
    private ItemDeleteListener listener;

    public ContactPhonesEditAdapter(List<PhoneNumber> phoneNumbers, ItemDeleteListener listener) {
        this.phones = new ArrayList<PhoneNumber>(phoneNumbers);
        this.listener = listener;
    }

    public void updateList(List<PhoneNumber> phoneNumbers) {
        this.phones.clear();
        this.phones.addAll(phoneNumbers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactPhonesEditAdapter.ContactPhoneEditHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EditPhoneItemBinding view = DataBindingUtil.inflate(inflater, R.layout.edit_phone_item, parent, false);
        return new ContactPhonesEditAdapter.ContactPhoneEditHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPhonesEditAdapter.ContactPhoneEditHolder holder, int position) {
        holder.itemView.setPhone(phones.get(position));
        ImageButton btnDelete = holder.itemView.getRoot().findViewById(R.id.btnDeletePhone);
        btnDelete.setOnClickListener(v -> listener.Delete(position));
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    class ContactPhoneEditHolder extends RecyclerView.ViewHolder {

        public EditPhoneItemBinding itemView;

        public ContactPhoneEditHolder(@NonNull EditPhoneItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}