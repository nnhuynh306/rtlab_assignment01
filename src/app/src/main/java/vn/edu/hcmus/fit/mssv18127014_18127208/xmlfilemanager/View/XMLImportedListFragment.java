package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.View;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters.XmlFilesAdapter;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Adapters.XmlImportedFilesAdapter;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.Models.XMLFile;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;
import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.ViewModels.XMLFileViewModel.XmlFileViewModel;

public class XMLImportedListFragment extends Fragment {

    private static final String TAG = "XMLImportedListFragment";
    private XmlFileViewModel xml_file_view_model;

    private RecyclerView xml_files_recycler_view;
    private XmlImportedFilesAdapter xml_files_adapter;
    private ProgressBar importProgressBar;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xml_imported_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bnv = requireActivity().findViewById(R.id.nav_view);
        bnv.setVisibility(View.VISIBLE);

        this.xml_file_view_model = new ViewModelProvider(requireActivity()).get(XmlFileViewModel.class);

        getViews(view);
        setupViewsContent(view);

    }

    private void getViews(View root) {
        this.xml_files_recycler_view = root.findViewById(R.id.xml_files_recycler_view);
        this.importProgressBar = root.findViewById(R.id.progress_bar);
    }

    private void setupViewsContent(View view) {
//        requireActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
        setupAdapter(view);

        this.xml_files_recycler_view.setAdapter(xml_files_adapter);
        this.xml_files_recycler_view.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void setupAdapter(View view) {
        this.xml_files_adapter = new XmlImportedFilesAdapter(requireContext());
        this.xml_files_adapter.setXmlFileViewModel(this.xml_file_view_model);
        this.xml_files_adapter.setNavController(Navigation.findNavController(view));

        this.xml_file_view_model.getXmlFileListLiveData().observe(getViewLifecycleOwner(), new Observer<List<XMLFile>>() {
            @Override
            public void onChanged(List<XMLFile> xmlFiles) {
                xml_files_adapter.setXmlFiles(xmlFiles);
            }
        });
    }

}
