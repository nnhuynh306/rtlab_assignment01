package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.View;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import pub.devrel.easypermissions.EasyPermissions;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters.XmlFilesAdapter;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.RequestCode;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel.XmlFileViewModel;

public class XmlListFragment extends Fragment{

    private XmlFileViewModel xml_file_view_model;

    private RecyclerView xml_files_recycler_view;
    private XmlFilesAdapter xml_files_adapter;
    private ProgressBar importProgressBar;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xml_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.xml_file_view_model = new ViewModelProvider(requireActivity()).get(XmlFileViewModel.class);

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(requireActivity(), perms)) {
            getViews(view);
            setupViewsContent();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.request_permission)
                    , RequestCode.READ_EXTERNAL_STORAGE, perms);
        }

    }

    private void getViews(View root) {
        this.xml_files_recycler_view = root.findViewById(R.id.xml_files_recycler_view);
        this.importProgressBar = root.findViewById(R.id.progress_bar);
    }

    private void setupViewsContent() {
        setupAdapter();

        this.xml_files_recycler_view.setAdapter(xml_files_adapter);
        this.xml_files_recycler_view.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupAdapter() {
        this.xml_files_adapter = new XmlFilesAdapter(requireContext(), new ImportHandler());
        this.xml_files_adapter.setFilesName(this.xml_file_view_model.getXmlFilesName(requireContext()));
        this.xml_files_adapter.setImportProgressBar(this.importProgressBar);
        this.xml_files_adapter.setImportHandler(new ImportHandler());
        this.xml_files_adapter.setXmlFileViewModel(this.xml_file_view_model);
    }

    private class ImportHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0: {
                    importProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), R.string.import_xml_fail, Toast.LENGTH_LONG).show();
                    break;
                } case 1: {
                    importProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), R.string.import_xml_success, Toast.LENGTH_LONG).show();
                    break;
                } default: {
                    importProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), R.string.import_xml_fail, Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
    }

}
