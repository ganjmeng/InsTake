package config;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.kingDev.Instagram_video_downloader.ConsentSDK;

import android.app.Activity;
import android.widget.LinearLayout;

public class admob {

    public static String publisherID ="pub-1234567890123456";
    private static String admBanner = "ca-app-pub-3940256099942544/6300978111";
	public static String Interstitial = "ca-app-pub-3940256099942544/1033173712";
    public static String privacy_policy_url ="https://www.google.com/policies/privacy/";

    public static void admobBannerCall(Activity acitivty , LinearLayout linerlayout){
		
        AdView adView = new AdView(acitivty);
        adView.setAdUnitId(admBanner);
        adView.setAdSize(AdSize.BANNER);
        adView.loadAd(ConsentSDK.getAdRequest(acitivty));
        linerlayout.addView(adView);

	}
	
}