package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;

public class XmlFilesAdapter extends RecyclerView.Adapter<XmlFilesAdapter.ViewHolder> {

    private static final String TAG = "XmlFilesAdapter";
    private ArrayList<String> filesName;
    private Context context;

    public XmlFilesAdapter(Context context) {
        this.context = context;
    }

    public void setFilesName(ArrayList<String> filesName) {
        this.filesName = filesName;
        notifyDataSetChanged();
    }

    @Override
    public XmlFilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_xml_list_list_item, null
        ));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.xml_file_name_text_view.setText(this.filesName.get(position));
    }

    @Override
    public int getItemCount() {
        return filesName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xml_file_name_text_view;

        public ViewHolder(View itemView) {
            super(itemView);
            xml_file_name_text_view = itemView.findViewById(R.id.xml_file_name);
        }
    }
}
