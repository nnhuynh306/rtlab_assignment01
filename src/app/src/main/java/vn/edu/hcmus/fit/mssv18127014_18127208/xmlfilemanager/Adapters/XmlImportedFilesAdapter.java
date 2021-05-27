package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.View.XMLImportedListFragmentDirections;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel.XmlFileViewModel;

public class XmlImportedFilesAdapter extends RecyclerView.Adapter<XmlImportedFilesAdapter.ViewHolder> {

    private static final String TAG = "XmlFilesAdapter";
    private List<XMLFile> xmlFiles = new ArrayList<>();
    private Context context;
    private Handler importHandler = null;
    private XmlFileViewModel xmlFileViewModel;
    private NavController navController;

    public XmlImportedFilesAdapter(Context context) {
        this.context = context;
    }

    public void setImportHandler(Handler importHandler) {
        this.importHandler = importHandler;
    }


    public void setXmlFileViewModel(XmlFileViewModel xmlFileViewModel) {
        this.xmlFileViewModel = xmlFileViewModel;
    }

    public void setXmlFiles(List<XMLFile> xmlFiles) {
        this.xmlFiles = xmlFiles;
        notifyDataSetChanged();
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    @Override
    public XmlImportedFilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_xml_imported_list_item, null
        ));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.xml_file_instance_id_text_view.setText(this.xmlFiles.get(position).getInstanceID());
    }

    @Override
    public int getItemCount() {
        return xmlFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView xml_file_instance_id_text_view;
        ImageButton import_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            this.xml_file_instance_id_text_view = itemView.findViewById(R.id.xml_file_instance_id);
            this.import_btn = itemView.findViewById(R.id.view_detail_btn);

            this.import_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XMLFile xmlFile = xmlFiles.get(getAdapterPosition());
                    NavDirections action = XMLImportedListFragmentDirections
                            .actionXMLImportedListFragmentToXMLImportedDetailFragment(
                                    xmlFile.getInstanceID(), xmlFile.getFilePath()
                            );
                    navController.navigate(action);
                }
            });
        }
    }
}
