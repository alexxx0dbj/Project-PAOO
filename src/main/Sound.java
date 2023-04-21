package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip; //open audio file
    URL soundURL[] = new URL[30];
    public Sound(){
        soundURL[0]=getClass().getResource("/sound/GameSoundFull.wav");//no usage yet
        soundURL[1]=getClass().getResource("/sound/chestOpen.wav");
        soundURL[2]=getClass().getResource("/sound/doorOpen.wav");
        soundURL[3]=getClass().getResource("/sound/pickup.wav");
        soundURL[4]=getClass().getResource("/sound/PowerUp.wav");
        soundURL[5]=getClass().getResource("/sound/fundal.wav");
        soundURL[6]=getClass().getResource("/sound/attack.wav");
        soundURL[7]=getClass().getResource("/sound/damage_attack_to_monster.wav");
        soundURL[8]=getClass().getResource("/sound/i_get_touch.wav");

    }

    public void setFile(int i){
        try{
            //open audio format file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
