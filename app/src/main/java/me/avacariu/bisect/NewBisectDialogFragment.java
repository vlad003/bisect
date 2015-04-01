package me.avacariu.bisect;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class NewBisectDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.new_dialog, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton(R.string.create, null)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewBisectDialogFragment.this.getDialog().cancel();
                    }
                });


        Dialog dialog = builder.create();

        final EditText nameField = (EditText) view.findViewById(R.id.dialog_name);
        final EditText startField = (EditText) view.findViewById(R.id.dialog_start);
        final EditText endField = (EditText) view.findViewById(R.id.dialog_end);
        final RadioButton bisectRadio = (RadioButton) view.findViewById(R.id.dialog_bisect);
        final RadioButton binSearchRadio = (RadioButton) view.findViewById(R.id.dialog_binsearch);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final AlertDialog d = (AlertDialog) dialog;
                d.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

                    private boolean isEmpty(EditText etText) {
                        return etText.getText().toString().trim().length() == 0;
                    }

                    @Override
                    public void onClick(View v) {
                        int emptyFields = 0;

                        if (isEmpty(nameField)) {
                            nameField.setHintTextColor(Color.RED);
                            emptyFields++;
                        }

                        if (isEmpty(startField)) {
                            startField.setHintTextColor(Color.RED);
                            emptyFields++;
                        }

                        if (isEmpty(endField)) {
                            endField.setHintTextColor(Color.RED);
                            emptyFields++;
                        }

                        if (emptyFields == 0) {
                            // TODO save info here
                            d.dismiss();
                        }
                        // else dialog stays open
                    }
                });
            }
        });

        return dialog;
    }

}
