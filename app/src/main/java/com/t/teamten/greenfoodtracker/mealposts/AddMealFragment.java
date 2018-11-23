package com.t.teamten.greenfoodtracker.mealposts;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.t.teamten.greenfoodtracker.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.t.teamten.greenfoodtracker.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import firebaseuser.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMealFragment extends DialogFragment {
    private TextView leaveText;
    private EditText mealNameText;
    private EditText restauantNameText;
    private EditText restaurantLocationText;
    private EditText mealDescriptionText;

    private ImageView mealImage;
    private Spinner spinner;
    private Spinner locationSpinner;
    private Button postButton;

    private MealPost post;
    private String mealName;
    private String mealProtein;
    private String restaurantName;
    private String restaurantLocation;
    private String mealDescription;
    private String userId;
    private String postId;
    private String imageURL;

    static final int REQUEST_CAMERA = 1;
    static final int SELECT_FILE = 2;
    private String userChoosenTask;
    private String mCameraFileName;
    private Uri outuri;
    private FirebaseStorage storage;
    private StorageReference mStorageReference;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public AddMealFragment() {
        // Required empty public constructor
    }

    public static AddMealFragment newInstanceFragment(String title) {
        AddMealFragment fragment = new AddMealFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_meal, container, false);
    }

    @Override
    public void onViewCreated(View view, @NonNull Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("mealposts");
        postId = reference.push().getKey();

        mealNameText = (EditText) view.findViewById(R.id.mealNameText);
        restauantNameText = (EditText) view.findViewById(R.id.restaurantNameText);
        //restaurantLocationText = (EditText) view.findViewById(R.id.restaurantLocationText);
        locationSpinner = (Spinner) view.findViewById(R.id.locationSpinner);
        mealDescriptionText = (EditText) view.findViewById(R.id.mealDescriptionText);
        spinner = (Spinner) view.findViewById(R.id.proteinSpinner);
        ///////////////////////////////////////
        ///////////////////////////////////////
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        storage = FirebaseStorage.getInstance();
        mStorageReference = storage.getReference();

        imageURL = "";
        mealImage = (ImageView) view.findViewById(R.id.mealImage);
        mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

            }
        });
        ///////////////////////////////
        //////////////////////////////
        leaveText = (TextView) view.findViewById(R.id.leaveText);
        leaveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        postButton = (Button) view.findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealName = mealNameText.getText().toString();
                restaurantName = restauantNameText.getText().toString();
                restaurantLocation = locationSpinner.getSelectedItem().toString();
                mealDescription = mealDescriptionText.getText().toString();
                mealProtein = spinner.getSelectedItem().toString();

                post = new MealPost(mealName, mealProtein, restaurantName,
                        restaurantLocation, imageURL, mealDescription, userId, postId);
                storeMealPostToFirebase(post, postId);
                getDialog().dismiss();
            }
        });

        String title = getArguments().getString("Post", "Enter Post");
        getDialog().setTitle(title);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void storeMealPostToFirebase(MealPost mealPost, String id) {
        reference.child(id).setValue(mealPost);
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);


        Date date = new Date();
        DateFormat df = new SimpleDateFormat("-mm-ss");

        String newPicFile = "Bild" + df.format(date) + ".jpg";
        String outPath = "/sdcard/" + newPicFile;
        File outFile = new File(outPath);

        mCameraFileName = outFile.toString();
        outuri = Uri.fromFile(outFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);

        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Uri filePath = data.getData();
                updateURL(filePath);
            }
            else if (requestCode == REQUEST_CAMERA) {
                updateURL(outuri);
            }
        }
    }

    private void updateURL(Uri filePath) {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = mStorageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //get the uri from firestore
                                    //store it to firebase
                                    //TextView test = (TextView)findViewById(R.id.testingURL);
                                    //test.setText(uri.toString());
                                    //display image from url
                                    imageURL = uri.toString();
                                    Picasso.get().load(uri).into(mealImage);
                                }
                            });
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }



}
