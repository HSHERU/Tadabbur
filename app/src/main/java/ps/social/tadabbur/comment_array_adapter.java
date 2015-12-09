package ps.social.tadabbur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abdulrahman on 09-12-15.
 */
public class comment_array_adapter extends ArrayAdapter<comment_object> {
    private final Context context;
    private final List<comment_object> values;

    public comment_array_adapter(Context context, List<comment_object> values) {
        super(context, R.layout.comment_list_template, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.comment_list_template, parent, false);
        TextView comment_textView = (TextView) rowView.findViewById(R.id.comment_list_textView);
        TextView date_textView = (TextView) rowView.findViewById(R.id.date_list_textView);
        TextView username_textView = (TextView) rowView.findViewById(R.id.username_list_textView);
        comment_textView.setText(values.get(position).comment);
        TextView ayah_number_textView = (TextView) rowView.findViewById(R.id.ayahNumber_list_textView);
        ayah_number_textView.setText(values.get(position).Ayah_number);
        //TextView Sourah_ID_textView = (TextView) rowView.findViewById(R.id.Sourah_ID_list_textView);
        //Sourah_ID_textView.setText(values.get(position).SourahID);
        date_textView.setText(values.get(position).date);
        username_textView.setText(values.get(position).username);
        return rowView;
    }
}