package func;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

public class player {

	public static void mPlayer(String filepath , Activity activity){

		File file = new File(filepath);

		if(!reg.getBack(filepath, "((\\.mp4|\\.webm|\\.ogg|\\.mpK|\\.avi|\\.mkv|\\.flv|\\.mpg|\\.wmv|\\.vob|\\.ogv|\\.mov|\\.qt|\\.rm|\\.rmvb\\.|\\.asf|\\.m4p|\\.m4v|\\.mp2|\\.mpeg|\\.mpe|\\.mpv|\\.m2v|\\.3gp|\\.f4p|\\.f4a|\\.f4b|\\.f4v)$)").isEmpty()){

			try {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "video/*");
				//open movie player in Nexus plus All Google based ROMs
				if(getlistpackage.doesPackageExist("com.google.android.gallery3d", activity)){
					intent.setClassName("com.google.android.gallery3d", "com.android.gallery3d.app.MovieActivity");
					//open movie player in Sony Xperia android devices
				}else if(getlistpackage.doesPackageExist("com.android.gallery3d", activity)){
					intent.setClassName("com.android.gallery3d", "com.android.gallery3d.app.MovieActivity");
					//open movie player in Samsung TouchWiz based ROMs
				}else if(getlistpackage.doesPackageExist("com.cooliris.media", activity)){
					intent.setClassName("com.cooliris.media", "com.cooliris.media.MovieView");
				}

				activity.startActivity(intent);

			}catch(Exception e){

				if(Build.VERSION.SDK_INT>=24){
					try{
						Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
						m.invoke(null);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}

				try{
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(file), "video/*");
					activity.startActivity(intent);
				}catch(Exception j){
					Toast.makeText(activity , "Sorry, Play video not working properly , try again!" , Toast.LENGTH_LONG).show();
				}

			}


		}else if(!reg.getBack(filepath, "((\\.3ga|\\.aac|\\.aif|\\.aifc|\\.aiff|\\.amr|\\.au|\\.aup|\\.caf|\\.flac|\\.gsm|\\.kar|\\.m4a|\\.m4p|\\.m4r|\\.mid|\\.midi|\\.mmf|\\.mp2|\\.mp3|\\.mpga|\\.ogg|\\.oma|\\.opus|\\.qcp|\\.ra|\\.ram|\\.wav|\\.wma|\\.xspf)$)").isEmpty()){

			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), "audio/*");
			activity.startActivity(intent);

		}else if(!reg.getBack(filepath, "((\\.jpg|\\.png|\\.gif|\\.jpeg|\\.bmp)$)").isEmpty()){

			if(Build.VERSION.SDK_INT>=24){
				try{
					Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
					m.invoke(null);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			try{
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse("file:///"+Uri.fromFile(file).getPath()) , "image/*");
				activity.startActivity(intent);
//				Toast.makeText(activity , ""+Uri.parse("file:///" + Environment.getExternalStorageDirectory() + "/WhatsApp/Media/.Statuses/x.jpg \n "
//						+Uri.fromFile(file).getPath()) , Toast.LENGTH_LONG).show();
			}catch (Exception e){
				Toast.makeText(activity , "Sorry. We can't Display Images. try again" , Toast.LENGTH_LONG).show();
			}

		}


	}

	public static void mPlayerStream(String filepath , Activity activity){

		if(!reg.getBack(filepath,
				"((\\.mp4(\\?[^?]+)?$)" +
						"|(\\.webm(\\?[^?]+)?$)" +
						"|(\\.ogg(\\?[^?]+)?$)" +
						"|(\\.mpK(\\?[^?]+)?$)" +
						"|(\\.avi(\\?[^?]+)?$)" +
						"|(\\.mkv(\\?[^?]+)?$)" +
						"|(\\.flv(\\?[^?]+)?$)" +
						"|(\\.mpg(\\?[^?]+)?$)" +
						"|(\\.wmv(\\?[^?]+)?$)" +
						"|(\\.vob(\\?[^?]+)?$)" +
						"|(\\.ogv(\\?[^?]+)?$)" +
						"|(\\.mov(\\?[^?]+)?$)" +
						"|(\\.qt(\\?[^?]+)?$)" +
						"|(\\.rm(\\?[^?]+)?$)" +
						"|(\\.rmvb(\\?[^?]+)?$)" +
						"|(\\.asf(\\?[^?]+)?$)" +
						"|(\\.m4p(\\?[^?]+)?$)" +
						"|(\\.m4v(\\?[^?]+)?$)" +
						"|(\\.mp2(\\?[^?]+)?$)" +
						"|(\\.mpeg(\\?[^?]+)?$)" +
						"|(\\.mpe(\\?[^?]+)?$)" +
						"|(\\.mpv(\\?[^?]+)?$)" +
						"|(\\.m2v(\\?[^?]+)?$)" +
						"|(\\.3gp(\\?[^?]+)?$)" +
						"|(\\.f4p(\\?[^?]+)?$)" +
						"|(\\.f4a(\\?[^?]+)?$)" +
						"|(\\.f4b(\\?[^?]+)?$)" +
						"|(\\.f4v(\\?[^?]+)?$))").isEmpty()) {

			try {

				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);

				intent.setDataAndType(Uri.parse(filepath), "video/*");
				Log.e("videomustwork", filepath);
				//open movie player in Nexus plus All Google based ROMs
				if (getlistpackage.doesPackageExist("com.google.android.gallery3d", activity)) {
					intent.setClassName("com.google.android.gallery3d", "com.android.gallery3d.app.MovieActivity");
					//open movie player in Sony Xperia android devices
				} else if (getlistpackage.doesPackageExist("com.android.gallery3d", activity)) {
					intent.setClassName("com.android.gallery3d", "com.android.gallery3d.app.MovieActivity");
					//open movie player in Samsung TouchWiz based ROMs
				} else if (getlistpackage.doesPackageExist("com.cooliris.media", activity)) {
					intent.setClassName("com.cooliris.media", "com.cooliris.media.MovieView");
				}

				activity.startActivity(intent);

			} catch (Exception e) {

				try {

					Intent intentj = new Intent();
					intentj.setAction(Intent.ACTION_VIEW);
					intentj.setDataAndType(Uri.parse(filepath), "video/*");
					activity.startActivity(intentj);

				} catch (Exception e1) {

					Toast.makeText(activity, "Sorry, Stream not working properly , but you can download video", Toast.LENGTH_LONG).show();
				}

			}

		}else if (!reg.getBack(filepath,
				"((\\.3ga(\\?[^?]+)?$)" +
						"|(\\.aac(\\?[^?]+)?$)" +
						"|(\\.aif(\\?[^?]+)?$)" +
						"|(\\.aifc(\\?[^?]+)?$)" +
						"|(\\.aiff(\\?[^?]+)?$)" +
						"|(\\.amr(\\?[^?]+)?$)" +
						"|(\\.au(\\?[^?]+)?$)" +
						"|(\\.aup(\\?[^?]+)?$)" +
						"|(\\.caf(\\?[^?]+)?$)" +
						"|(\\.flac(\\?[^?]+)?$)" +
						"|(\\.gsm(\\?[^?]+)?$)" +
						"|(\\.kar(\\?[^?]+)?$)" +
						"|(\\.m4a(\\?[^?]+)?$)" +
						"|(\\.m4p(\\?[^?]+)?$)" +
						"|(\\.m4r(\\?[^?]+)?$)" +
						"|(\\.mid(\\?[^?]+)?$)" +
						"|(\\.midi(\\?[^?]+)?$)" +
						"|(\\.mmf(\\?[^?]+)?$)" +
						"|(\\.mp2(\\?[^?]+)?$)" +
						"|(\\.mp3(\\?[^?]+)?$)" +
						"|(\\.mpga(\\?[^?]+)?$)" +
						"|(\\.ogg(\\?[^?]+)?$)" +
						"|(\\.oma(\\?[^?]+)?$)" +
						"|(\\.opus(\\?[^?]+)?$)" +
						"|(\\.qcp(\\?[^?]+)?$)" +
						"|(\\.ra(\\?[^?]+)?$)" +
						"|(\\.ram(\\?[^?]+)?$)" +
						"|(\\.wav(\\?[^?]+)?$)" +
						"|(\\.wma(\\?[^?]+)?$)" +
						"|(\\.xspf(\\?[^?]+)?$))").isEmpty()){

			try{

				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.parse(filepath), "audio/*");
				activity.startActivity(intent);

			}catch(Exception j){

			}

		}
	}

}
