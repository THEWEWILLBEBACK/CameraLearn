package com.adnonstop.cameranew.gl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.View;

/**
 * Created by Xiejq on 2017/11/24.
 */

public abstract class Shape implements GLSurfaceView.Renderer {

    protected View mView;


    public Shape(View view) {
        mView = view;
    }



    public int loadShader(int type, String shaderCode){
        //根据type来创建顶点着色器或者片源着色器
        int shader = GLES20.glCreateShader(type);
        //将资源加到着色器中，并编译
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

}
