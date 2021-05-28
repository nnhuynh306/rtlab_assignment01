package vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.View;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

import vn.edu.hcmus.fit.mssv18127014_18127208.xmlfilemanager.R;

public class XMLImportedDetailFragment extends Fragment {

    private TextView instance_id_text_view;
    private TextView content_text_view;

    private int page;
    private static final int bufferSize = 32000;

    String passedInstanceID;
    String passedFilePath;

    NavController navController;

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

        setupVariables(view);

        getViews(view);
        setupViewsContent(view);
        setupButton(view);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void setupVariables(View view) {
        this.navController = Navigation.findNavController(view);

        this.passedInstanceID = XMLImportedDetailFragmentArgs.fromBundle(getArguments()).getInstanceID();
        this.passedFilePath = XMLImportedDetailFragmentArgs.fromBundle(getArguments()).getFilePath();

        this.page = 0;
    }


    private void getViews(View root) {
        this.instance_id_text_view = root.findViewById(R.id.title);
        this.content_text_view = root.findViewById(R.id.content);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupViewsContent(View root) {

        this.instance_id_text_view.setText(passedInstanceID);
        setContentTextView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupButton(View root) {
        root.findViewById(R.id.back_btn).setOnClickListener(v -> {
            Navigation.findNavController(root).popBackStack();
        });

        root.findViewById(R.id.backward_page_btn).setOnClickListener(v -> {
            page--;
            if (page < 0) {
                page ++;
            } else {
                setContentTextView();
            }
        });

        root.findViewById(R.id.forward_page_btn).setOnClickListener(v -> {
            page++;
            setContentTextView();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setContentTextView() {
        try {
            FileInputStream xmlFileInputStream = new FileInputStream(passedFilePath);

            byte[] buffer = new byte[bufferSize];
            int byteRead = 0;

            long byteSkip = xmlFileInputStream.skip(bufferSize * page);
            byteRead = xmlFileInputStream.read(buffer);

            if (byteRead <= 0) {
                page--;
                return;
            } else {
                String stringToAppend = new String( Arrays.copyOfRange(buffer, 0, byteRead), StandardCharsets.UTF_8);
                content_text_view.setText(stringToAppend);
            }

            xmlFileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            content_text_view.setText(R.string.read_xml_error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class LoadXmlToTextViewAsyncTask extends AsyncTask<Void, Void, Void> {
        private String filePath;
        private TextView textView;

        public LoadXmlToTextViewAsyncTask(String filePath, TextView textView) {
            this.filePath = filePath;
            this.textView = textView;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                FileInputStream xmlFileInputStream = new FileInputStream(filePath);

                int bufferSize = 8192;
                byte[] buffer = new byte[bufferSize];
                int byteRead = 0;

                while ((byteRead = xmlFileInputStream.read(buffer)) > 0) {
                    String stringToAppend = Base64.getEncoder().encodeToString(
                            Arrays.copyOfRange(buffer, 0, byteRead));
                    textView.append(stringToAppend);
                }
            } catch (IOException e) {
                e.printStackTrace();
                textView.setText(R.string.read_xml_error);
            }
            return null;
        }
    }
}
