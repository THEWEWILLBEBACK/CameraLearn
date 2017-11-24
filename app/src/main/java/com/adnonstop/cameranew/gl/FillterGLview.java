package com.adnonstop.cameranew.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Xiejq on 2017/11/24.
 */

public class FillterGLview extends GLSurfaceView {


    private static final String TAG = FillterGLview.class.getSimpleName();
    private FGLRender mRenderer;

    public FillterGLview(Context context) {
        this(context, null);
    }

    public FillterGLview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //Inform the default EGLContextFactory and default EGLConfigChooser which EGLContext client version to pick.
        setEGLContextClientVersion(2);
        mRenderer = new FGLRender(this);
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void setShape(Class<? extends Shape> clazz){
        try {
            mRenderer.setShape(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "FillterGLview \tonPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "FillterGLview \tonResume: ");
    }
}
