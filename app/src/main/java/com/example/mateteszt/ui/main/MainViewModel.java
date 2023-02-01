package com.example.mateteszt.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;


public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<String> _text = new MutableLiveData<>("");
    LiveData<String> text = _text;

    MutableLiveData<Boolean> _progress = new MutableLiveData<>(false);
    LiveData<Boolean> progress = _progress;

    MutableLiveData<List<String>> _loadTexts = new MutableLiveData<>();
    LiveData<List<String>> loadTexts = _loadTexts;

    public void changeText(String s) {
        Log.d("viewmodel onChange", s);
        _text.setValue(s);
    }

    public void load() {
        new Thread(() -> {
            _progress.postValue(true);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            _progress.postValue(false);
            _loadTexts.postValue(Arrays.asList("cica", "kutya", "malac"));
        }).start();
    }
}