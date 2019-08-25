package com.example.debtsnote.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<String> liveUserId = new MutableLiveData<String>();
    private final MutableLiveData<Boolean> menuButtonPressed = new MutableLiveData<Boolean>();

    private boolean isNewUser, inputCorrect;


    public boolean isInputCorrect() {
        return inputCorrect;
    }

    public void setInputCorrect(boolean inputCorrect) {
        this.inputCorrect = inputCorrect;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean userNew) {
        isNewUser = userNew;
    }

    public LiveData<Boolean> getMenuButtonPressed() {
        return menuButtonPressed;
    }

    public void setMenuButtonPressed(Boolean menuButtonPressed) {
        this.menuButtonPressed.setValue(menuButtonPressed);
    }

    public LiveData<String> getUserId() {
        return liveUserId;
    }

    public void setUserId(String userId) {
        isNewUser = false;
        this.liveUserId.setValue(userId);
    }

    public void setNewUserId() {
        isNewUser = true;
        this.liveUserId.setValue(UUID.randomUUID().toString());
    }
}
