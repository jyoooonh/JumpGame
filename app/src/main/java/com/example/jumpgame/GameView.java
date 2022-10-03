package com.example.jumpgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class GameView extends View {

    private Context context;
    private GameThread mThread;             // 쓰레드

    private int Width, Height, cx, cy;      // 화면의 전체 폭과 중심점
    private int x1, y1, x2, y2;             // Viewport 좌표
    private int sx1, sy1, sx2, sy2;         // Viewport가 1회 이동할 거리

    private Bitmap imgBack1, imgBack2;      // 배경 이미지
    private Bitmap Theif;                   // 캐릭터

    private int w, h;                       // 캐릭터의 폭과 높이
    private long counter = 0;               // 루프의 전체 반복 횟수
    private boolean canRun = true;          // 스레드 실행용 플래그

    // 생성자
    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);

        this.context = context;
        CommonResources.set(context);           // 공용 리소스 작성

        // 화면 해상도 구하기
        Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();
    }

    // View의 크기 구하기
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        this.w = w;             // 화면 크기
        this.h = h;

        // 스레드 가동
        imgBack = BitmapFactory.decodeResource(getResources(), R.drawalbe.tutorial);
        imgBack = Bitmap.createScaledBitmap(imgBack, w, h, true);

        if(mThread == null){
            mThread = new GameThread();
            mThread.start();
        }
    }
}
