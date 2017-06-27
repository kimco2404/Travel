package net.pmsv.diadiemangiang;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import net.pmsv.diadiemangiang.R;


import static net.pmsv.diadiemangiang.R.attr.title;

/**
 * Created by KimCo on 6/14/2017.
 */

public class AddnewDiaDiem extends Activity {

    private static final int SELECT_PICTURE = 1;

    private ImageView img;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew_diadiem);

        img = (ImageView)findViewById(R.id.imageBrowse);
        ((Button) findViewById(R.id.button1))
                .setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        Uri uri = Uri
                                .parse("C:\\Users\\KimCo\\Downloads\\DiaDiemCaoBang\\app\\src\\main\\res\\drawable-xxhdpi\n" +
                                         i);
                        intent.putExtra(Intent.EXTRA_STREAM, uri);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
                    }
                });
    }
String i;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                img.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}