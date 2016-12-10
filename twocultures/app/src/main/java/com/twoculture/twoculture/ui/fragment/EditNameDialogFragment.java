package com.twoculture.twoculture.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.ui.interfaces.UserNameInputListener;

/**
 * Created by rainbow on 16/12/10.
 */

public class EditNameDialogFragment extends DialogFragment {


    private EditText mUserName;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_username_dialog, null);
        mUserName = (EditText) view.findViewById(R.id.et_username);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (getActivity() instanceof UserNameInputListener) {
                                    UserNameInputListener listener = (UserNameInputListener) getActivity();
                                    listener.setUserName(mUserName.getText().toString());
                                }

                            }
                        }).setNegativeButton("Cancel", null);
        return builder.create();
    }
}
