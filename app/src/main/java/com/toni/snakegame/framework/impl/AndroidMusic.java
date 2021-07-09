package com.toni.snakegame.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.toni.snakegame.framework.Music;

import java.io.IOException;

public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;
    boolean isPrepared = false;

    public AndroidMusic(AssetFileDescriptor assetDescriptor){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        }catch (Exception e){
            throw new RuntimeException("No puede cargar la música");
        }
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void play() {
        if(mediaPlayer.isPlaying()){
            return;
        }
        try{
            synchronized (this){
                if(!isPrepared){
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared = false;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}
