package com.toni.snakegame.framework.impl;

import android.graphics.Bitmap;

import com.toni.snakegame.framework.Graphics;
import com.toni.snakegame.framework.Pixmap;

public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
