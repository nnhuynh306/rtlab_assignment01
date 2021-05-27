package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel.XmlFileViewModel;

public class XmlFilesAdapter extends RecyclerView.Adapter<XmlFilesAdapter.ViewHolder> {

    private static final String TAG = "XmlFilesAdapter";
    private ArrayList<String> filesName;
    private Context context;
    private Handler importHandler = null;
    private ProgressBar importProgressBar = null;
    private XmlFileViewModel xmlFileViewModel;

    public XmlFilesAdapter(Context context, Handler importHandler) {
        this.context = context;
    }

    public void setImportHandler(Handler importHandler) {
        this.importHandler = importHandler;
    }

    public void setImportProgressBar(ProgressBar importProgressBar) {
        this.importProgressBar = importProgressBar;
    }

    public void setXmlFileViewModel(XmlFileViewModel xmlFileViewModel) {
        this.xmlFileViewModel = xmlFileViewModel;
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
        ImageButton import_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.xml_file_name_text_view = itemView.findViewById(R.id.xml_file_name);
            this.import_btn = itemView.findViewById(R.id.import_btn);

            this.import_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    xmlFileViewModel.importXMLFile(context, filesName.get(getAdapterPosition()),
                            importHandler, importProgressBar);

                    importProgressBar.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
