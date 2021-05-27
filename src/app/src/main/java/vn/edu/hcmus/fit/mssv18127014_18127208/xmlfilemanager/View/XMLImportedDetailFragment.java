package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.View;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;

public class XMLImportedDetailFragment extends Fragment {

    private TextView instance_id_text_view;
    private TextView content_text_view;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xml_imported_detail, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);

        getViews(view);
        setupViewsContent(view);
    }

    private void getViews(View root) {
        this.instance_id_text_view = root.findViewById(R.id.title);
        this.content_text_view = root.findViewById(R.id.content);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupViewsContent(View root) {
        String passedInstanceID = XMLImportedDetailFragmentArgs.fromBundle(getArguments()).getInstanceID();
        String passedFilePath = XMLImportedDetailFragmentArgs.fromBundle(getArguments()).getFilePath();

        String content = null;
        try {
            content = Files.lines(Paths.get(passedFilePath)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            content = getString(R.string.read_xml_error);
        }
        this.instance_id_text_view.setText(passedInstanceID);
        this.content_text_view.setText(content);

        root.findViewById(R.id.back_btn).setOnClickListener(v -> {
            Navigation.findNavController(root).popBackStack();
        });
    }
}
