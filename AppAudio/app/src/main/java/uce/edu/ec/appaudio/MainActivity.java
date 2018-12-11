package uce.edu.ec.appaudio;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity  {

    private MediaRecorder recorder;
    private String archivoSalida=null;
    private Button play, rec;
    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        play = (Button)findViewById(R.id.btn_play);
        rec = (Button)findViewById(R.id.btn_rec);





    }
    public void recorderM(View view){
        if(recorder==null){
            archivoSalida=Environment.getExternalStorageDirectory().getAbsolutePath()+"/grabacion.mp3";
            recorder= new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //formato de salida
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //codifica el archivo
            recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            //carga en el archivosalida
            recorder.setOutputFile(archivoSalida);
            try{
                recorder.prepare();
                recorder.start();
            }catch (IOException e){
                System.out.println(e);
            }
            //
            rec.setBackgroundResource(R.drawable.rec);
            Toast.makeText(getApplicationContext(),"Grabando...",Toast.LENGTH_SHORT).show();
        }else if(recorder!=null){
            recorder.stop();
            recorder.release();
            recorder=null;
            rec.setBackgroundResource(R.drawable.stop_recor);
            Toast.makeText(getApplicationContext(),"Grabacion finalizada",Toast.LENGTH_SHORT).show();

        }

    }
    public void reproducir(View view){
        MediaPlayer mediaPlayer=new MediaPlayer();
        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch (IOException e){

        }
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(),"Reproduciendo audio",Toast.LENGTH_SHORT).show();
    }

}
