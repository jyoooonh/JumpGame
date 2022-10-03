package com.example.jumpgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;

public class Theif {
    private int scrW, scrH;                 // 화면 크기, 바닥의 높이
    private float ground;
    private float deadline;                 // 떨어지면 탈락하는 높이
    private float stair;                    // 계단의 높이

    private int speed = 300;                // 이동 및 회전 속도
    private int rogAng = 120;

    private float gravity = 1500f;          // 중력 및 반사 계수
    private float bounce = 0.8f;

    private PointF dir = new PointF();      // 캐릭터가 이동하는 방향 벡터

    public float x, y;                      // 현재 위치, 반지름
    public int w, h;                        // 로켓의 크기

    public Bitmap theif;                    // 공의 비트맵, 소멸?
    public boolean isDead;

    // 생성자
    public Theif(Context context, int width, int height) {

        // 화면 크기
        scrW = width;
        scrH = height;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.isScaled = false;

        // 캐릭터의 이미지와 크기
        theif = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball, options);
        w = theif.getWidth() / 100;
        h = theif.getHeight() / 100;


        // 바닥의 높이
        ground = scrH;

        // 캐릭터의 이동 방향
        dir.x = speed;
        dir.y = 0;
    }

    private void reset(){
        // 현재 캐릭터의 위치로 초기화 해줘야하나?
        x = w;
        y = scrH -h;

        ang = 0;
    }

    // 캐릭터 점프
    public void jump(float px, float py){
        double rad = -Math.atan2(py - y - h, px - x);

        // 점프 방향과 속도
        dir.x = (float) Math.cos(rad) * speed;
        dir.y = -(float) Math.sin(rad) * speed;
    }

    // 바닥을 새로 설정
    public boolean update() {
        boolean canRun = true;

        dir.y += gravity * Time.deltaTime;

        x += dir.x * Time.deltaTime;
        y += dir.y * Time.deltaTime;

        // 드래그 각도에 따라서 다르게 바꿔줘야함
        double rad = -Math.atan2(dir.y, dir.x);
        ang = 90 - (float)Math.toDegrees(rad);

        // 화면을 벗어나면 그대로 떨어짐
        if (x > scrW + h * 2 || y > scrH + h * 2){
            // 라인에 닿을때까지 떨어짐
        }
        // 바닥에 떨어지면 소거
    }
}