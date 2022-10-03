package com.example.jumpgame;

import android.graphics.Bitmap;
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

    private PointF dir = new PointF();      // 공이 이동하는 방향 벡터

    public float x, y;                      // 현재 위치, 반지름
    public int r;

    public float ang;                       // 현재 각도, 공의 비트맵, 소멸?
    public Bitmap ball;
    public boolean isDead;

    // 생성자
    public Theif(int width, int height, float px, float py) {

        // 화면 크기
        scrW = width;
        scrH = height;
        x = px;
        y = py;

        // 캐릭터의 이미지와 크기
        theif = CommonResources.ball;
        r = CommonResources.r;

        // 바닥의 높이
        ground = scrH;

        // 공의 이동 방향
        dir.x = speed;
        dir.y = 0;
    }

    // 바닥을 새로 설정
    public void update() {

        // 바닥에 떨어지면 소거
    }
}