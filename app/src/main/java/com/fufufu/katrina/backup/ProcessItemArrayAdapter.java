package com.fufufu.katrina.backup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ProcessItemArrayAdapter extends ArrayAdapter<ProcessItem> {
    private final Context context;
    private int defaultColor;
    private final List<ProcessItem> values;

    public ProcessItemArrayAdapter(Context context, List<ProcessItem> list) {
        super(context, C0978R.layout.processitem_row, list);
        this.context = context;
        this.values = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(C0978R.layout.processitem_row, viewGroup, false);
        final TextView textView = (TextView) viewInflate.findViewById(C0978R.id.firstLine);
        final TextView textView2 = (TextView) viewInflate.findViewById(C0978R.id.secondLine);
        final CheckBox checkBox = (CheckBox) viewInflate.findViewById(C0978R.id.check);
        this.defaultColor = textView.getTextColors().getDefaultColor();
        final ProcessItem processItem = this.values.get(i);
        textView.setText(processItem.applicationName);
        textView2.setText(processItem.packageName);
        checkBox.setChecked(processItem.check.booleanValue());
        SetFontWeight(textView, textView2, checkBox);
        ((ImageView) viewInflate.findViewById(C0978R.id.icon)).setImageDrawable(processItem.icon);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {             @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                processItem.check = Boolean.valueOf(z);
                ProcessItemArrayAdapter.this.SetFontWeight(textView, textView2, checkBox);
                if (processItem.saveChanges.booleanValue()) {
                    processItem.save();
                }
            }
        });
        viewInflate.setOnClickListener(new View.OnClickListener() {             @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                processItem.check = Boolean.valueOf(!processItem.check.booleanValue());
                checkBox.setChecked(processItem.check.booleanValue());
                ProcessItemArrayAdapter.this.SetFontWeight(textView, textView2, checkBox);
                if (processItem.saveChanges.booleanValue()) {
                    processItem.save();
                }
            }
        });
        return viewInflate;
    }

    public void SetFontWeight(TextView textView, TextView textView2, CheckBox checkBox) {
        if (checkBox.isChecked()) {
            textView.setTextColor(this.defaultColor);
            textView2.setTextColor(this.defaultColor);
        } else {
            textView.setTextColor(this.defaultColor);
            textView2.setTextColor(this.defaultColor);
        }
    }
}