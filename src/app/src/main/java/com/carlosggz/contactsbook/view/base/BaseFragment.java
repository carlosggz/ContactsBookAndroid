package com.carlosggz.contactsbook.view.base;

import androidx.fragment.app.Fragment;

import com.carlosggz.contactsbook.R;
import com.carlosggz.contactsbook.helpers.Utils;
import com.carlosggz.contactsbook.view.MainActivity;

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
}
