package com.kingDev.Instagram_video_downloader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;

import config.admob;

/**
 * Created by mac on 26/02/16.
 */
public class SettingsFragment extends PreferenceFragment {
    int REQUEST_DIRECTORY = 198;
    Preference rateus , sharethisapp , moreapp , path , contactme, consent, privacy;
    private String mBaseFolderPath;
    ConsentSDK consentSDK;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference);

        consentSDK = new ConsentSDK.Builder(getActivity())
                .addPrivacyPolicy(admob.privacy_policy_url) // Add your privacy policy urlhttp://tiny.cc/panorama-studio-privacy
                .addPublisherId(admob.publisherID) // Add your admob publisher id
                .build();


        rateus = (Preference) findPreference("rateus");
        sharethisapp = (Preference) findPreference("sharethisapp");
        // moreapp = (Preference) findPreference("moreapp");
        path = (Preference) findPreference("path");
        contactme = (Preference) findPreference("contactme");
        consent = (Preference) findPreference("consent");
        privacy = (Preference) findPreference("privacy");
        consent.setEnabled(false);
        consentSDK.checkConsent(new ConsentSDK.ConsentCallback() {
            @Override
            public void onResult(boolean isRequestLocationInEeaOrUnknown) {
                // Your code
                if(isRequestLocationInEeaOrUnknown) {
                    consent.setEnabled(true);
                    consent.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                        public boolean onPreferenceClick(Preference preference) {
                            consentSDK.checkConsent2(new ConsentSDK.ConsentCallback() {
                                @Override
                                public void onResult(boolean isRequestLocationInEeaOrUnknown) {
                                    // Your code
                                }
                            });
                            return true;
                        }
                    });
                }else {
                    consent.setEnabled(false);
                }
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);

        String folderName = getResources().getString(R.string.foldername);

            if(!preferences.getString("path", "DEFAULT").equals("DEFAULT")){

                mBaseFolderPath = preferences
                        .getString("path", "DEFAULT");

            }else{

                mBaseFolderPath = android.os.Environment
                        .getExternalStorageDirectory()
                        + File.separator
                        + folderName + File.separator;
            }

            path.setSummary(mBaseFolderPath);

        SwitchPreference notification = (SwitchPreference)findPreference(getResources().getString(R.string.pref_notification));
        SwitchPreference disableNotificationsDownloader = (SwitchPreference)findPreference(getResources().getString(R.string.pref_hidenotification));

        notification.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object isdataTrafficEnabled) {

                boolean isDataTrafficOn = ((Boolean) isdataTrafficEnabled).booleanValue();

                SharedPreferences sharedpref = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sharedpref.edit();
                e.putBoolean(getResources().getString(R.string.pref_notification), isDataTrafficOn);
                e.commit();

                return true;
            }
        });

        disableNotificationsDownloader.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object isdataTrafficEnabled) {

                boolean isDataTrafficOn = ((Boolean) isdataTrafficEnabled).booleanValue();

                SharedPreferences sharedpref = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sharedpref.edit();
                e.putBoolean(getResources().getString(R.string.pref_hidenotification), isDataTrafficOn);
                e.commit();

                return true;
            }
        });

        path.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                final Intent chooserIntent = new Intent(
                        getActivity(),
                        DirectoryChooserActivity.class);

                final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                        .newDirectoryName(getResources().getString(R.string.foldername))
                        .allowReadOnlyDirectory(true)
                        .allowNewDirectoryNameModification(true)
                        .build();

                chooserIntent.putExtra(
                        DirectoryChooserActivity.EXTRA_CONFIG,
                        config);

                startActivityForResult(chooserIntent, REQUEST_DIRECTORY);

                return true;
            }
        });

        rateus.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }

                return true;
            }
        });



        sharethisapp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                func.share.mShareText("Hey my friend check out this app\n https://play.google.com/store/apps/details?id="+ getActivity().getPackageName() + " \n", getActivity());
                return true;

            }
        });

        privacy.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(admob.privacy_policy_url)));
                } catch (android.content.ActivityNotFoundException anfe) {

                }
                return true;

            }
        });

        /*
        moreapp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:mobicodepro")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=pub:mobicodepro")));
                }
                return true;

            }
        }); */

        contactme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:ganjmeng@gmail.com"+
                            "?subject=" + Uri.encode(getActivity().getPackageName())));
                    startActivity(intent);
                }catch(Exception e){
                }
                return true;

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DIRECTORY) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {

                SharedPreferences sharedpref = getActivity().getSharedPreferences(getResources().getString(R.string.pref_appname), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                editor.putString("path", data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR));
                editor.commit();
                path.setSummary(data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR));

                // using this method to update my recycler
//                Intent jp = new Intent(getActivity() , MainActivity.class);
//                startActivityForResult(jp, 2901);

            } else {
                // Nothing selected
            }
        }
    }

}