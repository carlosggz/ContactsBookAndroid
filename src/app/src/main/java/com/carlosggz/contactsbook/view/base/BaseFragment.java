package com.carlosggz.contactsbook.view.base;

import android.app.ProgressDialog;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.view.MainActivity;
import com.carlosggz.contactsbook.view.listeners.OkAction;
import com.carlosggz.contactsbook.view.utils.MessagingUtils;

public abstract class BaseFragment extends Fragment {

    private ProgressDialog savingProgress;

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

    protected  void changeInteraction(boolean enable) {
        FragmentActivity activity  = this.getActivity();

        if (activity == null) {
            return;
        }

        if (enable) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    protected void showPleaseWait(String message) {

        if (savingProgress == null) {
            savingProgress = new ProgressDialog(getContext());
            savingProgress.setMessage(message);
            savingProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            savingProgress.setIndeterminate(true);
        }
        else {
            savingProgress.setMessage(message);
        }

        savingProgress.show();
    }

    protected  void hidePleaseWait() {
        if (savingProgress != null && savingProgress.isShowing()) {
            savingProgress.hide();
        }
    }
}
