package com.iai.nurulimam.Activity.Parent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.Presenter.ParentPresenter.ParentPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.ParentView.ParentView;

public class ParentActivity extends AppCompatActivity implements ParentView {
    private ParentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        Preferences preferences = new Preferences(this);
        presenter = new ParentPresenter(preferences, this, this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogout:
                presenter.logout();
                break;
        }
    }
}