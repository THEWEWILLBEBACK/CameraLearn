package com.adnonstop.cameranew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.adnonstop.cameranew.contants.KeyConstant;
import com.adnonstop.cameranew.gl.FillterGLview;
import com.adnonstop.cameranew.gl.Shape;
import com.adnonstop.cameranew.gl.Triangle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DrawViewActivity.class.getSimpleName();
    @BindView(R.id.draw_activity_toolbar)
    Toolbar mDrawActivityToolbar;
    @BindView(R.id.glsurface_draw)
    FillterGLview mGLSurfaceDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view);
        ButterKnife.bind(this);
        setSupportActionBar(mDrawActivityToolbar);
        initListener();
    }

    private void initListener() {
        mDrawActivityToolbar.setNavigationOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.draw_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mGLSurfaceDraw.onPause();//手动调用SurfaceView的opause方法，强制调用surfaceView重新
        switch (item.getItemId()) {
            case R.id.menu_one:
                Toast.makeText(this, "三角形", Toast.LENGTH_SHORT).show();
                drawChooseShape(Triangle.class);
                break;
            case R.id.menu_two:

                break;
            case R.id.menu_three:

                break;
            case R.id.menu_four:

                break;
            case R.id.menu_five:

                break;
            case R.id.menu_six:

                break;
            case R.id.menu_seven:

                break;
            case R.id.menu_eight:

                break;
            case R.id.menu_nine:

                break;
            case R.id.menu_ten:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void drawChooseShape(Class<? extends Shape> shape) {
        mGLSurfaceDraw.setShape(shape);
        mGLSurfaceDraw.onResume();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceDraw.onResume();
        Log.i(KeyConstant.TAG, "DrawViewActivity \t onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceDraw.onPause();
        Log.i(KeyConstant.TAG, "DrawViewActivity \t onPause: ");
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ImageButton) {
            finish();
        }
    }
}
