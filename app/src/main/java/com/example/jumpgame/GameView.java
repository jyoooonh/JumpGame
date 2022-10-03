package com.example.jumpgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameView extends View {

    private Context context;
    private GameThread mThread;             // 쓰레드

    private int Width, Height, cx, cy;      // 화면의 전체 폭과 중심점
    private int x1, y1, x2, y2;             // Viewport 좌표
    private int sx1, sy1, sx2, sy2;         // Viewport가 1회 이동할 거리

    private Bitmap imgBack1, imgBack2;      // 배경 이미지
    private Theif theif;                    // 캐릭터
    private boolean isJump = false;         // 터치 금지용
    private List<Stair> mStair;             // 계단 발판

    private int w, h;                       // 캐릭터의 폭과 높이
    private long counter = 0;               // 루프의 전체 반복 횟수
    private boolean canRun = true;          // 스레드 실행용 플래그

    // 생성자
    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;

        // 화면 해상도 구하기
        Display display = ((WindowManager)context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        mStair = Collections.synchronizedList(new ArrayList<Stair>());
    }

    // View의 크기 구하기
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        this.w = w;             // 화면 크기
        this.h = h;

        // 스레드 가동
        imgBack1 = BitmapFactory.decodeResource(getResources(), R.drawable.tutorial);
        imgBack1 = Bitmap.createScaledBitmap(imgBack1, w, h, true);

        if(mThread == null){
            mThread = new GameThread();
            mThread.start();
        }
    }

    // View의 종료
    @Override
    protected void onDetachedFromWindow(){
        mThread.canRun = false;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(imgBack1, 0, 0, null);

        canvas.drawBitmap(theif.theif, theif.x - theif.w, theif.y - theif.h, null);
    }

    private void moveTheif(){
        if(isJump){
            isJump = theif.update();
        }
    }

    private synchronized void makeStair(float x, float y){
        // 랜덤으로 계단 배치
    }

    // 터치 이벤트 (드래그 해서 트램펄린 만들어주기)
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return false;
    }

    class GameThread extends Thread {
        public boolean canRun = true;

        @Override
        public void run(){
            while (canRun){
                try{
                    moveTheif();
                    postInvalidate();
                } catch (Exception e){
                    // 게임 로비로 이동
                }
            }
        }
    }
}
