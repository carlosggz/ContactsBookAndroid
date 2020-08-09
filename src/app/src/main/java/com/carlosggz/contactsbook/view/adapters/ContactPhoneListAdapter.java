package com.carlosggz.contactsbook.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.databinding.DetailsPhoneItemBinding;
import com.carlosggz.contactsbook.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public class ContactPhoneListAdapter extends RecyclerView.Adapter<ContactPhoneListAdapter.ContactPhoneHolder> {

    private ArrayList<PhoneNumber> phones;

    public ContactPhoneListAdapter(List<PhoneNumber> phoneNumbers) {
        this.phones = new ArrayList<PhoneNumber>(phoneNumbers);
    }

    public void updateList(List<PhoneNumber> phoneNumbers) {
        this.phones.clear();
        this.phones.addAll(phoneNumbers);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactPhoneListAdapter.ContactPhoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DetailsPhoneItemBinding view = DataBindingUtil.inflate(inflater, R.layout.details_phone_item, parent, false);
        return new ContactPhoneListAdapter.ContactPhoneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPhoneListAdapter.ContactPhoneHolder holder, int position) {
        holder.itemView.setPhone(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    class ContactPhoneHolder extends RecyclerView.ViewHolder {

        public DetailsPhoneItemBinding itemView;

        public ContactPhoneHolder(@NonNull DetailsPhoneItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
