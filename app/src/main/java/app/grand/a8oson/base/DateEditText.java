package app.grand.a8oson.base;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Calendar;

public class DateEditText extends CustomEditText {
    DatePickerDialog datePickerDialog;

    public DateEditText(Context context) {
        super(context);
        init();
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setOnClickListener(v -> showDateDialog());
        Log.i("init", "init: clicked");
    }

    private void showDateDialog() {
        if (datePickerDialog == null) {
            Calendar mcurrentTime = Calendar.getInstance();
            int year = mcurrentTime.get(Calendar.YEAR);
            int month = mcurrentTime.get(Calendar.MONTH) + 1;
            datePickerDialog
                    = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
                String selectedDate = i + "-" + (++i1) + "-" + i2;
                setText(selectedDate);
                Log.i("showDateDialog", "showDateDialog: " + selectedDate);
            }, year, month, 0);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        datePickerDialog.show();

    }

    public DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }
}
