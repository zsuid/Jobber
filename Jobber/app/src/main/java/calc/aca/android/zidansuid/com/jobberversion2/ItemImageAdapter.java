package calc.aca.android.zidansuid.com.jobberversion2;

/**
 * Created by zidansuid on 11/5/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ItemImageAdapter extends ArrayAdapter<Item> {
    Context context;
    int layoutResourceId;
    // BcardImage data[] = null;
    ArrayList<Item> data = new ArrayList<Item>();

    public ItemImageAdapter(Context context, int layoutResourceId,
                            ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Item getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.screen_list, parent, false);

            holder = new ImageHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        } else {
            holder = (ImageHolder) row.getTag();
        }

        Item picture = data.get(position);
        holder.txtTitle.setText(picture._name);
        byte[] outImage = picture._image;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);
        return row;

    }

    static class ImageHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}

