package com.carlosggz.contactsbook.view.base;

import androidx.fragment.app.Fragment;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.view.MainActivity;
import com.carlosggz.contactsbook.view.listeners.OkAction;
import com.carlosggz.contactsbook.view.utils.MessagingUtils;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            activity.setActionBarTitle(getTitle());
        }
    }

    abstract protected String getTitle();

    protected void displayMessage(String title, String message, OkAction action) {
        MessagingUtils.displayAlert(title, message, action, this.getContext());
    }

    protected void displayMessage(String title, String message) {
        MessagingUtils.displayAlert(title, message, () -> {}, this.getContext());
    }

    protected void displayList(String title, String[] messages) {
        MessagingUtils.displayMessagesList(title, messages, () -> {}, this.getContext());
    }
}
