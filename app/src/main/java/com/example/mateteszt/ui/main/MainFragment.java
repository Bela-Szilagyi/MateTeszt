package com.example.mateteszt.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateteszt.MyAdapter;
import com.example.mateteszt.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        EditText editText = view.findViewById(R.id.editText);
        TextView textView = view.findViewById(R.id.textView);
        mViewModel.text.observe(getViewLifecycleOwner(), s -> textView.setText(s));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mViewModel.changeText(s.toString());
            }
        });

        ProgressBar progressBar = view.findViewById(R.id.progress);
        mViewModel.progress.observe(getViewLifecycleOwner(), show -> {
            if(show) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        view.findViewById(R.id.loadButton).setOnClickListener( v -> {
            mViewModel.load();
        });

       RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        mViewModel.loadTexts.observe(getViewLifecycleOwner(), texts -> {
            if(!texts.isEmpty()) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setData(texts);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
        });

        return view;
    }

}