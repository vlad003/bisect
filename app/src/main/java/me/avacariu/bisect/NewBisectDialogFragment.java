package me.avacariu.bisect;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;


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

        final DbHelper mDbHelper = new DbHelper(getActivity());

        final EditText nameField = (EditText) view.findViewById(R.id.dialog_name);
        final EditText startField = (EditText) view.findViewById(R.id.dialog_start);
        final EditText endField = (EditText) view.findViewById(R.id.dialog_end);
        final RadioButton bisectRadio = (RadioButton) view.findViewById(R.id.dialog_bisect);

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
                            // Gets the data repository in write mode
                            SQLiteDatabase db = mDbHelper.getWritableDatabase();

                            ContentValues values = new ContentValues();
                            values.put(DbContract.DbEntry.COLUMN_NAME_NAME, nameField.getText().toString());
                            values.put(DbContract.DbEntry.COLUMN_NAME_START, Integer.parseInt(startField.getText().toString()));
                            values.put(DbContract.DbEntry.COLUMN_NAME_END, Integer.parseInt(endField.getText().toString()));

                            String type;
                            if (bisectRadio.isChecked()) {
                                type = DbContract.DbEntry.COLUMN_TYPE_BISECT;
                            } else {
                                type = DbContract.DbEntry.COLUMN_TYPE_BIN_SEARCH;
                            }

                            values.put(DbContract.DbEntry.COLUMN_NAME_TYPE, type);

                            long newRowId;
                            newRowId = db.insert(DbContract.DbEntry.TABLE_NAME, null, values);

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
