package umn.ac.id.uas_mobile_musicplayer;

public class SongInfo {
    public String songName,songUrl;

    public void songInfo(String songName, String songUrl){
        this.songName = songName;
        this.songUrl = songUrl;
    }

    public String getSongName(){
        return songName;
    }

    public void getSongUrl(){

    }
}
