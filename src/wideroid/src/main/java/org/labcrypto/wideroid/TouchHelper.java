/**

 Wideroid Android Helper Framework
 Copyright (C) 2015  Kamran Amini

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along
 with this program; if not, write to the Free Software Foundation, Inc.,
 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

 */

package org.labcrypto.wideroid;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;

/**
 * @author Kamran Amini  <kam.cpp@gmail.com>
 * @author Mohammad Ghorbani  <???>
 */
public class TouchHelper {

    /**
     * Static method for creating a new TouchHelper instance.
     */
    public static TouchHelper createTouchHelper() {
        return new TouchHelper();
    }

    /**
     * Listener for events caused by touches from up to down.
     */
    public interface UpToDownTouchListener {
        void handle(TouchEvent touchEvent);
    }

    /**
     * Listener for events caused by touches from down to up.
     */
    public interface DownToUpTouchListener {
        void handle(TouchEvent touchEvent);
    }

    /**
     * Listener for events caused by touches from left to right.
     */
    public interface LeftToRightTouchListener {
        void handle(TouchEvent touchEvent);
    }

    /**
     * Listener for events caused by touches from right to left.
     */
    public interface RightToLeftTouchListener {
        void handle(TouchEvent touchEvent);
    }

    /**
     * Listener for events caused by touches with unknown directional pattern.
     */
    public interface UnknownDirectionTouchListener {
        void handle(TouchEvent touchEvent);
    }

    /**
     * Event object propagated when a touch happens.
     */
    public class TouchEvent {
        public float dx;
        public float dy;
        public float dt;
        public float r;
    }

    /**
     * 
     */
    protected View view;
    protected UpToDownTouchListener upToDownTouchListener;
    protected DownToUpTouchListener downToUpTouchListener;
    protected LeftToRightTouchListener leftToRightTouchListener;
    protected RightToLeftTouchListener rightToLeftTouchListener;
    protected UnknownDirectionTouchListener unknownDirectionTouchListener;


    public View getView() {
        return view;
    }

    public TouchHelper setView(View view) {
        this.view = view;
        return this;
    }

    public UpToDownTouchListener getUpToDownTouchListener() {
        return upToDownTouchListener;
    }

    public TouchHelper setUpToDownTouchListener(UpToDownTouchListener upToDownTouchListener) {
        this.upToDownTouchListener = upToDownTouchListener;
        return this;
    }

    public DownToUpTouchListener getDownToUpTouchListener() {
        return downToUpTouchListener;
    }

    public TouchHelper setDownToUpTouchListener(DownToUpTouchListener downToUpTouchListener) {
        this.downToUpTouchListener = downToUpTouchListener;
        return this;
    }

    public LeftToRightTouchListener getLeftToRightTouchListener() {
        return leftToRightTouchListener;
    }

    public TouchHelper setLeftToRightTouchListener(LeftToRightTouchListener leftToRightTouchListener) {
        this.leftToRightTouchListener = leftToRightTouchListener;
        return this;
    }

    public RightToLeftTouchListener getRightToLeftTouchListener() {
        return rightToLeftTouchListener;
    }

    public TouchHelper setRightToLeftTouchListener(RightToLeftTouchListener rightToLeftTouchListener) {
        this.rightToLeftTouchListener = rightToLeftTouchListener;
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

            private long downTime;
            private PointF downPoint;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downTime = new Date().getTime();
                        downPoint = new PointF(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (downPoint != null) {
                            TouchEvent touchEvent = new TouchEvent();
                            touchEvent.dx = event.getX() - downPoint.x;
                            touchEvent.dy = event.getY() - downPoint.y;
                            touchEvent.dt = new Date().getTime() - downTime;
                            touchEvent.r = Math.abs(touchEvent.dy / touchEvent.dx);
                            downPoint = null;
                            if (touchEvent.r < 0.4) {
                                if (touchEvent.dx > 0) {
                                    if (leftToRightTouchListener != null) {
                                        leftToRightTouchListener.handle(touchEvent);
                                    }
                                } else {
                                    if (rightToLeftTouchListener != null) {
                                        rightToLeftTouchListener.handle(touchEvent);
                                    }
                                }
                            } else if (touchEvent.r > 3.0) {
                                if (touchEvent.dy > 0) {
                                    if (upToDownTouchListener != null) {
                                        upToDownTouchListener.handle(touchEvent);
                                    }
                                } else {
                                    if (downToUpTouchListener != null) {
                                        downToUpTouchListener.handle(touchEvent);
                                    }
                                }
                            } else {
                                if (unknownDirectionTouchListener != null) {
                                    unknownDirectionTouchListener.handle(touchEvent);
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
}
