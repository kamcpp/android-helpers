/**

 Wideroid Android Helper Framework
 Copyright 2015 Kamran Amini

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */

package org.labcrypto.wideroid;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.Date;

/**
 * TODO
 *
 * @author Kamran Amini  <kam.cpp@gmail.com>
 * @author Mohammad Ghorbani  <mohammad.68.gh@gmail.com>
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
     * Target view which should be monitored for touch events.
     */
    protected View view;
    /**
     * Listener reference for up to down touch events.
     */
    protected UpToDownTouchListener upToDownTouchListener;
    /**
     * Listener reference for down to up touch events.
     */
    protected DownToUpTouchListener downToUpTouchListener;
    /**
     * Listener reference for left to right touch events.
     */
    protected LeftToRightTouchListener leftToRightTouchListener;
    /**
     * Listener reference for right to left touch events.
     */
    protected RightToLeftTouchListener rightToLeftTouchListener;
    /**
     * Listener reference for unknown touch events.
     */
    protected UnknownDirectionTouchListener unknownDirectionTouchListener;

    /**
     * Returns target view.
     */
    public View getView() {
        return view;
    }

    /**
     * Sets target view.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setView(View view) {
        this.view = view;
        return this;
    }

    /**
     * Returns up to down listener.
     */
    public UpToDownTouchListener getUpToDownTouchListener() {
        return upToDownTouchListener;
    }

    /**
     * Sets up to down listener.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setUpToDownTouchListener(UpToDownTouchListener upToDownTouchListener) {
        this.upToDownTouchListener = upToDownTouchListener;
        return this;
    }

    /**
     * Returns down to up listener.
     */
    public DownToUpTouchListener getDownToUpTouchListener() {
        return downToUpTouchListener;
    }

    /**
     * Sets down to up listener.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setDownToUpTouchListener(DownToUpTouchListener downToUpTouchListener) {
        this.downToUpTouchListener = downToUpTouchListener;
        return this;
    }

    /**
     * Returns left to right listener.
     */
    public LeftToRightTouchListener getLeftToRightTouchListener() {
        return leftToRightTouchListener;
    }

    /**
     * Sets left to right listener.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setLeftToRightTouchListener(LeftToRightTouchListener leftToRightTouchListener) {
        this.leftToRightTouchListener = leftToRightTouchListener;
        return this;
    }

    /**
     * Returns right to left listener.
     */
    public RightToLeftTouchListener getRightToLeftTouchListener() {
        return rightToLeftTouchListener;
    }

    /**
     * Sets right to left listener.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setRightToLeftTouchListener(RightToLeftTouchListener rightToLeftTouchListener) {
        this.rightToLeftTouchListener = rightToLeftTouchListener;
        return this;
    }

    /**
     * Returns unknown touch listener.
     */
    public UnknownDirectionTouchListener getUnknownDirectionTouchListener() {
        return unknownDirectionTouchListener;
    }

    /**
     * Sets unknown touch listener.
     *
     * @return Reference to this touch helper object.
     */
    public TouchHelper setUnknownDirectionTouchListener(UnknownDirectionTouchListener unknownDirectionTouchListener) {
        this.unknownDirectionTouchListener = unknownDirectionTouchListener;
        return this;
    }

    /**
     * Attaches to target view for monitoring touch events and assigns the listeners.
     * This method should be called at last in the method call chains.
     */
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
