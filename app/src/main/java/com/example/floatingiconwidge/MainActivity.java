package com.example.floatingiconwidge;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {


            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            initializeView();
        }
    }
    private void initializeView() {

        ImageView imageView1,imageView2,imageView3;

        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);
        imageView3=findViewById(R.id.image3);

        Glide.with(this).load("http://4.bp.blogspot.com/-uhjF2kC3tFc/U_r3myvwzHI/AAAAAAAACiw/tPQ2XOXFYKY/s1600/Circles-3.gif").placeholder(R.drawable.ic_android_circle).into(imageView1);
        Glide.with(this).load("https://media1.giphy.com/media/9rnIwMimzlm39aHhud/giphy.gif?cid=790b76115ced0fe4796b66342eba9f09&rid=giphy.gif").placeholder(R.drawable.ic_android_circle).into(imageView2);
        Glide.with(this).load("https://media.giphy.com/media/3o7WINmFq3zmpWvhSw/giphy.gif").placeholder(R.drawable.ic_android_circle).into(imageView3);
        findViewById(R.id.image1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, FloatingService.class);
                intent.putExtra("url","http://4.bp.blogspot.com/-uhjF2kC3tFc/U_r3myvwzHI/AAAAAAAACiw/tPQ2XOXFYKY/s1600/Circles-3.gif");
                startService(intent);
//                finish();
            }
        });
        findViewById(R.id.image2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, FloatingService.class);
                intent.putExtra("url","https://media1.giphy.com/media/9rnIwMimzlm39aHhud/giphy.gif?cid=790b76115ced0fe4796b66342eba9f09&rid=giphy.gif");
                startService(intent);
//                startService(new Intent(MainActivity.this, FloatingService.class));
//                finish();
            }
        });
        findViewById(R.id.image3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, FloatingService.class);
                intent.putExtra("url","https://media.giphy.com/media/3o7WINmFq3zmpWvhSw/giphy.gif");
                startService(intent);
//                startService(new Intent(MainActivity.this, FloatingService.class));
//                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                initializeView();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
