package com.example.readimagemediacontentquery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.readimagemediacontentquery.model.Image;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Image> imageList=new ArrayList<Image>();
    private Integer READ_CODE=100;
    ImageView imageView;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.gird);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
            loadImgae();
        else
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},READ_CODE);
    }

    class ImageAdapter extends BaseAdapter {
        public ImageAdapter(List<Image> imageList1) {
            this.imageList1 = imageList1;
        }

        List<Image> imageList1=new ArrayList<Image>();

        @Override
        public int getCount() {
            return imageList1.size();
        }

        @Override
        public Object getItem(int position) {
            return imageList1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return Long.valueOf(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view= getLayoutInflater().inflate(R.layout.row_item,null,false);
            Image image=imageList1.get(position);
            imageView =(ImageView) view.findViewById(R.id.imageView);
            imageView.setImageURI(Uri.parse(image.getUrl()));
            return view;
        }
    }
     void loadImgae(){
        Uri uri= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cur=getContentResolver().query(uri,null,null,null,null,null);
        if(cur!=null){
            while(cur.moveToNext()){
                String url=cur.getString(cur.getColumnIndex(MediaStore.Images.Media.DATA));
                String title=cur.getString(cur.getColumnIndex(MediaStore.Images.Media.TITLE));
                imageList.add(new Image(title,url));
            }
        }
        ImageAdapter adapter=new ImageAdapter(imageList);
        gridView.setAdapter(adapter);
     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==READ_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            loadImgae();
    }
}