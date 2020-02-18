package com.kingDev.Instagram_video_downloader;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import func.reg;

public class dialog_rename extends DialogFragment {
    String extension = "" , filename = "" , name = "", split = "";

    public dialog_rename() {
        // Empty constructor required for DialogFragment
    }

    public static dialog_rename newInstance(String title) {
        dialog_rename frag = new dialog_rename();
        Bundle args = new Bundle();
        args.putString("filename", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        filename = getArguments().getString("filename");
        split = reg.getBack(filename, "([^/]+$)");

        try{
            extension = split.substring(split.lastIndexOf(".") + 1);
            name = split.substring(0 , split.lastIndexOf("."));
        }catch(Exception e){
            name = "";
            extension = "emptyorerror";
            Toast.makeText(getActivity(), "There is Issue to change file name. try again!" , Toast.LENGTH_LONG).show();
        }

        input.setText(name);

        String title = "Change Name :";
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setView(input);
        alertDialogBuilder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                if (!extension.equals("emptyorerror") && !name.equals(input.getText().toString())) {

                    try {

                        File from = new File(filename);
                        File to = new File(filename.replaceAll(split,"") + input.getText().toString() + "." + extension);
                        from.renameTo(to);

                        recyclerview j = ((recyclerview) getActivity().getSupportFragmentManager()
                                .findFragmentByTag("android:switcher:" + R.id.viewpager + ":1"));
                        j.loadMedia();

                        Toast.makeText(getActivity(), "Renamed Successful.", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {

                        Toast.makeText(getActivity(), "There is Issue to change file name. try again!" , Toast.LENGTH_LONG).show();

                    }


                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
}
