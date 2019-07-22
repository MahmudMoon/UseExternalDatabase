package moonc.example.com.useexternaldatabase.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import moonc.example.com.useexternaldatabase.models.ObjectCreated;
import moonc.example.com.useexternaldatabase.R;

public class adapter extends BaseAdapter {
    Context mContext;
    LayoutInflater layoutInflater;
    ArrayList<ObjectCreated> mArrayList;
    TextView tv_id_,tv_name_;
    ImageView imageView;
    public adapter(Context mContext, ArrayList<ObjectCreated> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = layoutInflater.inflate(R.layout.adapter,null);
        tv_id_ = (TextView)view.findViewById(R.id.tv_id);
        tv_name_ = (TextView)view.findViewById(R.id.tv_name);
        imageView = (ImageView)view.findViewById(R.id.iv_image);
        String id  = Integer.toString(mArrayList.get(position).getId());
        tv_id_.setText(id);
        tv_name_.setText(mArrayList.get(position).getName());
        Bitmap bmp1 = BitmapFactory.decodeByteArray(mArrayList.get(position).getImage(), 0, mArrayList.get(position).getImage().length);
        imageView.setImageBitmap(bmp1);
        return view;
    }
}
