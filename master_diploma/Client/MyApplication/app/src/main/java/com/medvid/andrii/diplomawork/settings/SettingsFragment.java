package com.medvid.andrii.diplomawork.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;
import com.medvid.andrii.diplomawork.util.DialogUtils;
import com.medvid.andrii.diplomawork.util.OnListItemClickListener;

import java.util.Arrays;

public class SettingsFragment extends Fragment implements SettingsContract.View, OnListItemClickListener<String> {

    private RecyclerView mRecyclerView;
    private SettingsContract.Presenter mPresenter;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

    }

    /**
     * {@link SettingsContract.View} methods
     */

    @Override
    public void showAboutInfo() {

    }

    @Override
    public void showLogout() {

    }

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     * Interfaces implementation
     */

    @Override
    public void onListItemClick(String object) {
        Preconditions.checkNotNull(object);

        if (object.equals(getString(R.string.about_camel_style))) {
            showAboutDialog();
            return;
        }

        if (object.equals(getString(R.string.log_out_camel_style))) {
            showLogoutDialog();
            return;
        }
    }

    /**
     * Private Methods
     */
    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        SettingsListAdapter settingsListAdapter
                = new SettingsListAdapter(
                Arrays.asList(
                        new String[] {
                                getString(R.string.about_camel_style),
                                getString(R.string.log_out_camel_style)
                        }),
                this);
        mRecyclerView.setAdapter(settingsListAdapter);
    }

    private void showLogoutDialog()  {

        DialogInterface.OnClickListener confirmClickAction = new  DialogInterface.OnClickListener()    {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.performLogout();
                dialog.dismiss();
            }
        };

        Dialog logoutDialog = DialogUtils.createDialog(
                getActivity(),
                getString(R.string.log_out_camel_style),
                getString(R.string.log_out_message),
                getString(R.string.ok),
                getString(R.string.cancel),
                confirmClickAction,
                null,
                false);
        logoutDialog.show();
    }

    private void showAboutDialog()  {
        Dialog logoutDialog = DialogUtils.createConfirmDialog(
                getActivity(),
                getString(R.string.about_camel_style),
                getString(R.string.diploma_about),
                getString(R.string.ok),
                null,
                false);

        logoutDialog.show();
    }
}
