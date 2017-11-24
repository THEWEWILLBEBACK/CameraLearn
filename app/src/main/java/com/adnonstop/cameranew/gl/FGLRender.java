package com.adnonstop.cameranew.gl;

import android.opengl.GLES20;
import android.util.Log;
import android.view.View;

import com.adnonstop.cameranew.contants.KeyConstant;

import java.lang.reflect.Constructor;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Xiejq on 2017/11/24.
 */

public class FGLRender extends Shape {
    private Shape mShape;
    private Class<? extends Shape> cls;


    public FGLRender(View view) {
        super(view);
    }

    public void setShape(Class<? extends Shape> cls) {
        this.cls = cls;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i(KeyConstant.TAG, "FGLRender \t onSurfaceCreated: ");
        GLES20.glClearColor(0.5f, 0.5f, 05, 1.0f);
        try {
            Constructor<? extends Shape> constructor = cls.getDeclaredConstructor(View.class);
            constructor.setAccessible(true);
            mShape = constructor.newInstance(mView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(KeyConstant.TAG, "FGLRender \t onSurfaceChanged: ");
        GLES20.glViewport(0, 0, width, height);
        if (mShape != null) {
            mShape.onSurfaceChanged(gl, width, height);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        if (mShape != null) {
            mShape.onDrawFrame(gl);
        }
    }
}
