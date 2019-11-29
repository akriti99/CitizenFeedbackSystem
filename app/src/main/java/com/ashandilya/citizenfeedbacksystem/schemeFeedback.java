package com.ashandilya.citizenfeedbacksystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class schemeFeedback extends AppCompatActivity {

    ImageView imageViewUpvote, imageViewDownvote;
    TextView textViewVote;
    Spinner spinnerSelectCategory;
    EditText editTextFeedback;
    Feedback feedback;
    ImageView imageViewGallery, imageViewCamera;
    Button buttonSubmit;

    private ProgressDialog progressDialog;

    private StorageReference storageReference;

    private static final int CAMERA_REQUEST_CODE = 1;

    DatabaseReference databaseFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_feedback);

        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference();

        databaseFeedback = FirebaseDatabase.getInstance().getReference("Agriculture");

        imageViewUpvote = findViewById(R.id.upvote);
        imageViewDownvote = findViewById(R.id.downvote);
        textViewVote = findViewById(R.id.voteCount);
        editTextFeedback = findViewById(R.id.feedbackEdittext);
        imageViewGallery = findViewById(R.id.photoGallery);
        imageViewCamera = findViewById(R.id.photoCamera);
        spinnerSelectCategory = findViewById(R.id.selectCategory);

        feedback = new Feedback();

        buttonSubmit =  findViewById(R.id.feedbackSubmitBtn);

      buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeedback();

                Intent intent = new Intent(schemeFeedback.this, thankyou.class);
                startActivity(intent);
            }
        });

      imageViewCamera.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivityForResult(intent,CAMERA_REQUEST_CODE);

          }
      });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

            progressDialog.setMessage("Uploading Image . . . ");
            progressDialog.show();

            Uri uri = data.getData();

            StorageReference filepath = storageReference.child("Photos")/*.child(uri.getLastPathSegment())*/;

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(schemeFeedback.this,"Uploading Finished . . . ",Toast.LENGTH_LONG ).show();

                }
            });
        }
    }

    private void addFeedback() {
        String selectCategory = spinnerSelectCategory.getSelectedItem().toString();
        String feedbackText = editTextFeedback.getText().toString().trim();

        if( !TextUtils.isEmpty(feedbackText)){

            String id = databaseFeedback.push().getKey();

            Feedback feedback = new Feedback(feedbackText, selectCategory);

            databaseFeedback.child("Scheme1").setValue(feedback);

            Toast.makeText(this, "Feedback Submitted", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Type Feedback here:", Toast.LENGTH_LONG).show();
        }
    }
}
