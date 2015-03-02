package org.labcrypto.android.helpers;

import android.view.MotionEvent;
import android.view.View;

public class TouchHelper {

    public interface TouchListener {
        void onTouch(TouchEvent touchEvent);
    }

    public interface RightToLeftTouchListener extends TouchListener {
    }

    public interface LeftToRightTouchListener extends TouchListener {
    }

    public interface TopToBottomTouchListener extends TouchListener {
    }

    public interface BottomToTopTouchListener extends TouchListener {
    }

    public interface UnknownDirectionTouchListener extends TouchListener {
    }

    public static class TouchEvent {

        private float dx;
        private float dy;
        private int duration;

        public TouchEvent(float dx, float dy, int duration) {
            this.dx = dx;
            this.dy = dy;
            this.duration = duration;
        }

        public float getDx() {
            return dx;
        }

        public void setDx(float dx) {
            this.dx = dx;
        }

        public float getDy() {
            return dy;
        }

        public void setDy(float dy) {
            this.dy = dy;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public float getRatio() {
            return dy / dx;
        }
    }

    private View view;
    private RightToLeftTouchListener rightToLeftTouchListener;
    private LeftToRightTouchListener leftToRightTouchListener;
    private TopToBottomTouchListener topToBottomTouchListener;
    private BottomToTopTouchListener bottomToTopTouchListener;
    private UnknownDirectionTouchListener unknownDirectionTouchListener;

    public View getView() {
        return view;
    }

    public TouchHelper setView(View view) {
        this.view = view;
        return this;
    }

    public RightToLeftTouchListener getRightToLeftTouchListener() {
        return rightToLeftTouchListener;
    }

    public TouchHelper setRightToLeftTouchListener(RightToLeftTouchListener rightToLeftTouchListener) {
        this.rightToLeftTouchListener = rightToLeftTouchListener;
        return this;
    }

    public LeftToRightTouchListener getLeftToRightTouchListener() {
        return leftToRightTouchListener;
    }

    public TouchHelper setLeftToRightTouchListener(LeftToRightTouchListener leftToRightTouchListener) {
        this.leftToRightTouchListener = leftToRightTouchListener;
        return this;
    }

    public TopToBottomTouchListener getTopToBottomTouchListener() {
        return topToBottomTouchListener;
    }

    public TouchHelper setTopToBottomTouchListener(TopToBottomTouchListener topToBottomTouchListener) {
        this.topToBottomTouchListener = topToBottomTouchListener;
        return this;
    }

    public BottomToTopTouchListener getBottomToTopTouchListener() {
        return bottomToTopTouchListener;
    }

    public TouchHelper setBottomToTopTouchListener(BottomToTopTouchListener bottomToTopTouchListener) {
        this.bottomToTopTouchListener = bottomToTopTouchListener;
        return this;
    }

    public UnknownDirectionTouchListener getUnknownDirectionTouchListener() {
        return unknownDirectionTouchListener;
    }

    public TouchHelper setUnknownDirectionTouchListener(UnknownDirectionTouchListener unknownDirectionTouchListener) {
        this.unknownDirectionTouchListener = unknownDirectionTouchListener;
        return this;
    }

    public void assign() {
        view.setOnTouchListener(new View.OnTouchListener() {
            // TODO
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO
                return false;
            }
        });
    }
}
