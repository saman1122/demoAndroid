package fr.saman.demostyles;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Saman on 14/11/2017.
 */

public class CardViewGroup extends LinearLayout {
    public CardViewGroup(Context context) {
        super(context);
    }

    public CardViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context ctx, @Nullable AttributeSet attrs) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.card,this,true);

        final TypedArray array = ctx.obtainStyledAttributes(attrs,R.styleable.CardViewGroup,0,0);

        final ImageView img = view.findViewById(R.id.image);
        final TextView title = view.findViewById(R.id.title);
        final TextView subTitle = view.findViewById(R.id.subTitle);

        img.setImageDrawable(array.getDrawable(R.styleable.CardViewGroup_image));
        title.setText(array.getText(R.styleable.CardViewGroup_title));
        subTitle.setText(array.getText(R.styleable.CardViewGroup_subTitle));

        array.recycle();
    }
}
