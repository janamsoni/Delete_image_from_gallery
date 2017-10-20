package trick007.com.uvgal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                String realPath;
                try {
                    realPath=PathUtil.getPath(MainActivity.this, selectedImageUri);
                    Toast.makeText(this,realPath,Toast.LENGTH_LONG).show();
                    File file = new File(realPath);
                    Toast.makeText(this,realPath,Toast.LENGTH_LONG).show();
                    boolean deleted = file.delete();
                    if(deleted==true)
                    {
                        Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();

                    }else
                        {                        Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();


                        }



                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();

                }
            }
        }
    }


//                String t=getRealPathFromURI(selectedImageUri);
//                Toast.makeText(this,t,Toast.LENGTH_LONG).show();


}
