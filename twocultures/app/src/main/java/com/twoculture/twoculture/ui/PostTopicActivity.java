package com.twoculture.twoculture.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.UploadImageResponse;
import com.twoculture.twoculture.network.RxClient;
import com.twoculture.twoculture.tools.AppConstants;
import com.twoculture.twoculture.tools.FileUtils;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PostTopicActivity extends BaseActivity implements View.OnClickListener {

    private final static String TAG = PostTopicActivity.class.getSimpleName();
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1000;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;
    private final int ACTIVITY_RESULT_REQUEST_CODE_CAMERA = 2000;
    private final int ACTIVITY_RESULT_REQUEST_CODE_GALLERY = 2001;

    @BindView(R.id.btn_add_picture)
    ImageView mImageAddPicture;

    @BindView(R.id.rv_pictures)
    RecyclerView mRecyclerViewPictures;

    @BindView(R.id.et_content)
    EditText mEditContent;

    @BindView(R.id.et_text_header)
    EditText mEditHeader;

    private AlertDialog mDialog;
    private String mImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_post_topic;
    }

    public void initView() {
        mImageAddPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_picture:
                choosePictrueSource();
                break;
        }
    }


    public void choosePictrueSource() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_choose_picture, (ViewGroup) this.getWindow().getDecorView(), false);
        TextView tv_change_gravtar_photos = (TextView) view.findViewById(R.id.tv_change_gravtar_photos);
        TextView tv_change_gravtar_camera = (TextView) view.findViewById(R.id.tv_change_gravtar_camera);
        tv_change_gravtar_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(PostTopicActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PostTopicActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                } else {
                    startGallery();
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }

            }
        });
        tv_change_gravtar_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(PostTopicActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PostTopicActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                } else {
                    startCamera();
                }
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });

        builder.setView(view);
        mDialog = builder.show();
        mDialog.setCanceledOnTouchOutside(true);
    }


    /**
     * start system camera to capture images
     */
    private void startCamera() {
        String status = Environment.getExternalStorageState();
        File imagePath = new File(getApplicationContext().getFilesDir(), "images");
        File file = new File(imagePath,
                UUID.randomUUID().toString() + ".jpg");
        mImagePath = file.getAbsolutePath();
        if (status.equals("mounted")) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                if (!file.getParentFile().isDirectory()) {
                    Toast.makeText(this, "create on disk failed", Toast.LENGTH_SHORT).show();
                    this.finish();
                }
            }
            if (file.exists()) {
                file.delete();
            }
            Uri fileUri = FileProvider.getUriForFile(this, "au.com.woolworths.android.healthcheck.fileprovider", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            this.startActivityForResult(intent, ACTIVITY_RESULT_REQUEST_CODE_CAMERA);
        } else {
            Toast.makeText(this, "there is no storage for images", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    /**
     * start system gallery to choose a picture
     */
    private void startGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ACTIVITY_RESULT_REQUEST_CODE_GALLERY);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startGallery();
                } else {
                    Toast.makeText(this, R.string.warning_after_garllery_permission_denied, Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    Toast.makeText(this, "you have to agree the permission to use the camera", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case ACTIVITY_RESULT_REQUEST_CODE_CAMERA:
                if (TextUtils.isEmpty(mImagePath)) {
                    return;
                }
                File file = new File(mImagePath);
                if (!file.exists()) {
                    return;
                }
                compressImageAndUploadImage(file);
                break;
            case ACTIVITY_RESULT_REQUEST_CODE_GALLERY:
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(
                        selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                compressImageAndUploadImage(new File(filePath));
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void compressImageAndUploadImage(File file) {
        //mProgressBar.show();
        long maxSize = 600 * 1024;
        File target = new File(FileUtils.getExternalFilesDir(getApplicationContext()),
                UUID.randomUUID().toString() + ".jpg");
        FileUtils.compressFile(file, maxSize, 1020, 1980, target);
        uploadImage(target);
    }


    private void uploadImage(final File file) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), "desc here");
        RxClient.getInstance().getTopicService().uploadAttachment(AppConstants.TOKEN,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UploadImageResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(UploadImageResponse uploadImageResponse) {
                    Log.d(TAG,uploadImageResponse.topic_header_url);
                    }
                });

    }
}
