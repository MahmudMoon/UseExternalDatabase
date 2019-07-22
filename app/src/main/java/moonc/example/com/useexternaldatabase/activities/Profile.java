package moonc.example.com.useexternaldatabase.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import moonc.example.com.useexternaldatabase.R;

public class Profile extends AppCompatActivity {

    ImageView profile;
    TextView name_,id_;
    int id;
    String name;
    byte[] image;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init_views();
        init_variables();
        init_functions();

        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0,image.length);
        id_.setText(Integer.toString(id));
        name_.setText(name);
        profile.setImageBitmap(bmp);
    }

    private void init_functions() {

    }

    private void init_variables() {
        intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        image =  intent.getByteArrayExtra("image");
    }


    private void init_views() {
        profile = (ImageView)findViewById(R.id.imageView);
        id_ = (TextView)findViewById(R.id.textView4);
        name_ = (TextView)findViewById(R.id.textView3);
    }
}
