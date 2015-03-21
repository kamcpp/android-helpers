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
 * @uathor Mohammad Ghorbani  <???>
 */
public class TouchHelper {

    public interface TouchHandler {
        void handle(TouchEvent touchEvent);
    }

    public static class TouchEvent {
        public float dx;
        public float dy;
        public float dt;
        public float r;
    }

    public static class Builder {

        public View view;
        public TouchHandler upHandler;
        public TouchHandler rightHandler;
        public TouchHandler downHandler;
        public TouchHandler leftHandler;
        public TouchHandler unknownHandler;

        public Builder(View view) {
            this.view = view;
        }

        public Builder setUpHandler(final TouchHandler upHandler) {
            this.upHandler = upHandler;
            return this;
        }

        public Builder setRightHandler(final TouchHandler rightHandler) {
            this.rightHandler = rightHandler;
            return this;
        }

        public Builder setDownHandler(final TouchHandler downHandler) {
            this.downHandler = downHandler;
            return this;
        }

        public Builder setLeftHandler(final TouchHandler leftHandler) {
            this.leftHandler = leftHandler;
            return this;
        }

        public Builder setUnKnownHandler(final TouchHandler unknownHandler) {
            this.unknownHandler = unknownHandler;
            return this;
        }

        public TouchHelper assign() {
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
                                        if (rightHandler != null) {
                                            rightHandler.handle(touchEvent);
                                        }
                                    } else {
                                        if (leftHandler != null) {
                                            leftHandler.handle(touchEvent);
                                        }
                                    }
                                } else if (touchEvent.r > 3.0) {
                                    if (touchEvent.dy > 0) {
                                        if (downHandler != null) {
                                            downHandler.handle(touchEvent);
                                        }
                                    } else {
                                        if (upHandler != null) {
                                            upHandler.handle(touchEvent);
                                        }
                                    }
                                } else {
                                    if (unknownHandler != null) {
                                        unknownHandler.handle(touchEvent);
                                    }
                                }
                            }
                    }
                    return true;
                }
            });
            return null;
        }
    }
}
