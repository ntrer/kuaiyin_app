package com.shushang.shushangjiatui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.Log;

import com.shushang.shushangjiatui.LoginActivity;
import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.activity.RestPwdActivity;
import com.shushang.shushangjiatui.base.MessageEvent;
import com.shushang.shushangjiatui.ui.ExtAlertDialog;
import com.shushang.shushangjiatui.utils.SharePreferences.PreferencesUtils;
import com.tencent.bugly.beta.Beta;

import org.greenrobot.eventbus.EventBus;


public class SettingFragment extends PreferenceFragment {

    private FingerprintManagerCompat fingerprintManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_activity);
        fingerprintManager = FingerprintManagerCompat.from(getActivity());

        final Preference listPreference = (Preference) getPreferenceManager()
                .findPreference("xiugaimima");


        final Preference listPreference3 = (Preference) getPreferenceManager()
                .findPreference("update");

        final Preference listPreference2 = (Preference) getPreferenceManager()
                .findPreference("quit");



        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(),RestPwdActivity.class));
                return false;
            }
        });

        listPreference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ExtAlertDialog.showSureDlg(getActivity(), "提醒", getString(R.string.quit), getString(R.string.exit_login), new ExtAlertDialog.IExtDlgClick() {
                    @Override
                    public void Oclick(int result) {
                        if (result == 1) {
//                            PreferencesUtils.putBoolean(mContext,"checked",false);
                            PreferencesUtils.putString(getActivity(), "token_id",null);
                            EventBus.getDefault().post(new MessageEvent("退出"));
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                        }

                    }
                });
                return false;
            }
        });


        listPreference3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d("asdas","afad");
                Beta.checkUpgrade();
                return false;
            }
        });

    }
}
