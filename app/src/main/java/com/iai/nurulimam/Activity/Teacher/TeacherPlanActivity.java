package com.iai.nurulimam.Activity.Teacher;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.iai.nurulimam.Presenter.TeacherPresenter.TeacherPlanPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.TeacherView.TeacherPlanView;

public class TeacherPlanActivity extends AppCompatActivity implements TeacherPlanView {
    private Spinner subjectSpinner, studentClassSpinner, semesterSpinner, meetingSpinner, timeAllocationSpinner;
    private TextInputLayout etYearsStudy,
            etBasicCompetency, etPurpose, etTheory,
            etToolsMedia, etLearningSource, etAssessment, etThink, etModel, etDate;
    private ProgressDialog progressDialog;
    private TeacherPlanPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_plan);
        view();
    }

    private void view() {

        // texInputLayout
        etYearsStudy = findViewById(R.id.etYearsStudy);
        etBasicCompetency = findViewById(R.id.etBasicCompetency);
        etPurpose = findViewById(R.id.etPurpose);
        etTheory = findViewById(R.id.etTheory);
        etToolsMedia = findViewById(R.id.etToolsMedia);
        etLearningSource = findViewById(R.id.etLearningSource);
        etAssessment = findViewById(R.id.etAssessment);
        etThink = findViewById(R.id.etThink);
        etModel = findViewById(R.id.etModel);
        etDate = findViewById(R.id.etDate);

        // progressDialog
        progressDialog = new ProgressDialog(this);

        // presenter
        presenter = new TeacherPlanPresenter(this, this);

        // spinner
        subjectSpinner = findViewById(R.id.subjectsSpinner);
        studentClassSpinner = findViewById(R.id.studentClassSpinner);
        semesterSpinner = findViewById(R.id.semesterSpinner);
        meetingSpinner = findViewById(R.id.meetingSpinner);
        timeAllocationSpinner = findViewById(R.id.timeAllocation);

        // ArrrayList

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
                int id = sharedPreferences.getInt("response", 0);
                int subject = subjectSpinner.getSelectedItemPosition();
                String semester = (String) semesterSpinner.getSelectedItem();
                int studentClass = studentClassSpinner.getSelectedItemPosition();
                String meeting = (String) meetingSpinner.getSelectedItem();
                String years = etYearsStudy.getEditText().getText().toString().trim();
                String timeAllocation = (String) timeAllocationSpinner.getSelectedItem();
                String basicCompetency = etBasicCompetency.getEditText().getText().toString().trim();
                String purpose = etPurpose.getEditText().getText().toString().trim();
                String think = etThink.getEditText().getText().toString().trim();
                String theory = etTheory.getEditText().getText().toString().trim();
                String toolsMedia = etToolsMedia.getEditText().getText().toString().trim();
                String learningSource = etLearningSource.getEditText().getText().toString().trim();
                String assessment = etAssessment.getEditText().getText().toString().trim();
                String model = etModel.getEditText().getText().toString().trim();
                String date = etDate.getEditText().getText().toString().trim();
                presenter.addData(id, subject, studentClass, semester, years, basicCompetency, purpose, theory, think, model, toolsMedia, learningSource, assessment, meeting, timeAllocation, date);

                break;
        }
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Menambahkan Data");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Gagal menambahkan data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success() {
        Toast.makeText(this, "Sukses menambahkan data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataValidation() {
        Toast.makeText(this, "Data Harus Diisi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError() {
        Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEditText() {
        etYearsStudy.getEditText().getText().clear();
        etBasicCompetency.getEditText().getText().clear();
        etPurpose.getEditText().getText().clear();
        etTheory.getEditText().getText().clear();
        etToolsMedia.getEditText().getText().clear();
        etLearningSource.getEditText().getText().clear();
        etAssessment.getEditText().getText().clear();
        etThink.getEditText().getText().clear();
        etModel.getEditText().getText().clear();
        etDate.getEditText().getText().clear();
    }
}